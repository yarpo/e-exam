package data;

import data.interfaces.ISectionSrc;
import data.bean.SectionSrcBean;
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
public class SectionSrc implements ISectionSrc
{
    public static final String TABLE_NAME = "section";
    protected Connection conn;

    public SectionSrc (Connection conn)
    {
        this.conn = conn;
    }

    public void save (SectionSrcBean bean)
            throws SQLException
    {
        String query =
                "INSERT INTO " + TABLE_NAME + " (examId, kategoryId, type, howMuch,"
                + " gradeMin, gradeMax, points, name) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getExamId());
        ps.setInt(2, bean.getCategoryId());
        ps.setInt(3, bean.getType());
        ps.setInt(4, bean.getHowMuch());
        ps.setInt(5, bean.getGradeMin());
        ps.setInt(6, bean.getGradeMax());
        ps.setInt(7, bean.getPoints());
        ps.setString(8, bean.getName());
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

    public void update (SectionSrcBean bean)
            throws SQLException
    {
        String query = "UPDATE " + TABLE_NAME
                + " SET examId = ?, kategoryId = ?, type = ?, howMuch = ?,"
                + " gradeMin = ?, gradeMax = ?, points = ?, name = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, bean.getExamId());
        ps.setInt(2, bean.getCategoryId());
        ps.setInt(3, bean.getType());
        ps.setInt(4, bean.getHowMuch());
        ps.setInt(5, bean.getGradeMin());
        ps.setInt(6, bean.getGradeMax());
        ps.setInt(7, bean.getPoints());
        ps.setString(8, bean.getName());
        ps.executeUpdate();
    }

    public List<SectionSrcBean> get (int examId)
            throws SQLException
    {
        String query =
                "SELECT id, examId, kategoryId, type, howMuch,"
                + " gradeMin, gradeMax, points, name FROM " + TABLE_NAME + " WHERE examId = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, examId);
        ResultSet result = ps.executeQuery();

        ArrayList lista = new ArrayList<SectionSrcBean>();
        while (result.next())
        {
            SectionSrcBean bean = new SectionSrcBean(
                    result.getInt("id"), result.getInt("examId"),
                    result.getInt("kategoryId"), result.getInt("type"),
                    result.getInt("howMuch"), result.getInt("gradeMin"),
                    result.getInt("gradeMax"), result.getInt("points"),
                    result.getString("name"));
            lista.add(bean);
        }
        return lista;
    }

    public int getLastSectionId ()
            throws SQLException
    {
        String query = "SELECT MAX(id) as maxid from " + TABLE_NAME;
        PreparedStatement ps = this.conn.prepareStatement(query);
        ResultSet result = ps.executeQuery();
        result.next();
        int res = result.getInt("maxid");
        result.close();
        ps.close();
        return res;
    }
}
