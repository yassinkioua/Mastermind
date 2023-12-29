package model;

import java.awt.Color;
import java.util.Random;

public class Manche {
    private Color[] combinaison_secrete;
    private Color[] combinaison_actuelle;
    private Indice[] indices;
    private int taille_combi;
    private int nbtentative;
    private Color[] possibilite;

    public Manche(int nbpions_combi, int nb_tentative, Color[] possible) {
        this.combinaison_secrete = new Color[nbpions_combi];
        this.combinaison_actuelle = new Color[nbpions_combi];
        this.indices = new Indice[nbpions_combi];
        this.nbtentative = nb_tentative;
        this.possibilite = possible;
        this.taille_combi = this.combinaison_secrete.length;
        for (int i = 0; i < this.combinaison_actuelle.length; i++)
            this.combinaison_actuelle[i] = Color.LIGHT_GRAY;

        for (int k = 0; k < this.combinaison_secrete.length; k++)
            this.indices[k] = Indice.INCORRECT;
    }

    public void setCombiSecrete() {
        for (int i = 0; i < this.taille_combi; i++) {
            int indexAleatoire = new Random().nextInt(this.possibilite.length);
            Color couleurAleatoire = this.possibilite[indexAleatoire];
            this.combinaison_secrete[i] = couleurAleatoire;
        }
    }

    public void affiche_combi() {
        for (int i = 0; i < this.taille_combi; i++)
            System.out.println("Élément " + i + " : " + this.combinaison_actuelle[i]);
    }

    public void affiche_combisecrete() {
        for (int i = 0; i < this.taille_combi; i++)
            System.out.println("Élément " + i + " : " + this.combinaison_secrete[i]);
    }

    public void changeColor(Color[] changement) {
        for (int i = 0; i < this.taille_combi; i++) {
            this.combinaison_actuelle[i] = changement[i];
        }
    }

    public void creeIndice() {
        for (int i = 0; i < this.taille_combi; i++) {
            boolean bien_place = false;
            if (this.combinaison_actuelle[i] == this.combinaison_secrete[i]) {
                this.indices[i] = Indice.BONNE_PLACE;
                bien_place = true;
            }
            if (!bien_place) {
                for (int j = 0; j < this.taille_combi; j++)
                    if (this.combinaison_actuelle[i] == this.combinaison_secrete[j]) {
                        this.indices[i] = Indice.MAUVAISE_PLACE;
                        break;
                    } else {
                        this.indices[i] = Indice.INCORRECT;
                    }
            }
        }
    }

    public boolean estGagnee() {
        for (int i = 0; i < this.taille_combi; i++) {
            if (this.combinaison_actuelle[i] != this.combinaison_secrete[i]
                    || this.indices[i] != Indice.BONNE_PLACE) {
                return false;
            }
        }
        return true;
    }

    public void afficheIndice() {
        for (int i = 0; i < this.indices.length; i++)
            System.out.println("Élément " + i + " : " + this.indices[i]);
    }
}
