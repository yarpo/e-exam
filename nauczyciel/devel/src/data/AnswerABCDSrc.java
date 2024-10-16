package data;

import data.interfaces.IAnswerABCDSrc;
import data.bean.AnswerABCDSrcBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author michal
 */
public class AnswerABCDSrc implements IAnswerABCDSrc
{
    public static final String TABLE_NAME = "answerABCD";
    protected Connection conn;

    public AnswerABCDSrc (Connection conn)
    {
        this.conn = conn;
    }

    public void add (AnswerABCDSrcBean bean)
            throws SQLException
    {
        String query =
                "INSERT INTO " + TABLE_NAME + " (questionId, content, correct) VALUES (?, ?, ?);";

        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getQuestionId());
        ps.setString(2, bean.getContent());
        ps.setBoolean(3, bean.isCorrect());
        ps.executeUpdate();

    }

    public void delete (int id)
            throws SQLException
    {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void update (AnswerABCDSrcBean bean)
            throws SQLException
    {
        String query = "UPDATE " + TABLE_NAME
                + " SET questionId = ?, content = ?, correct = ? WHERE id = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getQuestionId());
        ps.setString(2, bean.getContent());
        ps.setBoolean(3, bean.isCorrect());
        ps.setInt(4, bean.getId());
        ps.executeUpdate();
    }

    public List<AnswerABCDSrcBean> get (int questionId)
            throws SQLException
    {
        String query = "SELECT id, questionId, content, correct FROM "
                + TABLE_NAME + " WHERE questionId = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, questionId);
        ResultSet result = ps.executeQuery();

        ArrayList lista = new ArrayList<AnswerABCDSrcBean>();
        while (result.next())
        {
            AnswerABCDSrcBean bean = new AnswerABCDSrcBean(
                    result.getInt("id"), result.getInt("questionId"),
                    result.getString("content"), result.getBoolean("correct"));
            lista.add(bean);
        }
        return lista;
    }
}
