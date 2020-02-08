// 
// Decompiled by Procyon v0.5.36
// 

package ccUtils;

import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import chopeCrew.ChopeCrew;
import java.io.File;

public class CustomFileChooser
{
    private String extension;
    private boolean isSave;
    
    public CustomFileChooser(final String extension, final boolean isSave) {
        this.extension = extension;
        this.isSave = isSave;
    }
    
    public File showDialog() {
        String repertoire = "";
        if (this.extension.equals("txt")) {
            repertoire = ChopeCrew.options.repTXT;
        }
        else if (this.extension.equals("xml")) {
            repertoire = ChopeCrew.options.repXML;
        }
        else if (this.extension.equals("ics")) {
            repertoire = ChopeCrew.options.repICS;
        }
        else if (this.extension.equals("pdf")) {
            repertoire = ChopeCrew.options.repPDF;
        }
        else if (this.extension.equals("html")) {
            repertoire = ChopeCrew.options.repHTML;
        }
        else if (this.extension.equals("png")) {
            repertoire = ChopeCrew.options.repPNG;
        }
        final JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File(repertoire));
        fc.setFileFilter(new CustomFileFilter(this.extension));
        if (!this.isSave) {
            final int returnVal = fc.showOpenDialog(ChopeCrew.mf);
            if (returnVal == 1) {
                return null;
            }
            return fc.getSelectedFile();
        }
        else {
            String nomFichierDefaut = "ChopeCrew." + this.extension;
            if (ChopeCrew.analyse.deltaMois == 0) {
                nomFichierDefaut = String.valueOf(ChopeCrew.analyse.matricule) + "_Plng_" + ChopeCrew.analyse.anneeInt + "_" + ChopeCrew.analyse.moisNum + "." + this.extension;
            }
            else if (ChopeCrew.analyse.deltaMois == 1) {
                nomFichierDefaut = String.valueOf(ChopeCrew.analyse.matricule) + "_Prev_" + ChopeCrew.analyse.anneeInt + "_" + ChopeCrew.analyse.moisNum + "." + this.extension;
            }
            else if (ChopeCrew.analyse.deltaMois == 2) {
                nomFichierDefaut = String.valueOf(ChopeCrew.analyse.matricule) + "_Prev_" + ChopeCrew.analyse.anneeInt + "_" + ChopeCrew.analyse.moisNum + "." + this.extension;
            }
            fc.setSelectedFile(new File(nomFichierDefaut));
            final int returnVal2 = fc.showSaveDialog(ChopeCrew.mf);
            if (returnVal2 == 1) {
                return null;
            }
            if (fc.getSelectedFile().exists()) {
                final int i = JOptionPane.showConfirmDialog(ChopeCrew.mf, "Le fichier existe d\u00e9j\u00e0, \u00e9craser ?", "ChopeCREW vous informe...", 0, 2);
                if (i != 0) {
                    return null;
                }
            }
            return fc.getSelectedFile();
        }
    }
}
