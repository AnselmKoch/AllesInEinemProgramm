package me.anselm.alles.menu;

import me.anselm.alles.Alles;
import me.anselm.alles.application.Application;
import me.anselm.alles.application.ApplicationType;
import me.anselm.alles.application.applications.games.DreiDimension;
import me.anselm.alles.application.applications.games.ShootShooter;
import me.anselm.alles.application.applications.misc.OpenGL;
import me.anselm.alles.application.applications.misc.Rainbow;
import me.anselm.alles.application.applications.misc.Raindrop;
import me.anselm.alles.application.applications.mathe.Sinus;
import me.anselm.alles.menu.menus.*;

import java.io.IOException;
import java.util.ArrayList;

public class MenuManager {

    private MenuPanel currentMenu, lastMenu;
    public MenuPanel mainMenu, testMenu, errorMenu, miscMenu, mathMenu, gamesMenu;

    public Application rainbow,sinus, raindrop,shootShooter, dreiDimension, openGL;

    private ArrayList<Menu> menus = new ArrayList<>();
    private ArrayList<Application> applications = new ArrayList<>();

    public MenuManager() throws IOException {

        mainMenu = new MainMenu(MenuType.MAIN_MENU);
        testMenu = new TestMenu(MenuType.TEST_MENU);
        errorMenu = new ErrorMenu(MenuType.ERROR_MENU);

        miscMenu = new MiscMenu(MenuType.MISC_MENU);
        gamesMenu = new GameMenu(MenuType.GAMES_MENU);
        mathMenu = new MathMenu(MenuType.MATH_MENU);

        openGL = new OpenGL(ApplicationType.MISC, "Open GL");
        sinus = new Sinus(ApplicationType.MISC, "Sinus");
        raindrop = new Raindrop(ApplicationType.MISC, "Raindrop");
        rainbow = new Rainbow(ApplicationType.MISC, "Rainbow");
        shootShooter = new ShootShooter(ApplicationType.GAMES, "ShootShooter");
        dreiDimension = new DreiDimension(ApplicationType.GAMES, "DreiDimension");



        applications.add(openGL);
        applications.add(rainbow);
        applications.add(sinus);
        applications.add(raindrop);
        applications.add(shootShooter);
        applications.add(dreiDimension);

        menus.add((Menu) miscMenu);
        menus.add((Menu) mainMenu);
        menus.add((Menu) testMenu);
        menus.add((Menu) errorMenu);
        menus.add((Menu) gamesMenu);
        menus.add((Menu) mathMenu);

        lastMenu = mainMenu;
        currentMenu = mainMenu;
        Alles.getInstance().setContentPane(currentMenu);
        Alles.getInstance().setTitle(currentMenu.getName());
        Alles.getInstance().repaint();
    }

    public void switchMenu(Application application) {
            lastMenu = currentMenu;
            currentMenu.setVisible(false);
            currentMenu = application;
            currentMenu.setVisible(true);
             Alles.getInstance().setContentPane(currentMenu);
            Alles.getInstance().setTitle(currentMenu.getName());
            Alles.getInstance().setJMenuBar(application.getjMenuBar());
            application.start();
            application.grabFocus();
    }

    public void switchToLastMenu() {
        MenuPanel tmpPanel = currentMenu;
        currentMenu.setVisible(false);
        currentMenu = lastMenu;
        if(tmpPanel instanceof Application) {
            Application application = (Application)tmpPanel;
            application.cancelTimer();
            application.stop();
            if(application.isHasOptions()) {
                application.getOptionFrame().setVisible(false);
            }
            lastMenu = Alles.menuManager.mainMenu;
        }else {
            lastMenu = currentMenu;
        }
        currentMenu.setVisible(true);
        Alles.getInstance().setTitle(currentMenu.getName());
        Alles.getInstance().setContentPane(currentMenu);
        Alles.getInstance().setJMenuBar(null);
    }

    public void switchMenu(MenuPanel menuPanel) {
        if(menuPanel != currentMenu) {
            lastMenu = currentMenu;
            currentMenu.setVisible(false);
            currentMenu = menuPanel;
            currentMenu.setVisible(true);
            Alles.getInstance().setTitle(currentMenu.getName());
            Alles.getInstance().setContentPane(currentMenu);
        }
    }

    public Application getApplicaionFromName(String name) {
        for(Application application : applications) {
            if(application.getName().equalsIgnoreCase(name)) {
                return application;
            }
        }
        return null;
    }

    public MenuPanel getMenuFromID(int ID) {
        for(Menu menu : menus) {
            if(ID == menu.getID()) {
                return menu;
            }
        }
        return errorMenu;
    }

    public MenuPanel getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public ArrayList<Menu> getMenuPanels() {
        return menus;
    }

    public MenuPanel getLastMenu() {
        return lastMenu;
    }

    public void setLastMenu(Menu lastMenu) {
        this.lastMenu = lastMenu;
    }
}
