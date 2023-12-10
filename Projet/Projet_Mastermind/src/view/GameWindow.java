package view;

import model.Couleur;
import model.Indice;
import model.Observeur;
import model.Partie;

import javax.swing.*;
public class GameWindow extends JFrame implements Observeur {

    public GameWindow(Partie p) {
        setTitle("Fenêtre de jeu");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        p.play();
    }

    @Override
    public void updateIndice(Indice indice) {

    }
}