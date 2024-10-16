package models.imports;

import beans.imports.Exam;
import beans.imports.interfaces.IQuestion;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author michal
 */
public class ExamImport {
    protected IImporter importer;

    public ExamImport(IImporter importType) {
	this.importer = importType;
    }

    public void importFromFile() throws IOException, ClassNotFoundException {
	this.importer.importExam();
    }

    public void importFromInputStream() throws IOException, ClassNotFoundException {
	this.importer.importExam();

    }

    public Exam getExam() throws IOException {
	return this.importer.getExam();
    }

    public List<IQuestion> getQuestions() throws IOException {
	return this.importer.getQuestions();
    }
    
}
