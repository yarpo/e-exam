/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import data.staticClass.TypeCalculator;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import logic.checkExam.ExportQuestion;
import logic.checkExam.StudentGradeAnswers;

/**
 *
 * @author Michal
 */
public class SingleAnsExamPanel extends JPanel
{
    private JLabel labContent;
    protected JLabel labPointToMaxPoint;
    protected List<Integer> ansPoints;
    protected Integer globPoints;
    protected ExportQuestion ques;
    protected JTextField texFieldPoints;
    protected int quesIndex;
    protected int numPoint;
    protected GridBagConstraints cons;
    protected StudentGradeAnswers gradeAnswers;
    protected StudPointPanel studPointPanel;

    public StudPointPanel getStudPointPanel ()
    {
        return studPointPanel;
    }

    public void setStudPointPanel (StudPointPanel studPointPanel)
    {
        this.studPointPanel = studPointPanel;
    }
    protected static int TFSIZE = 5;

    public SingleAnsExamPanel (List<Integer> ansPoints, ExportQuestion ques,
            Integer globPoints, int quesIndex)
    {
        this.ques = ques;
        this.ansPoints = ansPoints;
        this.globPoints = globPoints;

        int procPoints = processPoints();

        String textPointToMax = null;

        if (procPoints == StudentGradeAnswers.NOCHECK_QUES)
        {
            textPointToMax = 0 + "/" + ques.getPoint();
        }
        else
        {
            textPointToMax = procPoints + "/" + ques.getPoint();
        }

        labPointToMaxPoint = new JLabel(textPointToMax);

        cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.anchor = GridBagConstraints.LINE_START;

        this.quesIndex = quesIndex;
        labContent = new JLabel();
        texFieldPoints = new JTextField(TFSIZE);
        texFieldPoints.setText(String.valueOf(globPoints));
        texFieldPoints.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate (DocumentEvent e)
            {
                changePointValue();
            }

            public void removeUpdate (DocumentEvent e)
            {
                changePointValue();
            }

            public void insertUpdate (DocumentEvent e)
            {
                changePointValue();
            }
        });


        this.setLayout(new GridBagLayout());
    }

    public void init ()
    {
        String text = createHtmlQuestion();
        labContent.setText(text);
        this.add(labContent, cons);

        cons.gridy++;
        texFieldPoints.setText(String.valueOf(globPoints));
        this.add(texFieldPoints, cons);

        cons.gridx++;
        this.add(labPointToMaxPoint, cons);
    }

    private String createHtmlQuestion ()
    {
        String quesText = "<html>";
        quesText += quesIndex + ".";

        if (TypeCalculator.isGap(ques.getType()))
        {
            quesText += createGapHtml();
        }
        else
        {
            quesText += ques.getContent();
        }


        int pointNum = 0;

        for (int i = 0; i < ques.getAllAnswers().size(); i++)
        {
            pointNum = ansPoints.get(i);
            quesText += "<br>";
            if (TypeCalculator.isGap(ques.getType()))
            {
                quesText += (i + 1) + "." + addColorGapAnswer(i, pointNum, ques);
            }
            else
            {
                quesText += (i + 1) + "." + addColorAnswer(i, pointNum, ques);
            }
        }
        quesText += "<html>";

        return quesText;
    }

    private String createGapHtml ()
    {
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append(ques.getContent());

        List<String> corAnswers = ques.getCorrectAnswers();
        int lastIndex = 0;
        for (int i = 0; i < corAnswers.size(); i++)
        {
            lastIndex = strBuilder.indexOf("{", lastIndex);

            lastIndex++;
            strBuilder.insert(lastIndex, corAnswers.get(i));
        }

        return strBuilder.toString();
    }

    private String addColorAnswer (int ansIndex, int pointNum,
            ExportQuestion ques)
    {
        String ansContent = ques.getAllAnswers().get(ansIndex);
        if (pointNum == -1)
        {
            if (ques.getCorrectAnswers().contains(ansContent))
            {
                return addColorText("blue", ansContent);
            }
            else
            {
                return addColorText("red", ansContent);
            }
        }
        else if (pointNum == 0)
        {
            if (ques.getCorrectAnswers().contains(ansContent))
            {
                return addColorText("blue", ansContent);
            }
            else
            {
                return addColorText("black", ansContent);
            }
        }
        else //>0
        {
            return addColorText("green", ansContent);
        }
    }

    private String addColorGapAnswer (int ansIndex, int pointNum,
            ExportQuestion ques)
    {
        String ansContent = ques.getAnswersStud().get(ansIndex);
        if (pointNum == -1)
        {
            return addColorText("red", ansContent);
        }
        else if (pointNum == 0)
        {
            return addColorText("black", ansContent);
        }
        else //>0
        {
            return addColorText("green", ansContent);
        }
    }

    private String addColorText (String color, String text)
    {
        String colorText = "<font color=\"" + color + "\">" + text + "</font>";

        return colorText;
    }

    public void setNumPoint (int point)
    {
        this.numPoint = point;
    }

    public StudentGradeAnswers getGradeAnswers ()
    {
        return gradeAnswers;
    }

    public void setGradeAnswers (StudentGradeAnswers gradeAnswers)
    {
        this.gradeAnswers = gradeAnswers;

    }

    private void changePointValue ()
    {
        if (!texFieldPoints.getText().equals(""))
        {
            try
            {
                int point = Integer.parseInt(texFieldPoints.getText());
                gradeAnswers.getGlobPoints().set(quesIndex - 1,
                        point);

                labPointToMaxPoint.setText(point + "/1");
                studPointPanel.update(gradeAnswers);
            }
            catch (Exception e)
            {
            }
        }
    }

    protected int processPoints ()
    {
        if (globPoints != StudentGradeAnswers.NOCHECK_QUES)
        {
            return globPoints;
        }
        else
        {
            return StudentGradeAnswers.NOCHECK_QUES;
        }
    }
}
