package main;

import rabbitmq.RabbitMQModule;
import server.Server;

public class Main {
    public static void main(String[] args) {
        Server communicationModule = new RabbitMQModule();
        communicationModule.startConsumingMessages();
    }
}