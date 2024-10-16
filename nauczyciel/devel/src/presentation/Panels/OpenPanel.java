/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.Panels;
/**
 * Panel obslugujacy pytania otwarte
 * @author Sokol
 */
public class OpenPanel extends SelPanel
{
    public OpenPanel ()
    {
        super();
        this.add(buildActivePanel());
    }

    @Override
    public void clearFields ()
    {
        chBoxActive.setSelected(false);
        bActive = false;
    }
}
