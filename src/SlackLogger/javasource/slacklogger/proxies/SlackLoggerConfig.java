// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package slacklogger.proxies;

public class SlackLoggerConfig
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject slackLoggerConfigMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "SlackLogger.SlackLoggerConfig";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		ConfigName("ConfigName"),
		ConfigVersionNum("ConfigVersionNum"),
		Description("Description"),
		IsEnable("IsEnable"),
		Token("Token"),
		Channel("Channel"),
		LogLevel("LogLevel"),
		PrintStackTrace("PrintStackTrace"),
		ZoneId("ZoneId"),
		QueueDepth("QueueDepth"),
		ProcessInterval("ProcessInterval"),
		ExcludeRegexp("ExcludeRegexp");

		private final java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@java.lang.Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public SlackLoggerConfig(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, entityName));
	}

	protected SlackLoggerConfig(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject slackLoggerConfigMendixObject)
	{
		if (slackLoggerConfigMendixObject == null) {
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		}
		if (!com.mendix.core.Core.isSubClassOf(entityName, slackLoggerConfigMendixObject.getType())) {
			throw new java.lang.IllegalArgumentException(String.format("The given object is not a %s", entityName));
		}	

		this.slackLoggerConfigMendixObject = slackLoggerConfigMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'SlackLoggerConfig.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static slacklogger.proxies.SlackLoggerConfig initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return slacklogger.proxies.SlackLoggerConfig.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 * @param context The context to be used
	 * @param mendixObject The Mendix object for the new instance
	 * @return a new instance of this proxy class
	 */
	public static slacklogger.proxies.SlackLoggerConfig initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new slacklogger.proxies.SlackLoggerConfig(context, mendixObject);
	}

	public static slacklogger.proxies.SlackLoggerConfig load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return slacklogger.proxies.SlackLoggerConfig.initialize(context, mendixObject);
	}

	public static java.util.List<slacklogger.proxies.SlackLoggerConfig> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		return com.mendix.core.Core.createXPathQuery(String.format("//%1$s%2$s", entityName, xpathConstraint))
			.execute(context)
			.stream()
			.map(obj -> slacklogger.proxies.SlackLoggerConfig.initialize(context, obj))
			.collect(java.util.stream.Collectors.toList());
	}

	/**
	 * Commit the changes made on this proxy object.
	 * @throws com.mendix.core.CoreException
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 * @throws com.mendix.core.CoreException
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of ConfigName
	 */
	public final java.lang.String getConfigName()
	{
		return getConfigName(getContext());
	}

	/**
	 * @param context
	 * @return value of ConfigName
	 */
	public final java.lang.String getConfigName(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.ConfigName.toString());
	}

	/**
	 * Set value of ConfigName
	 * @param configname
	 */
	public final void setConfigName(java.lang.String configname)
	{
		setConfigName(getContext(), configname);
	}

	/**
	 * Set value of ConfigName
	 * @param context
	 * @param configname
	 */
	public final void setConfigName(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String configname)
	{
		getMendixObject().setValue(context, MemberNames.ConfigName.toString(), configname);
	}

	/**
	 * @return value of ConfigVersionNum
	 */
	public final java.lang.Long getConfigVersionNum()
	{
		return getConfigVersionNum(getContext());
	}

	/**
	 * @param context
	 * @return value of ConfigVersionNum
	 */
	public final java.lang.Long getConfigVersionNum(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Long) getMendixObject().getValue(context, MemberNames.ConfigVersionNum.toString());
	}

	/**
	 * Set value of ConfigVersionNum
	 * @param configversionnum
	 */
	public final void setConfigVersionNum(java.lang.Long configversionnum)
	{
		setConfigVersionNum(getContext(), configversionnum);
	}

	/**
	 * Set value of ConfigVersionNum
	 * @param context
	 * @param configversionnum
	 */
	public final void setConfigVersionNum(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Long configversionnum)
	{
		getMendixObject().setValue(context, MemberNames.ConfigVersionNum.toString(), configversionnum);
	}

	/**
	 * @return value of Description
	 */
	public final java.lang.String getDescription()
	{
		return getDescription(getContext());
	}

	/**
	 * @param context
	 * @return value of Description
	 */
	public final java.lang.String getDescription(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Description.toString());
	}

	/**
	 * Set value of Description
	 * @param description
	 */
	public final void setDescription(java.lang.String description)
	{
		setDescription(getContext(), description);
	}

	/**
	 * Set value of Description
	 * @param context
	 * @param description
	 */
	public final void setDescription(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String description)
	{
		getMendixObject().setValue(context, MemberNames.Description.toString(), description);
	}

	/**
	 * @return value of IsEnable
	 */
	public final java.lang.Boolean getIsEnable()
	{
		return getIsEnable(getContext());
	}

	/**
	 * @param context
	 * @return value of IsEnable
	 */
	public final java.lang.Boolean getIsEnable(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Boolean) getMendixObject().getValue(context, MemberNames.IsEnable.toString());
	}

	/**
	 * Set value of IsEnable
	 * @param isenable
	 */
	public final void setIsEnable(java.lang.Boolean isenable)
	{
		setIsEnable(getContext(), isenable);
	}

	/**
	 * Set value of IsEnable
	 * @param context
	 * @param isenable
	 */
	public final void setIsEnable(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Boolean isenable)
	{
		getMendixObject().setValue(context, MemberNames.IsEnable.toString(), isenable);
	}

	/**
	 * @return value of Token
	 */
	public final java.lang.String getToken()
	{
		return getToken(getContext());
	}

	/**
	 * @param context
	 * @return value of Token
	 */
	public final java.lang.String getToken(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Token.toString());
	}

	/**
	 * Set value of Token
	 * @param token
	 */
	public final void setToken(java.lang.String token)
	{
		setToken(getContext(), token);
	}

	/**
	 * Set value of Token
	 * @param context
	 * @param token
	 */
	public final void setToken(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String token)
	{
		getMendixObject().setValue(context, MemberNames.Token.toString(), token);
	}

	/**
	 * @return value of Channel
	 */
	public final java.lang.String getChannel()
	{
		return getChannel(getContext());
	}

	/**
	 * @param context
	 * @return value of Channel
	 */
	public final java.lang.String getChannel(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Channel.toString());
	}

	/**
	 * Set value of Channel
	 * @param channel
	 */
	public final void setChannel(java.lang.String channel)
	{
		setChannel(getContext(), channel);
	}

	/**
	 * Set value of Channel
	 * @param context
	 * @param channel
	 */
	public final void setChannel(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String channel)
	{
		getMendixObject().setValue(context, MemberNames.Channel.toString(), channel);
	}

	/**
	 * Set value of LogLevel
	 * @param loglevel
	 */
	public final slacklogger.proxies.LogLevels getLogLevel()
	{
		return getLogLevel(getContext());
	}

	/**
	 * @param context
	 * @return value of LogLevel
	 */
	public final slacklogger.proxies.LogLevels getLogLevel(com.mendix.systemwideinterfaces.core.IContext context)
	{
		Object obj = getMendixObject().getValue(context, MemberNames.LogLevel.toString());
		if (obj == null) {
			return null;
		}
		return slacklogger.proxies.LogLevels.valueOf((java.lang.String) obj);
	}

	/**
	 * Set value of LogLevel
	 * @param loglevel
	 */
	public final void setLogLevel(slacklogger.proxies.LogLevels loglevel)
	{
		setLogLevel(getContext(), loglevel);
	}

	/**
	 * Set value of LogLevel
	 * @param context
	 * @param loglevel
	 */
	public final void setLogLevel(com.mendix.systemwideinterfaces.core.IContext context, slacklogger.proxies.LogLevels loglevel)
	{
		if (loglevel != null) {
			getMendixObject().setValue(context, MemberNames.LogLevel.toString(), loglevel.toString());
		} else {
			getMendixObject().setValue(context, MemberNames.LogLevel.toString(), null);
		}
	}

	/**
	 * @return value of PrintStackTrace
	 */
	public final java.lang.Boolean getPrintStackTrace()
	{
		return getPrintStackTrace(getContext());
	}

	/**
	 * @param context
	 * @return value of PrintStackTrace
	 */
	public final java.lang.Boolean getPrintStackTrace(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Boolean) getMendixObject().getValue(context, MemberNames.PrintStackTrace.toString());
	}

	/**
	 * Set value of PrintStackTrace
	 * @param printstacktrace
	 */
	public final void setPrintStackTrace(java.lang.Boolean printstacktrace)
	{
		setPrintStackTrace(getContext(), printstacktrace);
	}

	/**
	 * Set value of PrintStackTrace
	 * @param context
	 * @param printstacktrace
	 */
	public final void setPrintStackTrace(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Boolean printstacktrace)
	{
		getMendixObject().setValue(context, MemberNames.PrintStackTrace.toString(), printstacktrace);
	}

	/**
	 * @return value of ZoneId
	 */
	public final java.lang.String getZoneId()
	{
		return getZoneId(getContext());
	}

	/**
	 * @param context
	 * @return value of ZoneId
	 */
	public final java.lang.String getZoneId(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.ZoneId.toString());
	}

	/**
	 * Set value of ZoneId
	 * @param zoneid
	 */
	public final void setZoneId(java.lang.String zoneid)
	{
		setZoneId(getContext(), zoneid);
	}

	/**
	 * Set value of ZoneId
	 * @param context
	 * @param zoneid
	 */
	public final void setZoneId(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String zoneid)
	{
		getMendixObject().setValue(context, MemberNames.ZoneId.toString(), zoneid);
	}

	/**
	 * @return value of QueueDepth
	 */
	public final java.lang.Integer getQueueDepth()
	{
		return getQueueDepth(getContext());
	}

	/**
	 * @param context
	 * @return value of QueueDepth
	 */
	public final java.lang.Integer getQueueDepth(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Integer) getMendixObject().getValue(context, MemberNames.QueueDepth.toString());
	}

	/**
	 * Set value of QueueDepth
	 * @param queuedepth
	 */
	public final void setQueueDepth(java.lang.Integer queuedepth)
	{
		setQueueDepth(getContext(), queuedepth);
	}

	/**
	 * Set value of QueueDepth
	 * @param context
	 * @param queuedepth
	 */
	public final void setQueueDepth(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Integer queuedepth)
	{
		getMendixObject().setValue(context, MemberNames.QueueDepth.toString(), queuedepth);
	}

	/**
	 * @return value of ProcessInterval
	 */
	public final java.lang.Integer getProcessInterval()
	{
		return getProcessInterval(getContext());
	}

	/**
	 * @param context
	 * @return value of ProcessInterval
	 */
	public final java.lang.Integer getProcessInterval(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Integer) getMendixObject().getValue(context, MemberNames.ProcessInterval.toString());
	}

	/**
	 * Set value of ProcessInterval
	 * @param processinterval
	 */
	public final void setProcessInterval(java.lang.Integer processinterval)
	{
		setProcessInterval(getContext(), processinterval);
	}

	/**
	 * Set value of ProcessInterval
	 * @param context
	 * @param processinterval
	 */
	public final void setProcessInterval(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Integer processinterval)
	{
		getMendixObject().setValue(context, MemberNames.ProcessInterval.toString(), processinterval);
	}

	/**
	 * @return value of ExcludeRegexp
	 */
	public final java.lang.String getExcludeRegexp()
	{
		return getExcludeRegexp(getContext());
	}

	/**
	 * @param context
	 * @return value of ExcludeRegexp
	 */
	public final java.lang.String getExcludeRegexp(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.ExcludeRegexp.toString());
	}

	/**
	 * Set value of ExcludeRegexp
	 * @param excluderegexp
	 */
	public final void setExcludeRegexp(java.lang.String excluderegexp)
	{
		setExcludeRegexp(getContext(), excluderegexp);
	}

	/**
	 * Set value of ExcludeRegexp
	 * @param context
	 * @param excluderegexp
	 */
	public final void setExcludeRegexp(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String excluderegexp)
	{
		getMendixObject().setValue(context, MemberNames.ExcludeRegexp.toString(), excluderegexp);
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return slackLoggerConfigMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this) {
			return true;
		}
		if (obj != null && getClass().equals(obj.getClass()))
		{
			final slacklogger.proxies.SlackLoggerConfig that = (slacklogger.proxies.SlackLoggerConfig) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@java.lang.Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return entityName;
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
