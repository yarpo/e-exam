/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import data.interfaces.ICategorySrc;
import data.staticClass.TableObjectFactory;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import logic.question.Questions;
import logic.question.interfaces.IQuestion;
import presentation.table.QuesTableModel;

/**
 * Panel obslugujacy szukanie pytan
 * @author Sokol
 */
public class SearchQuestionPanel extends JPanel implements ActionListener,
        ListSelectionListener
{
    private JButton butSearch;
    private JButton butDel;
    private QuesTableModel tabModel;
    private Questions quesMan;
    private JTable tabQues;
    private ArrayList<IQuestion> listQues;
    private AddQuestionPanel quesPan;
    private JScrollPane scrollPane;
    private ICategorySrc categories;
    private JComboBox comBoxSearchCat;

    public SearchQuestionPanel ()
    {
        tabModel = new QuesTableModel();
        quesMan = new Questions();

        JLabel labCat = new JLabel("Kategoria");
        comBoxSearchCat = new JComboBox();


        try
        {
            categories = TableObjectFactory.getInstance().getCategory();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        setCategoriesToComboBox();

        butSearch = new JButton("Szukaj");
        butSearch.addActionListener(this);
        butDel = new JButton("Usun");
        butDel.addActionListener(this);

        tabQues = new JTable(tabModel);

        tabQues.getSelectionModel().addListSelectionListener(this);
        tabQues.setPreferredScrollableViewportSize(new Dimension(500, 150));
        tabQues.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(tabQues);

        JPanel panMain = new JPanel();

        panMain.setLayout(new BoxLayout(panMain, BoxLayout.Y_AXIS));
        panMain.add(labCat);
        panMain.add(comBoxSearchCat);
        panMain.add(butSearch);
        panMain.add(scrollPane);
        panMain.add(butDel);

        this.add(panMain);
    }

    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getSource() == butSearch)
        {
            refresh();
        }
        else if (evt.getSource() == butDel && listQues.size() > 0)
        {
            int selIndex = tabQues.getSelectedRow();
            if (selIndex > -1)
            {
                HashMap filter = new HashMap();
                try
                {
                    quesMan.delete(listQues.get(selIndex).getId());
                    listQues = (ArrayList<IQuestion>) quesMan.get(filter);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            tabModel.setData(listQues);
            tabQues.setModel(tabModel);

            refresh();
        }

    }

    public void valueChanged (ListSelectionEvent evt)
    {
        int selRow = tabQues.getSelectedRow();
        quesPan.clearFields();
        quesPan.FillPanel(listQues.get(selRow));

    }

    /**
     * refresh list of questions
     */
    public void refresh ()
    {
        HashMap filter = new HashMap();
        filter.put("category", comBoxSearchCat.getSelectedItem());
        try
        {

            listQues = (ArrayList<IQuestion>) quesMan.get(filter);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        tabModel.setData(listQues);
        tabQues.setModel(tabModel);

        scrollPane.updateUI();
        scrollPane.repaint();
        this.repaint();
    }

    public void setAddPanel (AddQuestionPanel addQues)
    {
        quesPan = addQues;
    }

    private void setCategoriesToComboBox ()
    {

        List<String> getCategories = null;
        comBoxSearchCat.removeAllItems();
        try
        {
            getCategories = categories.get();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        for (int i = 0; i < getCategories.size(); i++)
        {
            comBoxSearchCat.addItem(getCategories.get(i));
        }
    }

    public void updateCategories ()
    {
        setCategoriesToComboBox();
    }
}
