/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.exams;

import data.bean.ExamSrcBean;
import data.bean.SectionABCDSrcBean;
import data.bean.SectionGapSrcBean;
import data.bean.SectionSrcBean;
import data.interfaces.*;
import data.staticClass.TableObjectFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author yarpo
 */
public class Exam implements IExam
{
    protected int examId;
    protected Date date;
    protected String name;
    public List<ISection> sections;
    protected ExamSrcBean exam;
    /**
     * jesli examId ma taka wartosc to znaczy, ze jeszcze nie zostal zapisany egzamin
     */
    public static final int NOT_YET_SAVED = -1;

    /**
     * @param int SectionSrcBean - identyfikator egzaminu
     * @param Date date - data utworzenia
     * @param String name
     */
    public Exam (int id, String name, Date date)
            throws Exception
    {
        init(id, name, date);
    }

    private void init (int id, String name, Date date)
            throws Exception
    {
        examId = id;
        this.date = date;
        this.name = name;
        exam = new ExamSrcBean(id, name, date);
        getSectionsForThisExam();
    }

    /**
     * Pobiera na stworzenie calego obiektu z beanu
     */
    public Exam (ExamSrcBean exam)
            throws Exception
    {
        this(exam.getId(), exam.getName(), exam.getDate());
    }

    /**
     * Pobiera na stworzenie calego obiektu z beanu
     */
    public Exam (int id)
            throws Exception
    {
        IExamSrc examSrc = TableObjectFactory.getInstance().getExam();
        ExamSrcBean examBean = examSrc.getById(id);
        init(examBean.getId(), examBean.getName(), examBean.getDate());
    }

    /**
     * Pobiera wszystkie sekcje przypisane do tego egzaminu
     */
    private List<ISection> getSectionsForThisExam ()
            throws Exception
    {
        ISectionSrc sectionSrc = TableObjectFactory.getInstance().getSection();
        List<SectionSrcBean> sectionBeans = sectionSrc.get(examId);
        this.sections = new ArrayList<ISection>();

        for (SectionSrcBean section : sectionBeans)
        {
            if (section.getType() == ISection.OPENED)
            {
                this.sections.add((ISection) new Section(section));
            }
            else if (section.getType() == ISection.GAP)
            {
                ISectionGapSrc sectionGapSrc = TableObjectFactory.getInstance().
                        getSectionGap();
                List<SectionGapSrcBean> sectionGapBeans = sectionGapSrc.get(section.
                        getId());
                this.sections.add((ISection) new SectionGap(section, sectionGapBeans.
                        get(0)));
            }
            else if (section.getType() == ISection.ABCD)
            {
                ISectionABCDSrc sectionABCDSrc = TableObjectFactory.getInstance().
                        getSectionABCD();
                List<SectionABCDSrcBean> sectionABCDBeans = sectionABCDSrc.get(section.
                        getId());
                this.sections.add((ISection) new SectionABCD(section, sectionABCDBeans.
                        get(0)));
            }
        }

        return this.sections;
    }

    /**
     * Pozwala stworzyc nowy egzamin
     * @param String name - nazwa egzaminu
     */
    public Exam (String name)
    {
        this.name = name;
        date = new Date();
        // po zapisaniu egzaminu dostaje on dopiero swoj SectionSrcBean
        examId = NOT_YET_SAVED;
        sections = new ArrayList<ISection>();
        exam = new ExamSrcBean(examId, name, date);
    }

    /**
     * Dodaje nowa sekcje do egzaminu
     * @param section obiekt sekcji
     */
    public void addSection (ISection section)
            throws Exception
    {
        // jesli ustali NOT_YET_SAVED, to nie bedzie usuwal w daleteSections
        section.setExamId(examId);
        sections.add(section);
    }

    /**
     * Zapisuje nowy egzamin / nadpisuje istniejacy
     */
    public void save ()
            throws Exception
    {
        IExamSrc examSrc = TableObjectFactory.getInstance().getExam();
        if (NOT_YET_SAVED == examId)
        {
            examSrc.add(exam);
            examId = examSrc.getLastExamId();
        }
        else
        {
            examSrc.update(exam);
            deleteSections();
        }
        saveSections();
    }

    private void saveSections ()
            throws Exception
    {

        for (ISection section : sections)
        {
            System.out.println("Zapisuje sekcje");
            section.setExamId(examId);
            if (section.getType() == ISection.OPENED)
            {
                section.save();
            }
            else if (section.getType() == ISection.GAP)
            {
                SectionGap s = (SectionGap) section;
                s.save();
            }
            else if (section.getType() == ISection.ABCD)
            {
                System.out.println("Zapisuje sekcje ABCD");
                SectionABCD s = (SectionABCD) section;
                s.save();
            }
        }
    }

    private void deleteSections ()
            throws Exception
    {
        for (ISection section : sections)
        {
            if (section.getExamId() != NOT_YET_SAVED)
            {
                if (section.getType() == ISection.OPENED)
                {
                    section.delete();
                }
                else if (section.getType() == ISection.GAP)
                {
                    SectionGap s = (SectionGap) section;
                    s.delete();
                }
                else if (section.getType() == ISection.ABCD)
                {
                    SectionABCD s = (SectionABCD) section;
                    s.delete();
                }
            }
        }
    }

    public int getId ()
    {
        return examId;
    }

    public String getName ()
    {
        return name;
    }

    public Date getDate ()
    {
        return date;
    }

    public void setId (int id)
    {
        examId = id;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void setDate (Date date)
    {
        this.date = date;
    }

    public List<ISection> getSections ()
    {
        return sections;
    }

    public void delete ()
            throws Exception
    {
        deleteSections();
        IExamSrc examSrc = TableObjectFactory.getInstance().getExam();
        examSrc.delete(exam.getId());
    }
}
