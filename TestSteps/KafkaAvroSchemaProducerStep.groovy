import org.apache.kafka.clients.producer.*;
import org.apache.avro.Schema;
import org.apache.avro.generic.*
import io.confluent.kafka.serializers.*

def kafkaEndpoint = "<Kafka endpoint>:9092"
def topic = "example.avroSchema.topic"
def schemaRegistryUrl = "http://<schema registry IP>:8085"

// Set up the producer properties
def kafkaProps = new Properties()
// SSL/TLS Config
kafkaProps.put("security.protocol", "SSL")
kafkaProps.put("ssl.truststore.location", "C:\\Some\\path\\to\\the\\truststore.jks") // Update with your truststore path
kafkaProps.put("ssl.truststore.password", "password") // Update with your truststore password

kafkaProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaEndpoint)
kafkaProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
kafkaProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName())
kafkaProps.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

// Create the producer object
Producer<GenericRecord, GenericRecord> producer = new KafkaProducer<>(kafkaProps);

// Provide schama for the record
String keySchemaString = null;
String valueSchemaString = '''
{
        "namespace": "example.avro",
        "type": "record",
        "name": "ExampleValue",
        "fields": [
                 {"name": "first_name", "type": "string"},
                 {"name": "favorite_city", "type": "string"},
                 {"name": "favorite_number",  "type": ["int", "null"]},
                 {"name": "favorite_color", "type": ["string", "null"]}
        ]
}
''';
// Parse schemas
Schema avroKeySchema = new Schema.Parser().parse(keySchemaString);
Schema avroValueSchema = new Schema.Parser().parse(valueSchemaString);

//Create and prepare the avro record
GenericRecord avroMessageRecord = new GenericData.Record(avroValueSchema);
avroMessageRecord.put("first_name", "David");
avroMessageRecord.put("favorite_city", "Wrocław");
avroMessageRecord.put("favorite_number", 7);
avroMessageRecord.put("favorite_color", "Green");

ProducerRecord<String, GenericRecord> record = new ProducerRecord<>(topic, null, avroMessageRecord);

// Send the record
producer.send(record);
// Close the producer
producer.close();