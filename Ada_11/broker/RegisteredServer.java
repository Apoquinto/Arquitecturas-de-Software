import java.util.ArrayList;
import java.util.Iterator;

public class RegisteredServer {
    private String host;
    private int port;
    private ArrayList<Service> supportedServices;

    public RegisteredServer(String host, int port) {
        this.host = host;
        this.port = port;
        this.supportedServices = new ArrayList<>();
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Iterator<String> getServices() {
        ArrayList<String> outList = new ArrayList<>();

        for (Service service : supportedServices) {
            outList.add(service.getName() + "," + service.getParams());
        }

        return outList.iterator();
    }

    public int getParamsOfService(String serviceName) {
        int paramsAmount = -1;

        for (Service service : supportedServices) {
            if (service.getName().equals(serviceName)) {
                paramsAmount = service.getParams();
                break;
            }
        }

        return paramsAmount;
    }

    public void registerService(String serviceName, int paramsAmount) {
        if (!supportsService(serviceName)) {
            supportedServices.add(new Service(serviceName, paramsAmount));
        }
    }

    public boolean supportsService(String serviceName) {
        // The service's equals method does not care about the parameters amount, just
        // the name
        return supportedServices.contains(new Service(serviceName, -1));
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof RegisteredServer) {
            RegisteredServer server = (RegisteredServer) obj;
            equal = server.getHost().equals(getHost()) && server.getPort() == getPort();
        }

        return equal;
    }

    @Override
    public String toString() {
        String output = "Host: " + getHost() + "\nPort: " + getPort() + "\nServices: ";

        for (Service service : supportedServices) {
            output += service.getName() + ",";
        }

        return output.substring(0, output.length() - 1);
    }
}
