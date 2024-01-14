package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static utils.ColorSwap.getColorName;

public class Manche
{
    private final Color[] combinaison_secrete;
    private Color[] combinaison_actuelle;
    private Indice[] indices;
    private int taille_combi;
    private Color[] possibilite;
    private AfficheIndiceStrategy context;

    // Initialise la manche avec les options du joueur
    public Manche(int nbpions_combi, int nb_tentative, Color[] possible, AfficheIndiceStrategy strategy)
    {
        this.combinaison_secrete = new Color[nbpions_combi];
        this.combinaison_actuelle = new Color[nbpions_combi];
        this.indices = new Indice[nbpions_combi];
        this.possibilite = possible;
        this.taille_combi = this.combinaison_secrete.length;
        Arrays.fill(this.combinaison_actuelle, Color.LIGHT_GRAY);
        for (int k = 0; k < this.combinaison_secrete.length; k++)
            this.indices[k] = Indice.INCORRECT;
        this.context = strategy;
    }
    // Mettre en place la combinaison secrète
    public void setCombiSecrete()
    {
        for (int i = 0; i < this.taille_combi; i++)
        {
            int indexAleatoire = new Random().nextInt(this.possibilite.length);
            Color couleurAleatoire = this.possibilite[indexAleatoire];
            this.combinaison_secrete[i] = couleurAleatoire;
        }
    }

    public ArrayList<String> getAfficheIndice() { return this.context.afficherIndice(indices); }

    public void affiche_combisecrete()
    {
        System.out.println("--------------------- SECRET -------------------");
        for (int i = 0; i < this.taille_combi; i++)
            System.out.println("Élément " + i + " : " + getColorName(this.combinaison_secrete[i]));
        System.out.println("------------------------------------------------");
    }

    // Permet de récupérer la combinaison actuelle
    public void changeColor(Color[] changement)
    {
        for (int i = 0; i < this.taille_combi; i++)
            this.combinaison_actuelle[i] = changement[i];
    }

    // Créer un indice en fonction de la combinaison actuelle et secrète
    public void creeIndice()
    {
        for (int i = 0; i < this.taille_combi; i++)
        {
            boolean bien_place = false;
            if (this.combinaison_actuelle[i] == this.combinaison_secrete[i])
            {
                this.indices[i] = Indice.BONNE_PLACE;
                bien_place = true;
            }
            if (!bien_place)
            {
                for (int j = 0; j < this.taille_combi; j++)
                    if (this.combinaison_actuelle[i] == this.combinaison_secrete[j])
                    {
                        this.indices[i] = Indice.MAUVAISE_PLACE;
                        break;
                    }
                    else
                    {
                        this.indices[i] = Indice.INCORRECT;
                    }
            }
        }
    }

    // Vérifie si la manche est gagné
    public boolean estGagnee()
    {
        for (int i = 0; i < this.taille_combi; i++)
            if (this.combinaison_actuelle[i] != this.combinaison_secrete[i] || this.indices[i] != Indice.BONNE_PLACE)
                return false;
        return true;
    }

    public void afficheIndice()
    {
        System.out.println("-------------------- INDICES -------------------");
        for (int i = 0; i < this.indices.length; i++)
            System.out.println("Élément " + i + " : " + this.indices[i]);
        System.out.println("------------------------------------------------");
    }

    public void changeStrat(AfficheIndiceStrategy mode)
    {
        this.context = mode;
    }
    public Color[] getCombinaisonSecrete()
    {
        return this.combinaison_secrete;
    }
}
