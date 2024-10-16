/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels.ExamSections;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import logic.exams.SectionGap;
import presentation.Panels.AddExamPanel;

/**
 *
 * @author michal
 */
public class SectionGapPanel extends SectionPanel implements ActionListener
{
    static
    {
        CONS_SIZE = 10;
    }

    public SectionGapPanel (AddExamPanel addExamPanel)
    {
        super(addExamPanel);
        buildAddButtonPanel();
        buildCategoryPanel();

        butAdd.addActionListener(this);
    }

    @Override
    protected void buildCategoryPanel ()
    {
        super.buildCategoryPanel();
        cons.gridx = SectionPanel.CONS_SIZE;
    }

    public void buildSection ()
    {
        ExamSectionGapPanel examSec = new ExamSectionGapPanel(addExamPanel);
        examSec.init();

        examSecsPans.add(examSec);

        JButton delBut = new JButton("Usun");
        delBut.addActionListener(this);
        delButs.add(delBut);

        cons.gridx = 0;
        cons.gridy++;

        panSections.add(examSec, cons);

        int tempGridX = examSec.getConstraints().gridx;
        cons.gridx = tempGridX;
        cons.gridx++;
        panSections.add(delBut, cons);

        this.revalidate();
        this.repaint();
    }

    public void actionPerformed (ActionEvent event)
    {
        if (event.getSource() == butAdd)
        {
            buildSection();
        }
        else
        {
            for (int i = 0; i < delButs.size(); i++)
            {
                if (event.getSource() == delButs.get(i))
                {
                    panSections.remove((JButton) delButs.get(i));
                    panSections.remove((JPanel) examSecsPans.get(i));

                    delButs.remove(i);
                    examSecsPans.remove(i);

                    this.revalidate();
                    this.repaint();
                }
            }
        }
    }

    public void addSection (SectionGap sec)
    {
        ExamSectionGapPanel gapSec = new ExamSectionGapPanel(addExamPanel);
        addBasicSection(sec, gapSec);
        examSecsPans.add(gapSec);

        cons.gridy++;
        panSections.add(gapSec, cons);

        JButton delBut = new JButton("Usuń");
        delBut.addActionListener(this);
        delButs.add(delBut);

        cons.gridx = 10; //TODO zmienic

        panSections.add(delBut, cons);
    }
}
