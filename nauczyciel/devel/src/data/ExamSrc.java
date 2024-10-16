package data;

import data.interfaces.IExamSrc;
import data.bean.ExamSrcBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author michal
 */
public class ExamSrc implements IExamSrc
{
    public static final String TABLE_NAME = "exam";
    protected Connection conn;

    public ExamSrc (Connection conn)
    {
        this.conn = conn;
    }

    public void add (ExamSrcBean bean)
            throws SQLException
    {
        String query = "INSERT INTO " + TABLE_NAME
                + " (date, name) VALUES ( ?, ?);";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setDate(1, convertDateUtil2Sql(bean.getDate()));
        ps.setString(2, bean.getName());
        ps.executeUpdate();
    }

    private java.sql.Date convertDateUtil2Sql (Date date)
    {
        return new java.sql.Date(date.getTime());
    }

    public void delete (int id)
            throws SQLException
    {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?;";
        System.out.println(query);
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public void update (ExamSrcBean bean)
            throws SQLException
    {
        String query = "UPDATE " + TABLE_NAME
                + " SET date = ?, name = ? WHERE id = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setDate(1, convertDateUtil2Sql(bean.getDate()));
        ps.setString(2, bean.getName());
        ps.setInt(3, bean.getId());
        ps.executeUpdate();
    }

    public List<ExamSrcBean> get ()
            throws SQLException
    {
        String query = "SELECT id, date, name FROM " + TABLE_NAME;
        PreparedStatement ps = this.conn.prepareStatement(query);
        ResultSet result = ps.executeQuery();

        ArrayList lista = new ArrayList<ExamSrcBean>();
        while (result.next())
        {
            ExamSrcBean bean = new ExamSrcBean(result.getInt("id"),
                    result.getString("name"), result.getDate("date"));
            lista.add(bean);
        }
        return lista;
    }

    public ExamSrcBean getById (int id)
            throws Exception
    {
        String query = "SELECT id, date, name FROM " + TABLE_NAME
                + " WHERE id = ?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet result = ps.executeQuery();

        if (result.next())
        {
            return new ExamSrcBean(result.getInt("id"),
                    result.getString("name"), result.getDate("date"));
        }

        throw new Exception("Nie ma danych dla tego id w bazie");
    }

    public int getLastExamId ()
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
