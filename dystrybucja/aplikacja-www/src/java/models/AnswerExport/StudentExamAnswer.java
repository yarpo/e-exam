/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.AnswerExport;

import beans.imports.Question;
import beans.imports.extension.QuestionExt;
import beans.imports.interfaces.IAnswer;
import beans.imports.interfaces.IAnswerABCD;
import beans.imports.interfaces.IAnswerGap;
import beans.imports.interfaces.IAnswers;
import beans.imports.interfaces.IQuestion;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sokol
 */
public class StudentExamAnswer {


    public void Export(HttpServletRequest request) throws UnsupportedEncodingException
    {
        StudentAnswers studAns=new StudentAnswers();
        ArrayList<IQuestion> questions= (ArrayList) request.getSession().getAttribute("questionList");
        
        IQuestion ques=null;
        ExportQuestion expQues=null;

        String tempKey = (String)request.getSession().getAttribute("userKey");
        studAns.setIdKey(tempKey);

        List<String> correctAnswers =null;
        for(int i=0; i<questions.size(); i++)
        {
            correctAnswers =new ArrayList<String>();
            ques=questions.get(i);

            FillCorrectAnswers(ques,correctAnswers);

            expQues=new ExportQuestion();
            String parName="question"+ques.getId();

            String[] answers=request.getParameterValues(parName);

	    if(answers!=null)
	    {
		for(String ans :answers )
		{
		    expQues.addAnswerStud(ans);
		}
	    }
	    
            List<IAnswer> allAnswers=null;

            if(ques.getAnswers()!=null)
            {
             allAnswers = ques.getAnswers().getAllAnswers();
            }

            if(allAnswers!=null)
            {
                for(int j=0; j<allAnswers.size(); j++)
                {
                    expQues.addAllAnswers(allAnswers.get(j).getContent());
                }
            }
            
            expQues.setCorrectAnswers(correctAnswers);
            expQues.setType(ques.getType());
            expQues.setContent(ques.getContent());
            expQues.setId(ques.getId());
            expQues.setPoint( ((QuestionExt)ques).getPoints());

            studAns.getQuestions().add(expQues);
        }

	XStream xStream = new XStream(new DomDriver("UTF-8"));
	xStream.alias("Question", Question.class);
        xStream.alias("StudentAnswers",StudentAnswers.class);
        xStream.alias("Question", ExportQuestion.class);
	
        FileOutputStream fos = null;

        String realPath=request.getSession().getServletContext().getRealPath("/XML/");
        realPath+="/";

        try {
            System.out.println("Real path:"+realPath);
            fos = new FileOutputStream(realPath+studAns.getIdKey()+".xml");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(StudentExamAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

	    ObjectOutputStream output = xStream.createObjectOutputStream(fos);
            output.writeObject(studAns);
	    output.close();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(StudentExamAnswer.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public void FillCorrectAnswers(IQuestion ques, List<String> corAns)
    {
        IAnswers answers = ques.getAnswers();
        if(answers!=null)
        {
            List<IAnswerABCD> correctAnswersABCD = answers.getCorrectAnswersABCD();
            List<IAnswerGap> answersGap = answers.getAnswersGap();

            for(int i=0; i<correctAnswersABCD.size(); i++)
            {
                corAns.add(correctAnswersABCD.get(i).getContent());
            }
            for(int i=0; i<answersGap.size(); i++)
            {
                corAns.add(answersGap.get(i).getContent());
            }
        }
    }
}
