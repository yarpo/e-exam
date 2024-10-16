package data;

import data.interfaces.IQuestionSrc;
import data.bean.QuestionSrcBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author michal
 */
public class QuestionSrc implements IQuestionSrc
{
    public static final String TABLE_NAME = "question";
    protected Connection conn;

    public QuestionSrc (Connection conn)
    {
        this.conn = conn;
    }

    public void add (QuestionSrcBean bean)
            throws SQLException
    {
        String query = "INSERT INTO " + TABLE_NAME
                + " (content, grade, type, comment, category)"
                + " values (?, ?, ?, ?, ?);";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setString(1, bean.getContent());
        ps.setInt(2, bean.getGrade());
        ps.setInt(3, bean.getType());
        ps.setString(4, bean.getComment());
        ps.setString(5, bean.getCategory());
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

    public void update (QuestionSrcBean bean)
            throws SQLException
    {
        String query =
                "UPDATE " + TABLE_NAME
                + " SET content=?, grade=?, type=?, comment=?, category=? where id=?;";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setString(1, bean.getContent());
        ps.setInt(2, bean.getGrade());
        ps.setInt(3, bean.getType());
        ps.setString(4, bean.getComment());
        ps.setString(5, bean.getCategory());
        ps.setInt(6, bean.getId());
        ps.executeUpdate();
    }

    public List<QuestionSrcBean> get (HashMap filter)
            throws SQLException
    {
        //potrzebna do ustalania łączników AND i WHERE
        String selection = " WHERE ";
        boolean first = true;

        if (filter.get("type") != null)
        {
            selection = selection + " type & " + filter.get("type");
            first = false;
        }
        if (filter.get("minGrade") != null)
        {
            if (!first)
            {
                selection = selection + " AND ";
            }
            selection = selection + " grade >= " + filter.get("minGrade");
            first = false;
        }
        if (filter.get("maxGrade") != null)
        {
            if (!first)
            {
                selection = selection + " AND ";
            }
            selection = selection + " grade <= " + filter.get("maxGrade");
            first = false;
        }
        if (filter.get("category") != null)
        {
            if (!first)
            {
                selection = selection + " AND ";
            }
            if (filter.get("category").getClass() != String.class)
            {
                String categoryString = getStringCategory((Integer) filter.get(
                        "category"));
                selection = selection + " category = '" + categoryString + "'";
            }
            else
            {
                selection =
                        selection + " category = '" + filter.get("category") + "'";
            }
            first = false;
        }

        String query = "SELECT id, content, grade, type, comment, category FROM "
                + TABLE_NAME;
        if (!first)
        {
            query = query + selection;
        }
        System.out.println("Question query: " + query);
        PreparedStatement ps = this.conn.prepareStatement(query);
        ResultSet result = ps.executeQuery();

        List lista = new ArrayList<QuestionSrcBean>();
        while (result.next())
        {
            QuestionSrcBean bean = new QuestionSrcBean(
                    result.getInt("id"), result.getString("content"),
                    result.getInt("grade"), result.getInt("type"),
                    result.getString("comment"), result.getString("category"));
            lista.add(bean);
        }
        return lista;
    }

    public int getLastQuesId ()
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

    private String getStringCategory (int category)
            throws SQLException
    {
        String name;
        System.out.println("categoryId: " + category);
        String query = "SELECT name from " + CategorySrc.TABLE_NAME
                + " WHERE id = ?";
        System.out.println("category query: " + query);
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setInt(1, category);
        ResultSet result = ps.executeQuery();
        if (result.next())
        {
            name = result.getString("name");
        }
        else
        {
            name = "";
        }
        System.out.println("categoryString : " + name);
        result.close();
        ps.close();

        return name;
    }
}
