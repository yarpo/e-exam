package data.bean;
/**
 *
 * @author michal
 */
public class AnswerGapSrcBean
{
    protected int id;
    protected int questionId;
    protected String content;
    protected int gapNumber;

    public AnswerGapSrcBean (int id, int questionId, String content,
            int gapNumber)
    {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.gapNumber = gapNumber;
    }

    public AnswerGapSrcBean (int questionId, String content, int gapNumber)
    {
        this(0, questionId, content, gapNumber);
    }

    public AnswerGapSrcBean ()
    {
    }

    public int getGapNumber ()
    {
        return gapNumber;
    }

    public void setGapNumber (int iGapNumber)
    {
        this.gapNumber = iGapNumber;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int iId)
    {
        this.id = iId;
    }

    public int getQuestionId ()
    {
        return questionId;
    }

    public void setQuestionId (int iQuestionId)
    {
        this.questionId = iQuestionId;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String sContent)
    {
        this.content = sContent;
    }
}
