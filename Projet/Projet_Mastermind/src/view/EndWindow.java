package view;

import controller.PartieController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EndWindow extends JFrame {

    // Fenêtre de fin de jeu
    public EndWindow(PartieController controller)
    {
        setTitle("MasterMind - Fin de partie");
        setSize(400, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel resultLabel = new JLabel(controller.hasWon() ? "Bravo, vous avez gagné !" : "Dommage, vous avez perdu...");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(resultLabel);

        mainPanel.add(Box.createVerticalStrut(20));

        JLabel scoreLabel = new JLabel("Score : " + controller.getScore());
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(scoreLabel);

        if (!controller.hasWon())
        {
            JPanel colorsPanel = new JPanel();
            colorsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            for (Color color : controller.getCombinaisonSecrete())
            {
                JButton colorButton = new JButton();
                colorButton.setBackground(color);
                colorButton.setPreferredSize(new Dimension(50, 50));
                colorButton.setEnabled(false);
                colorsPanel.add(colorButton);
            }

            mainPanel.add(colorsPanel);
            mainPanel.add(Box.createVerticalStrut(10));

            JLabel mancheLabel = new JLabel("Manche : " + controller.getManche() + "/" + controller.getNbManche());
            mancheLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(mancheLabel);

            JButton nextRoundButton = new JButton("Prochaine manche");

            if (controller.getManche() < controller.getNbManche())
            {
                nextRoundButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        controller.addManche();
                        GameWindow gameWindow = new GameWindow(controller);
                        controller.initializeManche();
                        gameWindow.setVisible(true);
                        dispose();
                    }
                });
                nextRoundButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                mainPanel.add(nextRoundButton);
            }
            JButton restartButton = new JButton("Recommencer");

            restartButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    StartWindow st = new StartWindow();
                    st.setVisible(true);
                    dispose();
                }
            });
            restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(restartButton);
        }
        else
        {
            mainPanel.add(Box.createVerticalGlue());
            JButton newGameButton = new JButton("Nouvelle partie");
            newGameButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    StartWindow st = new StartWindow();
                    st.setVisible(true);
                    dispose();
                }
            });
            newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(newGameButton);
        }

        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
