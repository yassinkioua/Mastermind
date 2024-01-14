package controller;

import model.AfficheIndiceStrategy;
import model.Manche;
import java.awt.*;
import java.util.ArrayList;

public class MancheController
{
    private Manche manche;
    public void initializeManche(int nbPionsCombi, int nbTentative, Color[] possibilite, AfficheIndiceStrategy strategy)
    {
        this.manche = new Manche(nbPionsCombi, nbTentative, possibilite, strategy);
        this.manche.setCombiSecrete();
    }
    public void check_color(Color[] changement)
    {
        this.manche.changeColor(changement);
        this.manche.creeIndice();
        System.out.println("\n");
        this.manche.afficheIndice();
        System.out.println("\n");
        this.manche.affiche_combisecrete();
    }
    public boolean hasWon()
    {
        return this.manche.estGagnee();
    }
    public ArrayList<String> getAfficheIndice() {return this.manche.getAfficheIndice();}
    public void changeStrat(AfficheIndiceStrategy mode)
    {
        this.manche.changeStrat(mode);
    }
    public Color[] getCombinaisonSecrete(){ return this.manche.getCombinaisonSecrete();}
}