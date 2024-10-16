package models.imports;

import beans.imports.AnswerABCD;
import beans.imports.AnswerGap;
import beans.imports.Answers;
import beans.imports.Exam;
import beans.imports.Question;
import beans.imports.Section;
import beans.imports.SectionABCD;
import beans.imports.SectionGap;
import beans.imports.interfaces.IQuestion;
import com.thoughtworks.xstream.XStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michal
 */
public class XMLImporter implements IImporter{
    protected InputStream inputStream;
    protected Exam exam;
    protected List<IQuestion> questionList;

    public XMLImporter(String filename) throws FileNotFoundException {
	this.inputStream = new FileInputStream(filename);
	this.questionList = new ArrayList<IQuestion>();
    }

    public XMLImporter(InputStream input) {
	this.inputStream = input;
	this.questionList = new ArrayList<IQuestion>();
    }

    public void importExam() throws IOException, ClassNotFoundException{
	XStream xstream = new XStream();
	ObjectInputStream input = xstream.createObjectInputStream(this.inputStream);

	xstream.alias("Question", Question.class);
	xstream.alias("Exam", Exam.class);
	xstream.alias("Answers", Answers.class);
	xstream.alias("AnswerABCD", AnswerABCD.class);
	xstream.alias("AnswerGap", AnswerGap.class);
	xstream.alias("Section", Section.class);
	xstream.alias("SectionABCD", SectionABCD.class);
	xstream.alias("SectionGap", SectionGap.class);

	readExam(input);
	readQuestions(input);

	input.close();
    }

    public Exam getExam() throws IOException{
	if (this.exam == null) {
	    throw new IOException("Nie ma egzaminu");
	}
	return this.exam;
    }

    public List<IQuestion> getQuestions() throws IOException {
	if (this.questionList == null) {
	    throw new IOException("Nie ma pytan");
	}
	return this.questionList;
    }

    protected void readExam(ObjectInputStream input) throws IOException, ClassNotFoundException {
	
	this.exam = (Exam) input.readObject();
    }

    protected void readQuestions(ObjectInputStream input) throws IOException, ClassNotFoundException {
	int size = input.readInt();

	while (0 < size--) {
	    Question bean = (Question) input.readObject();
	    this.questionList.add(bean);
	}
    }

    @Override
    protected void finalize() throws Throwable {
	try {
	    this.inputStream.close();        // close open files
        } finally {
	    super.finalize();
        }
    }


}
