package data.interfaces;

import data.bean.QuestionSrcBean;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author michal
 */
public interface IQuestionSrc
{
    public void add (QuestionSrcBean bean)
            throws SQLException;

    public void delete (int id)
            throws SQLException;

    public void update (QuestionSrcBean bean)
            throws SQLException;

    public int getLastQuesId ()
            throws SQLException;

    public List<QuestionSrcBean> get (HashMap filter)
            throws SQLException;
}
