package view;

import controller.MancheController;
import controller.PartieController;
import model.ButtonObserveur;
import model.Manche;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameWindow extends JFrame implements ButtonObserveur {
    private final PartieController controller;
    private int currentLineHeight = 0;
    private int initLineCount = 0;
    private JPanel mainPanel;
    private final JButton[] buttons = new JButton[4];

    public GameWindow(PartieController pc) {
        this.controller = pc;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Fenêtre de jeu");
        if (controller.getNbTentative() == 10)
            setSize(600, 750);
        else if (controller.getNbTentative() == 11)
            setSize(600, 790);
        else if (controller.getNbTentative() == 12)
            setSize(600, 840);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        initLine();
        JPanel bottomButtonPanel = new JPanel(new GridLayout(4, 1, 0, 10));

        JButton validateButton = new JButton("Valider");
        JButton resetButton = new JButton("Réinitialiser");
        JButton nextRoundButton = new JButton("Manche suivante");
        JButton changeDisplayModeButton = new JButton("Changer mode affichage");

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color[] validate = getValidationTableau();
                controller.testCombinaison(validate);
                if (!controller.hasWon() && controller.getNbTentative() > initLineCount) {
                    currentLineHeight += 50;
                    initLine();
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour réinitialiser la combinaison
                // Appelez la méthode correspondante dans votre modèle (partie)
                // Vous pouvez également mettre à jour l'interface en conséquence
            }
        });

        nextRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour passer à la manche suivante
                // Appelez la méthode correspondante dans votre modèle (partie)
                // Vous pouvez également mettre à jour l'interface en conséquence
            }
        });

        changeDisplayModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour changer le mode d'affichage des indices
                // Appelez la méthode correspondante dans votre modèle (partie)
                // Vous pouvez également mettre à jour l'interface en conséquence
            }
        });

        bottomButtonPanel.add(validateButton);
        bottomButtonPanel.add(resetButton);
        bottomButtonPanel.add(nextRoundButton);
        bottomButtonPanel.add(changeDisplayModeButton);

        this.mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);

        getContentPane().add(this.mainPanel);
    }

    private void initLine()
    {
        if (initLineCount !=0)
            for (JButton button : buttons)
                button.setEnabled(false);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 0));

        buttons[0] = createColorButton(1);
        buttons[1] = createColorButton(2);
        buttons[2] = createColorButton(3);
        buttons[3] = createColorButton(4);

        JPanel test = new JPanel(new GridLayout(1, 4, 0, 0));
        JButton[] indice = createIndiceButtons();
        for (JButton button : indice)
            test.add(button);
        buttonPanel.add(buttons[0]);
        buttonPanel.add(buttons[1]);
        buttonPanel.add(buttons[2]);
        buttonPanel.add(buttons[3]);
        buttonPanel.add(test);

        for (JButton button : buttons)
            button.setPreferredSize(new Dimension(50, 40));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(currentLineHeight, 0, 0, 0));

        this.mainPanel.add(buttonPanel, BorderLayout.NORTH);
        initLineCount+=1;
    }


    private JButton createColorButton(int buttonIndex) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));
        button.putClientProperty("currentColorIndex", 0);
        button.putClientProperty("buttonIndex", buttonIndex);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                int currentColorIndex = (int) clickedButton.getClientProperty("currentColorIndex");
                int buttonIndex = (int) clickedButton.getClientProperty("buttonIndex");
                controller.getNextColor(clickedButton, currentColorIndex, buttonIndex);
            }
        });

        return button;
    }
    private JButton[] createIndiceButtons() {
        JButton[] buttons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            JButton button = new JButton();
            button.setBackground(Color.WHITE);
            button.setEnabled(false);
            buttons[i] = button;
        }

        return buttons;
    }

    public void updateButtons(JButton button, Color color) {
        button.setBackground(color);
        button.repaint();
    }

    public Color[] getValidationTableau() {
        Color[] tableau = new Color[4];
        for (int i = 0; i < 4; i++)
            tableau[i] = buttons[i].getBackground();
        return tableau;
    }
}
