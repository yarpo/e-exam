/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import logic.question.Question;
import logic.question.interfaces.IQuestion;

/**
 *
 * @author michal
 */
public class QuesTableModel extends AbstractTableModel {

    private String[] columnNames={
      "Tresc", "Trudnosc", "Kategoria"
    };
    private ArrayList<IQuestion> data=new ArrayList();
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
            return data.get(row).getContent();
        }
        if(col==1)
        {
            return data.get(row).getGrade();
        }
        if(col==2)
        {
            return data.get(row).getCategory();
        }
        else
        {
            return null;
        }
    }

    public void setData(ArrayList<IQuestion> quesList)
    {
        this.data=quesList;
    }
}
