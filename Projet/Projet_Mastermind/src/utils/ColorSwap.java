package utils;

import java.awt.Color;
public class ColorSwap {

    public static String getColorName(Color color)
    {
        if (color.equals(Color.RED))
            return "ROUGE";
        else if (color.equals(Color.ORANGE))
            return "ORANGE";
        else if (color.equals(Color.YELLOW))
            return "JAUNE";
        else if (color.equals(Color.MAGENTA))
            return "MAGENTA";
        else if (color.equals(Color.BLUE))
            return "BLEU";
        else if (color.equals(Color.GREEN))
            return "VERT";
        else if (color.equals(Color.BLACK))
            return "NOIR";
        else if (color.equals(Color.WHITE))
            return "BLANC";
        else if (color.equals(Color.lightGray))
            return "PAS RENSEIGNÉE";
        else
            return color.toString();
    }
}
