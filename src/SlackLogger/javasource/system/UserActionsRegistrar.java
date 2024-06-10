package system;

import com.mendix.core.actionmanagement.IActionRegistrator;

public class UserActionsRegistrar
{
  public void registerActions(IActionRegistrator registrator)
  {
    registrator.bundleComponentLoaded();
    registrator.registerUserAction(home.actions.GetXASId.class);
    registrator.registerUserAction(slacklogger.actions.RegisterSlackLogger.class);
    registrator.registerUserAction(slacklogger.actions.SendMessageToSlack.class);
    registrator.registerUserAction(slacklogger.actions.ShutdownSlackLoggers.class);
    registrator.registerUserAction(slacklogger.actions.TestRegexMatch.class);
    registrator.registerUserAction(slacklogger.actions.TestRegexMatchAsTarget.class);
    registrator.registerUserAction(system.actions.VerifyPassword.class);
  }
}
