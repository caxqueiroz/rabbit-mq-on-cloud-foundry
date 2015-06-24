#!/usr/bin/env python
import pika
credentials = pika.PlainCredentials('guest', 'guest')
connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost', credentials=credentials))
channel = connection.channel()
print ' [*] Waiting for messages. To exit press CTRL+C'
def callback(ch, method, properties, body):
    print " [x] Received %r" % (body,)
channel.basic_consume(callback, queue='FILE_PROC_QUEUE', no_ack=True)
channel.start_consuming()

