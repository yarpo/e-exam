/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.exams;

import data.bean.SectionGapSrcBean;
import data.bean.SectionSrcBean;
import data.interfaces.ISectionGapSrc;
import data.staticClass.TableObjectFactory;
import java.util.List;

/**
 *
 * @author yarpo
 */
public class SectionGap extends Section
{
    protected boolean underline;
    protected boolean shownChars;
    protected int howMuchChars;
    protected int charsMode;

    public SectionGap (int id, int examId, int category, int type,
            int questionsNo, int gradeMin, int gradeMax,
            int points, boolean underline, boolean shownChars,
            int howMuchChars, int charsMode, String name)
    {
        super(id, examId, category, type, questionsNo, gradeMin, gradeMax,
                points, name);
        this.underline = underline;
        this.shownChars = shownChars;
        this.howMuchChars = howMuchChars;
        this.charsMode = charsMode;
    }

    public SectionGap (SectionSrcBean bean, SectionGapSrcBean gapBean)
    {
        this(bean.getId(), bean.getExamId(), bean.getCategoryId(),
                bean.getType(), bean.getHowMuch(), bean.getGradeMin(),
                bean.getGradeMax(), bean.getPoints(), gapBean.isUnderline(),
                gapBean.isShowChars(), gapBean.getHowMuchChars(),
                gapBean.getCharsMode(), bean.getName());
    }

    public int getCharsMode ()
    {
        return charsMode;
    }

    public void setCharsMode (int charsMode)
    {
        this.charsMode = charsMode;
    }

    public int getHowMuchChars ()
    {
        return howMuchChars;
    }

    public void setHowMuchChars (int howMuchChars)
    {
        this.howMuchChars = howMuchChars;
    }

    public boolean isShownChars ()
    {
        return shownChars;
    }

    public void setShownChars (boolean shownChars)
    {
        this.shownChars = shownChars;
    }

    public boolean isUnderline ()
    {
        return underline;
    }

    public void setUnderline (boolean underline)
    {
        this.underline = underline;
    }

    @Override
    public void save ()
            throws Exception
    {
        _save();
        ISectionGapSrc sectionSrc = TableObjectFactory.getInstance().
                getSectionGap();
        SectionGapSrcBean section = new SectionGapSrcBean(this.getId(),
                this.isUnderline(), this.isShownChars(), this.getHowMuchChars(),
                this.getCharsMode());
        sectionSrc.save(section);
    }

    @Override
    public void delete ()
            throws Exception
    {
        ISectionGapSrc sectionSrc = TableObjectFactory.getInstance().
                getSectionGap();
        sectionSrc.delete(this.getId());
    }

    public void update (SectionGapSrcBean section)
            throws Exception
    {
        ISectionGapSrc sectionSrc = TableObjectFactory.getInstance().
                getSectionGap();
        sectionSrc.update(section);
    }

    public List<SectionGapSrcBean> get (int taskId)
            throws Exception
    {
        ISectionGapSrc sectionSrc = TableObjectFactory.getInstance().
                getSectionGap();
        List<SectionGapSrcBean> sections = sectionSrc.get(id);
        return sections;
    }
}
