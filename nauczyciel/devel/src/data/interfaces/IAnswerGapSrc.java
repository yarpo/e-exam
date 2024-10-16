package data.interfaces;

import data.bean.AnswerGapSrcBean;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author michal
 */
public interface IAnswerGapSrc
{
    void add (AnswerGapSrcBean bean)
            throws SQLException;

    void delete (int id)
            throws SQLException;

    void update (AnswerGapSrcBean bean)
            throws SQLException;

    List<AnswerGapSrcBean> get (int questionId)
            throws SQLException;
}
