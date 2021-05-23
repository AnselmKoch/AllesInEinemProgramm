package me.anselm.alles.application.options;

import me.anselm.alles.Alles;
import me.anselm.alles.application.Application;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OptionFrame extends JFrame {

    private ArrayList<JComponent> optionList = new ArrayList<>();
    private JPanel optionPanel = new JPanel();
    private JPanel notePanel = new JPanel();
    private JTextArea noteTextArea;

    public OptionFrame(Application application) {
        this.setTitle(application.getName() + " Optionen");
        this.setSize(Alles.WIDTH / 3, 100);
        this.setLocationRelativeTo(application);
        this.setResizable(false);
    }

    private void updateFrameSize() {
        if(this.notePanel != null) {
            this.setLayout(new GridLayout(2,1));
        }else{
            this.setLayout(new GridLayout(1,1));
        }
        this.optionPanel.setLayout(new GridLayout(optionList.size(), 1));
        int y = 150;
        for(JComponent jComponent : optionList) {
            this.setSize(new Dimension(Alles.WIDTH / 3, y));
            y+=50;
        }
        this.add(optionPanel);
    }

    public void addNoteTextArea() {
        if(this.noteTextArea == null) {
            this.noteTextArea = new JTextArea();
            this.notePanel.add(noteTextArea);
            this.noteTextArea.setEditable(false);
            this.notePanel.setVisible(true);
            this.notePanel.setLayout(new FlowLayout());
            this.noteTextArea.setText("Notizen...");
            this.add(notePanel);
        }
    }
    public void addOption(JComponent jComponent, String name) {
        JLabel jLabel = new JLabel(name);
        jLabel.setLabelFor(jComponent);
        this.optionList.add(jComponent);
        this.optionPanel.add(jLabel);
        this.optionPanel.add(jComponent);
        updateFrameSize();
    }
    public void addOption(JTextField jTextField, String name, String defaultText) {
        JLabel jLabel = new JLabel(name);
        jLabel.setLabelFor(jTextField);
        jTextField.setText(defaultText);
        this.optionList.add(jTextField);
        this.optionPanel.add(jLabel);
        this.optionPanel.add(jTextField);
        updateFrameSize();
    }

    public JTextArea getNoteTextArea() {
        return this.noteTextArea;
    }
}
