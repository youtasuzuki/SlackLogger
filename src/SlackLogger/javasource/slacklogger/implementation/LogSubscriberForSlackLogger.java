package slacklogger.implementation;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.logging.LogLevel;
import com.mendix.logging.LogMessage;
import com.mendix.logging.LogSubscriber;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;

import slacklogger.proxies.LogLevels;
import slacklogger.proxies.SlackLoggerConfig;
import slacklogger.proxies.SlackLoggerStatus;

/**
 */
public class LogSubscriberForSlackLogger extends LogSubscriber {
	private static Map<String, LogSubscriberForSlackLogger> subscribers = new HashMap<String, LogSubscriberForSlackLogger>();
	private static final ILogNode logger = Core.getLogger("SlackLogger");

	private Timer myTimer;
	private SlackLoggerConfig myConfig;
	private LinkedList<LogMessage> cueuedMessage = new LinkedList<LogMessage>();
	private boolean isStarted = false;
	private boolean isRegistered = false;
	private Date lastProcessTime;
	private String lastProcessResult = "Never";
	private Date lastErrorTime;
	private String lastErrorInfo = "Nothing";
	private Long overflowedCount = 0L;
	private long lastStatusSyncTime = 0L;


	/**
	 */
	public static synchronized LogSubscriberForSlackLogger getInstance(String configName) throws Exception {
		LogSubscriberForSlackLogger instance = subscribers.get(configName);
		if (instance == null) {
			instance = new LogSubscriberForSlackLogger(configName, LogLevel.ERROR);
			subscribers.put(configName, instance);
		}
		return instance;
	}

	/**
	 */
	public static synchronized void shutdownAll() throws Exception {
		for (Map.Entry<String, LogSubscriberForSlackLogger> entry : subscribers.entrySet()) {
			LogSubscriberForSlackLogger sl = entry.getValue();
			sl.stop();
			Iterator<ILogNode> it = sl.getSubscriptions().keySet().iterator();
			while (it.hasNext()) {
				ILogNode node = it.next();
				node.subscribe(sl, LogLevel.NONE);
			}
		}
		return;
	}

	/**
	 */
	private static LogLevel convertToMxLogLevel(LogLevels level) {
		LogLevel mxLogLevel = LogLevel.NONE;
		switch (level) {
		case TRACE:
			mxLogLevel = LogLevel.TRACE;
			break;
		case DEBUG:
			mxLogLevel = LogLevel.DEBUG;
			break;
		case INFO:
			mxLogLevel = LogLevel.INFO;
			break;
		case WARNING:
			mxLogLevel = LogLevel.WARNING;
			break;
		case ERROR:
			mxLogLevel = LogLevel.ERROR;
			break;
		case CRITICAL:
			mxLogLevel = LogLevel.CRITICAL;
			break;
		}
		return mxLogLevel;
	}

	/*
	 */
	private static String getServerIP() throws Exception {
		InetAddress ipAdd;
		ipAdd = InetAddress.getLocalHost();
		return ipAdd.getHostAddress();
	}

	/**
	 */
	LogSubscriberForSlackLogger(String configName, final LogLevel logLevel) {
		super(LogSubscriberForSlackLogger.class.getName() + "_" +  configName, logLevel);
	}

	/**
	 */
	public synchronized void configure(SlackLoggerConfig config) {
		myConfig = config;
		if (!isRegistered) {
			// This have to be registered before node.subscribe().
			Core.registerLogSubscriber(this);
			isRegistered = true;
		}
		LogLevel logLeval = convertToMxLogLevel(config.getLogLevel());
		setAutoSubscribeLevel(logLeval);
		Iterator<ILogNode> it = getSubscriptions().keySet().iterator();
		while (it.hasNext()) {
			ILogNode node = it.next();
			node.subscribe(this, logLeval);
		}
	}

	/**
	 */
	public boolean isStarted() {
		return isStarted;
	}

	/**
	 */
	public synchronized void start() {
		cueuedMessage.clear();
		isStarted = true;
		myTimer = new Timer(true);
		myTimer.scheduleAtFixedRate(new MyTask(), myConfig.getProcessInterval(), myConfig.getProcessInterval());
	}

	/**
	 */
	public synchronized void stop() {
		isStarted = false;
		myTimer.cancel();
		myTimer = null;
	}

	/**
	 */
	public boolean setSubscribe(String logNodeName, LogLevels level) {
		ILogNode node = Core.getLogger(logNodeName);
		if (node == null) {
			return false;
		} else {
			subscribe(node, convertToMxLogLevel(level));
			return true;
		}
	}

	/*
	 */
	@Override
	public void processMessage(final LogMessage logMessage) {
		if (isStarted && myConfig.getIsEnable() && !logMessage.node.name().equals("SlackLogger")) {
			synchronized (this) {
				if (myConfig.getQueueDepth() > cueuedMessage.size()) {
					cueuedMessage.add(logMessage);
				} else {
					overflowedCount += 1;
				}
			}
		}
	}

	/*
	 */
	private class MyTask extends TimerTask {
		public void run() {
			if (isStarted) {
				LogMessage logMessage;
				synchronized (this) {
					logMessage = cueuedMessage.peek();
				}
				if (logMessage != null && myConfig.getIsEnable()) {
					try {
						lastProcessTime = Calendar.getInstance().getTime();
						StringBuilder sb = new StringBuilder();
						ZoneId zoneId = ZoneId.of(myConfig.getZoneId());
						sb.append((new Timestamp(logMessage.timestamp).toInstant().atZone(zoneId)).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)).append(' ');
						sb.append('[').append(myConfig.getConfigName()).append(']');
						sb.append('[').append(getServerIP()).append(']');
						sb.append('[').append(logMessage.node.name()).append(']');
						sb.append('[').append(logMessage.level.name()).append(']');
						sb.append(' ').append(logMessage.message.toString());

						String error = new SlackMessageSender().sendMessage(myConfig.getToken(), myConfig.getChannel(), sb.toString());
						if (error == null) {
							lastProcessResult = "Success";
						} else {
							lastErrorTime = Calendar.getInstance().getTime();
							lastProcessResult = "Failure";
							lastErrorInfo = error;
						}
					} catch (Exception e) {
						lastProcessResult = "Failure";
						lastErrorTime = Calendar.getInstance().getTime();
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						e.printStackTrace(pw);
						lastErrorInfo = sw.toString();
					} finally {
						synchronized (this) {
							cueuedMessage.poll();
						}
					}
				}
				Long currentTime = System.currentTimeMillis();
				if (currentTime > (lastStatusSyncTime + 30000)) {
					updateSlackLoggerStatus();
					lastStatusSyncTime = currentTime;
				}
			}
		}
	}

	/*
	 */
	private void updateSlackLoggerStatus() {
		// update SlackLoggerStatus entity
		// SlackLoggerStatusをリトリーブして、無ければ新規生成
		// 内容をprivate変数で最新化してupdateする
		IContext context = Core.createSystemContext();
		context.startTransaction();
		try {
			SlackLoggerStatus status;
			List<IMendixObject> resultList = Core.retrieveXPathQuery(context,
					"//SlackLogger.SlackLoggerStatus" + "[ConfigName = '" + myConfig.getConfigName() + "'][IpAddress = '" + getServerIP() + "']");
			if (resultList.size() == 0) {
				status = new SlackLoggerStatus(context);
				status.setIpAddress(getServerIP());
				status.setConfigName(myConfig.getConfigName());
			} else {
				status = SlackLoggerStatus.initialize(context, resultList.get(0));
				if (status.getReconfigRequest()) {
					List<IMendixObject> configList = Core.retrieveXPathQuery(context,
							"//SlackLogger.SlackLoggerConfig" + "[ConfigName = '" + myConfig.getConfigName() + "']");
					if (configList.size() == 1) {
						stop();
						configure(SlackLoggerConfig.initialize(context, configList.get(0)));
						start();
					}
					status.setReconfigRequest(false);
				}

			}
			status.setConfigVersionNum(myConfig.getConfigVersionNum());
			status.setLastProcessTime(lastProcessTime);
			status.setLastProcessResult(lastProcessResult);
			status.setLastErrorTime(lastErrorTime);
			status.setLastErrorInfo(lastErrorInfo);
			status.setOverflowedCount(overflowedCount);
			status.commit();
		} catch (Exception e) {
			logger.error("Status update failed:", e);
		} finally {
			context.endTransaction();
		}
	}

}
