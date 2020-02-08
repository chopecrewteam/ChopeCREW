// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.UIDefaults;
import java.awt.Color;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class OverridingLookAndFeel extends MetalLookAndFeel
{
    private static final long serialVersionUID = 1L;
    private static final Color BUTTON_BACKGROUND;
    private static final Color WINDOW_BACKGROUND;
    
    static {
        BUTTON_BACKGROUND = new Color(225, 225, 225);
        WINDOW_BACKGROUND = new Color(240, 240, 240);
    }
    
    @Override
    public String getName() {
        return "OverridingLookAndFeel";
    }
    
    @Override
    public String getID() {
        return "OverridingLookAndFeel";
    }
    
    @Override
    public String getDescription() {
        return "Extends metal, changing a few new options";
    }
    
    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }
    
    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }
    
    @Override
    protected void initComponentDefaults(final UIDefaults table) {
        super.initComponentDefaults(table);
        table.put("window", OverridingLookAndFeel.WINDOW_BACKGROUND);
        table.put("Button.background", OverridingLookAndFeel.BUTTON_BACKGROUND);
        table.put("ToggleButton.background", OverridingLookAndFeel.BUTTON_BACKGROUND);
        table.put("TextPane.background", Color.WHITE);
        table.put("TextField.background", Color.WHITE);
        table.put("PasswordField.background", Color.WHITE);
        table.put("FileChooser.listViewBackground", Color.WHITE);
        table.put("FormattedTextField.background", Color.WHITE);
        table.put("EditorPane.background", Color.WHITE);
        table.put("TextArea.background", Color.WHITE);
        table.put("ProgressBar.selectionBackground", Color.DARK_GRAY);
        table.put("ProgressBar.selectionForeground", Color.DARK_GRAY);
        table.put("TabbedPane.tabInsets", new Insets(4, 4, 4, 4));
        table.put("TitledBorder.font", new Font("SansSerif", 0, 12));
        table.put("OptionPane.messageFont", new Font("SansSerif", 0, 16));
        table.put("OptionPane.buttonFont", new Font("SansSerif", 0, 16));
        table.put("ToolTip.background", new Color(255, 255, 225, 255));
    }
}
