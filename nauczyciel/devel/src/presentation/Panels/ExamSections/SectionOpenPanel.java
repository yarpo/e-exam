/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels.ExamSections;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import logic.exams.Section;
import presentation.Panels.AddExamPanel;

/**
 *
 * @author michal
 */
public class SectionOpenPanel extends SectionPanel implements ActionListener
{
    public SectionOpenPanel (AddExamPanel addExamPanel)
    {
        super(addExamPanel);
        buildAddButtonPanel();
        buildCategoryPanel();

        butAdd.addActionListener(this);
    }

    public void buildSection ()
    {
        ExamSectionPanel examSec = new ExamSectionPanel(addExamPanel);
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

    @Override
    public void addSection (Section sec)
    {
        ExamSectionPanel sect = new ExamSectionPanel(addExamPanel);
        addBasicSection(sec, sect);

        JButton delBut = new JButton("Usuń");
        delBut.addActionListener(this);
        delButs.add(delBut);

        examSecsPans.add(sect);

        cons.gridy++;
        panSections.add(sect, cons);

        cons.gridx = sect.getConstraints().gridx;
        cons.gridx++;
        panSections.add(delBut, cons);
        scrollPanSections.revalidate();
        scrollPanSections.repaint();
        scrollPanSections.updateUI();
    }
}
