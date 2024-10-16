package models.imports;

import beans.imports.Exam;
import beans.imports.interfaces.IQuestion;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author michal
 */
public interface IImporter {
    void importExam() throws IOException, ClassNotFoundException;
    Exam getExam() throws IOException;
    List<IQuestion> getQuestions() throws IOException;
}
