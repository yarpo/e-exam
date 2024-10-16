/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.interfaces.ICategorySrc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sokol
 */
public class CategorySrc implements ICategorySrc
{
    public static final String TABLE_NAME = "kategory";
    protected Connection conn;

    public CategorySrc (Connection conn)
    {
        this.conn = conn;
    }

    public void add (String name)
            throws SQLException
    {
        String query = "INSERT INTO " + TABLE_NAME + " ( name) VALUES (?);";
        PreparedStatement ps = this.conn.prepareStatement(query);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    public List<String> get ()
            throws SQLException
    {
        String query = "SELECT name FROM " + TABLE_NAME;
        PreparedStatement ps = this.conn.prepareStatement(query);
        ResultSet result = ps.executeQuery();

        ArrayList lista = new ArrayList<String>();
        while (result.next())
        {
            String res = result.getString("name");
            lista.add(res);
        }
        return lista;
    }
}
