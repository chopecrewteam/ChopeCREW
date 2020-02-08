// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import ccUtils.Utils;
import ccUtils.Constantes;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;

public class EventColorPicker extends JPanel
{
    private static final long serialVersionUID = 1L;
    private String colorId;
    
    public EventColorPicker() {
        this.colorId = null;
        this.setFont(new Font("Tahoma", 0, 16));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }
    
    public EventColorPicker(final String colorId) {
        this.colorId = null;
        this.colorId = colorId;
        this.updateBackground();
    }
    
    public void updateBackground() {
        int index = 0;
        try {
            index = Integer.parseInt(this.colorId);
        }
        catch (Exception ex) {}
        if (index == 0) {
            this.setBackground(UIManager.getColor("Panel.background"));
        }
        else {
            this.setBackground(Utils.hex2rgb(Constantes.GOOGLE_EVENT_COLORS_VALUES[index]));
        }
    }
    
    public String getColorId() {
        return this.colorId;
    }
    
    public void setColorId(final String colorId) {
        this.colorId = colorId;
        this.updateBackground();
    }
}
