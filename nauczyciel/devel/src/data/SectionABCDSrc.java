package data;

import data.bean.SectionABCDSrcBean;
import data.interfaces.ISectionABCDSrc;
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
public class SectionABCDSrc implements ISectionABCDSrc
{
    public static final String TABLE_NAME = "sectionABCD";
    protected Connection conn;

    public SectionABCDSrc (Connection conn)
    {
        this.conn = conn;
    }

    public void save (SectionABCDSrcBean bean)
            throws SQLException
    {
        String query = "INSERT INTO " + TABLE_NAME
                + " (taskId, correct, allAnswers) VALUES (?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getSectionId());
        ps.setInt(2, bean.getCorrect());
        ps.setInt(3, bean.getAll());
        ps.executeUpdate();
    }

    public void delete (int id)
            throws SQLException
    {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE taskId = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void update (SectionABCDSrcBean bean)
            throws SQLException
    {
        String query = "UPDATE " + TABLE_NAME
                + " SET correct = ?, allAnswers = ? WHERE taskId = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getCorrect());
        ps.setInt(2, bean.getAll());
        ps.setInt(3, bean.getSectionId());
        ps.executeUpdate();
    }

    public List<SectionABCDSrcBean> get (int taskId)
            throws SQLException
    {
        String query = "SELECT taskId, correct, allAnswers FROM " + TABLE_NAME
                + " WHERE taskId = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, taskId);
        ResultSet result = ps.executeQuery();

        ArrayList lista = new ArrayList<SectionABCDSrcBean>();
        while (result.next())
        {
            SectionABCDSrcBean bean = new SectionABCDSrcBean(
                    result.getInt("taskId"), result.getInt("correct"), result.
                    getInt("allAnswers"));
            lista.add(bean);
        }
        return lista;
    }
}
