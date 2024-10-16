/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import logic.question.interfaces.IAnswerABCD;

/**
 * panel zawierajacy pojedyncza odpowiedz abcd
 * @author sokol
 */
public class ABCAnsPanel extends JPanel
{
    private JTextField texFieldAns;
    private JCheckBox checkBoxCor;

    public ABCAnsPanel ()
    {
        JPanel panTemp = new JPanel();
        panTemp.setLayout(new BoxLayout(panTemp, BoxLayout.X_AXIS));

        JLabel labAns = new JLabel("Odpowiedz");
        texFieldAns = new JTextField(10);
        checkBoxCor = new JCheckBox();

        panTemp.add(labAns);
        panTemp.add(texFieldAns);
        panTemp.add(checkBoxCor);

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

    /**
     * Sprawdz czy dana odpowiedz ma byc zaznaczona jako poprawna
     * @return true =odpowiedz poprawna false=niepoprawna
     */
    public boolean getCorrectness ()
    {
        return checkBoxCor.isSelected();
    }

    public void fillData (IAnswerABCD ans)
    {
        checkBoxCor.setSelected(ans.isCorrect());
        texFieldAns.setText(ans.getContent());
    }
}
