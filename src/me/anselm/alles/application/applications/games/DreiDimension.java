package me.anselm.alles.application.applications.games;

import me.anselm.alles.Alles;
import me.anselm.alles.application.Application;
import me.anselm.alles.application.ApplicationType;
import me.anselm.alles.application.applications.games.gameLogic.KeyInputHandler;
import me.anselm.alles.application.options.IOption;
import me.anselm.alles.application.options.OptionFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;

public class DreiDimension extends Application implements IOption{

    private JTextField yDepth = new JTextField();
    private double yDepthD = 1.8;
    private double yDepthSin = 0.01;
    private JTextField z = new JTextField();
    private double zValue = 100.0;
    private JTextField xDepth = new JTextField();
    private double xDepthValue = 2;
    private JTextField colorField = new JTextField();
    private double colorValue = 999999;
    private JTextField xxField = new JTextField();
    private double xxValue = 10;

    private double xPos = 0;
    private double yPos = 0;

    private KeyInputHandler keyInputHandler = new KeyInputHandler();

    public DreiDimension(ApplicationType applicationType, String name) {
        super(applicationType, name);
        setNeedBufferedImage(true);
        setOptionFrame(new OptionFrame(this));
        addOptions();
        this.addKeyListener(keyInputHandler);
    }

    @Override
    public void actionToPerfom() {
        checkOptions();
        drawPixel();
        handleKeyInput();
    }

    @Override
    public void render(Graphics graphics) {
    }

    double time = 0.0;

    @Override
    public void drawPixel() {
        BufferStrategy bufferStrategy = this.getCanvas().getBufferStrategy();
        if(bufferStrategy == null) {
            this.getCanvas().createBufferStrategy(3);
            return;
        }

        int height = Alles.HEIGHT;
        int width = Alles.WIDTH;

        for(int y = 0; y < height; y++) {
            double ceiling = (y - height / 2.0) / (height);

            if(ceiling < 0) {
                ceiling = -ceiling;
            }

            double z = 5 / ceiling;
            time += 0.0005;

            for(int x = 0; x < width; x++) {
                double depth = (x - width / 2.0) / (width);
                depth *= z;

                double yy = z + yPos;
                double xx = depth + xPos;
                int xPix = (int)xx;
                int yPix = (int)yy;
                    setPixels(x + y * width, ((xPix & 15) * (int) colorValue) | ((yPix & 15) * (int) colorValue) << 8);
                }
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.drawImage(this.getBufferedImage(), 0,0,Alles.WIDTH,Alles.WIDTH, null);
        graphics.dispose();
        bufferStrategy.show();
    }

    @Override
    public void restoreDefault() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "999": Alles.menuManager.switchToLastMenu();break;
            case "option": this.getOptionFrame().setVisible(true);break;
        }
    }

    @Override
    public void run() {

            doLoop();

    }

    private void handleKeyInput() {
        int crKey = keyInputHandler.getCurrentKey();
        if (keyInputHandler.getPressedKeys().size() != 0) {
            if (crKey == 37) {
                xPos -= 1;
            }
            if (crKey == 39) {
               xPos += 1;
            }
            if (crKey == 38) {
                yPos += 1.05;
            }
            if (crKey == 40) {
                yPos -= 1.05;
            }
        }
    }

        @Override
        public void checkOptions () {
            try {
                xDepthValue = Double.valueOf(xDepth.getText());
                zValue = Double.valueOf(z.getText());
                colorValue = Double.valueOf(colorField.getText());
                yDepthD = Double.valueOf(yDepth.getText());
                xxValue = Double.valueOf(xxField.getText());

            } catch (NumberFormatException e) {
                System.out.println("Exception");
            }
        }

    @Override
    public void updateNote() {

    }

    @Override
    public void addOptions() {
        this.addOption(xDepth, "X Tiefe", String.valueOf(xDepthValue));
        this.addOption(z, "Z", String.valueOf(zValue));
        this.addOption(yDepth, "Y Tiefe", String.valueOf(yDepthD));
        this.addOption(colorField, "Farbe", String.valueOf(colorValue));
        this.addOption(xxField, "XX Wert", String.valueOf(xxValue));
    }
}
