// 
// Decompiled by Procyon v0.5.36
// 

package ccGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.net.URI;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.Box;
import java.awt.event.KeyListener;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Color;
import ccUtils.Utils;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDialog;

public class Dlg_Aide extends JDialog
{
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane;
    private JPanel panel;
    private JPanel pnlApropos;
    private JLabel labVersion;
    private JLabel labDatePublication;
    private JLabel labAuteurs;
    private JLabel labContact;
    private JLabel labUrl;
    private JLabel labVersionValue;
    private JLabel labDatePublicationValue;
    private JLabel labAuteursValue;
    private JLabel labContactValue;
    private JLabel labUrlValue;
    private JButton btn_Fermer;
    private Component verticalStrut_Down;
    private Component horizontalStrut_Right;
    private Component horizontalStrut_Left;
    
    public Dlg_Aide(final Frame owner) {
        super(owner);
        this.jContentPane = null;
        this.pnlApropos = null;
        this.labVersion = null;
        this.labDatePublication = null;
        this.labAuteurs = null;
        this.labContact = null;
        this.labUrl = null;
        this.labVersionValue = null;
        this.labDatePublicationValue = null;
        this.labAuteursValue = null;
        this.labContactValue = null;
        this.labUrlValue = null;
        this.btn_Fermer = null;
        this.initialize();
    }
    
    private void initialize() {
        this.setModal(true);
        this.setSize(480, 256);
        this.setResizable(false);
        this.setTitle("Aide & \u00e0 propos...");
        this.setContentPane(this.getJContentPane());
    }
    
    private void actionBtnClose() {
        this.dispose();
    }
    
    private JPanel getJContentPane() {
        if (this.jContentPane == null) {
            (this.jContentPane = new JPanel()).setLayout(new BoxLayout(this.jContentPane, 3));
            this.jContentPane.add(this.getPanel());
            this.jContentPane.add(this.getBtn_Fermer());
            this.jContentPane.add(this.getVerticalStrut_Down());
        }
        return this.jContentPane;
    }
    
    private JPanel getPanel() {
        if (this.panel == null) {
            (this.panel = new JPanel()).setLayout(new BoxLayout(this.panel, 0));
            this.panel.add(this.getHorizontalStrut_Left());
            this.panel.add(this.getPnlApropos());
            this.panel.add(this.getHorizontalStrut_Right());
        }
        return this.panel;
    }
    
    private JPanel getPnlApropos() {
        if (this.pnlApropos == null) {
            (this.pnlApropos = new JPanel()).setBorder(null);
            final GridBagLayout gbl_pnlApropos = new GridBagLayout();
            gbl_pnlApropos.columnWidths = new int[] { 184, 256, 0 };
            gbl_pnlApropos.rowHeights = new int[] { 30, 30, 30, 30, 30, 0 };
            gbl_pnlApropos.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
            gbl_pnlApropos.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
            this.pnlApropos.setLayout(gbl_pnlApropos);
            (this.labVersion = new JLabel()).setFont(new Font("Tahoma", 0, 16));
            this.labVersion.setText("Version :");
            final GridBagConstraints gbc_labVersion = new GridBagConstraints();
            gbc_labVersion.anchor = 17;
            gbc_labVersion.insets = new Insets(0, 5, 5, 0);
            gbc_labVersion.gridx = 0;
            gbc_labVersion.gridy = 0;
            this.pnlApropos.add(this.labVersion, gbc_labVersion);
            (this.labVersionValue = new JLabel()).setAlignmentX(1.0f);
            this.labVersionValue.setFont(new Font("Tahoma", 0, 16));
            this.labVersionValue.setHorizontalAlignment(4);
            this.labVersionValue.setHorizontalTextPosition(4);
            this.labVersionValue.setText("3.8");
            final GridBagConstraints gbc_labVersionValue = new GridBagConstraints();
            gbc_labVersionValue.anchor = 13;
            gbc_labVersionValue.insets = new Insets(0, 0, 5, 5);
            gbc_labVersionValue.gridx = 1;
            gbc_labVersionValue.gridy = 0;
            this.pnlApropos.add(this.labVersionValue, gbc_labVersionValue);
            (this.labDatePublication = new JLabel()).setAlignmentX(0.5f);
            this.labDatePublication.setFont(new Font("Tahoma", 0, 16));
            this.labDatePublication.setText("Date de publication :");
            final GridBagConstraints gbc_labDatePublication = new GridBagConstraints();
            gbc_labDatePublication.anchor = 17;
            gbc_labDatePublication.insets = new Insets(0, 5, 5, 0);
            gbc_labDatePublication.gridx = 0;
            gbc_labDatePublication.gridy = 1;
            this.pnlApropos.add(this.labDatePublication, gbc_labDatePublication);
            (this.labDatePublicationValue = new JLabel()).setAlignmentX(0.5f);
            this.labDatePublicationValue.setFont(new Font("Tahoma", 0, 16));
            this.labDatePublicationValue.setHorizontalAlignment(4);
            this.labDatePublicationValue.setHorizontalTextPosition(4);
            this.labDatePublicationValue.setText(Utils.convertBuildToDate(20200202));
            final GridBagConstraints gbc_labDatePublicationValue = new GridBagConstraints();
            gbc_labDatePublicationValue.anchor = 13;
            gbc_labDatePublicationValue.insets = new Insets(0, 0, 5, 5);
            gbc_labDatePublicationValue.gridx = 1;
            gbc_labDatePublicationValue.gridy = 1;
            this.pnlApropos.add(this.labDatePublicationValue, gbc_labDatePublicationValue);
            (this.labAuteurs = new JLabel()).setAlignmentX(0.5f);
            this.labAuteurs.setFont(new Font("Tahoma", 0, 16));
            this.labAuteurs.setText("Auteurs :");
            final GridBagConstraints gbc_labAuteurs = new GridBagConstraints();
            gbc_labAuteurs.anchor = 17;
            gbc_labAuteurs.insets = new Insets(0, 5, 5, 0);
            gbc_labAuteurs.gridx = 0;
            gbc_labAuteurs.gridy = 2;
            this.pnlApropos.add(this.labAuteurs, gbc_labAuteurs);
            (this.labAuteursValue = new JLabel()).setAlignmentX(0.5f);
            this.labAuteursValue.setFont(new Font("Tahoma", 0, 16));
            this.labAuteursValue.setHorizontalAlignment(4);
            this.labAuteursValue.setHorizontalTextPosition(4);
            this.labAuteursValue.setText("Cyril REAL et Laurent GAFFIOT");
            final GridBagConstraints gbc_labAuteursValue = new GridBagConstraints();
            gbc_labAuteursValue.anchor = 13;
            gbc_labAuteursValue.insets = new Insets(0, 0, 5, 5);
            gbc_labAuteursValue.gridx = 1;
            gbc_labAuteursValue.gridy = 2;
            this.pnlApropos.add(this.labAuteursValue, gbc_labAuteursValue);
            (this.labContact = new JLabel()).setAlignmentX(0.5f);
            this.labContact.setFont(new Font("Tahoma", 0, 16));
            this.labContact.setText("Adresse e-mail :");
            final GridBagConstraints gbc_labContact = new GridBagConstraints();
            gbc_labContact.anchor = 17;
            gbc_labContact.insets = new Insets(0, 5, 5, 0);
            gbc_labContact.gridx = 0;
            gbc_labContact.gridy = 3;
            this.pnlApropos.add(this.labContact, gbc_labContact);
            (this.labContactValue = new JLabel()).setAlignmentX(0.5f);
            this.labContactValue.setFont(new Font("Tahoma", 0, 16));
            this.labContactValue.setHorizontalAlignment(4);
            this.labContactValue.setHorizontalTextPosition(4);
            this.labContactValue.setForeground(Color.blue);
            this.labContactValue.setText("chopecrew@free.fr");
            this.labContactValue.setCursor(new Cursor(12));
            this.labContactValue.addMouseListener(new MyMouseAdapter());
            final GridBagConstraints gbc_labContactValue = new GridBagConstraints();
            gbc_labContactValue.anchor = 13;
            gbc_labContactValue.insets = new Insets(0, 0, 5, 5);
            gbc_labContactValue.gridx = 1;
            gbc_labContactValue.gridy = 3;
            this.pnlApropos.add(this.labContactValue, gbc_labContactValue);
            (this.labUrl = new JLabel()).setAlignmentX(0.5f);
            this.labUrl.setFont(new Font("Tahoma", 0, 16));
            this.labUrl.setText("Site Internet  (Aide) :");
            final GridBagConstraints gbc_labUrl = new GridBagConstraints();
            gbc_labUrl.anchor = 17;
            gbc_labUrl.insets = new Insets(0, 5, 5, 0);
            gbc_labUrl.gridx = 0;
            gbc_labUrl.gridy = 4;
            this.pnlApropos.add(this.labUrl, gbc_labUrl);
            (this.labUrlValue = new JLabel()).setAlignmentX(0.5f);
            this.labUrlValue.setForeground(Color.blue);
            this.labUrlValue.setHorizontalAlignment(4);
            this.labUrlValue.setHorizontalTextPosition(4);
            this.labUrlValue.setText("http://chopecrew.free.fr");
            this.labUrlValue.setCursor(new Cursor(12));
            this.labUrlValue.setFont(new Font("Tahoma", 0, 16));
            this.labUrlValue.addMouseListener(new MyMouseAdapter());
            final GridBagConstraints gbc_labUrlValue = new GridBagConstraints();
            gbc_labUrlValue.insets = new Insets(0, 0, 5, 5);
            gbc_labUrlValue.anchor = 13;
            gbc_labUrlValue.gridx = 1;
            gbc_labUrlValue.gridy = 4;
            this.pnlApropos.add(this.labUrlValue, gbc_labUrlValue);
        }
        return this.pnlApropos;
    }
    
    private JButton getBtn_Fermer() {
        if (this.btn_Fermer == null) {
            (this.btn_Fermer = new JButton()).setPreferredSize(new Dimension(128, 32));
            this.btn_Fermer.setMinimumSize(new Dimension(128, 32));
            this.btn_Fermer.setMaximumSize(new Dimension(128, 40));
            this.btn_Fermer.setAlignmentX(0.5f);
            this.btn_Fermer.setFont(new Font("Tahoma", 0, 16));
            this.btn_Fermer.setMargin(new Insets(2, 2, 2, 2));
            this.btn_Fermer.setText("Fermer");
            this.btn_Fermer.addMouseListener(new MyMouseAdapter());
            this.btn_Fermer.addKeyListener(new MyKeyAdapter());
        }
        return this.btn_Fermer;
    }
    
    private Component getVerticalStrut_Down() {
        if (this.verticalStrut_Down == null) {
            this.verticalStrut_Down = Box.createVerticalStrut(20);
        }
        return this.verticalStrut_Down;
    }
    
    private Component getHorizontalStrut_Right() {
        if (this.horizontalStrut_Right == null) {
            this.horizontalStrut_Right = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Right;
    }
    
    private Component getHorizontalStrut_Left() {
        if (this.horizontalStrut_Left == null) {
            this.horizontalStrut_Left = Box.createHorizontalStrut(20);
        }
        return this.horizontalStrut_Left;
    }
    
    class MyMouseAdapter extends MouseAdapter
    {
        @Override
        public void mouseClicked(final MouseEvent e) {
            if (e.getSource() == Dlg_Aide.this.btn_Fermer) {
                Dlg_Aide.this.actionBtnClose();
            }
            else if (e.getSource() == Dlg_Aide.this.labContactValue) {
                final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.MAIL)) {
                    try {
                        desktop.mail(new URI("mailto", "chopecrew@free.fr", null));
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            else if (e.getSource() == Dlg_Aide.this.labUrlValue) {
                final Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
                if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                    try {
                        desktop.browse(new URI("http://chopecrew.free.fr"));
                    }
                    catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
    
    class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(final KeyEvent e) {
            if (e.getKeyCode() == 10 && e.getSource() == Dlg_Aide.this.btn_Fermer) {
                Dlg_Aide.this.actionBtnClose();
            }
        }
    }
}
