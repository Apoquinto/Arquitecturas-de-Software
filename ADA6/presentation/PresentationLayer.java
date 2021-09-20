package presentation;

public interface PresentationLayer {
    // Basic funcs
    public String read();
    public void clear();
    // Logs msg
    public void commonMsg(String msg);
    public void successMsg(String msg);
    public void warningMsg(String msg);
    public void dangerMsg(String msg);
    // Action func
    public void run();
}
