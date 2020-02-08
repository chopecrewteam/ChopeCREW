// 
// Decompiled by Procyon v0.5.36
// 

package ccUtils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class CustomFileFilter extends FileFilter
{
    private String extension;
    
    public CustomFileFilter(final String extension) {
        this.extension = extension;
    }
    
    @Override
    public boolean accept(final File f) {
        return f.isDirectory() || f.getName().endsWith("." + this.extension);
    }
    
    @Override
    public String getDescription() {
        return "Plannings ChopeCrew (." + this.extension + ")";
    }
}
