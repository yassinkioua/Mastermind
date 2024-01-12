package controller;


import model.AfficheIndiceStrategy;
import model.Indice;
import model.Manche;
import model.Partie;
import view.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PartieController
{
    private Partie partie;
    private MancheController mancheController;
    private GameWindow mainWindows;
    public PartieController()
    {
        this.partie = new Partie();
        this.mainWindows = new GameWindow(this);
        this.partie.addObserveurs(this.mainWindows);
        this.mancheController = new MancheController();
    }
    public void getStrategy(AfficheIndiceStrategy modestrategy) { this.partie.change_strategy(modestrategy);}
    public void addCurentPions(int nbPions){ this.partie.setNbpions(nbPions);}
    public void addCurrentTentative(int nbTentative){ this.partie.setNbTentatives(nbTentative);}
    public void addCurentPionsCombi(int nbPionsCombi){ this.partie.setNbpions_combi(nbPionsCombi);}
    public void addCurentManche(int m) { this.partie.setNbManches(m);}
    public void addCouleurPossible() { this.partie.setCouleurPossible();}
    public void affichage() { this.partie.Affichage();}
    public void getNextColor(JButton button, int currentColorIndex, int buttonIndex) {this.partie.NextColor(button, currentColorIndex, buttonIndex);}
    public int getNbTentative() { return this.partie.nb_tentative;}
    public int getNbPionsCombi() {return this.partie.nbpions_combi; }
    public void initializeManche() {
        this.mancheController.initializeManche(this.partie.getNbpions_combi(), this.partie.getNbTentatives(), this.partie.getCouleurPossible(), this.partie.getStrategy());
    }
    public void testCombinaison(Color[] validate)
    {
        this.mancheController.check_color(validate);
    }
    public boolean hasWon() { return this.mancheController.hasWon(); }
    public ArrayList<String> getAfficheIndice() {return this.mancheController.getAfficheIndice();}

}
