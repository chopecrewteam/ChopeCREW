// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class CustomCheckBox extends JCheckBox
{
    private static final long serialVersionUID = 1L;
    
    public CustomCheckBox() {
        this.customize();
    }
    
    public CustomCheckBox(final String text) {
        super(text);
        this.customize();
    }
    
    private void customize() {
        this.setIcon(new ImageIcon(MainFrame.class.getResource("/res_images/icon_checkbox.png")));
        this.setSelectedIcon(new ImageIcon(MainFrame.class.getResource("/res_images/icon_checkbox_selected.png")));
    }
}
