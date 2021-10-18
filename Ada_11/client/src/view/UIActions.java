package view;

import model.Model;

public interface UIActions {
    public void updateContent(Model model);
    public void showError(String message);
}
