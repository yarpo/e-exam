/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels.ExamSections;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import logic.exams.Section;
import presentation.Panels.AddExamPanel;

/**
 *
 * @author michal
 */
public class SectionPanel extends JPanel
{
    protected GridBagConstraints cons; //constraints'y dla wewnetrznych paneli
    protected GridBagConstraints mainCons;
    protected AddExamPanel addExamPanel;
    protected JPanel panAddSecBut;
    protected JPanel panCategory;
    protected JPanel panSections;
    protected JScrollPane scrollPanSections;
    protected int curGridY;
    protected int curGridX;
    protected ArrayList examSecsPans;
    protected ArrayList delButs;
    protected JButton butAdd;
    public static int CONS_SIZE = 6;  //number of max elements in panel

    public SectionPanel (AddExamPanel addExamPanel)
    {
        this.addExamPanel = addExamPanel;
        butAdd = new JButton("Dodaj sekcję");
        panCategory = new JPanel(new GridBagLayout());
        panSections = new JPanel(new GridBagLayout());
        panAddSecBut = new JPanel(new GridBagLayout());
        cons = new GridBagConstraints();
        mainCons = new GridBagConstraints();

        this.setLayout(new GridBagLayout());

        cons.gridx = 0;
        cons.gridy = 0;
        mainCons.gridx = 0;
        mainCons.gridy = 0;

        examSecsPans = new ArrayList();
        delButs = new ArrayList();

        mainCons.gridwidth = 7;
        this.add(panAddSecBut, mainCons);
        mainCons.gridy++;
        this.add(panCategory, mainCons);
        mainCons.gridy++;

        scrollPanSections = new JScrollPane(panSections);
        scrollPanSections.setPreferredSize(new Dimension(800, 300));

        this.add(scrollPanSections, mainCons);
    }

    protected void buildAddButtonPanel ()
    {
        panAddSecBut.add(butAdd, cons);
        cons.gridy++;
    }

    protected void buildCategoryPanel ()
    {
        JLabel labName = new JLabel("Nazwa");
        JLabel labCat = new JLabel("Kategoria");
        JLabel labQuan = new JLabel("Liczba");
        JLabel labDiffMin = new JLabel("MinPoziom");
        JLabel labDiffMax = new JLabel("MaxPoziom");
        JLabel labPoint = new JLabel("Pkt za pyt");

        cons.anchor = GridBagConstraints.LINE_START;
        cons.gridx = 0;
        cons.ipadx = 40;
        panCategory.add(labName, cons);
        cons.gridx++;
        cons.ipadx = 30;

        panCategory.add(labCat, cons);
        cons.gridx++;
        panCategory.add(labQuan, cons);

        cons.gridx++;
        panCategory.add(labDiffMin, cons);

        cons.gridx++;
        panCategory.add(labDiffMax, cons);

        cons.gridx++;
        panCategory.add(labPoint, cons);

    }

    public ArrayList getSections ()
    {
        return examSecsPans;
    }

    public int checkFill ()
    {
        int fill = 1;

        for (int i = 0; i < examSecsPans.size(); i++)
        {
            if (((ExamSectionPanel) examSecsPans.get(i)).checkFill() == 0)
            {
                fill = 0;
                break;
            }

        }
        return fill;
    }

    public void clearFields ()
    {
        this.remove(scrollPanSections);

        panSections = new JPanel(new GridBagLayout());
        scrollPanSections = new JScrollPane(panSections);
        scrollPanSections.setPreferredSize(new Dimension(800, 300));

        this.add(scrollPanSections, mainCons);

        delButs = new ArrayList();
        examSecsPans = new ArrayList();
    }

    public void addSection (Section sec)
    {
    }

    public void addBasicSection (Section sec, ExamSectionPanel examSec)
    {
        cons.gridx = 0;
        examSec.init();
        examSec.setTaskName(sec.getName());
        examSec.setCategory(sec.getCategory());
        examSec.setMaxDifficulty(sec.getGradeMax());
        examSec.setMinDifficulty(sec.getGradeMin());
        examSec.setCategory(sec.getCategory());
        examSec.setQuantity(sec.getQuestionsNo());
        examSec.setPoints(sec.getPoints());
    }

    /**
     * Get all points from this panel's sections
     * @return
     */
    public int calcPoints ()
    {
        int numPoints = 0;
        for (int i = 0; i < examSecsPans.size(); i++)
        {
            ExamSectionPanel tempPanel = (ExamSectionPanel) examSecsPans.get(i);
            numPoints += tempPanel.calcPoints();
        }

        return numPoints;
    }

    public void updateCategories ()
    {
        for (int i = 0; i < examSecsPans.size(); i++)
        {
            ExamSectionPanel tempPan = (ExamSectionPanel) examSecsPans.get(i);
            tempPan.setCategories();
        }
    }
}
