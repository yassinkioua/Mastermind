package model;

import java.util.ArrayList;

public class AffichageFacile implements AfficheIndiceStrategy {
    @Override
    public ArrayList<String> afficherIndice(Indice[] indices) {
        ArrayList<String> res = new ArrayList<String>(indices.length);
        for (int i = 0; i < indices.length; i++)
        {
            if (indices[i] == Indice.BONNE_PLACE) {
                res.add("noir");
            } else if(indices[i] == Indice.MAUVAISE_PLACE) {
                res.add("blanc");
            } else {
                res.add("gris");
            }
        }
        return res;
    }
}
