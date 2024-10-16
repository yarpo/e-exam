/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.checkExam;

import data.staticClass.TypeCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Sokol
 * Klasa zawierajaca całą strukturę odpowiedzi oraz liczbę punktów za określone zadnia
 */
public class StudentGradeAnswers extends StudentAnswers
{
    private List<ArrayList<Integer>> ansPoint; //lista, ktore odpowiedzi sa poprawne
    private List<Integer> globPoints; //globalba ilsoc punktow dla kazdego pytania
    public static int NOCHECK_QUES = -669;

    public StudentGradeAnswers ()
    {
        ansPoint = new ArrayList<ArrayList<Integer>>();
        globPoints = new ArrayList<Integer>();
    }

    /**
     * Przetworzenie pytan i zapisanie punktow do pytan/odpowiedzi
     * na razie dziala bez sprawdzanai rozmairu liter
     */
    private void processCorrectAnswers ()
    {
        for (int i = 0; i < questions.size(); i++)
        {

            List<String> correctAnswers = questions.get(i).getCorrectAnswers();
            List<String> studAnswers = questions.get(i).getAnswersStud();
            List<String> allAnswers = questions.get(i).getAllAnswers();

            List<Integer> points = compareAnswers(i, allAnswers, correctAnswers,
                    studAnswers);

            int globPoint = processGlobalPoints(points);
            globPoints.add(globPoint);

            ansPoint.add((ArrayList<Integer>) points);
        }
    }

    ///Sprawdzenie poprawnosci odpowiedzi studenta ze wzorcowymi
    ///TODO dodac mozliwosc obslugi pytan abcd z wieloma odpowiedziami poprawnymi
    private List<Integer> compareAnswers (int index, List<String> allAnswers,
            List<String> correctAnswers, List<String> studAnswers)
    {
        List<Integer> ansPoints = new ArrayList<Integer>();
        int type = questions.get(index).getType();

        if (TypeCalculator.isABCD(type) == true)
        {
            compareAnswersABCD(allAnswers, correctAnswers, studAnswers,
                    ansPoints, questions.get(index).getPoint());
        }
        else if (TypeCalculator.isGap(type) == true)
        {
            compareAnswersGap(allAnswers, correctAnswers, studAnswers, ansPoints, questions.
                    get(index).getPoint());
        }

        return ansPoints;
    }

    private void compareAnswersABCD (List<String> allAnswers,
            List<String> correctAnswers,
            List<String> studAnswers, List<Integer> ansPoints, int pointPerQues)
    {
        for (int i = 0; i < allAnswers.size(); i++)
        {
            String answer = allAnswers.get(i);
            if (correctAnswers.contains(answer))
            {
                insertAnsABCDPoints(studAnswers, answer, ansPoints, pointPerQues);
            }
            else
            {
                insertAnsABCDPoints(studAnswers, answer, ansPoints, -1);
            }
        }
    }

    private void insertAnsABCDPoints (List<String> studAnswers, String answer,
            List<Integer> ansPoints, int numPoint)
    {
        if (studAnswers.contains(answer))
        {
            ansPoints.add(numPoint);
        }
        else
        {
            ansPoints.add(0);
        }
    }

    private void compareAnswersGap (List<String> allAnswers,
            List<String> correctAnswers,
            List<String> studAnswers, List<Integer> ansPoints, int pointPerQues)
    {
        for (int i = 0; i < allAnswers.size(); i++)
        {
            if (studAnswers.size() > i)
            {
                if (allAnswers.get(i).equals(studAnswers.get(i)))
                {
                    ansPoints.add(pointPerQues);
                }
                else
                {
                    ansPoints.add(-1);
                }
            }
            else
            {
                ansPoints.add(0);
            }
        }
    }

    public void createFromStudentAnswers (StudentAnswers studAns)
    {
        this.questions = studAns.questions;
        this.idKey = studAns.idKey;

        processCorrectAnswers();
    }

    public List<ArrayList<Integer>> getAnsPoint ()
    {
        return ansPoint;
    }

    public void setAnsPoint (List<ArrayList<Integer>> ansPoint)
    {
        this.ansPoint = ansPoint;
    }

    private int processGlobalPoints (List<Integer> points)
    {
        int point = processGlobalOpenPoints(points);

        int retPoint=0;
        for (int i = 0; i < points.size(); i++)
        {
            point = points.get(i);

            if (-1 == point)
            {
                retPoint = 0;
                break;
            }
            else if (0 == point)
            {
                
            }
            else
            {
                retPoint = 1;
            }
        }

        return retPoint;
    }

    private int processGlobalOpenPoints (List<Integer> points)
    {
        if (points.isEmpty())
        {
            return NOCHECK_QUES;
        }
        else
        {
            return 0;
        }
    }

    public List<Integer> getGlobPoints ()
    {
        return globPoints;
    }

    public void setGlobPoints (List<Integer> globPoints)
    {
        this.globPoints = globPoints;
    }
}
