package logic.export;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import logic.exams.IExam;
import logic.question.interfaces.IQuestion;

/**
 * Powinny go implementować wszystkie klasy, które chcą być odpowiedzialne
 * za eksport danych do odpowiednich formatów. Wykorzystywany przez
 * logic.export.ExamExport
 * @author michal
 * @see ExamExport
 */
public interface IExporter
{
    void addExam (ExportedExam exam);

    void addQuestion (IQuestion question);

    void addQuestionList (List<IQuestion> questions);

    void generate ()
            throws FileNotFoundException, IOException, Exception;
}
