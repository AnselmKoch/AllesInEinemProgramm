package me.anselm.alles.application;

import me.anselm.alles.Alles;
import me.anselm.alles.menu.MenuPanel;
import me.anselm.alles.application.options.OptionFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Timer;

public abstract class Application extends MenuPanel implements ActionListener, Runnable {

    private String name;
    private ApplicationType applicationType;
    private Timer timer;
    private int timerDelay;
    private BufferedImage bufferedImage;
    private int[] pixels;
    private boolean needBufferedImage = false, hasOptions = false;
    private OptionFrame optionFrame;
    private Thread thread;
    private JMenuBar jMenuBar;
    private JMenu jMenu;
    private JMenuItem optionItem;
    private JMenuItem returnItem;
    private Canvas canvas;
    private int fps = 0, updates = 0;

    public Application(ApplicationType applicationType, String name) {
        this.applicationType = applicationType;
        this.name = name;
        this.canvas = new Canvas();
        this.add(canvas);
        this.pixels = new int[Alles.WIDTH * Alles.HEIGHT];
        this.bufferedImage = new BufferedImage(Alles.WIDTH, Alles.HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)this.bufferedImage.getRaster().getDataBuffer()).getData();
        this.jMenuBar = new JMenuBar();
        this.jMenu = new JMenu("Datei");
        this.optionItem = new JMenuItem("Optionen");
        this.optionItem.addActionListener(this);
        this.optionItem.setActionCommand("option");
        this.returnItem = new JMenuItem("Zurück");
        this.returnItem.addActionListener(this);
        this.returnItem.setActionCommand("999");
        this.jMenu.add(returnItem);
        this.jMenu.add(optionItem);
        this.jMenuBar.add(jMenu);
        this.setFocusable(true);
    }

    public abstract void actionToPerfom();

    public ApplicationType getApplicationType() {
        return applicationType;
    }

   public abstract void render(Graphics graphics);

    public abstract void drawPixel();

    public void setPixels(int pixel, int data) {
        this.pixels[pixel] = data;
    }
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        if(needBufferedImage) {
            graphics2D.drawImage(bufferedImage, null, null);
        }else{
            render(graphics2D);
        }
    }

    public void doLoop() {
        this.requestFocus();
        this.requestFocusInWindow();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                this.actionToPerfom();
                this.setUpdates(getUpdates() + 1);
                delta--;
            }
            repaint();
            this.setFps(getFps() + 1);
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                Alles.getInstance().setTitle(this.getName() + " fps: " + getFps() + " ," + "ups: " + getUpdates());
                this.setFps(0);
                this.setUpdates(0);
            }
        }
    }

    public void start() {
        thread = new Thread(this);
        timer = new Timer();
        thread.start();
    }

    public abstract void restoreDefault();

    public void stop() {
        restoreDefault();
        thread.stop();
        thread = null;
    }

    public void addOption(JTextField jTextField, String name, String label) {
        this.getOptionFrame().addOption(jTextField, name, label);
    }

    public void addOption(JComponent jComponent, String name) {
        this.getOptionFrame().addOption(jComponent, name);
    }

    public void cancelTimer() {
        timer.cancel();
    }
    @Override
    public String getName() {
        return name;
    }

    public Thread getThread() {
        return this.thread;
    }
    public JMenuBar getjMenuBar() {
        return this.jMenuBar;
    }
    public void setTimerDelay(int newDelay) {
        this.timerDelay = newDelay;
    }
    public int getTimerDelay() {
        return this.timerDelay;
    }
    public BufferedImage getBufferedImage() {
        return this.bufferedImage;
    }
    public Timer getTimer() {
        return this.timer;
    }
    public boolean isNeedBufferedImage() {
        return needBufferedImage;
    }
    public void setNeedBufferedImage(boolean needBufferedImage) {
        this.needBufferedImage = needBufferedImage;
    }
    public OptionFrame getOptionFrame() {
        return optionFrame;
    }
    public void setOptionFrame(OptionFrame optionFrame) {
        this.setHasOptions(true);
        this.optionFrame = optionFrame;
    }
    public boolean isHasOptions() {
        return hasOptions;
    }
    public void setHasOptions(boolean hasOptions) {
        this.hasOptions = hasOptions;
    }
    public void setNote(String note) {
        this.getOptionFrame().getNoteTextArea().setText(note);
    }
    public void appendNote(String note){
        this.getOptionFrame().getNoteTextArea().append(note);
    }
    public int getFps() {
        return fps;
    }
    public void setFps(int fps) {
        this.fps = fps;
    }
    public int getUpdates() {
        return updates;
    }
    public void setUpdates(int updates) {
        this.updates = updates;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public int[] getPixels() {
        return pixels;
    }
}
