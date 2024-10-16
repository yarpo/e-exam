package logic.question;

import java.io.Serializable;
import logic.question.interfaces.IAnswerGap;

/**
 *
 * @author michal
 */
public class AnswerGap implements IAnswerGap, Serializable
{
    protected int id;
    protected int gapNumber;
    protected String content;
    protected int questionId;

    public AnswerGap (int id, int gapNumber, String content, int questionId)
    {
        this.id = id;
        this.gapNumber = gapNumber;
        this.content = content;
        this.questionId = questionId;
    }

    public AnswerGap (int gapNumber, String content, int questionId)
    {
        this(0, gapNumber, content, questionId);
    }

    public AnswerGap ()
    {
    }

    public int getGapNumber ()
    {
        return this.gapNumber;
    }

    public void setGapNumber (int gapNumber)
    {
        this.gapNumber = gapNumber;
    }

    public int getId ()
    {
        return this.id;
    }

    public String getContent ()
    {
        return this.content;
    }

    public int getQuestionId ()
    {
        return this.questionId;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public void setQuestionId (int questionId)
    {
        this.questionId = questionId;
    }
}
