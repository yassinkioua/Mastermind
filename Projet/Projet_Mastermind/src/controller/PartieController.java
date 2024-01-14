package controller;


import model.AfficheIndiceStrategy;
import model.Partie;
import view.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class PartieController
{
    private final Partie partie;
    private final MancheController mancheController;
    public PartieController()
    {
        this.partie = new Partie();
        GameWindow mainWindows = new GameWindow(this);
        this.partie.addObserveurs(mainWindows);
        this.mancheController = new MancheController();
    }
    public void changeStrategy(AfficheIndiceStrategy modestrategy) { this.partie.change_strategy(modestrategy); this.mancheController.changeStrat(modestrategy);}
    public void addCurentPions(int nbPions){ this.partie.setNbpions(nbPions);}
    public void addCurrentTentative(int nbTentative){ this.partie.setNbTentatives(nbTentative);}
    public void addCurentPionsCombi(int nbPionsCombi){ this.partie.setNbpions_combi(nbPionsCombi);}
    public void addCurentManche(int m) { this.partie.setNbManches(m);}
    public void addCouleurPossible() { this.partie.setCouleurPossible();}
    public void affichage() { this.partie.Affichage();}
    public void getNextColor(JButton button, int currentColorIndex, int buttonIndex) {this.partie.NextColor(button, currentColorIndex, buttonIndex);}
    public int getNbTentative() { return this.partie.getNbTentatives();}
    public int getNbPionsCombi() { return this.partie.getNbpions_combi(); }
    public int getNbManche() { return this.partie.getNbManche();}
    public void initializeManche() { this.mancheController.initializeManche(this.partie.getNbpions_combi(), this.partie.getNbTentatives(), this.partie.getCouleurPossible(), this.partie.getStrategy()); }
    public void testCombinaison(Color[] validate)
    {
        this.mancheController.check_color(validate);
    }
    public boolean hasWon() { return this.mancheController.hasWon(); }
    public ArrayList<String> getAfficheIndice() { return this.mancheController.getAfficheIndice();}
    public AfficheIndiceStrategy getStrategy() { return this.partie.getStrategy();}
    public int getManche() {return this.partie.getMancheActuelle();}
    public void addManche() { this.partie.addManche();}
    public void addNickName(String pseudo) { this.partie.setNickName(pseudo);}
    public String getNickName() { return this.partie.getNickName();}
    public void addScore(int score) { this.partie.setScore(score);}
    public int getScore() { return this.partie.getScore();}
    public Color[] getCombinaisonSecrete() { return this.mancheController.getCombinaisonSecrete();}
}
