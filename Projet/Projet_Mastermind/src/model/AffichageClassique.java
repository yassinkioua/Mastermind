package model;

public class AffichageClassique implements AfficheIndiceStrategy {
    @Override
    public void afficherIndice(Indice[] indices)
    {
        int blanc = NbBlanc(indices);
        int noir = NbNoir(indices);
        Indice[] res = new Indice[indices.length];
        for (int i = 0; i < indices.length; i++)
        {
            if (i < noir) {
                res[i] = Indice.BONNE_PLACE;
            } else if (i < noir + blanc) {
                res[i] = Indice.MAUVAISE_PLACE;
            } else {
                res[i] = Indice.INCORRECT;
            }
        }
        for (int j = 0; j < res.length; j++)
        {
            System.out.println("Élément " + j + " : " + res[j]);
        }
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
