package presentation;

public interface PresentationLayer {
    // Basic funcs
    public void print(String msg);
    public String read();
    public void clear();
    // Logs msg    
    public void successMsg(String msg);
    public void warningMsg(String msg);
    public void dangerMsg(String msg);
    public void run();
}
