# ReadAPI-Groovy-Scripts
Repository containing Examples of scripts.

### Why use Groovy Scripts in ReadyAPI ?

Besides standard funtionalities in ReadyAPI, you can use scripts to enhance tests and extend the ReadyAPI functionality on top of exisitng ones. You can also create your own modules and reuse them in multiple projects.

Most commonly, scripts are used to prepare your environment before running a test and remove any aftereffects of the test when it finishes or perform custom action which is not available with default features. 
For example, you can start virtual services before starting your test and stop them once the testing is over or execute a specific test step before continuing.

Scripts can be executed across sevral places and stages of ReadyAPI tests. This repository catalogues scripts based on where script can be executed:
- Groovy Scripts as Test Steps
- Setup Scripts and TearDown Scripts
- Scripted Events (based on Event Listners)
- Scripted Data Source Step

#### Why such separation ?
It is dictated by objects available to specific area where we use scripts. 
More on that topic here [LINK](https://support.smartbear.com/readyapi/docs/en/test-apis-with-readyapi/scripting.html)