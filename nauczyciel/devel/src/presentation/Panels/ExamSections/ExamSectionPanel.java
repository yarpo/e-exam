/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels.ExamSections;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import presentation.Panels.*;
import data.bean.SectionSrcBean;
import data.interfaces.ICategorySrc;
import data.staticClass.TableObjectFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Panel zawierajacy pojedyncza sekcje egzaminu
 * @author Michal
 */
public class ExamSectionPanel extends JPanel implements ActionListener,
        DocumentListener
{
    protected JPanel panSec;
    protected static final int TFSIZE = 9;
    private JComboBox comBoxCat;
    private JComboBox comBoxDiffMin;
    private JComboBox comBoxDiffMax;
    private JTextField textfName;
    private JTextField textfQuan;
    private JTextField textfPoint;
    private AddExamPanel tabExam;
    private ICategorySrc categories;
    protected GridBagConstraints cons;

    public ExamSectionPanel (AddExamPanel tabExam)
    {
        panSec = new JPanel(new GridBagLayout());

        try
        {
            categories = TableObjectFactory.getInstance().getCategory();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        this.tabExam = tabExam;
    }

    public void init ()
    {
        this.add(buildSectionPanel());
    }

    protected JPanel buildSectionPanel ()
    {
        comBoxCat = new JComboBox();
        setCategories();

        comBoxDiffMin = new JComboBox();
        comBoxDiffMax = new JComboBox();

        for (int i = 1; i <= 10; i++)
        {
            comBoxDiffMin.addItem(Integer.toString(i));
            comBoxDiffMax.addItem(Integer.toString(i));
        }

        cons = new GridBagConstraints();
        textfName = new JTextField(TFSIZE);
        textfQuan = new JTextField(TFSIZE);
        textfPoint = new JTextField(TFSIZE);
        textfPoint.getDocument().addDocumentListener(this);

        cons.gridx = 0;
        cons.gridy = 0;
        cons.ipadx = 0;
        panSec.add(textfName, cons);

        cons.gridx++;
        cons.ipadx = 20;
        panSec.add(comBoxCat, cons);

        cons.gridx++;
        cons.ipadx = 0;
        panSec.add(textfQuan, cons);

        cons.gridx++;
        cons.ipadx = 45;
        panSec.add(comBoxDiffMin, cons);

        cons.gridx++;
        panSec.add(comBoxDiffMax, cons);

        cons.gridx++;
        cons.ipadx = 0;
        panSec.add(textfPoint, cons);

        return panSec;
    }

    public void actionPerformed (ActionEvent evt)
    {
        reval();
    }

    public void setCategories ()
    {
        List<String> stringCat = null;
        comBoxCat.removeAllItems();
        try
        {
            stringCat = categories.get();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        for (int i = 0; i < stringCat.size(); i++)
        {
            comBoxCat.addItem(stringCat.get(i));
        }
    }

    public void reval ()
    {
        this.revalidate();
        this.repaint();
    }

    public SectionSrcBean getData ()
    {
        SectionSrcBean bean = new SectionSrcBean(1, 1, 1, 1, 1, 1, 1);

        return bean;
    }

    public int checkFill ()
    {
        int fill = 1;
        if (textfName.getText().equals("")
                || textfQuan.getText().equals("")
                || textfPoint.getText().equals(""))
        {
            fill = 0;
        }

        try
        {
            Integer.parseInt(textfQuan.getText());
            Integer.parseInt(textfPoint.getText());
        }
        catch (Exception e)
        {
            fill = 0;
        }

        return fill;
    }

    public int getAllPoints ()
    {
        try
        {
            int numPoints = Integer.parseInt(textfPoint.getText());
            int numAns = Integer.parseInt(textfQuan.getText());
            return numPoints * numAns;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public void insertUpdate (DocumentEvent arg0)
    {
        tabExam.calcPoints();
    }

    public void removeUpdate (DocumentEvent arg0)
    {
        tabExam.calcPoints();
    }

    public void changedUpdate (DocumentEvent arg0)
    {
        tabExam.calcPoints();
    }

    public void setTaskName (String name)
    {
        textfName.setText(name);
    }

    public void setQuantity (int quan)
    {
        textfQuan.setText(Integer.toString(quan));
    }

    public void setMinDifficulty (int diff)
    {
        comBoxDiffMin.setSelectedIndex(diff - 1);
    }

    public void setMaxDifficulty (int diff)
    {
        comBoxDiffMax.setSelectedIndex(diff - 1);
    }

    public void setPoints (int point)
    {
        textfPoint.setText(Integer.toString(point));
    }

    public void setCategory (int category)
    {
    }

    public String getTaskName ()
    {
        return textfName.getText();
    }

    public int getQuantity ()
    {
        String text = textfQuan.getText();
        return Integer.parseInt(text);
    }

    public int getMinDifficulty ()
    {
        int num = comBoxDiffMin.getSelectedIndex() + 1;
        return num;
    }

    public int getMaxDifficulty ()
    {
        int num = comBoxDiffMax.getSelectedIndex() + 1;
        return num;
    }

    public int getPoints ()
    {
        String text = textfPoint.getText();

        return Integer.parseInt(text);
    }

    public int getCategory ()
    {
        return comBoxCat.getSelectedIndex() + 1;
    }

    public GridBagConstraints getConstraints ()
    {
        return cons;
    }

    public int calcPoints ()
    {
        return getAllPoints();
    }
}
