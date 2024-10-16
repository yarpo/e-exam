package data.bean;
/**
 *
 * @author michal
 */
public class QuestionSrcBean
{
    protected int id;
    protected String content;
    protected int grade;
    protected int type;
    protected String comment;
    protected String category;
    final static private String NO_COMMENT = "";

    public QuestionSrcBean (int id, String content, int grade, int type,
            String comment, String category)
    {
        this.id = id;
        this.content = content;
        this.grade = grade;
        this.type = type;
        this.comment = comment;
        this.category = category;
    }

    public QuestionSrcBean (int id, String content, int grade, int type,
            String category)
    {
        this(id, content, grade, type, "", category);
    }

    public QuestionSrcBean (String content, int grade, int type, String comment,
            String category)
    {
        this(0, content, grade, type, comment, category);
    }

    public QuestionSrcBean ()
    {
    }

    public int getGrade ()
    {
        return grade;
    }

    public void setGrade (int grade)
    {
        this.grade = grade;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public int getType ()
    {
        return type;
    }

    public void setType (int type)
    {
        this.type = type;
    }

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getComment ()
    {
        return this.comment;
    }

    public void setComment (String comment)
    {
        this.comment = comment;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }
}
