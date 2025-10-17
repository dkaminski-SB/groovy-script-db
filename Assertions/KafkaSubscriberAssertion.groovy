// Wait for Kafka Subscriber
sleep(10000)

def messages = context.getCurrentStep().data

// Get sample of message you want to compare
def validMessage = "{   \"favorite_city\" : \"Berlin\",   \"favorite_number\" : 4,   \"favorite_color\" : \"Black\"}"
// OR get sample massage form the Kafka Test Step by reffering trough a tree e.g '${#[<TestSuiteName>#<TestCaseName>#<TestStepName>]#data}'
def data = context.expand( '${#[KafkaSuite#TestCase-example.topic#TestStepPublisher]#data}' )

def validCounter = 0;

log.info "Validate count for following message:" + validMessage

for(message in messages){
	if (message.replace("\\n","").replace("\\","").contains(validMessage)){
		validCounter++;
	}
}
log.info "Number of expected messages:" +  validCounter

assert (validCounter > 0 && validCounter < 2);