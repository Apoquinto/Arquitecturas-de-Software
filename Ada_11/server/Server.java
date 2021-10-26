import java.net.*;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket broker;

    public void startConnection(int port) {
        try {
            serverSocket = new ServerSocket(port);
            broker = new Socket("localhost", 8020);
            register();
            while (true) {
                new ServerServiceModule(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            System.out.println("Error while starting the connection!");
        }
    }

    public void closeConnection() {
        try {
            broker.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error while ending the connection!");
        }
    }

    private void register() {
        try {
            CallsFormatter formatter = new CallsFormatter();
            String message;
            BufferedReader in = new BufferedReader(new InputStreamReader(broker.getInputStream()));
            PrintWriter out = new PrintWriter(broker.getOutputStream(), true);
            ArrayList<String> variables = new ArrayList<>();

            variables.add("servidor");
            variables.add("localhost");
            variables.add("puerto");
            variables.add("8982");
            variables.add("servicio");
            variables.add("contar");
            variables.add("parametros");
            variables.add("0");

            message = formatter.generateRequest("registrar", 4, variables.iterator());
            out.println(message);
            in.readLine();

            variables.clear();
            variables.add("servidor");
            variables.add("localhost");
            variables.add("puerto");
            variables.add("8982");
            variables.add("servicio");
            variables.add("votar");
            variables.add("parametros");
            variables.add("1");

            message = formatter.generateRequest("registrar", 4, variables.iterator());
            out.println(message);
            in.readLine();

            variables.clear();
            variables.add("servidor");
            variables.add("localhost");
            variables.add("puerto");
            variables.add("8982");
            variables.add("servicio");
            variables.add("registrar");
            variables.add("parametros");
            variables.add("2");

            message = formatter.generateRequest("registrar", 4, variables.iterator());
            out.println(message);
            in.readLine();

            variables.clear();
            variables.add("servidor");
            variables.add("localhost");
            variables.add("puerto");
            variables.add("8982");
            variables.add("servicio");
            variables.add("listar");
            variables.add("parametros");
            variables.add("0");

            message = formatter.generateRequest("registrar", 4, variables.iterator());
            out.println(message);
            in.readLine();

            in.close();
            out.close();
        } catch (Exception e) {
            System.out.println("Error while registering!");
        }
    }

    class ServerServiceModule extends Thread {
        private Socket clientSocket;

        public ServerServiceModule(Socket cliSocket) {
            this.clientSocket = cliSocket;
        }

        @Override
        public synchronized void start() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String line;
                JSONParser parser = new JSONParser();

                while ((line = in.readLine()) != null) {
                    String response = "{}";

                    try {
                        // Read message
                        JSONObject json = (JSONObject) parser.parse(line);

                        // Do something
                        switch ((String) json.get("servicio")) {
                        case "contar" -> {
                            // TEST!!!!!
                            System.out.println(line);
                            response = "{\"servicio\" : \"contar\",\"respuestas\" : 3,\"respuesta1\" : \"Windows\",\"valor1\" : 20,\"respuesta2\" : \"MacOS\",\"valor2\" : 10,\"respuesta3\" : \"Unix\",\"valor3\" : 24}";
                        }
                        case "votar" -> {
                            // TEST!!!!!
                            System.out.println(line);
                            response = "{\"servicio\" : \"votar\",\"respuestas\" : 1,\"respuesta1\" : \"Windows\",\"valor1\" : 21}";
                        }
                        case "registrar" -> {
                            // ...
                            // response = ...
                        }
                        case "listar" -> {
                            // ...
                            // response = ...
                        }
                        default -> {
                            response = "{}";
                        }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Response
                    out.println(response);
                }

                in.close();
                out.close();
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    clientSocket.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static void main(String[] args) {
        new Server().startConnection(8982);
    }
}
