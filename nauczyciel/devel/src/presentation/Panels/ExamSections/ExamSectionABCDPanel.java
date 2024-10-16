/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels.ExamSections;

import javax.swing.JPanel;
import javax.swing.JTextField;
import presentation.Panels.AddExamPanel;

/**
 *
 * @author michal
 */
public class ExamSectionABCDPanel extends ExamSectionPanel
{
    private JTextField texCorNum;
    private JTextField texAnsNum;

    public ExamSectionABCDPanel (AddExamPanel tabExam)
    {
        super(tabExam);
        texCorNum = new JTextField(TFSIZE);
        texAnsNum = new JTextField(TFSIZE);
    }

    @Override
    public JPanel buildSectionPanel ()
    {
        super.buildSectionPanel();

        cons.gridx++;
        panSec.add(texAnsNum, cons);

        cons.gridx++;
        panSec.add(texCorNum, cons);

        return panSec;
    }

    public int getCorrectNum ()
    {
        int corNum = Integer.parseInt(texCorNum.getText());
        return corNum;
    }

    public void setCorrectNum (int num)
    {
        texCorNum.setText(String.valueOf(num));
    }

    public int getAnsNum ()
    {
        int ansNum = Integer.parseInt(texAnsNum.getText());
        return ansNum;
    }

    public void setAnsNum (int num)
    {
        texAnsNum.setText(Integer.toString(num));
    }

    @Override
    public int checkFill ()
    {
        int fill = super.checkFill();

        try
        {
            Integer.parseInt(texCorNum.getText());
        }
        catch (Exception e)
        {
            fill = 0;
        }

        return fill;
    }
}
