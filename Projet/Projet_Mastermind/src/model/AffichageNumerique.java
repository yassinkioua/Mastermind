package model;
import java.util.ArrayList;

public class AffichageNumerique implements AfficheIndiceStrategy
{
    // Permet d'afficher les indices dans la console et dans le jeu
    @Override
    public ArrayList<String> afficherIndice(Indice[] indices)
    {
        int blanc = NbBlanc(indices);
        int noir = NbNoir(indices);
        ArrayList<String> res = new ArrayList<>(indices.length);
        res.add("B");
        res.add(String.valueOf(noir));
        res.add("M");
        res.add(String.valueOf(blanc));
        for (int i = 4; i<indices.length; i++)
            res.add("");
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
