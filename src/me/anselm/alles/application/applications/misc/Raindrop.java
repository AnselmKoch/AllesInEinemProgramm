package me.anselm.alles.application.applications.misc;

import me.anselm.alles.Alles;
import me.anselm.alles.application.Application;
import me.anselm.alles.application.ApplicationType;
import me.anselm.alles.application.applications.misc.raindrop.Drop;
import me.anselm.alles.application.options.IOption;
import me.anselm.alles.application.options.OptionFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Random;

public class Raindrop extends Application implements IOption {

    float arraySize = 500;
    float waterRemoveRelativeToDrops;
    private Drop[] raindrops = new Drop[(int)arraySize];
    private float waterHeight = 0;
    private Color rainDropColor = Color.BLUE;
    private float velocity = 0.25F;
    private JTextField dropAmount;
    private JTextField color;
    private JTextField fallSpeed;

    private boolean isColor = true;
    private boolean isNumber = true;
    private boolean isCorrectInput = true;

    public Raindrop(ApplicationType applicationType, String name) {
        super(applicationType, name);
        this.setOptionFrame(new OptionFrame(this));
        this.addOptions();
    }

    @Override
    public void actionToPerfom() {
        checkOptions();
        for(int i = 0; i < arraySize; i++) {
            Random random = new Random();
            if((random.nextInt(150) == 1) && (raindrops[i] == null)) {
                random = new Random();
                Drop drop = new Drop(random.nextInt(Alles.WIDTH), rainDropColor);
                raindrops[i] = drop;
            }
        }
        if(waterHeight > 0) {
            waterRemoveRelativeToDrops = arraySize / 20000;
            float waterToRemove = waterRemoveRelativeToDrops * waterHeight;
            waterHeight -= waterToRemove;
        }
    }

    @Override
    public void render(Graphics graphics) {
        updateNote();
            for(int i = 0; i < arraySize; i++) {
                if (raindrops[i] != null) {
                    Drop drop = raindrops[i];
                    if(drop.getY() < Alles.HEIGHT) {
                        graphics.setColor(rainDropColor);
                        graphics.fillRect((int)drop.getX(), (int)drop.getY(), 4, 10);
                        graphics.fillRect(0,Alles.HEIGHT - (int) waterHeight, Alles.WIDTH, Alles.HEIGHT);
                        float tempY = drop.getY();
                        drop.setY(tempY += velocity);
                    }else{
                            waterHeight += 0.75F;
                            raindrops[i] = null;
                    }
                }
            }
    }

    @Override
    public void drawPixel() {

    }

    @Override
    public void restoreDefault() {
        waterHeight = 0;
        for(int i = 0; i < arraySize; i++) {
            if(raindrops[i] != null) {
                raindrops[i] = null;
            }
        }
    }


    @Override
    public void updateNote() {
        if(!isColor) {
            setNote("Die Farbe ist nicht gültig");
        }else if(!isNumber) {
            setNote("Keine richtige Zahl");
        }else if(!isCorrectInput) {
            setNote("Der eingebene Input ist \n A kleiner als null \n oder \n B größer als 50000");
        }else{
            setNote("");
        }
    }

    @Override
    public void addOptions() {
        this.dropAmount = new JTextField();
        this.color = new JTextField();
        this.fallSpeed = new JTextField();
        this.dropAmount.setText(String.valueOf((int)arraySize));
        this.addOption(fallSpeed, "Fallgeschw.", String.valueOf(velocity));
        this.addOption(color, "Farbe", "blue");
        this.addOption(dropAmount,"Anzahl Regentropfen", String.valueOf((int)arraySize));
        this.getOptionFrame().addNoteTextArea();
    }

    @Override
    public void checkOptions() {
        String colorByUser = color.getText().toLowerCase(Locale.ROOT);
        try{
            Field field = Class.forName("java.awt.Color").getField(colorByUser);
            rainDropColor = (Color)field.get(colorByUser);
            color.setBackground(Color.WHITE);
            isColor = true;
        }catch (Exception e) {
            isColor = false;
            color.setBackground(Color.RED);

        }

        try {
            float velocityUser = Float.valueOf(this.fallSpeed.getText());
            this.velocity = velocityUser;
            int amountByUser = Integer.valueOf(this.dropAmount.getText());
            isNumber=true;
            if(amountByUser != arraySize) {
                if (amountByUser > 0){
                    isCorrectInput = true;
                    this.dropAmount.setBackground(Color.WHITE);
                    arraySize = Float.valueOf(dropAmount.getText());
                    raindrops = new Drop[(int) arraySize];
                } else {
                    isCorrectInput = false;
                    this.dropAmount.setBackground(Color.RED);
                }
            }
        }catch (NumberFormatException exception) {
            this.dropAmount.setBackground(Color.RED);
            isNumber = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "999": Alles.menuManager.switchToLastMenu();break;
            case "option" : this.getOptionFrame().setVisible(true);break;
        }
    }

    @Override
    public void run() {

        this.doLoop();

    }
}
