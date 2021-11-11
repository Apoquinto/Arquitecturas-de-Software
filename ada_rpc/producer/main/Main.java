package main;

import java.util.Scanner;

import client.Client;
import rabbitmq.RabbitMQModule;

public class Main {
    public static void main(String[] args) {
        Client sender = new RabbitMQModule();
        System.out.println(sender.sendRequest(new Scanner(System.in).nextLine()));
    }
}