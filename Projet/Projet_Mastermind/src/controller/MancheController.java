package controller;

import model.Manche;
import model.Partie;
import java.awt.*;
public class MancheController {
    private Manche manche;

    public MancheController() {;
    }

    public void initializeManche(int nbPionsCombi, int nbTentative, Color[] possibilite) {
        manche = new Manche(nbPionsCombi, nbTentative, possibilite);
        manche.setCombiSecrete();
    }
    public void check_color(Color[] changement)
    {
        manche.changeColor(changement);
        manche.creeIndice();
        System.out.println("\n");
        manche.afficheIndice();
        System.out.println("\n");
        manche.affiche_combisecrete();
    }
    public boolean hasWon()
    {
        return manche.estGagnee();
    }

}