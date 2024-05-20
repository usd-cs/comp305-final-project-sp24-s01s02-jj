package edu.sandiego.comp305.sp24.schoolSim.view;

public abstract class AbstractForm {
    public abstract String toBulmaForm(String postPath);

    public String addFooterButtons(String path) {
        return "";
    }
}
