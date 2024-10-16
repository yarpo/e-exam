/*
 * Klasa odpowiedzialna za uklad elementow na gridzie
 * wykorzystuje wzorzec lancuchowy
 */
package presentation.Panels;

import java.awt.GridBagConstraints;

/**
 *
 * @author yarpo
 */
public class ExamGridBagConstraints extends GridBagConstraints
{
    public ExamGridBagConstraints ()
    {
        this(HORIZONTAL, 0.5, 1.0, 1);
    }

    public ExamGridBagConstraints (int gridwith)
    {
        this(HORIZONTAL, 0.5, 1.0, gridwith);
    }

    public ExamGridBagConstraints (int fill, double weightx, double weighty,
            int gridwith)
    {
        this.fill = fill;
        this.weightx = weightx;
        this.weighty = weighty;
        this.gridwidth = gridwith;
    }

    public ExamGridBagConstraints column (int x)
    {
        gridx = x;
        return this;
    }

    public ExamGridBagConstraints row (int y)
    {
        gridy = y;
        return this;
    }

    /**
     * Ustawia sie w okreslonej kolumnie i wierszu
     */
    public ExamGridBagConstraints setAtGrid (int x, int y)
    {
        gridx = x;
        gridy = y;

        return this;
    }

    public ExamGridBagConstraints nextRow ()
    {
        gridy++;
        column(0);
        return this;
    }

    public ExamGridBagConstraints nextColumn ()
    {
        gridx++;
        return this;
    }
}
