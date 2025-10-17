// Kafka Scripted Producer Step (example with no schema in use)
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

def kafkaEndpoint = "localhost:9092"
def topic = "example.noSchema.topic"

// Set up the producer properties
Properties props = new Properties();
props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaEndpoint);
props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

// Create the producer object
Producer<String, String> producer = new KafkaProducer<>(props);

// Create a producer record
def key = ""
// Example Kafka message
def message = '''
{   
	\"favorite_city\" : \"Berlin\",   
	\"favorite_number\" : 4,   
	\"favorite_color\" : \"Black\"
}'''

ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);

// Send the record
producer.send(record)
// Close the producer
producer.close();