/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.interfaces;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Michal
 */
public interface ICategorySrc
{
    void add (String name)
            throws SQLException;

    List<String> get ()
            throws SQLException;
}
