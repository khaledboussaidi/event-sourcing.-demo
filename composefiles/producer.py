

from kafka import KafkaProducer
import json
producer=KafkaProducer(value_serializer=lambda v: json.dumps(v).encode('utf-8'),
api_version=(0, 10, 1), bootstrap_servers=['localhost:9092'])


for i in range(10):
    producer.send("mytopic","hello from an other world ??????§§§§§§§§§§§§§§§§§§§§§")