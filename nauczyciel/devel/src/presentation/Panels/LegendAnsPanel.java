/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation.Panels;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Michal
 */
public class LegendAnsPanel extends JPanel {


    public LegendAnsPanel()
    {
        
      JLabel labBlue=new JLabel(addColorText("BLUE", "poprawna odpowiedz niezaznaczona"));
      JLabel labGreen=new JLabel(addColorText("GREEN", "poprawna odpowiedz zaznaczona"));
      JLabel labRed=new JLabel(addColorText("RED", "zla odpowiedz zaznaczona"));

      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      
      this.add(labBlue);
      this.add(labGreen);
      this.add(labRed);
    }


    private String addColorText (String color, String text)
    {
        String colorText = "<html><font color=\"" + color + "\">" + text + "</font>";

        colorText+="</html>";
        return colorText;
    }

}
