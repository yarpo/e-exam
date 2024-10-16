/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import java.awt.Color;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import logic.checkExam.ExportQuestion;
import logic.checkExam.StudentGradeAnswers;

/**
 *
 * @author Michal
 */
public class SingleOpenAnsExamPanel extends SingleAnsExamPanel
{
    public SingleOpenAnsExamPanel (List<Integer> ansPoints, ExportQuestion ques,
            Integer globPoints, int quesIndex)
    {
        super(ansPoints, ques, globPoints, quesIndex);
    }

    @Override
    public void init ()
    {
        String quesContent = quesIndex + "." + ques.getContent();
        JLabel labCon = new JLabel(quesContent);
        this.add(labCon, cons);

        cons.gridy++;
        JTextArea ansTextArea = new JTextArea(10, 10);
        ansTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ansTextArea.setText(ques.getAnswersStud().get(0));
        this.add(ansTextArea, cons);

        cons.gridy++;
        int procPoints = processPoints();

        if (procPoints == StudentGradeAnswers.NOCHECK_QUES)
        {
            texFieldPoints.setText("");
        }
        else
        {
            texFieldPoints.setText(Integer.toString(procPoints));
        }
        this.add(texFieldPoints, cons);

        cons.gridx++;
        this.add(labPointToMaxPoint, cons);
    }
}
