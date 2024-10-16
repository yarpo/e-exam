/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import data.staticClass.TypeCalculator;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import logic.checkExam.ExportQuestion;
import logic.checkExam.StudentGradeAnswers;

/**
 *
 * @author Michal
 * Klasa zawierajaca panel, na ktorym zaznaczone sa odpowiedzi studentow
 */
public class StudAnsExamPanel extends JPanel
{
    private StudentGradeAnswers studAnswers;
    private List<ArrayList<Integer>> ansPoints;
    private List<SingleAnsExamPanel> singleAnswers;
    private GridBagConstraints cons;
    private JPanel mainPanel;
    private StudPointPanel studPointPanel;

    public StudPointPanel getStudPointPanel ()
    {
        return studPointPanel;
    }

    public void setStudPointPanel (StudPointPanel studPointPanel)
    {
        this.studPointPanel = studPointPanel;
    }

    public StudAnsExamPanel ()
    {
        cons = new GridBagConstraints();
        cons.gridy = 0;
        cons.gridx = 0;
        cons.anchor = GridBagConstraints.LINE_START;

        singleAnswers = new ArrayList<SingleAnsExamPanel>();
        mainPanel = new JPanel(new GridBagLayout());
    }

    public void init (StudentGradeAnswers studAnswers)
    {
        this.studAnswers = studAnswers;
        ansPoints = studAnswers.getAnsPoint();

        initSingleAnswers();
        buildPanel();
    }

    private void buildPanel ()
    {
        for (int i = 0; i < singleAnswers.size(); i++)
        {
            singleAnswers.get(i).init();
            mainPanel.add(singleAnswers.get(i), cons);
            cons.gridy++;
        }

        this.add(mainPanel);
        update();
    }

    private void update ()
    {
        this.revalidate();
        this.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void removeAllElements ()
    {
        this.remove(mainPanel);
        singleAnswers = new ArrayList<SingleAnsExamPanel>();

        cons.gridy = 0;
        cons.gridx = 0;
        mainPanel = new JPanel(new GridBagLayout());
    }

    private void initSingleAnswers ()
    {
        SingleAnsExamPanel singleAns = null;
        ExportQuestion ques = null;
        for (int i = 0; i < studAnswers.getQuestions().size(); i++)
        {
            ques = studAnswers.getQuestions().get(i);

            if (TypeCalculator.isOpen(ques.getType()))
            {
                singleAns = new SingleOpenAnsExamPanel(ansPoints.get(i), ques,
                        studAnswers.getGlobPoints().get(i), (i + 1));
                singleAns.setGradeAnswers(studAnswers);
            }
            else
            {
                singleAns = new SingleAnsExamPanel(ansPoints.get(i), ques,
                        studAnswers.getGlobPoints().get(i), (i + 1));
                singleAns.setGradeAnswers(studAnswers);

            }
            singleAns.setStudPointPanel(studPointPanel);
            singleAnswers.add(singleAns);
        }
    }
}
