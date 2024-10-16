/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import logic.exams.Exam;

/**
 *
 * @author michal
 */
public class CategoryTableModel extends AbstractTableModel {

    private String[] columnNames={
      "Nazwa"
    };
    private List<String> data=new ArrayList<String>();
    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if(col==0)
        {
            return data.get(row);
        }
        else
        {
            return null;
        }
    }

    public void setData(List<String> categoryList)
    {
        this.data=categoryList;
    }
}
