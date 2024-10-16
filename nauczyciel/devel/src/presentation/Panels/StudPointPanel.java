/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.checkExam.StudentGradeAnswers;

/**
 *
 * @author Michal
 */
public class StudPointPanel extends JPanel
{
    private StudentGradeAnswers studAnswers;
    private JPanel panMain;
    private GridBagConstraints cons;
    private JLabel labCorFill;
    private JLabel labPoints;

    public StudPointPanel ()
    {
        cons = new GridBagConstraints();
        cons.gridx++;
        cons.gridy++;

        panMain = new JPanel(new GridBagLayout());

        this.setPreferredSize(new Dimension(100, 100));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void init (StudentGradeAnswers answers)
    {
        this.studAnswers = answers;

        JLabel labKey = new JLabel(studAnswers.getIdKey());
        panMain.add(labKey, cons);

        String textPoints = "Punkty :" + 0 + "/" + calcPossiblePoints();
        labPoints = new JLabel(textPoints);
        cons.gridy++;
        cons.anchor = GridBagConstraints.LINE_START;
        panMain.add(labPoints, cons);

        labCorFill = new JLabel("Sprawdzono:nie");
        cons.gridy++;
        panMain.add(labCorFill, cons);

        this.add(panMain);
    }

    public StudentGradeAnswers getStudAnswers ()
    {
        return studAnswers;
    }

    public void setStudAnswers (StudentGradeAnswers studAnswers)
    {
        this.studAnswers = studAnswers;
    }

    public void removeAllElements ()
    {
        cons.gridx = 0;
        cons.gridy = 0;

        panMain.removeAll();
        refres();

        this.revalidate();
        this.repaint();
    }

    //TODO przeneisc logike do innej klasy
    public void update (StudentGradeAnswers gradeAns)
    {
        int end = 0;
        int pointNum = 0;
        List<Integer> tempPoints = gradeAns.getGlobPoints();

        for (int i = 0; i < tempPoints.size(); i++)
        {
            if (tempPoints.get(i) < 0)
            {
                end = 1;
            }
            else
            {
                pointNum += tempPoints.get(i);
            }

        }

        if (end == 0)
        {
            labCorFill.setText("Sprawdzone:tak");
        }

        String textPoints = "Punkty :" + pointNum + "/" + calcPossiblePoints();
        labPoints.setText(textPoints);

        refres();
    }

    public void refres ()
    {
        panMain.revalidate();
        panMain.repaint();
    }

    //TODO przeneisc do innej klasy
    private int calcPossiblePoints ()
    {
        int sum = 0;

        for (int i = 0; i < studAnswers.getQuestions().size(); i++)
        {
            sum += studAnswers.getQuestions().get(i).getPoint();
        }

        return sum;
    }
}
