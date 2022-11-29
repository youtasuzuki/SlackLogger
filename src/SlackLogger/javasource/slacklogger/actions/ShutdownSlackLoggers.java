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
import com.mendix.webui.CustomJavaAction;
import slacklogger.implementation.LogSubscriberForSlackLogger;

public class ShutdownSlackLoggers extends CustomJavaAction<java.lang.Boolean>
{
	public ShutdownSlackLoggers(IContext context)
	{
		super(context);
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		LogSubscriberForSlackLogger.shutdownAll();
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "ShutdownSlackLoggers";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
