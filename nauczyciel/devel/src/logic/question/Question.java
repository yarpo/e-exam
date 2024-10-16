package logic.question;

import data.staticClass.TypeCalculator;
import java.io.Serializable;
import java.util.List;
import logic.question.interfaces.IAnswerABCD;
import logic.question.interfaces.IAnswerGap;
import logic.question.interfaces.IAnswers;
import logic.question.interfaces.IQuestion;

/**
 *
 * @author michal
 */
public class Question implements IQuestion, Serializable
{
    protected int id;
    protected int type;
    protected String content;
    protected int grade;
    protected IAnswers answers;
    protected String comment;
    protected String category;

    public void setCategory (String category)
    {
        this.category = category;
    }
    static private String NO_COMMENT = "";

    public Question ()
    {
    }

    public Question (int type, String content, int grade)
    {
        this(type, content, grade, NO_COMMENT);
    }

    public Question (int id, int type, String content, int grade)
    {
        this(id, type, content, grade, NO_COMMENT);
    }

    public Question (int type, String content, int grade, String comment)
    {
        this(0, type, content, grade, comment);
    }

    public Question (int id, int type, String content, int grade, String comment)
    {
        this.id = id;
        this.type = type;
        this.content = content;
        this.grade = grade;
        this.comment = comment;
    }

    public int getId ()
    {
        return this.id;
    }

    public String getContent ()
    {
        return this.content;
    }

    public int getGrade ()
    {
        return this.grade;
    }

    public int getType ()
    {
        return this.type;
    }

    public boolean isTypeOpened ()
    {
        return TypeCalculator.isOpen(type);
    }

    public boolean isTypeABCD ()
    {
        return TypeCalculator.isABCD(type);
    }

    public boolean isTypeGap ()
    {
        return TypeCalculator.isGap(type);
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public void setGrade (int grade)
    {
        this.grade = grade;
    }

    public void setType (int type)
    {
        this.type = type;
    }

    public String getComment ()
    {
        return this.comment;
    }

    public void setComment (String comment)
    {
        this.comment = comment;
    }

    public void setTypeOpened (boolean isOpened)
    {
        if (isOpened)
        {
            this.type = this.type | TypeCalculator.setOpen();
        }
        else
        {
            this.type = this.type & (~TypeCalculator.setOpen());
        }
    }

    public void setTypeABCD (boolean isABCD)
    {
        if (isABCD)
        {
            this.type = this.type | TypeCalculator.setABCD();
        }
        else
        {
            this.type = this.type & (~TypeCalculator.setABCD());
        }
    }

    public void setTypeGap (boolean isGap)
    {
        if (isGap)
        {
            this.type = this.type | TypeCalculator.setGap();
        }
        else
        {
            this.type = this.type & (~TypeCalculator.setGap());
        }
    }

    public IAnswers getAnswers ()
    {
        return answers;
    }

    public void setAnsQuesId (int quesId)
    {
        if (answers != null)
        {
            List<IAnswerABCD> ansAbcd = answers.getAnswersABCD();
            List<IAnswerGap> ansGap = answers.getAnswersGap();

            for (int i = 0; i < ansAbcd.size(); i++)
            {
                ansAbcd.get(i).setQuestionId(quesId);
            }
            for (int i = 0; i < ansGap.size(); i++)
            {
                ansGap.get(i).setQuestionId(quesId);
            }
        }
    }

    public void setAnswers (IAnswers ans)
    {
        answers = ans;
    }

    public String getCategory ()
    {
        return category;
    }
}
