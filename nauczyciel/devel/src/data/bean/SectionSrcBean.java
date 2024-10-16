package data.bean;
/**
 *
 * @author michal
 */
public class SectionSrcBean
{
    protected int id;
    protected int examId;
    protected int categoryId;
    protected int type;
    protected int howMuch;
    protected int gradeMin;
    protected int gradeMax;
    protected int points;
    protected String name;

    public SectionSrcBean (int sectionId, int examId, int categoryId, int type,
            int howMuch, int gradeMin, int gradeMax, int points, String name)
    {
        this.id = sectionId;
        this.examId = examId;
        this.categoryId = categoryId;
        this.type = type;
        this.howMuch = howMuch;
        this.gradeMin = gradeMin;
        this.gradeMax = gradeMax;
        this.points = points;
        this.name = name;
    }

    public SectionSrcBean (int sectionId, int examId, int categoryId, int type,
            int howMuch, int gradeMin, int gradeMax, int points)
    {
        this(sectionId, examId, categoryId, type, howMuch, gradeMin, gradeMax,
                points, "");
    }

    public SectionSrcBean ()
    {
        this(0, 0, 0, 0, 0, 0, 0);
    }

    public SectionSrcBean (int examId, int categoryId, int type, int howMuch,
            int gradeMin, int gradeMax, int points)
    {
        this(0, examId, categoryId, type, howMuch, gradeMin, gradeMax, points);
    }

    public int getCategoryId ()
    {
        return categoryId;
    }

    public void setCategoryId (int categoryId)
    {
        this.categoryId = categoryId;
    }

    public int getExamId ()
    {
        return examId;
    }

    public void setExamId (int examId)
    {
        this.examId = examId;
    }

    public int getGradeMax ()
    {
        return gradeMax;
    }

    public void setGradeMax (int gradeMax)
    {
        this.gradeMax = gradeMax;
    }

    public int getGradeMin ()
    {
        return gradeMin;
    }

    public void setGradeMin (int gradeMin)
    {
        this.gradeMin = gradeMin;
    }

    public int getHowMuch ()
    {
        return howMuch;
    }

    public void setHowMuch (int howMuch)
    {
        this.howMuch = howMuch;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public int getPoints ()
    {
        return points;
    }

    public void setPoints (int points)
    {
        this.points = points;
    }

    public int getType ()
    {
        return type;
    }

    public void setType (int type)
    {
        this.type = type;
    }

    public String getName ()
    {
        return this.name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
}
