package model;

public class AffichageFacile implements AfficheIndiceStrategy {
    @Override
    public void afficherIndice(Indice[] indices) {
        for (int i = 0; i < indices.length; i++)
        {
            System.out.println("Élément " + i + " : " + indices[i]);
        }
    }
}
