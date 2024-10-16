/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logic.question.interfaces.IAnswerGap;

/**
 * Panel zawierajacy pojedyncza odpowiedz typu gap
 * @author Sokol
 */
public class GapAnsPanel extends JPanel
{
    private JTextField texFieldAns;
    private int iGapNum;

    public GapAnsPanel ()
    {
        JPanel panTemp = new JPanel();
        panTemp.setLayout(new BoxLayout(panTemp, BoxLayout.X_AXIS));

        JLabel labAns = new JLabel("Odpowiedz");
        texFieldAns = new JTextField(10);

        panTemp.add(labAns);
        panTemp.add(texFieldAns);

        this.add(panTemp);
    }

    /**
     * Zwroc cialo odpowiedzi
     * @return cialo odpowiedzi
     */
    public String getAnswer ()
    {
        return texFieldAns.getText();
    }

    public void setGapNum (int gapNum)
    {
        iGapNum = gapNum;
    }

    public int getGapNum ()
    {
        return iGapNum;
    }

    public void fillData (IAnswerGap gap)
    {
        texFieldAns.setText(gap.getContent());
    }
}
