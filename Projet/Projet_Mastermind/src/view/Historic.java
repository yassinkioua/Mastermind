package view;

import model.Couleur;
import model.Indice;
import model.Observeur;

import javax.swing.*;
public class Historic extends JFrame implements Observeur {

    public Historic() {
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