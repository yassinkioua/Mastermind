package model;

import view.ObserveurConcretButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int manche;
    public int nbpions;
    public Color color;
    public int nbpions_combi;
    public int nb_tentative;
    private Color[] couleur_possible;
    IndiceStrategy context;
    private final List<ButtonObserveur> observers = new ArrayList<ButtonObserveur>();
    ObserveurConcretButton ob = new ObserveurConcretButton();
    public Partie()
    {
        this.color = Color.LIGHT_GRAY;
        this.observers.add(ob);
    }

    /*public void play()
    {
        Manche m1 = new Manche(this.nbpions_combi,this.nb_tentative,this.couleur_possible);
        m1.setCombiSecrete();
        Color[] c = {Color.ORANGE,Color.GREEN,Color.WHITE,Color.YELLOW,Color.RED,Color.BLUE};
        m1.Change_Color(c);
        m1.Cree_Indice();
        m1.affiche_combi();
        System.out.println("-----------------------------------------------------");
        m1.affiche_combisecrete();
        System.out.println("-----------------------------------------------------");
        m1.affiche_indice();
    }
     */
    public void change_strategy(IndiceStrategy c)
    {
        this.context = c;
        System.out.println("Stratégie changée ! ");
    }
    public void setCouleurPossible()
    {
        this.couleur_possible = new Color[this.nbpions];

        Color[] couleursPossibles = {
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.MAGENTA,
                Color.BLUE,
                Color.GREEN,
                Color.BLACK,
                Color.WHITE
        };

        for (int i = 0; i < this.nbpions; i++) {
            this.couleur_possible[i] = couleursPossibles[i % couleursPossibles.length];
        }
    }

    public void NextColor(JButton button, int currentColorIndex, int buttonIndex)
    {
        Color currentColor = couleur_possible[currentColorIndex];
        int nextColorIndex = (currentColorIndex + 1) % couleur_possible.length;
        button.putClientProperty("currentColorIndex", nextColorIndex);
        button.putClientProperty("index", buttonIndex);
        notifyChanges(button, currentColor);
    }
    public void setNbManches(int m) {this.manche = m;}
    public void setNbpions(int nbp) {this.nbpions = nbp;}
    public void setNbpions_combi(int nbc) {this.nbpions_combi = nbc;}
    public void setNbTentatives(int t) {this.nb_tentative = t;}
    public void addObserveurs(ButtonObserveur b) { this.observers.add(b);}
    public void notifyChanges(JButton button, Color color)
    {
        for (ButtonObserveur ob : observers)
            ob.updateButtons(button, color);
    }
    public int getNbpions_combi() { return this.nbpions_combi;}
    public int getNbTentatives() { return this.nb_tentative;}
    public Color[] getCouleurPossible() { return this.couleur_possible;}

    public void Affichage()
    {
        System.out.println("------------------- MASTERMIND -----------------");
        System.out.println("Nombre de manches : " + this.manche);
        System.out.println("Nombre de pions : " + this.nbpions);
        System.out.println("Nombre de combinaisons de pions " + this.nbpions_combi);
        System.out.println("Nombre de tentatives : " + this.nb_tentative);
        System.out.println("------------------------------------------------");
    }
}
