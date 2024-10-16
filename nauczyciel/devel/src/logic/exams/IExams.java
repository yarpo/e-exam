/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.exams;

import data.bean.ExamSrcBean;
import java.util.ArrayList;


/**
 *
 * @author yarpo
 */
public interface IExams {

    public IExam getById( int id ) throws Exception;
    public ArrayList<Exam> getAllExams() throws Exception;
    public void add(ExamSrcBean exam) throws Exception;
    public void save() throws Exception;
    public void saveWithSections(IExam exam) throws Exception;
    public void updateWithSections(IExam exam) throws  Exception;
    public void delete(int id) throws Exception;
    public void delete(ExamSrcBean exam) throws Exception;
}
