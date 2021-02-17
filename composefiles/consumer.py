
from kafka import KafkaConsumer
import json
consumer = KafkaConsumer('mytopic',
bootstrap_servers=['localhost:9092'],api_version=(0, 10, 1),
#auto_offset_reset='earliest',
group_id='my-group1',		
auto_commit_interval_ms=1,		
enable_auto_commit=True,
value_deserializer=lambda m: json.loads(m.decode('utf-8')))

count =0
for msg in consumer:
    count+=1
    print(msg.value)
    print(count)
