package controller;


import model.IndiceStrategy;
import model.Manche;
import model.Partie;
import view.GameWindow;

import javax.swing.*;
import java.awt.*;


public class PartieController
{
    private Partie partie;
    private MancheController mancheController;
    private GameWindow mainWindows;
    public PartieController()
    {
        partie = new Partie();
        mainWindows = new GameWindow(this);
        partie.addObserveurs(mainWindows);
        mancheController = new MancheController();
    }
    public void getStrategy(IndiceStrategy modestrategy) { partie.change_strategy(modestrategy);}
    public void addCurentPions(int nbPions){ partie.setNbpions(nbPions);}
    public void addCurrentTentative(int nbTentative){ partie.setNbTentatives(nbTentative);}
    public void addCurentPionsCombi(int nbPionsCombi){ partie.setNbpions_combi(nbPionsCombi);}
    public void addCurentManche(int m) { partie.setNbManches(m);}
    public void addCouleurPossible() { partie.setCouleurPossible();}
    public void affichage() { partie.Affichage();}
    public void getNextColor(JButton button, int currentColorIndex, int buttonIndex) {partie.NextColor(button, currentColorIndex, buttonIndex);}
    public int getNbTentative() { return partie.nb_tentative;}
    public void initializeManche() {
        mancheController.initializeManche(partie.getNbpions_combi(), partie.getNbTentatives(), partie.getCouleurPossible());
    }
    public void testCombinaison(Color[] validate)
    {
        mancheController.check_color(validate);
    }
    public boolean hasWon() { return mancheController.hasWon(); }
}
