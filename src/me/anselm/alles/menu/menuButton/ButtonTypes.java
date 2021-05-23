package me.anselm.alles.menu.menuButton;

public enum ButtonTypes {


    RETURNBUTTON("999", "Zurück!");
    private String actionCommand,text;

    ButtonTypes(String actionCommand,String text) {
        this.actionCommand = actionCommand;
        this.text = text;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public String getText() {
        return text;
    }
}
