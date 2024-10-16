package logic.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import logic.exams.IExam;
import logic.exams.ISection;
import logic.exams.Section;
import logic.question.interfaces.IQuestion;
import logic.question.interfaces.IQuestions;

/**
 *
 * @author michal
 */
public class ExamExport
{
    private IExporter exporter;
    private IQuestions questions;
    private IExam exam;

    /**
     * Tworzy obiekt, który pomaga eksportować konfigurację egzaminu wraz z
     * potrzebnymi pytaniami do formatu obsługiwanego przez 'type'.
     * @param type Obiekt, który konwertuje eksport do obsługiwanego formatu.
     *	    Patrz na odnośniki - klasy implementujące interfejs IExporter.
     * @param questions Obiekt odpowiedzialny za dostęp do pytań.
     * @param exam Obiekt egzaminu, który chcemy wyeksportować.
     * @see RawObjectExporter
     */
    public ExamExport (IExporter type, IQuestions questions, IExam exam)
    {
        this.exporter = type;
        this.questions = questions;
        this.exam = exam;
    }

    /**
     *Eksportuje egzamin podany w konstruktorze do reprezantacji plikowej.
     * @param filename Nazwa pliku.
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void export2File ()
            throws SQLException, FileNotFoundException, IOException,
            QuestionMatchException, Exception
    {
        this.exporter.addExam(createExportedExam());
        this.exporter.addQuestionList(getQuestions());
        this.exporter.generate();
    }

    /**
     * Pobiera listę pytań z bazy danych, które odpowiadają sekcjom w this.exam
     * @return lista pytań IQuestion
     * @throws SQLException
     */
    private List<IQuestion> getQuestions ()
            throws SQLException, QuestionMatchException
    {
        List questionList = new ArrayList<IQuestion>();
        List sectionList = this.exam.getSections();

        Iterator sectionIt = sectionList.iterator();

        while (sectionIt.hasNext())
        {
            List<IQuestion> list = new ArrayList<IQuestion>();

            ISection section = (ISection) sectionIt.next();
            list = getSectionQuestions(section);

            addUniqueQuestions(questionList, list);
        }
        return questionList;
    }

    private List<IQuestion> getSectionQuestions (ISection section)
            throws SQLException, QuestionMatchException
    {
        List questionList;
        HashMap filter = new HashMap();
        filter.put("type", section.getType());
        filter.put("minGrade", section.getGradeMin());
        filter.put("maxGrade", section.getGradeMax());
        filter.put("category", section.getCategory());

        questionList = this.questions.get(filter);
        System.out.println("Uzyskane pytania dla sekcji: " + questionList.size());
        if (questionList.size() < section.getQuestionsNo())
        {
            throw new QuestionMatchException(section.getId(), section.getName());
        }

        return questionList;
    }

    /**
     * Dodaje pytania do kontenera tak, aby sie w nim nie powtarzaly
     * @param Container to lista, która ma zawierać unikalne pytania
     * @param questions to lista pytań, które chcemy dodać
     */
    private void addUniqueQuestions (List<IQuestion> container,
            List<IQuestion> questions)
    {
        Iterator it = questions.iterator();

        while (it.hasNext())
        {	    // sprawdza kazdy element, ktory chcemy dodac
            IQuestion bean = (IQuestion) it.next();
            boolean unique = true;
            Iterator containerIt = container.iterator();

            // porownuje nowy element z przechowanymi elementami w container
            while (containerIt.hasNext())
            {
                IQuestion storedBean = (IQuestion) containerIt.next();
                if (storedBean.getId() == bean.getId())
                {
                    unique = false;
                }
            }
            if (unique)
            {
                container.add(bean);
            }
        }
    }

    private ExportedExam createExportedExam ()
            throws Exception
    {
        ExportedExam exportedExam = new ExportedExam(this.exam.getId(), this.exam.
                getName(), this.exam.getDate());
        List<ISection> sectionList = this.exam.getSections();
        Iterator it = sectionList.iterator();

        while (it.hasNext())
        {
            ExportedSection exportedSection = new ExportedSection((Section) it.
                    next());
            List<IQuestion> questionList = getSectionQuestions(exportedSection);

            Iterator questionIterator = questionList.iterator();
            while (questionIterator.hasNext())
            {
                exportedSection.addQuestionInSection(((IQuestion) questionIterator.
                        next()).getId());
            }

            exportedExam.addSection(exportedSection);
        }

        return exportedExam;
    }
}
