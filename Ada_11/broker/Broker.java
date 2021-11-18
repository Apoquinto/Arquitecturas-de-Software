import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class Broker {
    private ServerSocket serverSocket;
    private LinkedList<RegisteredServer> registeredServers;
    private String serverFilename;

    public Broker() {
        this.registeredServers = new LinkedList<>();
        this.serverFilename = "servers.csv";
        retrieveRegisters();
    }

    private void retrieveRegisters() {
        ArrayList<String> registers = new FileHandler().readFile(serverFilename);

        for (String register : registers) {
            try {
                String[] separated = register.split(",");
                registerServer(separated[0], Integer.parseInt(separated[1]));
                registerService(separated[0], Integer.parseInt(separated[1]), separated[2],
                        Integer.parseInt(separated[3]));
            } catch (Exception e) {
            }
        }
    }

    private ArrayList<String> generateServerCsvContent() {
        Iterator<RegisteredServer> servers = registeredServers.iterator();
        ArrayList<String> csvContent = new ArrayList<>();

        while (servers.hasNext()) {
            RegisteredServer server = servers.next();
            Iterator<String> services = server.getServices();

            while (services.hasNext()) {
                csvContent.add(server.getHost() + "," + server.getPort() + "," + services.next());
            }
        }

        return csvContent;
    }

    public void registerServer(String host, int port) {
        RegisteredServer server = new RegisteredServer(host, port);

        if (!registeredServers.contains(server)) {
            registeredServers.add(server);
        }
    }

    public void unregisterServer(String host, int port) {
        registeredServers.remove(new RegisteredServer(host, port));
    }

    public void registerService(String host, int port, String serviceName, int paramsAmount) {
        int index = registeredServers.indexOf(new RegisteredServer(host, port));

        if (index != -1) {
            registeredServers.get(index).registerService(serviceName, paramsAmount);
        }
    }

    public void startConnection(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                new BrokerServiceModule(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            System.out.println("Error while starting the connection!");
        }
    }

    public void closeConnection() {
        try {
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error while ending the connection!");
        }
    }

    class BrokerServiceModule extends Thread {
        private Socket clientSocket;

        public BrokerServiceModule(Socket cliSocket) {
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
                        case "registrar" -> {
                            response = register(json);
                            new FileHandler().writeFile(serverFilename, generateServerCsvContent());
                        }
                        case "listar" -> {
                            response = list(json);
                        }
                        case "ejecutar" -> {
                            response = execute(json);
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

        private String register(JSONObject json) {
            String response = "{}";

            try {
                String host = (String) json.get("valor1"), newService = (String) json.get("valor3");
                // When a string is parsed, every Integer is converted to a Long type
                int port = ((Long) json.get("valor2")).intValue(),
                        paramsAmount = ((Long) json.get("valor4")).intValue();
                ArrayList<String> variables = new ArrayList<>();

                registerServer(host, port);
                registerService(host, port, newService, paramsAmount);

                variables.add("identificador");
                variables.add("1");

                response = new CallsFormatter().generateResponse((String) json.get("servicio"), 1,
                        variables.iterator());
            } catch (Exception e) {
                response = "{}";
                e.printStackTrace();
            }

            return response;
        }

        private String list(JSONObject json) {
            String response = "{}";

            try {
                ArrayList<String> variables = new ArrayList<>();
                int varsAmount = ((Long) json.get("variables")).intValue();
                int answersAmount = 0;

                if (varsAmount == 0) {
                    // All services
                    Iterator<RegisteredServer> servers = registeredServers.iterator();

                    while (servers.hasNext()) {
                        RegisteredServer server = servers.next();
                        Iterator<String> services = server.getServices();

                        while (services.hasNext()) {
                            answersAmount++;
                            variables.add(services.next().split(",")[0]);
                            variables.add(server.getHost());
                        }
                    }
                } else {
                    // Specific services
                    Iterator<RegisteredServer> servers = registeredServers.iterator();
                    ArrayList<String> requestedServices = new ArrayList<>();

                    for (int i = 1; i <= varsAmount; i++) {
                        requestedServices.add((String) json.get("valor" + i));
                    }

                    while (servers.hasNext()) {
                        RegisteredServer server = servers.next();
                        Iterator<String> services = server.getServices();

                        while (services.hasNext()) {
                            String service = services.next().split(",")[0];

                            if (requestedServices.contains(service)) {
                                answersAmount++;
                                variables.add(service);
                                variables.add(server.getHost());
                            }
                        }
                    }
                }

                response = new CallsFormatter().generateResponse((String) json.get("servicio"), answersAmount,
                        variables.iterator());
            } catch (Exception e) {
                response = "{}";
                e.printStackTrace();
            }

            return response;
        }

        private String execute(JSONObject json) {
            String response = "{}";

            try {
                Iterator<RegisteredServer> servers = registeredServers.iterator();
                String serviceName = (String) json.get("valor1");
                int paramsAmount = ((Long) json.get("variables")).intValue() - 1;

                while (servers.hasNext()) {
                    RegisteredServer server = servers.next();

                    if (server.supportsService(serviceName) && server.getParamsOfService(serviceName) == paramsAmount) {
                        try {
                            // Send my request to the server found
                            Socket serverSocket = new Socket(server.getHost(), server.getPort());
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(serverSocket.getInputStream()));
                            PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
                            ArrayList<String> variables = new ArrayList<>();
                            CallsFormatter formatter = new CallsFormatter();

                            for (int i = 2; i <= paramsAmount + 1; i++) { // i = 1 has the service name, so skip
                                variables.add((String) json.get("variable" + i));
                                Object o = json.get("valor" + i);
                                variables.add(o instanceof String ? (String) o : ((Long) o).toString());
                            }

                            out.println(formatter.generateRequest(serviceName, paramsAmount, variables.iterator()));

                            // Manage my response
                            JSONObject serverOutput = (JSONObject) new JSONParser().parse(in.readLine());
                            in.close();
                            out.close();
                            variables.clear();
                            paramsAmount = ((Long) serverOutput.get("respuestas")).intValue();

                            variables.add("servicio");
                            variables.add(serviceName);

                            for (int i = 1; i <= paramsAmount; i++) {
                                variables.add((String) serverOutput.get("respuesta" + i));
                                Object o = serverOutput.get("valor" + i);
                                variables.add(o instanceof String ? (String) o : ((Long) o).toString());
                            }

                            response = formatter.generateResponse((String) json.get("servicio"), paramsAmount + 1,
                                    variables.iterator());

                            in.close();
                            out.close();
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
                response = "{}";
                e.printStackTrace();
            }

            return response;
        }
    }

    public static void main(String[] args) {
        new Broker().startConnection(8020);
    }
}
