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
        Manche m1 = new Manche(this);
        m1.setCombiSecrete();
        Couleur[] c = {Couleur.ORANGE,Couleur.GREEN,Couleur.WHITE,Couleur.YELLOW,Couleur.RED};
        m1.Change_Color(c);
        m1.Cree_Indice();
        m1.affiche_combi();
        System.out.println("-----------------------------------------------------");
        m1.affiche_combisecrete();
        System.out.println("-----------------------------------------------------");
        m1.affiche_indice();
    }
    public int getNbpions_combi()
    {
        return this.nbpions_combi;
    }
    public void setManche(int m)
    {
        this.manche = m;
    }
    public void setNbpions(int nbp)
    {
        this.nbpions = nbp;
    }
    public void setNbpions_combi(int nbc)
    {
        this.nbpions_combi = nbc;
    }
    public void setNb_tentative(int t)
    {
        this.nb_tentative = t;
    }
}
