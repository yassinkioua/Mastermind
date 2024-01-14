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
    // Permet de changer de stratégie d'indices
    public void changeStrategy(AfficheIndiceStrategy modestrategy) { this.partie.change_strategy(modestrategy); this.mancheController.changeStrat(modestrategy);}

    // Permet d'ajouter le nombre de pions saisi par le joueur
    public void addCurentPions(int nbPions){ this.partie.setNbpions(nbPions);}

    // Permet d'ajouter le nombre de tentatives saisi par le joueur
    public void addCurrentTentative(int nbTentative){ this.partie.setNbTentatives(nbTentative);}

    // Permet d'ajouter le nombre de pions dans la combinaison saisie par le joueur
    public void addCurentPionsCombi(int nbPionsCombi){ this.partie.setNbpions_combi(nbPionsCombi);}

    // Permet d'ajouter le nombre de manches actuel
    public void addCurentManche(int m) { this.partie.setNbManches(m);}

    // Initialise les couleurs possibles pour le joueur
    public void addCouleurPossible() { this.partie.setCouleurPossible();}

    // Affiche les détails de la partie dans la console
    public void affichage() { this.partie.Affichage();}

    // Obtient la couleur suivante pour le bouton
    public void getNextColor(JButton button, int currentColorIndex, int buttonIndex) {this.partie.NextColor(button, currentColorIndex, buttonIndex);}

    // Récupère le nombre de tentatives maximum
    public int getNbTentative() { return this.partie.getNbTentatives();}

    // Obtient le nombre de pions dans la combinaison
    public int getNbPionsCombi() { return this.partie.getNbpions_combi(); }

    // Obtient le nombre de manches
    public int getNbManche() { return this.partie.getNbManche();}

    // Initialise une nouvelle manche avec les paramètres appropriés
    public void initializeManche() { this.mancheController.initializeManche(this.partie.getNbpions_combi(), this.partie.getNbTentatives(), this.partie.getCouleurPossible(), this.partie.getStrategy()); }

    // Teste la combinaison fournie par le joueur
    public void testCombinaison(Color[] validate) { this.mancheController.check_color(validate);}

    // Vérifie si le joueur à gagner la manche
    public boolean hasWon() { return this.mancheController.hasWon(); }

    // Obtenir les indices à afficher
    public ArrayList<String> getAfficheIndice() { return this.mancheController.getAfficheIndice();}

    // Obtenir la stratégie d'affichage des indices
    public AfficheIndiceStrategy getStrategy() { return this.partie.getStrategy();}

    // Obtenir le numéro de la manche actuelle
    public int getManche() {return this.partie.getMancheActuelle();}

    // Ajoute une nouvelle manche
    public void addManche() { this.partie.addManche();}

    // Ajoute le pseudo du joueur
    public void addNickName(String pseudo) { this.partie.setNickName(pseudo);}

    // Obtenir le pseudo du joueur
    public String getNickName() { return this.partie.getNickName();}

    // Ajoute le score du joueur en fonction de son nombre de tentatives
    public void addScore(int score) { this.partie.setScore(score);}

    // Obtenir le score du joueur
    public int getScore() { return this.partie.getScore();}

    // Obtenir la combinaison secrète de la manche
    public Color[] getCombinaisonSecrete() { return this.mancheController.getCombinaisonSecrete();}

}
