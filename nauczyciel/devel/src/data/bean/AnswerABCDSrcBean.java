package data.bean;
/**
 *
 * @author michal
 */
public class AnswerABCDSrcBean
{
    protected int id;
    protected int questionId;
    protected String content;
    protected boolean correct;

    public AnswerABCDSrcBean (int id, int questionId, String content,
            boolean correct)
    {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.correct = correct;
    }

    public AnswerABCDSrcBean (int questionId, String content, boolean correct)
    {
        this(0, questionId, content, correct);
    }

    public AnswerABCDSrcBean ()
    {
    }

    public boolean isCorrect ()
    {
        return correct;
    }

    public void setCorrect (boolean bCorrect)
    {
        this.correct = bCorrect;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public int getQuestionId ()
    {
        return questionId;
    }

    public void setQuestionId (int questionId)
    {
        this.questionId = questionId;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }
}
