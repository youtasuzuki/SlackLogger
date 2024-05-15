// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package slacklogger.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import slacklogger.implementation.LogSubscriberForSlackLogger;

public class RegisterSlackLogger extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __config;
	private slacklogger.proxies.SlackLoggerConfig config;

	public RegisterSlackLogger(IContext context, IMendixObject config)
	{
		super(context);
		this.__config = config;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.config = this.__config == null ? null : slacklogger.proxies.SlackLoggerConfig.initialize(getContext(), __config);

		// BEGIN USER CODE
		LogSubscriberForSlackLogger subscriber = LogSubscriberForSlackLogger.getInstance(config.getConfigName());
		if (!subscriber.isStarted()) {
			subscriber.configure(config);
			subscriber.start();
			return true;
		} else {
			return false;
		}
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "RegisterSlackLogger";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
