package service;

public class ServiceModule {
    public String executeTask(String message) {
        // Here you write your algorithm
        return message.equals("1") ? "One!" : "Something, but not one.";
    }
}
