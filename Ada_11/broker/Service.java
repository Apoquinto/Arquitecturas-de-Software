public class Service {
    private String name;
    private int params;

    public Service(String name, int params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public int getParams() {
        return params;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Service ? ((Service) obj).getName().equals(getName()) : false;
    }
}
