package view;

import model.Couleur;
import model.Indice;
import model.Observeur;

import javax.swing.*;
public class EndWindow extends JFrame implements Observeur {

    public EndWindow() {
        setTitle("Fenêtre de jeu");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void updateColor(Couleur c) {

    }

    @Override
    public void updateIndice(Indice indice) {

    }
}