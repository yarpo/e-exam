package data.interfaces;

import data.bean.AnswerABCDSrcBean;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author michal
 */
public interface IAnswerABCDSrc
{
    void add (AnswerABCDSrcBean bean)
            throws SQLException;

    void delete (int id)
            throws SQLException;

    void update (AnswerABCDSrcBean bean)
            throws SQLException;

    List<AnswerABCDSrcBean> get (int questionId)
            throws SQLException;
}
