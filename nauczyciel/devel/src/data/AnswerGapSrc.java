package data;

import data.interfaces.IAnswerGapSrc;
import data.bean.AnswerGapSrcBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerGapSrc implements IAnswerGapSrc
{
    public static final String TABLE_NAME = "answerGap";
    protected Connection conn;

    public AnswerGapSrc (Connection conn)
    {
        this.conn = conn;
    }

    public void add (AnswerGapSrcBean bean)
            throws SQLException
    {
        String query = "INSERT INTO " + TABLE_NAME
                + " (questionId, content, gapNumber) values (?, ?, ?);";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getQuestionId());
        ps.setString(2, bean.getContent());
        ps.setInt(3, bean.getGapNumber());
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

    public void update (AnswerGapSrcBean bean)
            throws SQLException
    {
        String query =
                "UPDATE " + TABLE_NAME
                + " SET questionId = ?, content = ?, gapNumber = ? WHERE id = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getQuestionId());
        ps.setString(2, bean.getContent());
        ps.setInt(3, bean.getGapNumber());
        ps.executeUpdate();
    }

    public List<AnswerGapSrcBean> get (int questionId)
            throws SQLException
    {
        String query = "SELECT id, questionId, content, gapNumber from "
                + TABLE_NAME + " where questionId = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, questionId);
        ResultSet result = ps.executeQuery();

        ArrayList lista = new ArrayList<AnswerGapSrcBean>();
        while (result.next())
        {
            AnswerGapSrcBean bean = new AnswerGapSrcBean(
                    result.getInt("id"), result.getInt("questionId"),
                    result.getString("content"), result.getInt("gapNumber"));
            lista.add(bean);
        }
        return lista;
    }
}
