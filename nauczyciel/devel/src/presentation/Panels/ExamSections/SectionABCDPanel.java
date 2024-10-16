/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels.ExamSections;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.exams.SectionABCD;
import presentation.Panels.AddExamPanel;

/**
 *
 * @author michal
 */
public class SectionABCDPanel extends SectionPanel implements ActionListener
{
    static
    {
        CONS_SIZE = 8;
    }

    public SectionABCDPanel (AddExamPanel addExamPanel)
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

        JLabel labAnsNum = new JLabel("Liczba odpowiedzi");
        JLabel labCorNum = new JLabel("Liczba poprawnych");

        cons.gridx++;
        panCategory.add(labAnsNum, cons);

        cons.gridx++;
        panCategory.add(labCorNum, cons);

    }

    public void buildSection ()
    {
        ExamSectionABCDPanel examSec = new ExamSectionABCDPanel(addExamPanel);
        examSec.init();

        examSecsPans.add(examSec);

        JButton delBut = new JButton("Usuń");
        delBut.addActionListener(this);
        delButs.add(delBut);

        cons.gridx = 0;
        cons.gridy++;

        panSections.add(examSec, cons);

        cons.gridx = examSec.getConstraints().gridx;
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

    public void addSection (SectionABCD sec)
    {
        ExamSectionABCDPanel abcdSec = new ExamSectionABCDPanel(addExamPanel);
        addBasicSection(sec, abcdSec);

        abcdSec.setCorrectNum(sec.getCorrect());
        abcdSec.setAnsNum(sec.getAll());

        cons.gridy++;
        panSections.add(abcdSec, cons);

        examSecsPans.add(abcdSec);

        cons.gridy++;
        panSections.add(abcdSec, cons);

        JButton delBut = new JButton("Usuń");
        delBut.addActionListener(this);
        delButs.add(delBut);

        cons.gridx = 7; //TODO zmienic

        panSections.add(delBut, cons);
    }
}
