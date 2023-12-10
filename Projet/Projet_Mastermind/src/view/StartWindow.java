package view;

import controller.GameController;
import model.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StartWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField playerNameTextField;
    private JButton nextButton;

    private JSlider roundsSlider;
    private JSlider roundsPerMatchSlider;
    private JSlider attemptsSlider;
    private JSlider pinsPerCombinationSlider;
    private JButton backButton;
    private JButton startGameButton;

    public StartWindow() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Mastermind - Game Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel firstCardPanel = createFirstCardPanel();
        cardPanel.add(firstCardPanel, "first");

        JPanel secondCardPanel = createSecondCardPanel();
        cardPanel.add(secondCardPanel, "second");

        add(cardPanel);

        setVisible(true);
    }

    private JPanel createFirstCardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Master Mind Game");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JLabel rulesLabel = new JLabel("Les règles sont disponibles ici : https://fr.wikihow.com/jouer-au-Mastermind");
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel pseudonymPanel = new JPanel(new FlowLayout());
        pseudonymPanel.add(new JLabel("Indiquez votre pseudonyme:"));
        playerNameTextField = new JTextField(15);
        pseudonymPanel.add(playerNameTextField);
        nextButton = new JButton("Suivant");
        pseudonymPanel.add(nextButton);
        panel.add(pseudonymPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "second");
            }
        });

        return panel;
    }

    private JPanel createSecondCardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel sliderPanel = new JPanel(new GridLayout(5, 1));
        sliderPanel.add(createSliderPanel("Indiquez le nombre de couleurs:", roundsSlider = createSlider(4, 8, 8)));
        sliderPanel.add(createSliderPanel("Indiquez le nombre de manches:", roundsPerMatchSlider = createSlider(1, 5, 3)));
        sliderPanel.add(createSliderPanel("Indiquez le nombre de tentatives:", attemptsSlider = createSlider(10, 12, 10)));
        sliderPanel.add(createSliderPanel("Indiquez le nombre de pions de combinaisons:", pinsPerCombinationSlider = createSlider(4, 6, 4)));

        // Ajout du panneau des sliders au centre
        panel.add(sliderPanel, BorderLayout.CENTER);

        // Ajout des boutons en bas (au sud)
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("Revenir en arrière");
        startGameButton = new JButton("Lancer la partie");

        buttonsPanel.add(backButton);
        buttonsPanel.add(startGameButton);

        // Ajout du panneau des boutons en bas (au sud)
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        startGameButton.addActionListener(ActionEvent -> {
            Partie p = new Partie();
            p.setNbpions(roundsSlider.getValue());
            p.setManche(roundsPerMatchSlider.getValue());
            p.setNb_tentative(attemptsSlider.getValue());
            p.setNbpions_combi(pinsPerCombinationSlider.getValue());
            new GameWindow(p);
            System.exit(0);
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "first");
            }
        });

        return panel;
    }

    private JPanel createSliderPanel(String label, JSlider slider) {
        JPanel sliderPanel = new JPanel(new BorderLayout());
        JLabel labelComponent = new JLabel(label);
        labelComponent.setHorizontalAlignment(JLabel.RIGHT);
        sliderPanel.add(labelComponent, BorderLayout.WEST);
        sliderPanel.add(slider, BorderLayout.CENTER);

        return sliderPanel;
    }

    private JSlider createSlider(int min, int max, int initial) {
        JSlider slider = new JSlider(min, max, initial);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }
}
