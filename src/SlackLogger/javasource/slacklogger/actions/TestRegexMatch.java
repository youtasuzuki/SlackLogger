// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package slacklogger.actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;

public class TestRegexMatch extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.String Regex;
	private java.lang.String TestString;

	public TestRegexMatch(IContext context, java.lang.String Regex, java.lang.String TestString)
	{
		super(context);
		this.Regex = Regex;
		this.TestString = TestString;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
		Pattern pat = Pattern.compile(Regex);
		Matcher mat = pat.matcher(TestString);
		if (mat.find()) {
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
		return "TestRegexMatch";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
