package model;
import java.util.ArrayList;

public class AffichageClassique implements AfficheIndiceStrategy
{
    @Override
    public ArrayList<String> afficherIndice(Indice[] indices)
    {
        int blanc = NbBlanc(indices);
        int noir = NbNoir(indices);
        ArrayList<String> res = new ArrayList<>(indices.length);
        for (int i = 0; i < indices.length; i++)
        {
            if (i < noir)
                res.add("noir");
            else if (i < noir + blanc)
                res.add("blanc");
            else
                res.add("gris");
        }
        return res;
    }
    public int NbBlanc(Indice[] indices)
    {
        int count = 0;
        for (Indice index : indices)
            if (index == Indice.MAUVAISE_PLACE)
                count++;
        return count;
    }

    public int NbNoir(Indice[] indices)
    {
        int count = 0;
        for (Indice index : indices)
            if (index == Indice.BONNE_PLACE)
                count++;
        return count;
    }
}
