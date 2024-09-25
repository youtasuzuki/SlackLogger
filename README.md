# SlackLogger
A Mendix LogSubscriber implementation to forward log messages to slack.
# Typical usage scenario
* Can be used for monitoring and alerting on errors.
# Features and limitations
* Forward log messages to Slack by specified log level.
* Multiple destinations.
* Simple flow control.
* Dynamic reconfiguration for cluster environments.
* Slack connection test.
* Time zone of log message time can be specified.
# Dependencies
* There is no dependencies on other modules and oss jars.
# Configuration
* Go to "https://api.slack.com/apps" to create a new slack app and get an OAuth Token.
  * Click "Create New App" -> Choose "From scratch" -> Specify "App Name" -> Choose "Workspace" -> Click "Create App"
  *   -> Go "OAuth & Permissions" -> Click Scopes "Add on OAuth Scope" ->  Choose "chat:write"  -> Click OAuth Tokens "Install to YourApp" -> Copy "Bot User OAuth Token"
* Create a screen that snippet calls SNIP_SlackLoggerConfig and set the transfer settings to Slack. You can test the connection to slack here.
* Call ASU_RegisterSlackLogger in your app's AfterStartup.
# Restrictions
* It clips the message at 4000 characters because slack's max message length is 4000 characters.
* Do not retry transfer if any error occurs.
# More Details
* Flow control
* It simply queues messages into java heap when it receives log messages from Mendix. Then, in a background process, only one message is transferred at each specified interval. When it receives an amount of log messages from Mendix that exceeds the queue depth, it will simply be skipped and the status will record the number of messages skipped. So you need to set the queue depth and processing interval to appropriate values.
* Dynamic reconfiguration
* The SlackLogger running on each cluster node updates its own SlackLoggerStatus record once every 30 seconds. At that time, if the reconfiguration request in the SlackLoggerStatus record is true, it will read the new configuration and restart. You can check it with the ConfigVersion value. If the ConfigVersion values for SlackLoggerConfig and SlackLoggerStatus are the same, SlackLogger is running with the latest configuration!
