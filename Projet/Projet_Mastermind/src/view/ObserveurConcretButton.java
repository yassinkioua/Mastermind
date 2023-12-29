package view;
import utils.ColorSwap;
import model.ButtonObserveur;

import javax.swing.*;
import java.awt.*;

public class ObserveurConcretButton implements ButtonObserveur {

    @Override
    public void updateButtons(JButton button, Color color) {
        String colorName = ColorSwap.getColorName(color);
        Integer buttonIndex = (Integer) button.getClientProperty("index");
        System.out.println("Le bouton " + button.getText() + buttonIndex + " a changé de couleur : " + colorName);
    }
}
