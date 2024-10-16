/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.ExamHandler;

import beans.imports.Exam;
import beans.imports.interfaces.IQuestion;
import java.util.*;



/**
 *
 * @author misiakw
 */
public class ExamHandler {

    private Exam exam = null;
    private Map<Integer, IQuestion> questionList = null;
    int howMuch;

   // <editor-fold defaultstate="collapsed" desc="Mechanizm Singletonu">
    private static volatile ExamHandler instance = null;
    public static ExamHandler getInstance() {
        if (instance == null) {
            instance = new ExamHandler();
        }
        return instance;
    }
    ExamHandler() {}// </editor-fold>

   // <editor-fold defaultstate="collapsed" desc="get/set Exam">
    public void setExam(Exam e) {
        this.exam = e;
        howMuch = 0;
        for (int i=0; i<e.getSections().size(); i++)
            howMuch += e.getSections().get(i).getQuestionsNo();
    }
    public Exam getExam() {
        return this.exam;
    }// </editor-fold>

   // <editor-fold defaultstate="collapsed" desc="get/set QuestionList">
    public void setQuestionList(List<IQuestion> list) {
        questionList = new HashMap<Integer, IQuestion>();

        for (int i=0; i<list.size(); i++){
            IQuestion q = list.get(i);
            questionList.put(q.getId(), q);
        }
    }
    public List<IQuestion> getQuestionList() {
        List<IQuestion> list = new ArrayList<IQuestion>();

        Set<Integer> klucze = questionList.keySet();
        Iterator<Integer> iter = klucze.iterator();

        while (iter.hasNext()){
            list.add(questionList.get(iter.next()));
        }

        return list;
    }// </editor-fold>


    // zwracanie listy z pytaniami na określony egzamin
    public List<IQuestion> getQuestions(){
        List<IQuestion> studentQuestionList = new ArrayList<IQuestion>();
        if (exam == null || questionList == null)        
            return studentQuestionList;
        
        QuestionMatrix matrix = new QuestionMatrix(exam.getSections(), questionList);

        if (exam == null)
            return studentQuestionList;

        for (int i=0; i<howMuch; i++)
            studentQuestionList.add(matrix.getQuestion());

        return studentQuestionList;
    }
}
