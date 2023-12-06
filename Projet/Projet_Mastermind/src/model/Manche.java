package model;

import java.util.Random;

public class Manche{
    private Couleur[] combinaison_secrete;
    private Couleur[] combinaison_actuelle;
    private Indice[] indices;
    private int taille_combi;
    public Manche(Partie p)
    {
        super();
        this.combinaison_secrete = new Couleur[p.getNbpions_combi()];
        this.combinaison_actuelle = new Couleur[p.getNbpions_combi()];
        this.indices = new Indice[p.getNbpions_combi()];
        this.taille_combi = this.combinaison_secrete.length;
        for (int i = 0; i < this.combinaison_actuelle.length; i++) {
            this.combinaison_actuelle[i] = Couleur.NONE;
        }
        for (int k = 0; k < this.combinaison_secrete.length; k++) {
            this.indices[k] = Indice.INCORRECT;
        }
        System.out.println(p.getNbpions_combi());
    }

    public void setCombiSecrete()
    {
        Couleur[] valeurs = Couleur.values();
        for (int i = 0; i< this.combinaison_secrete.length;i++)
        {
            int indexAleatoire = new Random().nextInt(valeurs.length);
            if(indexAleatoire == 0)
            {
                while(indexAleatoire == 0)
                {
                    indexAleatoire = new Random().nextInt(valeurs.length);
                }
            }
            Couleur couleurAleatoire = valeurs[indexAleatoire];
            this.combinaison_secrete[i] = couleurAleatoire;
        }
    }

    public void affiche_combi()
    {
        for (int i = 0; i < this.combinaison_actuelle.length; i++)
        {
            System.out.println("Élément " + i + " : " + this.combinaison_actuelle[i]);
        }
    }

    public void affiche_combisecrete()
    {
        for (int i = 0; i < this.combinaison_secrete.length; i++)
        {
            System.out.println("Élément " + i + " : " + this.combinaison_secrete[i]);
        }
    }

    public void Change_Color(Couleur[] changement)
    {
        for(int i = 0;i<this.combinaison_actuelle.length;i++)
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
