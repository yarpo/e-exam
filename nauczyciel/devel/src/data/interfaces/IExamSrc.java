package data.interfaces;

import data.bean.ExamSrcBean;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author michal
 */
public interface IExamSrc
{
    void add (ExamSrcBean bean)
            throws SQLException;

    void delete (int id)
            throws SQLException;

    void update (ExamSrcBean bean)
            throws SQLException;

    List<ExamSrcBean> get ()
            throws SQLException;

    ExamSrcBean getById (int id)
            throws Exception;

    public int getLastExamId ()
            throws SQLException;
}
