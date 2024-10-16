package data;

import data.bean.SectionGapSrcBean;
import data.interfaces.ISectionGapSrc;
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
public class SectionGapSrc implements ISectionGapSrc
{
    public final static String TABLE_NAME = "sectionGap";
    protected Connection conn;

    public SectionGapSrc (Connection conn)
    {
        this.conn = conn;
    }

    public void save (SectionGapSrcBean bean)
            throws SQLException
    {
        String query = "INSERT INTO " + TABLE_NAME
                + " (taskId, underline, showChars, howMuchChars, charsMode) "
                + "VALUES (?, ?, ?, ?, ?);";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getSectionId());
        ps.setBoolean(2, bean.isUnderline());
        ps.setBoolean(3, bean.isShowChars());
        ps.setInt(4, bean.getHowMuchChars());
        ps.setInt(5, bean.getCharsMode());
        ps.executeUpdate();
    }

    public void delete (int taskId)
            throws SQLException
    {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE taskId = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, taskId);
        ps.executeUpdate();
    }

    public void update (SectionGapSrcBean bean)
            throws SQLException
    {
        String query =
                "UPDATE " + TABLE_NAME + " SET underline = ?,"
                + " showChars = ?, howMuchChars = ?, charsMode = ? WHERE taskId = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setBoolean(1, bean.isUnderline());
        ps.setBoolean(2, bean.isShowChars());
        ps.setInt(3, bean.getHowMuchChars());
        ps.setInt(4, bean.getCharsMode());
        ps.setInt(5, bean.getSectionId());
        ps.executeUpdate();
    }

    public List<SectionGapSrcBean> get (int taskId)
            throws SQLException
    {
        String query = "SELECT taskId, underline, showChars, howMuchChars, charsMode"
                + " FROM " + TABLE_NAME + " WHERE taskId = ?";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, taskId);
        ResultSet result = ps.executeQuery();

        ArrayList lista = new ArrayList<SectionGapSrcBean>();
        while (result.next())
        {
            SectionGapSrcBean bean = new SectionGapSrcBean(
                    result.getInt("taskId"), result.getBoolean("underline"),
                    result.getBoolean("showChars"),
                    result.getInt("howMuchChars"),
                    result.getInt("charsMode"));
            lista.add(bean);
        }
        return lista;
    }
}
