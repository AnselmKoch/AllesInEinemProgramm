package me.anselm.alles.menu;

public enum MenuType {


    GAMES_MENU(4,"Games"),MATH_MENU(3, "Math"),MISC_MENU(2,"Misc"),MAIN_MENU(0,"Hauptmenü"), TEST_MENU(1, "Test"), ERROR_MENU(404,"Error");

    private final String menuName;
    private final int menuID;

    MenuType(int menuID, String menuName) {
        this.menuName = menuName;
        this.menuID = menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuID() {
        return menuID;
    }
}
