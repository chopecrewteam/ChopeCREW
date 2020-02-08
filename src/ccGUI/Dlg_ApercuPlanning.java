// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Box;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import ccUtils.Utils;
import java.io.File;
import ccExport.Export_PDF;
import ccUtils.CustomFileChooser;
import java.awt.Container;
import chopeCrew.ChopeCrew;
import java.awt.Frame;
import java.awt.Component;
import ccExport.Export_HTML;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JDialog;

public class Dlg_ApercuPlanning extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane;
    private JPanel pnl_Export;
    private JPanel pnl_Export_Boutons;
    private JButton btn_Pdf;
    private JButton btn_Html;
    private JButton btn_Png;
    private JScrollPane scrollPane;
    private JEditorPane editorPane;
    private JPanel pnl_Fermeture;
    private JButton btn_Fermer;
    private Export_HTML html;
    private Component horizontalStrutWest;
    private Component horizontalStrutEast;
    private Component verticalStrut_Export_1;
    private Component verticalStrut_Export_2;
    private Component horizontalStrut_Boutons_1;
    private Component horizontalStrut_Boutons_2;
    private Component verticalStrut_Fermeture_1;
    private Component verticalStrut_Fermeture_2;
    
    public Dlg_ApercuPlanning(final Frame owner) {
        super(owner);
        this.jContentPane = null;
        this.pnl_Export = null;
        this.btn_Pdf = null;
        this.btn_Html = null;
        this.btn_Png = null;
        this.scrollPane = null;
        this.editorPane = null;
        this.pnl_Fermeture = null;
        this.btn_Fermer = null;
        this.html = null;
        (this.html = new Export_HTML()).createHtml(ChopeCrew.analyse);
        this.initialize();
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(1024, 630);
        this.setResizable(false);
        this.setTitle("Aper\u00e7u du planning");
        this.setContentPane(this.getJContentPane());
    }
    
    private void actionBtnPDF() {
        final CustomFileChooser fc = new CustomFileChooser("pdf", true);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return;
        }
        final Export_PDF pdf = new Export_PDF();
        pdf.createPDF(ChopeCrew.analyse, returnFile);
        ChopeCrew.options.repPDF = returnFile.getParent();
    }
    
    private void actionBtnHtml() {
        final CustomFileChooser fc = new CustomFileChooser("html", true);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return;
        }
        Utils.saveToFile(this.html.getHtmlWithEncoding(), returnFile.getAbsolutePath(), "UTF8");
        ChopeCrew.options.repHTML = returnFile.getParent();
    }
    
    private void actionBtnPNG() {
        final CustomFileChooser fc = new CustomFileChooser("png", true);
        final File returnFile = fc.showDialog();
        if (returnFile == null) {
            return;
        }
        final BufferedImage im = new BufferedImage(this.editorPane.getWidth(), this.editorPane.getHeight(), 2);
        final Graphics2D g2d = im.createGraphics();
        this.editorPane.print(g2d);
        g2d.dispose();
        try {
            ImageIO.write(im, "png", returnFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ChopeCrew.options.repPNG = returnFile.getParent();
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BorderLayout(0, 0));
            this.jContentPane.add(this.getHorizontalStrutWest(), "West");
            this.jContentPane.add(this.getHorizontalStrutEast(), "East");
            this.jContentPane.add(this.getPnl_Export(), "North");
            this.jContentPane.add(this.getScrollPane());
            this.jContentPane.add(this.getPnl_Fermeture(), "South");
        }
        return this.jContentPane;
    }
    
    private JPanel getPnl_Export() {
        if (this.pnl_Export == null) {
            (this.pnl_Export = new JPanel()).setLayout(new BoxLayout(this.pnl_Export, 3));
            this.pnl_Export.add(this.getVerticalStrut_Export_1());
            this.pnl_Export.add(this.getPnl_Export_Boutons());
            this.pnl_Export.add(this.getVerticalStrut_Export_2());
        }
        return this.pnl_Export;
    }
    
    private JPanel getPnl_Export_Boutons() {
        if (this.pnl_Export_Boutons == null) {
            (this.pnl_Export_Boutons = new JPanel()).setLayout(new BoxLayout(this.pnl_Export_Boutons, 2));
            this.pnl_Export_Boutons.add(this.getBtn_Pdf());
            this.pnl_Export_Boutons.add(this.getHorizontalStrut_Boutons_1());
            this.pnl_Export_Boutons.add(this.getBtn_Html());
            this.pnl_Export_Boutons.add(this.getHorizontalStrut_Boutons_2());
            this.pnl_Export_Boutons.add(this.getBtn_Png());
        }
        return this.pnl_Export_Boutons;
    }
    
    private JButton getBtn_Pdf() {
        if (this.btn_Pdf == null) {
            (this.btn_Pdf = new JButton()).setMaximumSize(new Dimension(128, 40));
            this.btn_Pdf.setMinimumSize(new Dimension(128, 40));
            this.btn_Pdf.setPreferredSize(new Dimension(128, 40));
            this.btn_Pdf.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Pdf.setFont(new Font("Tahoma", 0, 16));
            this.btn_Pdf.setText("PDF");
            this.btn_Pdf.setToolTipText("Exporte au format PDF");
            this.btn_Pdf.addMouseListener(new MyMouseAdapter());
            this.btn_Pdf.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Pdf;
    }
    
    private JButton getBtn_Html() {
        if (this.btn_Html == null) {
            (this.btn_Html = new JButton()).setMaximumSize(new Dimension(128, 40));
            this.btn_Html.setMinimumSize(new Dimension(128, 40));
            this.btn_Html.setPreferredSize(new Dimension(128, 40));
            this.btn_Html.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Html.setFont(new Font("Tahoma", 0, 16));
            this.btn_Html.setText("HTML");
            this.btn_Html.setToolTipText("Exporte au format HTML");
            this.btn_Html.addMouseListener(new MyMouseAdapter());
            this.btn_Html.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Html;
    }
    
    private JButton getBtn_Png() {
        if (this.btn_Png == null) {
            (this.btn_Png = new JButton()).setMaximumSize(new Dimension(128, 40));
            this.btn_Png.setMinimumSize(new Dimension(128, 40));
            this.btn_Png.setPreferredSize(new Dimension(128, 40));
            this.btn_Png.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Png.setFont(new Font("Tahoma", 0, 16));
            this.btn_Png.setText("PNG");
            this.btn_Png.setToolTipText("Exporte au format Image PNG");
            this.btn_Png.addMouseListener(new MyMouseAdapter());
            this.btn_Png.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Png;
    }
    
    private JScrollPane getScrollPane() {
        if (this.scrollPane == null) {
            (this.scrollPane = new JScrollPane()).setHorizontalScrollBarPolicy(31);
            this.scrollPane.setVerticalScrollBarPolicy(22);
            this.scrollPane.setViewportView(this.getEditorPane());
        }
        return this.scrollPane;
    }
    
    private JEditorPane getEditorPane() {
        if (this.editorPane == null) {
            (this.editorPane = new JEditorPane()).setFont(new Font("Tahoma", 0, 16));
            this.editorPane.setContentType("text/html");
            this.editorPane.setEditable(false);
            this.editorPane.setOpaque(true);
            this.editorPane.setText(this.html.getHtml());
            this.editorPane.setCaretPosition(0);
        }
        return this.editorPane;
    }
    
    private JPanel getPnl_Fermeture() {
        if (this.pnl_Fermeture == null) {
            (this.pnl_Fermeture = new JPanel()).setLayout(new BoxLayout(this.pnl_Fermeture, 3));
            this.pnl_Fermeture.add(this.getVerticalStrut_Fermeture_1());
            this.pnl_Fermeture.add(this.getBtn_Fermer());
            this.pnl_Fermeture.add(this.getVerticalStrut_Fermeture_2());
        }
        return this.pnl_Fermeture;
    }
    
    private JButton getBtn_Fermer() {
        if (this.btn_Fermer == null) {
            (this.btn_Fermer = new JButton()).setPreferredSize(new Dimension(128, 40));
            this.btn_Fermer.setMinimumSize(new Dimension(128, 40));
            this.btn_Fermer.setMaximumSize(new Dimension(128, 40));
            this.btn_Fermer.setAlignmentX(0.5f);
            this.btn_Fermer.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Fermer.setFont(new Font("Tahoma", 0, 16));
            this.btn_Fermer.setText("Fermer");
            this.btn_Fermer.addMouseListener(new MyMouseAdapter());
            this.btn_Fermer.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Fermer;
    }
    
    private Component getHorizontalStrutWest() {
        if (this.horizontalStrutWest == null) {
            this.horizontalStrutWest = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrutWest;
    }
    
    private Component getHorizontalStrutEast() {
        if (this.horizontalStrutEast == null) {
            this.horizontalStrutEast = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrutEast;
    }
    
    private Component getVerticalStrut_Export_1() {
        if (this.verticalStrut_Export_1 == null) {
            this.verticalStrut_Export_1 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Export_1;
    }
    
    private Component getVerticalStrut_Export_2() {
        if (this.verticalStrut_Export_2 == null) {
            this.verticalStrut_Export_2 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Export_2;
    }
    
    private Component getHorizontalStrut_Boutons_1() {
        if (this.horizontalStrut_Boutons_1 == null) {
            this.horizontalStrut_Boutons_1 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Boutons_1;
    }
    
    private Component getHorizontalStrut_Boutons_2() {
        if (this.horizontalStrut_Boutons_2 == null) {
            this.horizontalStrut_Boutons_2 = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Boutons_2;
    }
    
    private Component getVerticalStrut_Fermeture_1() {
        if (this.verticalStrut_Fermeture_1 == null) {
            this.verticalStrut_Fermeture_1 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Fermeture_1;
    }
    
    private Component getVerticalStrut_Fermeture_2() {
        if (this.verticalStrut_Fermeture_2 == null) {
            this.verticalStrut_Fermeture_2 = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Fermeture_2;
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_ApercuPlanning.this.btn_Pdf) {
                Dlg_ApercuPlanning.this.actionBtnPDF();
            }
            else if (e.getSource() == Dlg_ApercuPlanning.this.btn_Html) {
                Dlg_ApercuPlanning.this.actionBtnHtml();
            }
            else if (e.getSource() == Dlg_ApercuPlanning.this.btn_Png) {
                Dlg_ApercuPlanning.this.actionBtnPNG();
            }
            else if (e.getSource() == Dlg_ApercuPlanning.this.btn_Fermer) {
                Dlg_ApercuPlanning.this.dispose();
            }
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10) {
                if (e.getSource() == Dlg_ApercuPlanning.this.btn_Pdf) {
                    Dlg_ApercuPlanning.this.actionBtnPDF();
                }
                else if (e.getSource() == Dlg_ApercuPlanning.this.btn_Html) {
                    Dlg_ApercuPlanning.this.actionBtnHtml();
                }
                else if (e.getSource() == Dlg_ApercuPlanning.this.btn_Png) {
                    Dlg_ApercuPlanning.this.actionBtnPNG();
                }
                else if (e.getSource() == Dlg_ApercuPlanning.this.btn_Fermer) {
                    Dlg_ApercuPlanning.this.dispose();
                }
            }
        }
    }
}
