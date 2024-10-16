/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.checkExam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michal
 */
public class ExportQuestion implements Serializable
{
    private List<String> answersStud = new ArrayList();
    private List<String> correctAnswers = new ArrayList<String>();
    private List<String> allAnswers = new ArrayList<String>();
    private int id;
    private String content;
    private int type;
    private int point;

    public int getPoint ()
    {
        return point;
    }

    public void setPoint (int point)
    {
        this.point = point;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public int getType ()
    {
        return type;
    }

    public void setType (int type)
    {
        this.type = type;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public void addAnswerStud (String answer)
    {
        getAnswersStud().add(answer);
    }

    /**
     * @param allAnswers the allAnswers to set*/
    public void addAllAnswers (String ans)
    {
        getAllAnswers().add(ans);
    }

    /**
     * @return the answersStud
     */
    public List<String> getAnswersStud ()
    {
        return answersStud;
    }

    /**
     * @param answersStud the answersStud to set
     */
    public void setAnswersStud (List<String> answersStud)
    {
        this.answersStud = answersStud;
    }

    /**
     * @return the correctAnswers
     */
    public List<String> getCorrectAnswers ()
    {
        return correctAnswers;
    }

    /**
     * @param correctAnswers the correctAnswers to set
     */
    public void setCorrectAnswers (List<String> correctAnswers)
    {
        this.correctAnswers = correctAnswers;
    }

    /**
     * @return the allAnswers
     */
    public List<String> getAllAnswers ()
    {
        return allAnswers;
    }

    /**
     * @param allAnswers the allAnswers to set
     */
    public void setAllAnswers (List<String> allAnswers)
    {
        this.allAnswers = allAnswers;
    }
}
