// 
// Decompiled by Procyon v0.5.36
// 

package ccUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.InputStream;
import java.io.File;
import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.security.MessageDigest;
import java.io.Writer;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils
{
    public static String findValue(final String source, final String name) {
        String s = null;
        final String cible = "input type=\"hidden\"(.*?) name=\"" + name + "\" value=\"(.*?)\"";
        final Pattern regex = Pattern.compile(cible);
        final Matcher result = regex.matcher(source);
        if (result.find()) {
            s = result.group(2);
        }
        return s;
    }
    
    public static int saveToFile(final String source, final String chemin, final String encodage) {
        try {
            final FileOutputStream fos = new FileOutputStream(chemin);
            Writer out;
            if (encodage == null) {
                out = new OutputStreamWriter(fos);
            }
            else {
                out = new OutputStreamWriter(fos, encodage);
            }
            out.write(source);
            out.close();
            fos.close();
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static double arrondi(final double x, final int n) {
        final double k = Math.pow(10.0, n);
        return Math.floor(x * k + 0.5 + 1.0E-9) / k;
    }
    
    public static String timetoString(final double time) {
        final long h = (long)Math.floor(time);
        final long m = Math.round((time - h) * 60.0);
        if (m < 10L) {
            return String.valueOf(h) + "h0" + m;
        }
        return String.valueOf(h) + "h" + m;
    }
    
    public static String hourtoString(final double time) {
        final long h = (long)Math.floor(time);
        final long m = Math.round((time - h) * 60.0);
        if (m < 10L && h < 10L) {
            return "0" + h + "h0" + m;
        }
        if (m < 10L && h >= 10L) {
            return String.valueOf(h) + "h0" + m;
        }
        if (m >= 10L && h < 10L) {
            return "0" + h + "h" + m;
        }
        return String.valueOf(h) + "h" + m;
    }
    
    public static long timeStringtoMs(final String time) {
        if (time == null) {
            return 0L;
        }
        final String cible = "h";
        final int index = time.indexOf(cible);
        final int heures = Integer.parseInt(time.substring(0, index));
        final int minutes = Integer.parseInt(time.substring(index + 1));
        return heures * 60 * 60 * 1000 + minutes * 60 * 1000;
    }
    
    public static String getMD5(final String s) {
        byte[] md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5").digest(s.getBytes());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        final StringBuilder sb = new StringBuilder();
        byte[] array;
        for (int length = (array = md5).length, i = 0; i < length; ++i) {
            final byte element = array[i];
            final String hex = Integer.toHexString(element);
            if (hex.length() == 1) {
                sb.append('0');
                sb.append(hex.charAt(hex.length() - 1));
            }
            else {
                sb.append(hex.substring(hex.length() - 2));
            }
        }
        return sb.toString();
    }
    
    public static long calculNuit(final GregorianCalendar eventDebut, final GregorianCalendar eventFin, final int plageDebut, final int plageFin) {
        final GregorianCalendar cal1 = new GregorianCalendar();
        final GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTimeZone(eventDebut.getTimeZone());
        cal2.setTimeZone(eventDebut.getTimeZone());
        long result = 0L;
        cal1.setTime(eventDebut.getTime());
        cal1.set(11, plageDebut);
        cal1.set(12, 0);
        cal1.set(13, 0);
        cal1.set(14, 0);
        if (cal1.after(eventDebut)) {
            cal1.add(5, -1);
        }
        cal2.setTime(cal1.getTime());
        cal2.set(11, plageFin);
        if (cal2.before(cal1)) {
            cal2.add(5, 1);
        }
        while (cal1.before(eventFin)) {
            result += Math.max(Math.min(eventFin.getTimeInMillis(), cal2.getTimeInMillis()) - Math.max(eventDebut.getTimeInMillis(), cal1.getTimeInMillis()), 0L);
            cal1.add(5, 1);
            cal2.add(5, 1);
        }
        return result;
    }
    
    public static int calculDecouchersLC(final GregorianCalendar eventDebut, final GregorianCalendar eventFin, final int plageDebut, final int plageFin, final int duree) {
        final GregorianCalendar cal1 = new GregorianCalendar();
        final GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTimeZone(eventDebut.getTimeZone());
        cal2.setTimeZone(eventDebut.getTimeZone());
        int result = 0;
        cal1.setTime(eventDebut.getTime());
        cal1.set(11, 0);
        cal1.set(12, plageDebut);
        cal1.set(13, 0);
        cal1.set(14, 0);
        if (cal1.after(eventDebut)) {
            cal1.add(5, -1);
        }
        cal2.setTime(cal1.getTime());
        cal2.set(11, 0);
        cal2.set(12, plageFin);
        if (cal2.before(cal1)) {
            cal2.add(5, 1);
        }
        while (cal1.before(eventFin)) {
            if (Math.max(Math.min(eventFin.getTimeInMillis(), cal2.getTimeInMillis()) - Math.max(eventDebut.getTimeInMillis(), cal1.getTimeInMillis()), 0L) >= duree * 60000) {
                ++result;
            }
            cal1.add(5, 1);
            cal2.add(5, 1);
        }
        return result;
    }
    
    public static String convertBuildToDate(final int build) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(0L);
        try {
            date = sdf.parse(String.valueOf(build));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern("dd/MM/yyyy");
        return sdf.format(date);
    }
    
    public static void sendExceptionToClipboard(final Exception e) {
        final StringSelection ss = new StringSelection(String.valueOf(e.getMessage()) + "\n" + e.getLocalizedMessage());
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(ss, null);
    }
    
    public static String rgb2hex(final Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    
    public static Color hex2rgb(final String colorStr) {
        if (colorStr == null) {
            return null;
        }
        return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16), Integer.valueOf(colorStr.substring(5, 7), 16));
    }
    
    public static boolean deleteDirectory(final File path) {
        boolean resultat = true;
        if (path.exists()) {
            final File[] files = path.listFiles();
            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    resultat &= deleteDirectory(files[i]);
                }
                else {
                    resultat &= files[i].delete();
                }
            }
        }
        resultat &= path.delete();
        return resultat;
    }
    
    public static String streamToString(final InputStream is, final String charset) {
        final StringWriter sw = new StringWriter();
        try {
            InputStreamReader isr;
            if (charset == null) {
                isr = new InputStreamReader(is);
            }
            else {
                isr = new InputStreamReader(is, charset);
            }
            int chr;
            while ((chr = isr.read()) != -1) {
                sw.write(chr);
            }
            isr.close();
            sw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
