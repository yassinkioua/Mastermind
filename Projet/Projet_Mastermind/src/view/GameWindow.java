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

import static utils.ColorSwap.getColorName;

public class GameWindow extends JFrame implements ButtonObserveur {
    private final PartieController controller;
    private int LigneActuelle = 0;
    private JPanel mainPanel;
    private ArrayList<JButton> buttons;
    private ArrayList<JPanel> lignePanels; // Nouvelle variable pour stocker les références aux lignes
    private ArrayList<ArrayList<JButton>> ligneButtons; // Nouvelle variable pour stocker les références aux boutons dans chaque ligne

    public GameWindow(PartieController pc) {
        this.controller = pc;
        buttons = new ArrayList<>(controller.getNbPionsCombi());
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Fenêtre de jeu");
        if (this.controller.getNbTentative() == 10)
            setSize(600, 750);
        else if (this.controller.getNbTentative() == 11)
            setSize(600, 790);
        else if (this.controller.getNbTentative() == 12)
            setSize(600, 840);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.lignePanels = new ArrayList<>();
        this.ligneButtons = new ArrayList<>();

        this.mainPanel = new JPanel(new BorderLayout());
        this.mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel bottomButtonPanel = new JPanel(new GridLayout(4, 1, 0, 10));

        CreateLine();

        JButton validateButton = new JButton("Valider");
        JButton resetButton = new JButton("Réinitialiser");
        JButton nextRoundButton = new JButton("Manche suivante");
        JButton changeDisplayModeButton = new JButton("Changer mode affichage");

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color[] validate = getValidationTableau(ligneButtons.get(LigneActuelle));
                controller.testCombinaison(validate);
                if (!controller.hasWon() && LigneActuelle < controller.getNbTentative() - 1) {

                    // Rend la prochaine ligne visible
                    lignePanels.get(LigneActuelle + 1).setVisible(true);

                    // Bloque les boutons des lignes précédentes
                    for (int i = 0; i <= LigneActuelle; i++) {
                        for (JButton button : ligneButtons.get(i)) {
                            button.setEnabled(false);
                        }
                    }
                    LigneActuelle++;
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for (JButton button : buttons)
                    updateButtons(button, Color.LIGHT_GRAY);
            }
        });

        nextRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.initializeManche();
                LigneActuelle = 0;
                mainPanel.removeAll();
                initializeUI();
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        changeDisplayModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        bottomButtonPanel.add(validateButton);
        bottomButtonPanel.add(resetButton);
        bottomButtonPanel.add(nextRoundButton);
        bottomButtonPanel.add(changeDisplayModeButton);

        this.mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        getContentPane().add(this.mainPanel);
    }

    private void CreateLine() {
        int nbPionsCombi = this.controller.getNbPionsCombi();
        int nbTentative = this.controller.getNbTentative();

        JPanel PanelJeu = new JPanel(new GridLayout(nbTentative,1, 10, 10));

        for (int i = 0; i < nbTentative; i++)
        {
            JPanel buttonPanel = new JPanel(new GridLayout(1, nbPionsCombi + 1, 10, 10));


            for (int j = 1; j <= nbPionsCombi; j++) {
                JButton button = createColorButton(j);
                this.buttons.add(button);
                buttonPanel.add(button);
            }


            JPanel test = new JPanel(new GridLayout(1, nbPionsCombi, 0, 0));
            JButton[] indice = createIndiceButtons();
            for (JButton button : indice)
                test.add(button);
            buttonPanel.add(test);

            for (JButton button : this.buttons) {
                button.setPreferredSize(new Dimension(50, 40));
            }
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

            this.lignePanels.add(buttonPanel); // Ajoute la référence du JPanel à la liste
            this.ligneButtons.add(new ArrayList<>(this.buttons)); // Ajoute la référence des boutons à la liste

            PanelJeu.add(buttonPanel);
            if (i == 0)
            {
                buttonPanel.setVisible(true);
            }
            else
            {
                buttonPanel.setVisible(false);
            }
        }
        this.mainPanel.add(PanelJeu, BorderLayout.NORTH);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
    }

    private JButton createColorButton(int buttonIndex) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));
        button.setBackground(Color.LIGHT_GRAY);
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
        JButton[] buttons = new JButton[this.controller.getNbPionsCombi()];

        for (int i = 0; i < this.controller.getNbPionsCombi(); i++) {
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

    public Color[] getValidationTableau(ArrayList<JButton> ligneButtons) {

        System.out.println(ligneButtons.size());
        Color[] tableau = new Color[controller.getNbPionsCombi()];
        for (int i = 0; i < controller.getNbPionsCombi(); i++) {
            tableau[i] = ligneButtons.get(i + controller.getNbPionsCombi() * LigneActuelle).getBackground();
            System.out.println("Tableau de i : " + getColorName(tableau[i]));
        }
        return tableau;
    }
}