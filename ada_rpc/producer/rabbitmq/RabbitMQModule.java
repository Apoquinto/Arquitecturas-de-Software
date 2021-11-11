package rabbitmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import client.Client;

public class RabbitMQModule implements Client {
    private final static String QUEUE_NAME = "Queue";
    private ConnectionFactory factory;

    public RabbitMQModule() {
        this("localhost");
    }

    public RabbitMQModule(String host) {
        this.factory = new ConnectionFactory();
        factory.setHost(host);
    }

    @Override
    public String sendRequest(String message) {
        String result;

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String correlationId = UUID.randomUUID().toString();
            String callbackQueueName = channel.queueDeclare().getQueue();
            final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
            AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder().correlationId(correlationId)
                    .replyTo(callbackQueueName).build();

            // Send message
            channel.basicPublish("", QUEUE_NAME, properties, message.getBytes(StandardCharsets.UTF_8));

            // Get response
            channel.basicConsume(callbackQueueName, true, (consumerTag, delivery) -> {
                if (delivery.getProperties().getCorrelationId().equals(correlationId)) {
                    response.offer(new String(delivery.getBody(), "UTF-8"));
                }
            }, consumerTag -> {
            });

            // Save response and close
            result = response.take();

            channel.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("ERROR: Message not sent.");
            result = "";
        }

        return result;
    }

    public void startConsumingMessages() {
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicQos(1);

            // Call's lambda function
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                AMQP.BasicProperties currentCallerProperties = new AMQP.BasicProperties.Builder()
                        .correlationId(delivery.getProperties().getCorrelationId()).build();
                String message = new String(delivery.getBody(), "UTF-8");

                // Do a task with the message
                String response = message;

                // Answer
                channel.basicPublish("", delivery.getProperties().getReplyTo(), currentCallerProperties,
                        response.getBytes(StandardCharsets.UTF_8));
            };

            // Get message
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (Exception e) {
            System.out.println("ERROR: Message not received.");
        }
    }
}