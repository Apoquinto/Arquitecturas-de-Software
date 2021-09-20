package logic;

import java.util.ArrayList;

public interface LogicLayer {
    public boolean verifyIsKwicCommand(String message);
    public ArrayList<String> executeKwic(String message);
}
