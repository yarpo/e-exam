/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.checkExam;

import com.thoughtworks.xstream.XStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.question.Question;

/**
 *
 * @author Michal
 */
public class CheckExam
{
    private List<StudentAnswers> loadExamFromFile (String fileExamName)
    {
        List<StudentAnswers> answers = new ArrayList<StudentAnswers>();

        XStream xStream = new XStream();
        xStream.alias("Question", Question.class);
        xStream.alias("StudentAnswers", StudentAnswers.class);
        xStream.alias("Question", ExportQuestion.class);

        FileInputStream fis = null;
	    ObjectInputStream objInput = null;
	    try {
		fis = new FileInputStream(fileExamName);
		objInput = xStream.createObjectInputStream(fis);
		answers = (List<StudentAnswers>) objInput.readObject();
	    } catch (ClassNotFoundException ex) {
		Logger.getLogger(CheckExam.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
		Logger.getLogger(CheckExam.class.getName()).log(Level.SEVERE, null, ex);
	    } finally {
		try {
		    objInput.close();
		    fis.close();
		} catch (IOException ex) {
		    Logger.getLogger(CheckExam.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
        return answers;
    }

    public List<StudentGradeAnswers> processExam (String fileExamName)
    {
        List<StudentAnswers> examOld = loadExamFromFile(fileExamName);
        List<StudentGradeAnswers> examNew = new ArrayList<StudentGradeAnswers>();

        for (int i = 0; i < examOld.size(); i++)
        {
            examNew.add(new StudentGradeAnswers());
            examNew.get(i).createFromStudentAnswers(examOld.get(i));
        }

        return examNew;
    }
}
