/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.export;

import com.thoughtworks.xstream.XStream;
import data.bean.SectionSrcBean;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import logic.exams.SectionABCD;
import logic.exams.SectionGap;
import logic.question.AnswerABCD;
import logic.question.AnswerGap;
import logic.question.Answers;
import logic.question.Question;
import logic.question.interfaces.IQuestion;

/**
 *
 * @author michal
 */
public class XMLExporter implements IExporter
{
    private String filename;
    private ExportedExam exam;
    private List<IQuestion> questionList;

    public XMLExporter (String filename)
    {
        this.filename = filename;
    }

    public void addExam (ExportedExam exam)
    {
        this.exam = exam;
        this.questionList = new ArrayList<IQuestion>();
    }

    public void addQuestion (IQuestion question)
    {
        this.questionList.add(question);
    }

    public void addQuestionList (List<IQuestion> questions)
    {
        this.questionList.addAll(questions);
    }

    public void generate ()
            throws FileNotFoundException, IOException, Exception
    {
        XStream xstream = new XStream();
        FileOutputStream file = new FileOutputStream(this.filename);
        ObjectOutputStream output = xstream.createObjectOutputStream(file);

        xstream.alias("Question", Question.class);
        xstream.alias("Exam", ExportedExam.class);
        xstream.alias("Answers", Answers.class);
        xstream.alias("AnswerABCD", AnswerABCD.class);
        xstream.alias("AnswerGap", AnswerGap.class);
        xstream.alias("Section", ExportedSection.class);
        xstream.alias("SectionABCD", SectionABCD.class);
        xstream.alias("SectionGap", SectionGap.class);
        writeExam(output);
        writeQuestions(output);

        output.close();
    }

    private void writeExam (ObjectOutputStream output)
            throws Exception
    {
        output.writeObject(this.exam);
    }

    private void writeQuestions (ObjectOutputStream output)
            throws IOException
    {
        output.writeInt(this.questionList.size());
        Iterator it = this.questionList.iterator();
        while (it.hasNext())
        {
            output.writeObject(it.next());
        }
    }
}
