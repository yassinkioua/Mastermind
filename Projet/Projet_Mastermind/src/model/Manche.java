package model;

import java.util.Random;

public class Manche{
    private Couleur[] combinaison_secrete;
    private Couleur[] combinaison_actuelle;
    private Indice[] indices;
    private int taille_combi;
    private int nbtentaitve;
    private Couleur[] possibilite;

    public Manche(int nbpions_combi,int nb_tentative,Couleur[] possible)
    {
        this.combinaison_secrete = new Couleur[nbpions_combi];
        this.combinaison_actuelle = new Couleur[nbpions_combi];
        this.indices = new Indice[nbpions_combi];
        this.nbtentaitve = nb_tentative;
        this.possibilite = possible;
        this.taille_combi = this.combinaison_secrete.length;
        for (int i = 0; i < this.combinaison_actuelle.length; i++) {
            this.combinaison_actuelle[i] = Couleur.NONE;
        }
        for (int k = 0; k < this.combinaison_secrete.length; k++) {
            this.indices[k] = Indice.INCORRECT;
        }
    }

    public void setCombiSecrete()
    {
        for (int i = 0; i< this.taille_combi;i++)
        {
            int indexAleatoire = new Random().nextInt(this.possibilite.length);
            Couleur couleurAleatoire = this.possibilite[indexAleatoire];
            this.combinaison_secrete[i] = couleurAleatoire;
        }
    }

    public void affiche_combi()
    {
        for (int i = 0; i < this.taille_combi; i++)
        {
            System.out.println("Élément " + i + " : " + this.combinaison_actuelle[i]);
        }
    }

    public void affiche_combisecrete()
    {
        for (int i = 0; i < this.taille_combi; i++)
        {
            System.out.println("Élément " + i + " : " + this.combinaison_secrete[i]);
        }
    }

    public void Change_Color(Couleur[] changement)
    {
        for(int i = 0;i<this.taille_combi;i++)
        {
            this.combinaison_actuelle[i] = changement[i];
        }
    }

    public void Cree_Indice()
    {
        for (int i = 0; i<this.taille_combi;i++)
        {
            boolean bien_place = false;
            if (this.combinaison_actuelle[i] == this.combinaison_secrete[i])
            {
                this.indices[i] = Indice.BONNE_PLACE;
                bien_place = true;
            }
            for (int j = 0; j < this.taille_combi; j++)
            {
                if (this.combinaison_actuelle[i] == this.combinaison_secrete[j] && !bien_place)
                {
                    this.indices[i] = Indice.MAUVAISE_PLACE;
                }
            }
        }
    }

    public void affiche_indice()
    {
        for (int i = 0; i < this.indices.length; i++)
        {
            System.out.println("Élément " + i + " : " + this.indices[i]);
        }
    }
}
