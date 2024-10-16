/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation.table;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import logic.exams.Exam;
import logic.exams.IExam;
import logic.question.Question;
import logic.question.interfaces.IQuestion;

/**
 *
 * @author michal
 */
public class ExamTableModel extends AbstractTableModel {

    private String[] columnNames={
      "Nazwa", "Data"
    };
    private ArrayList<Exam> data=new ArrayList();
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
            return data.get(row).getName();
        }
        if(col==1)
        {
            return data.get(row).getDate();
        }
        else
        {
            return null;
        }
    }

    public void setData(ArrayList<Exam> examList)
    {
        this.data=examList;
    }
}
