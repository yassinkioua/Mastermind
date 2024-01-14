package model;
import java.util.ArrayList;

public class AffichageFacile implements AfficheIndiceStrategy
{
    @Override
    public ArrayList<String> afficherIndice(Indice[] indices)
    {
        ArrayList<String> res = new ArrayList<>(indices.length);
        for (Indice index : indices)
        {
            if (index == Indice.BONNE_PLACE)
                res.add("noir");
            else if (index == Indice.MAUVAISE_PLACE)
                res.add("blanc");
            else
                res.add("gris");
        }
        return res;
    }
}
