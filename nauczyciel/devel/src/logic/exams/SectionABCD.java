/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.exams;

import data.bean.SectionABCDSrcBean;
import data.bean.SectionSrcBean;
import data.interfaces.ISectionABCDSrc;
import data.staticClass.TableObjectFactory;

/**
 *
 * @author yarpo
 */
public class SectionABCD extends Section
{
    protected int all;
    protected int correct;

    public SectionABCD (int id, int examId, int category, int type,
            int questionsNo, int gradeMin, int gradeMax,
            int points, int all, int correct, String name)
    {
        super(id, examId, category, type, questionsNo, gradeMin, gradeMax,
                points, name);
        this.all = all;
        this.correct = correct;
    }

    public SectionABCD (SectionSrcBean bean, SectionABCDSrcBean abcdBean)
    {
        this(bean.getId(), bean.getExamId(), bean.getCategoryId(),
                bean.getType(), bean.getHowMuch(), bean.getGradeMin(),
                bean.getGradeMax(), bean.getPoints(), abcdBean.getAll(),
                abcdBean.getCorrect(), bean.getName());
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

    @Override
    public void save ()
            throws Exception
    {
        _save();
        ISectionABCDSrc sectionSrc = TableObjectFactory.getInstance().
                getSectionABCD();
        SectionABCDSrcBean section = new SectionABCDSrcBean(this.getId(),
                this.getAll(), this.getCorrect());
        sectionSrc.save(section);
    }

    @Override
    public void update ()
            throws Exception
    {
        _update();
        ISectionABCDSrc sectionSrc = TableObjectFactory.getInstance().
                getSectionABCD();
        SectionABCDSrcBean bean = new SectionABCDSrcBean(this.getId(),
                this.getAll(), this.getCorrect());
        sectionSrc.update(bean);
    }

    @Override
    public void delete ()
            throws Exception
    {
        _delete();
        ISectionABCDSrc sectionSrc = TableObjectFactory.getInstance().
                getSectionABCD();
        sectionSrc.delete(this.getId());
    }
}
