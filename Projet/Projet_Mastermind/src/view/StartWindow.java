package view;

import controller.PartieController;
import model.Partie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame
{

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String pseudo;

    public StartWindow() {
        initializeUI();
    }

    private void initializeUI()
    {
        setTitle("Mastermind - Game Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel firstCardPanel = createFirstCardPanel();
        cardPanel.add(firstCardPanel, "first");

        JPanel secondCardPanel = createSecondCardPanel();
        cardPanel.add(secondCardPanel, "second");

        add(cardPanel);

        setVisible(true);
    }

    private JPanel createFirstCardPanel()
    {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Master Mind Game");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel pseudonymPanel = new JPanel(new FlowLayout());
        pseudonymPanel.add(new JLabel("Indiquez votre pseudonyme:"));
        JTextField playerNameTextField = new JTextField(15);
        pseudonymPanel.add(playerNameTextField);
        JButton nextButton = new JButton("Suivant");
        pseudonymPanel.add(nextButton);
        panel.add(pseudonymPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pseudo = playerNameTextField.getText();

                cardLayout.show(cardPanel, "second");
            }
        });

        return panel;
    }

    private JPanel createSecondCardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel sliderPanel = new JPanel(new GridLayout(5, 1));
        JSlider roundsSlider;
        sliderPanel.add(createSliderPanel("Indiquez le nombre de couleurs:", roundsSlider = createSlider(4, 8, 8)));
        JSlider roundsPerMatchSlider;
        sliderPanel.add(createSliderPanel("Indiquez le nombre de manches:", roundsPerMatchSlider = createSlider(1, 5, 3)));
        JSlider attemptsSlider;
        sliderPanel.add(createSliderPanel("Indiquez le nombre de tentatives:", attemptsSlider = createSlider(10, 12, 10)));
        JSlider pinsPerCombinationSlider;
        sliderPanel.add(createSliderPanel("Indiquez le nombre de pions de combinaisons:", pinsPerCombinationSlider = createSlider(4, 6, 4)));

        panel.add(sliderPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Revenir en arrière");
        JButton startGameButton = new JButton("Lancer la partie");

        buttonsPanel.add(backButton);
        buttonsPanel.add(startGameButton);

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        startGameButton.addActionListener(ActionEvent ->
        {
            PartieController pc = new PartieController();
            pc.addNickName(pseudo);
            pc.addCurentPions(roundsSlider.getValue());
            pc.addCurentManche(roundsPerMatchSlider.getValue());
            pc.addCurrentTentative(attemptsSlider.getValue());
            pc.addCurentPionsCombi(pinsPerCombinationSlider.getValue());
            pc.addCouleurPossible();
            pc.initializeManche();
            pc.affichage();

            GameWindow gameWindow = new GameWindow(pc);
            gameWindow.setVisible(true);
            dispose();
        });

        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "first");
            }
        });

        return panel;
    }

    private JPanel createSliderPanel(String label, JSlider slider)
    {
        JPanel sliderPanel = new JPanel(new BorderLayout());
        JLabel labelComponent = new JLabel(label);
        labelComponent.setHorizontalAlignment(JLabel.RIGHT);
        sliderPanel.add(labelComponent, BorderLayout.WEST);
        sliderPanel.add(slider, BorderLayout.CENTER);
        return sliderPanel;
    }

    private JSlider createSlider(int min, int max, int initial)
    {
        JSlider slider = new JSlider(min, max, initial);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }
}
