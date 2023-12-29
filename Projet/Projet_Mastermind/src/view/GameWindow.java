package view;

import controller.PartieController;
import model.ButtonObserveur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GameWindow extends JFrame implements ButtonObserveur {
    private PartieController controller;
    private JButton[] buttons = new JButton[4];
    private int currentColorIndex1 = 0;
    private int currentColorIndex2 = 0;
    private int currentColorIndex3 = 0;
    private int currentColorIndex4 = 0;

    public GameWindow(PartieController pc) {
        this.controller = pc;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Fenêtre de jeu");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        buttons[0] = createColorButton();
        buttons[1] = createColorButton();
        buttons[2] = createColorButton();
        buttons[3] = createColorButton();

        panel.add(buttons[0]);
        panel.add(buttons[1]);
        panel.add(buttons[2]);
        panel.add(buttons[3]);

        JButton validateButton = new JButton("Valider");
        JButton resetButton = new JButton("Réinitialiser");
        JButton nextRoundButton = new JButton("Manche suivante");
        JButton changeDisplayModeButton = new JButton("Changer mode affichage");

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color[] validate = getValidationTableau();
                // Appel de la méthode pour traiter la validation dans le contrôleur
                // (vous devrez créer cette méthode dans PartieController)
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

        // Ajout des boutons d'actions au panneau
        panel.add(validateButton);
        panel.add(resetButton);
        panel.add(nextRoundButton);
        panel.add(changeDisplayModeButton);

        getContentPane().add(panel, BorderLayout.SOUTH);
    }

    private JButton createColorButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                Color currentColor;
                int currentColorIndex;

                if (clickedButton == buttons[0]) {
                    currentColorIndex = currentColorIndex1;
                    currentColor = controller.getPossibleColor()[currentColorIndex];
                    currentColorIndex1 = (currentColorIndex1 + 1) % controller.getPossibleColor().length;
                } else if (clickedButton == buttons[1]) {
                    currentColorIndex = currentColorIndex2;
                    currentColor = controller.getPossibleColor()[currentColorIndex];
                    currentColorIndex2 = (currentColorIndex2 + 1) % controller.getPossibleColor().length;
                } else if (clickedButton == buttons[2]) {
                    currentColorIndex = currentColorIndex3;
                    currentColor = controller.getPossibleColor()[currentColorIndex];
                    currentColorIndex3 = (currentColorIndex3 + 1) % controller.getPossibleColor().length;
                } else if (clickedButton == buttons[3]) {
                    currentColorIndex = currentColorIndex4;
                    currentColor = controller.getPossibleColor()[currentColorIndex];
                    currentColorIndex4 = (currentColorIndex4 + 1) % controller.getPossibleColor().length;
                } else {
                    return;
                }

                updateButtons(clickedButton, currentColor);
            }
        });

        return button;
    }

    public void updateButtons(JButton button, Color color) {
        button.setBackground(color);
        button.repaint();
    }

    public Color[] getValidationTableau() {
        Color[] tableau = new Color[4];
        for (int i = 0; i < 4; i++) {
            tableau[i] = buttons[i].getBackground();
        }
        return tableau;
    }

}
