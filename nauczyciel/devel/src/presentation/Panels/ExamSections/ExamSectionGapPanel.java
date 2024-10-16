/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels.ExamSections;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import presentation.Panels.AddExamPanel;

/**
 *
 * @author michal
 */
public class ExamSectionGapPanel extends ExamSectionPanel
{
    private JCheckBox boxUnderline = new JCheckBox();
    private JCheckBox boxShowChars = new JCheckBox();
    private JTextField texNumChars = new JTextField(TFSIZE);
    private JTextField texCharMode = new JTextField(TFSIZE);

    public ExamSectionGapPanel (AddExamPanel tabExam)
    {
        super(tabExam);
    }

    @Override
    public JPanel buildSectionPanel ()
    {
        super.buildSectionPanel();
        return panSec;
    }

    public boolean isUnderline ()
    {
        return boxUnderline.isSelected();
    }

    public void setUnderline (boolean under)
    {
        boxUnderline.setSelected(under);
    }

    public boolean isShowChars ()
    {
        return boxShowChars.isSelected();
    }

    public void setShowChars (boolean showChars)
    {
        boxShowChars.setSelected(showChars);
    }

    public int getNumChars ()
    {
        return Integer.parseInt(texNumChars.getText());
    }

    public void setNumChars (int num)
    {
        texNumChars.setText(String.valueOf(num));
    }

    public int getCharMode ()
    {
        return Integer.parseInt(texCharMode.getText());
    }

    public void setCharMode (int num)
    {
        texCharMode.setText(String.valueOf(num));
    }
}
