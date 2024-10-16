/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import data.staticClass.TableObjectFactory;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import logic.category.Category;
import logic.category.ICategory;
import logic.category.exception.CategoryExistsException;
import presentation.Panels.communicates.AddCategory;
import presentation.TabExam;
import presentation.TabQues;
import presentation.helpers.PopupDialog;
import presentation.table.CategoryTableModel;

/**
 *
 * @author Michal
 */
public class CategoryPanel extends JPanel implements ListSelectionListener,
        ActionListener
{
    private static final int FIELD_SIZE = 15;
    private JPanel panMain;
    private ExamGridBagConstraints cons = new ExamGridBagConstraints();
    private CategoryTableModel tabModel;
    private JScrollPane scrollPane;
    private JButton butAddCategory = new JButton(AddCategory.ADD_BUTTON_LABEL);
    private JButton butRefresh = new JButton(AddCategory.REFRESH_BUTTON_LABEL);
    private JTextField texCatName = new JTextField(FIELD_SIZE);
    private JTable tabCategory = new JTable();
    private List<String> categories = null;
    private TabQues tabQues;
    private TabExam tabExam;

    public CategoryPanel (TabQues tabQues, TabExam tabExam)
    {
        this.tabQues = tabQues;
        this.tabExam = tabExam;

        panMain = new JPanel(new GridBagLayout());
        JLabel labCatName = new JLabel(AddCategory.CATEGORY_LABEL);
        tabModel = new CategoryTableModel();
        tabCategory = new JTable(tabModel);
        tabCategory.getSelectionModel().addListSelectionListener(this);
        tabCategory.setPreferredScrollableViewportSize(new Dimension(250, 150));
        tabCategory.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tabCategory);
        panMain.add(labCatName, cons);
        cons.setAtGrid(0, 0);
        cons.nextColumn();
        panMain.add(texCatName, cons);
        cons.nextColumn();
        panMain.add(butAddCategory, cons);
        cons.nextRow();
        cons.gridwidth = 0;
        panMain.add(scrollPane, cons);
        cons.nextRow();
        panMain.add(butRefresh, cons);
        butAddCategory.addActionListener(this);
        butRefresh.addActionListener(this);

        this.add(panMain);
        refresh();
    }

    public void valueChanged (ListSelectionEvent e)
    {
        int selRow = tabCategory.getSelectedRow();
        this.texCatName.setText(categories.get(selRow));
    }

    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getSource() == butAddCategory)
        {
            addCategory();
        }
        else if (evt.getSource() == butRefresh)
        {
            refresh();
        }
    }

    private void refresh ()
    {
        try
        {
            categories = TableObjectFactory.getInstance().getCategory().get();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        tabModel.setData(categories);
        tabCategory.setModel(tabModel);

        scrollPane.updateUI();
        scrollPane.repaint();
        this.repaint();
    }

    public void addCategory ()
    {
        String newCatName = texCatName.getText();

        if (newCatName.equals(""))
        {
            new PopupDialog().createPopupDialog(AddCategory.ERROR_EMPTY_NAME);
            return;
        }

        if (save(newCatName))
        {
            repaint();
            refresh();
            tabQues.updateChildren();
            tabExam.updateChildren();
        }
    }

    private boolean save (String name)
    {
        ICategory categoryBean = new Category();

        try
        {
            categoryBean.add(name);
            new PopupDialog().createPopupDialog(AddCategory.SUCCESS_ADD_CATEGORY);
        }
        catch (CategoryExistsException ex)
        {
            new PopupDialog().createPopupDialog(AddCategory.EX_EXISTS_CATEGORY
                    + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        catch (Exception ex)
        {
            new PopupDialog().createPopupDialog(AddCategory.EX_ADD_CATEGORY);
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
