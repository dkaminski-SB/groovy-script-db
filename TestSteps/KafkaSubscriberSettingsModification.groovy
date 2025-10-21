// On this example Script can modify Kafka Subscriber Test Step settings. This allows use of Property Expansion on those.

def kafkaStepName = "API Subscriber"
def maxIdleTime = 20
def maxMessagesRecived = 5
def maxRunTime =80

testRunner.testCase.getTestStepByName(kafkaStepName).setMaxIdleTime(maxIdleTime)
log.info "Currently for \"" + kafkaStepName + "\" > set Maximum Idle Time is: " +  testRunner.testCase.getTestStepByName(kafkaStepName).getMaxIdleTime()

testRunner.testCase.getTestStepByName(kafkaStepName).setMaxMessagesReceived(maxMessagesRecived)
log.info "Currently for \"" + kafkaStepName + "\" > Maximum no. of Messages Received is: " + testRunner.testCase.getTestStepByName(kafkaStepName).getMaxMessagesReceived()

testRunner.testCase.getTestStepByName(kafkaStepName).setMaxRunTime(maxRunTime)
log.info "Currently for \"" + kafkaStepName + "\" > Maximum Test Step Run Time is:   " + testRunner.testCase.getTestStepByName(kafkaStepName).getMaxRunTime()