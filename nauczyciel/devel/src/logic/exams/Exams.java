/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.exams;

import data.bean.ExamSrcBean;
import data.interfaces.IExamSrc;
import data.staticClass.TableObjectFactory;
import java.util.ArrayList;
import java.util.List;
import logic.exams.exceptions.NoExamFoundException;
/**
 *
 * @author yarpo
 */
public class Exams implements IExams
{
    /**
     * Lista egzaminow odczytanych z bd
     */
    protected ArrayList<ExamSrcBean> oExams;

    /**
     * Zwraca jeden konkretny egzamin o zadanym SectionSrcBean
     *
     * @param int SectionSrcBean
     */
    public IExam getById (int id)
            throws Exception
    {
        ExamSrcBean exam;

        try
        {
            exam = searchExamInList(id);
        }
        catch (NoExamFoundException ex)
        {
            exam = readFromDbById(id);
        }

        return new Exam(exam);
    }

    /**
     * Szuka egzaminu o zadanym SectionSrcBean w
     */
    private ExamSrcBean searchExamInList (int id)
            throws NoExamFoundException
    {
        for (ExamSrcBean e : oExams)
        {
            if (e.getId() == id)
            {
                return e;
            }
        }

        throw new NoExamFoundException(
                "Nie znaleziono egzaminu o id " + id + " na liscie");
    }

    /**
     * Odczytuje z bazy danych egzamin o zadanym SectionSrcBean
     *
     * @param int SectionSrcBean
     */
    private ExamSrcBean readFromDbById (int id)
            throws Exception
    {
        IExamSrc examSrc = TableObjectFactory.getInstance().getExam();
        ExamSrcBean exam = examSrc.getById(id);

        return exam;
    }

    /**
     * Dodaje egzamin do listy
     *
     * @param IExam exam
     */
    public void add (ExamSrcBean exam)
            throws Exception
    {
        IExamSrc examSrc = TableObjectFactory.getInstance().getExam();
        examSrc.add(exam);
    }

    /**
     * Zapisz do bazy pojedynczy egzamin wraz z sekcjami
     * egzamin wczesniej nie istnieje
     * by Sokol
     */
    public void saveWithSections (IExam exam)
            throws Exception
    {
        exam.save(); //zapisanie egzaminu - tam jest zapisywanie także sekcji
    }

    /**
     *
     */
    public void updateWithSections (IExam exam)
            throws Exception
    {
        saveWithSections(exam);
    }

    /**
     * Zwraca cala liste egzaminow
     */
    public ArrayList<Exam> getAllExams ()
            throws Exception
    {
        IExamSrc exam = TableObjectFactory.getInstance().getExam();
        List<ExamSrcBean> examBeans = exam.get();
        ArrayList<Exam> exams = new ArrayList<Exam>();
        Exam tempExam;

        //pobierz egzaminy
        for (ExamSrcBean bean : examBeans)
        {
            tempExam = new Exam(bean.getId(), bean.getName(), bean.getDate());
            exams.add(tempExam);
        }

        //dodaj do nich sekcje
        for (IExam ex : exams)
        {
            ex.getSections();
        }

        return exams;
    }

    /**
     * zapisuje liste egzaminow
     */
    public void save ()
            throws Exception
    {
        IExamSrc examSrc = TableObjectFactory.getInstance().getExam();

        for (ExamSrcBean exam : oExams)
        {
            examSrc.update(exam);
        }
    }

    public void delete (int id)
            throws Exception
    {
        try
        {
            Exam exam = new Exam(id);
            exam.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void delete (ExamSrcBean exam)
            throws Exception
    {
        if (exam.getId() != Exam.NOT_YET_SAVED)
        {
            delete(exam.getId());
            return;
        }
        throw new NoExamFoundException(
                "Podany obiekt egzaminu nie jest jeszcze zapisany");
    }
}
