package model;

import java.util.ArrayList;

public class AffichageNumerique implements AfficheIndiceStrategy {
    @Override
    public ArrayList<String> afficherIndice(Indice[] indices)
    {
        int blanc = NbBlanc(indices);
        int noir = NbNoir(indices);
        ArrayList<String> res = new ArrayList<String>(indices.length);
        res.add("B");
        res.add(String.valueOf(noir));
        res.add("M");
        res.add(String.valueOf(blanc));
        return res;
    }

    public int NbBlanc(Indice[] indices)
    {
        int count = 0;
        for (int i = 0; i < indices.length; i++)
        {
            if(indices[i] == Indice.MAUVAISE_PLACE)
            {
                count++;
            }
        }
        return count;
    }

    public int NbNoir(Indice[] indices)
    {
        int count = 0;
        for (int i = 0; i < indices.length; i++)
        {
            if(indices[i] == Indice.BONNE_PLACE)
            {
                count++;
            }
        }
        return count;
    }
}
