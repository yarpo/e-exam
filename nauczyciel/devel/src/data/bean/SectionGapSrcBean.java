package data.bean;
/**
 *
 * @author michal
 */
public class SectionGapSrcBean
{
    protected int sectionId;
    protected boolean underline;
    protected boolean shownChars;
    protected int howMuchChars;
    protected int charsMode;

    public SectionGapSrcBean (int sectionId, boolean underline,
            boolean showChars, int howMuchChars, int charsMode)
    {
        this.sectionId = sectionId;
        this.underline = underline;
        this.shownChars = showChars;
        this.howMuchChars = howMuchChars;
        this.charsMode = charsMode;
    }

    public SectionGapSrcBean (int sectionId, boolean underline,
            boolean showChars)
    {
        this(sectionId, underline, showChars, 0, 0);
    }

    public SectionGapSrcBean ()
    {
    }

    public int getHowMuchChars ()
    {
        return howMuchChars;
    }

    public void setHowMuchChars (int howMuchChars)
    {
        this.howMuchChars = howMuchChars;
    }

    public boolean isShowChars ()
    {
        return shownChars;
    }

    public void setShowChars (boolean showChars)
    {
        this.shownChars = showChars;
    }

    public boolean isUnderline ()
    {
        return underline;
    }

    public void setUnderline (boolean underline)
    {
        this.underline = underline;
    }

    public int getCharsMode ()
    {
        return charsMode;
    }

    public void setCharsMode (int charsMode)
    {
        this.charsMode = charsMode;
    }

    public int getSectionId ()
    {
        return sectionId;
    }

    public void setSectionId (int sectionkId)
    {
        this.sectionId = sectionkId;
    }
}
