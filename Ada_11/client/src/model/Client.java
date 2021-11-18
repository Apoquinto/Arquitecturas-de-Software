package model;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception e) {
            System.out.println("Error while starting the connection!");
        }
    }

    public String sendMessage(String msg) {
        String response;

        try {
            out.println(msg);
            response = in.readLine();
        } catch (Exception e) {
            response = "";
        }

        return response;
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.startConnection("localhost", 8020);
        ArrayList<String> vars = new ArrayList<>();

        // List all
        System.out.println(client.sendMessage(new CallsFormatter().generateRequest("listar", 0, vars.iterator())));

        // List "contar"
        vars.add("palabra");
        vars.add("contar");
        System.out.println(client.sendMessage(new CallsFormatter().generateRequest("listar", 1, vars.iterator())));

        // Service: Vote
        vars.clear();
        vars.add("servicio");
        vars.add("votar");
        vars.add("Windows");
        vars.add("1");
        System.out.println(client.sendMessage(new CallsFormatter().generateRequest("ejecutar", 2, vars.iterator())));

        // Service: Contar
        vars.clear();
        vars.add("servicio");
        vars.add("contar");
        System.out.println(client.sendMessage(new CallsFormatter().generateRequest("ejecutar", 1, vars.iterator())));

        client.stopConnection();
    }
}