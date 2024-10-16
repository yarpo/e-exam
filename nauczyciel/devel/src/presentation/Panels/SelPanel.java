/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import presentation.interfaces.IClearPanel;

/**
 * Pomocniczy panel, ktorego jedyna opcja jest zaznaczanie go
 * @author Sokol
 */
public class SelPanel extends JPanel implements ActionListener, IClearPanel
{
    protected boolean bActive;
    protected JCheckBox chBoxActive;
    protected GridBagConstraints cons;
    protected SelPanel panel1; //panel, potrzebny do sprawdzania czy juz pytanie okreslonego typu
    //nie jest zaznaczone
    protected SelPanel panel2;

    public SelPanel ()
    {
        cons = new GridBagConstraints();
    }

    protected JPanel buildActivePanel ()
    {
        JPanel panTemp = new JPanel();
        JLabel labActive = new JLabel("Aktywne");
        chBoxActive = new JCheckBox();
        chBoxActive.addActionListener(this);

        cons.fill = GridBagConstraints.BOTH;
        cons.weightx = 0.5;
        cons.weighty = 1.0;
        cons.gridx = 0;
        cons.gridy = 0;
        panTemp.add(labActive, cons);

        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.gridx = 1;
        cons.gridy = 0;
        panTemp.add(chBoxActive, cons);

        return panTemp;
    }

    public void actionPerformed (ActionEvent evt)
    {
        if (evt.getSource() == chBoxActive)
        {
            panel1.setUnactive();
            panel2.setUnactive();

            bActive = chBoxActive.isSelected();
        }
    }

    public boolean isActive ()
    {
        return bActive;
    }

    public void setActive ()
    {
        bActive = true;
        chBoxActive.setSelected(true);
    }

    public void setUnactive ()
    {
        bActive = false;
        chBoxActive.setSelected(false);
        clearFields();
    }

    public SelPanel getPanel1 ()
    {
        return panel1;
    }

    public void setPanel1 (SelPanel panel1)
    {
        this.panel1 = panel1;
    }

    public SelPanel getPanel2 ()
    {
        return panel2;
    }

    public void setPanel2 (SelPanel panel2)
    {
        this.panel2 = panel2;
    }

    public void clearFields ()
    {
    }
}
