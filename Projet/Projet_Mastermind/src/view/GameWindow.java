package view;

import controller.PartieController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;


public class GameWindow extends JFrame implements ButtonObserveur {
    private final PartieController controller;
    private int LigneActuelle = 0;
    private JPanel mainPanel;
    private ArrayList<JButton> buttons;
    private ArrayList<JButton> ListeIndice;
    private ArrayList<JPanel> lignePanels; // Nouvelle variable pour stocker les références aux lignes
    private ArrayList<ArrayList<JButton>> ligneButtons; // Nouvelle variable pour stocker les références aux boutons dans chaque ligne


    public GameWindow(PartieController pc) {
        this.controller = pc;
        buttons = new ArrayList<>(controller.getNbPionsCombi());
        ListeIndice = new ArrayList<>(controller.getNbPionsCombi());
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Fenêtre de jeu");
        if (this.controller.getNbTentative() == 10)
            setSize(1050, 750);
        else if (this.controller.getNbTentative() == 11)
            setSize(1050, 790);
        else if (this.controller.getNbTentative() == 12)
            setSize(1050, 840);
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
                if (!controller.hasWon() && LigneActuelle < controller.getNbTentative() -1) {
                    // Rend la prochaine ligne visible
                    lignePanels.get(LigneActuelle + 1).setVisible(true);

                    // Bloque les boutons des lignes précédentes
                    for (int i = 0; i <= LigneActuelle; i++) {
                        for (JButton button : ligneButtons.get(i)) {
                            button.setEnabled(false);
                        }
                    }
                    updateIndiceButtons(controller.getAfficheIndice());
                    LigneActuelle++;
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < controller.getNbPionsCombi(); i++) {
                    int line = controller.getNbPionsCombi() * LigneActuelle;
                    updateButtons(ligneButtons.get(LigneActuelle).get(i + line), Color.LIGHT_GRAY);
                }
            }
        });

        nextRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.getManche() < controller.getNbManche())
                {
                    controller.addManche();
                    GameWindow gameWindow = new GameWindow(controller);
                    gameWindow.setVisible(true);
                    dispose();
                }
            }
        });

        changeDisplayModeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller.getStrategy() instanceof AffichageClassique)
                {
                    controller.changeStrategy(new AffichageFacile());
                }
                else if(controller.getStrategy() instanceof AffichageFacile)
                {
                    controller.changeStrategy(new AffichageNumerique());
                }
                else
                {
                    controller.changeStrategy(new AffichageClassique());
                }
            }
        });

        bottomButtonPanel.add(validateButton);
        bottomButtonPanel.add(resetButton);
        bottomButtonPanel.add(nextRoundButton);
        bottomButtonPanel.add(changeDisplayModeButton);

        this.mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        getContentPane().add(this.mainPanel);

        System.out.println("Manche : " + this.controller.getManche());
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


            JPanel IndicePanel = new JPanel(new GridLayout(1, nbPionsCombi, 2, 0));
            JButton[] indice = createIndiceButtons();
            for (JButton button : indice)
            {
                IndicePanel.add(button);
                this.ListeIndice.add(button);
            }
            buttonPanel.add(IndicePanel);

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
            button.setPreferredSize(new Dimension(30, 30));
            button.setBackground(Color.WHITE);
            button.setEnabled(false);
            button.setLayout(new GridBagLayout());
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.CENTER);
            buttons[i] = button;
        }

        return buttons;
    }

    public void updateButtons(JButton button, Color color) {
        button.setBackground(color);
        button.repaint();
    }

    public void updateIndiceButtons(ArrayList<String> indices) {
        for (int i = 0; i < this.controller.getNbPionsCombi(); i++) {
            String indice = indices.get(i);
            System.out.println(indice);
            if (!(this.controller.getStrategy() instanceof AffichageNumerique)) {
                if (Objects.equals(indice, "noir")) {
                    this.ListeIndice.get(i + this.controller.getNbPionsCombi() * this.LigneActuelle).setBackground(Color.BLACK);
                } else if (Objects.equals(indice, "blanc")) {
                    this.ListeIndice.get(i + this.controller.getNbPionsCombi() * this.LigneActuelle).setBackground(Color.WHITE);
                } else if (Objects.equals(indice, "gris")) {
                    this.ListeIndice.get(i + this.controller.getNbPionsCombi() * this.LigneActuelle).setBackground(Color.GRAY);
                }
            }
            else {
                this.ListeIndice.get(i + this.controller.getNbPionsCombi() * this.LigneActuelle).setText(indices.get(i));
                }
            }
    }

    public Color[] getValidationTableau(ArrayList<JButton> ligneButtons) {
        Color[] tableau = new Color[this.controller.getNbPionsCombi()];
        for (int i = 0; i < this.controller.getNbPionsCombi(); i++) {
            tableau[i] = ligneButtons.get(i + this.controller.getNbPionsCombi() * this.LigneActuelle).getBackground();
        }
        return tableau;
    }
}