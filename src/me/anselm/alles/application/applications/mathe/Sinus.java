package me.anselm.alles.application.applications.mathe;

import me.anselm.alles.Alles;
import me.anselm.alles.application.Application;
import me.anselm.alles.application.ApplicationType;
import me.anselm.alles.application.options.IOption;
import me.anselm.alles.application.options.OptionFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Sinus extends Application implements IOption {

    private double x = 0;
    private double sinusX = 0;
    private final int y = Alles.HEIGHT / 2;
    private double amplitude = 5;
    private double scaleSin = 1;
    private double periodSin = 0.1;
    private double scaleX = 1;

    private boolean arrayOutOfBound = false;
    private boolean numberEx = false;

    private JTextField amplitudeField = new JTextField();
    private JTextField scaleSinField = new JTextField();
    private JTextField periodSinField = new JTextField();
    private JTextField scaleXField = new JTextField();

    public Sinus(ApplicationType applicationType, String name) {
        super(applicationType, name);
        setOptionFrame(new OptionFrame(this));
        setNeedBufferedImage(true);
        this.addOptions();
    }

    @Override
    public void actionToPerfom() {
        checkOptions();
        try {
            if (x < Alles.WIDTH) {
                int currentY = (int) (y + (Math.sin(sinusX * scaleSin) * amplitude));
                for (int i = 0; i < 10; i++) {
                    getBufferedImage().setRGB((int)x, currentY, Color.RED.getRGB());
                    currentY++;
                }
                sinusX += periodSin;
                x += scaleX;
            } else {
                x = 0;
                for (int i = 0; i < Alles.WIDTH; i++) {
                    for (int j = 0; j < Alles.HEIGHT; j++) {
                        getBufferedImage().setRGB(i, j, Color.WHITE.getRGB());
                    }
                }
            }
            for (int i = 0; i < Alles.WIDTH; i++) {
                getBufferedImage().setRGB(i, y, Color.BLACK.getRGB());
                if (i % 10 == 0) {
                    for (int j = 0; j < 10; j++) {
                        getBufferedImage().setRGB(i, j + y, Color.BLACK.getRGB());

                    }
                }
            }
            arrayOutOfBound = false;
            amplitudeField.setBackground(Color.WHITE);
        }catch (IndexOutOfBoundsException exception) {
            amplitudeField.setBackground(Color.RED);
            arrayOutOfBound = true;
        }
    }

    @Override
    public void render(Graphics graphics) {
    }

    @Override
    public void drawPixel() {

    }

    @Override
    public void restoreDefault() {
        x = 0;
    }

    @Override
    public void updateNote() {
        if(this.arrayOutOfBound) {
            setNote("Amplitude zu groß");
        }else if(this.numberEx) {
            setNote("Keine richtige Zahl");
        }else{
            setNote("");
        }
    }

    @Override
    public void addOptions() {
        addOption(amplitudeField, "Amplitude", String.valueOf(amplitude));
        addOption(scaleSinField, "Sin Skalierung", String.valueOf(scaleSin));
        addOption(periodSinField, "Sinus Periode", String.valueOf(periodSin));
        addOption(scaleXField, "X Skalierung", String.valueOf(scaleX));
        this.getOptionFrame().addNoteTextArea();
    }

    @Override
    public void checkOptions() {
        try {
            amplitude = Double.valueOf(amplitudeField.getText());
            scaleSin = Double.valueOf(scaleSinField.getText());
            scaleX = Double.valueOf(scaleXField.getText());
            periodSin = Double.valueOf(periodSinField.getText());
            numberEx = false;
        }catch (NumberFormatException numberFormatException){
            numberEx = true;
        }
        updateNote();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       switch (e.getActionCommand()){
           case"999":  Alles.menuManager.switchToLastMenu();break;
           case"option": getOptionFrame().setVisible(true);
        }
    }

    @Override
    public void run() {

            this.doLoop();

    }
}
