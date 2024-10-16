/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.AnswerExport;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author michal
 */
public class StudentAnswers implements Serializable {
    private String idKey;
    private ArrayList<ExportQuestion> questions;
   
    public StudentAnswers()
    {
        this.questions=new ArrayList<ExportQuestion>();
    }

    public ArrayList<ExportQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<ExportQuestion> questions) {
        this.questions = questions;
    }

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }






}
