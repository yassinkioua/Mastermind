package controller;

import model.AfficheIndiceStrategy;
import model.Manche;
import java.awt.*;
import java.util.ArrayList;

public class MancheController
{
    private Manche manche;

    // Initialise la manche avec les options du joueur
    public void initializeManche(int nbPionsCombi, int nbTentative, Color[] possibilite, AfficheIndiceStrategy strategy)
    {
        this.manche = new Manche(nbPionsCombi, nbTentative, possibilite, strategy);
        this.manche.setCombiSecrete();
    }
    // Vérifie si la combinaison du joueur est gagnante
    public void check_color(Color[] changement)
    {
        this.manche.changeColor(changement);
        this.manche.creeIndice();
        System.out.println("\n");
        this.manche.afficheIndice();
        System.out.println("\n");
        this.manche.affiche_combisecrete();
    }
    // Retourne vrai si la combinaison du joueur est gagnante
    public boolean hasWon()
    {
        return this.manche.estGagnee();
    }
    // Permet d'afficher les indices dans la bonne position
    public ArrayList<String> getAfficheIndice() {return this.manche.getAfficheIndice();}
    // Permet de changer la stratégie numérique/facile/classique des indices
    public void changeStrat(AfficheIndiceStrategy mode)
    {
        this.manche.changeStrat(mode);
    }
    // Permet de récupérer la combinaison secrète
    public Color[] getCombinaisonSecrete(){ return this.manche.getCombinaisonSecrete();}
}