package model;

public class Partie {
    private int manche;
    private int nbpions;
    private int nbpions_combi;
    private int nb_tentative;
    public Partie()
    {
        this.manche = 3;
        this.nbpions = 8;
        this.nbpions_combi = 4;
        this.nb_tentative = 10;
    }

    public void play()
    {
        Manche m1 = new Manche();
        m1.setCombiSecrete();
        Couleur[] c = {Couleur.ORANGE,Couleur.GREEN,Couleur.WHITE,Couleur.YELLOW};
        m1.Change_Color(c);
        m1.Cree_Indice();
        m1.affiche_combi();
        m1.affiche_combisecrete();
        m1.affiche_indice();
    }
    public int getManche() {
        return this.manche;
    }
    public int getNbpions() {
        return this.nbpions;
    }
    public int getNbpions_combi() {
        return this.nbpions_combi;
    }
    public int getNb_tentative() {
        return this.nb_tentative;
    }
}
