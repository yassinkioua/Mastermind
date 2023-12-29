package controller;


import model.IndiceStrategy;
import model.Partie;
import view.GameWindow;

import java.awt.*;


public class PartieController
{
    private Partie partie;
    private GameWindow mainWindows;
    public PartieController()
    {
        partie = new Partie();
        mainWindows = new GameWindow(this);
        partie.addObserveurs(mainWindows);
    }
    public void getStrategy(IndiceStrategy modestrategy) { partie.change_strategy(modestrategy);}
    public void addCurentPions(int nbPions){ partie.setNbpions(nbPions);}
    public void addCurrentTentative(int nbTentative){ partie.setNbTentatives(nbTentative);}
    public void addCurentPionsCombi(int nbPionsCombi){ partie.setNbpions_combi(nbPionsCombi);}
    public void addCurentManche(int m) { partie.setNbManches(m);}
    public void addCouleurPossible() { partie.setCouleurPossible();}
    public Color[] getPossibleColor() { return partie.getColorPossible();}
    public void start() { partie.play();}
}
