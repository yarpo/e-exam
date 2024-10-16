package data.bean;
/**
 *
 * @author michal
 */
public class SectionABCDSrcBean
{
    protected int sectionId;
    protected int all;
    protected int correct;

    public SectionABCDSrcBean ()
    {
    }

    public SectionABCDSrcBean (int sectionId, int all, int correct)
    {
        this.sectionId = sectionId;
        this.all = all;
        this.correct = correct;
    }

    public int getAll ()
    {
        return all;
    }

    public void setAll (int all)
    {
        this.all = all;
    }

    public int getCorrect ()
    {
        return correct;
    }

    public void setCorrect (int correct)
    {
        this.correct = correct;
    }

    public int getSectionId ()
    {
        return sectionId;
    }

    public void setSectionId (int sectionId)
    {
        this.sectionId = sectionId;
    }
}
