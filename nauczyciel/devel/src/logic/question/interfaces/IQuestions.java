/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package logic.question.interfaces;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public interface IQuestions {


    /**
     * pobranie wszystkich pytan
     * @return
     */
    public List<IQuestion> get(HashMap filter)throws SQLException;

    public void delete(int quesId)throws SQLException;

    public void update(IQuestion ques)throws SQLException;

    public void add(IQuestion ques)throws SQLException;
}
