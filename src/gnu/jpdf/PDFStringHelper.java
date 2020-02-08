package gnu.jpdf;

/**
 * String manipulation methods
 * 
 */
public class PDFStringHelper {
	/**
	 * This converts a string into PDF. It prefixes ( or ) with \ and wraps the
	 * string in a ( ) pair.
	 * 
	 * @param s
	 *            String to convert
	 * @return String that can be placed in a PDF (or Postscript) stream
	 */
	public static String makePDFString(String s) {
		if (s.indexOf("(") > -1) {
			s = replace(s, "(", "\\(");
		}
		
		if (s.indexOf(")") > -1) {
			s = replace(s, ")", "\\)");
		}
		
		return "(" + s + ")";
	}
	
	/**
	 * Helper method for makePDFString()
	 * 
	 * @param s
	 *            source string
	 * @param f
	 *            string to remove
	 * @param t
	 *            string to replace f
	 * @return string with f replaced by t
	 */
	private static String replace(String source, String removeThis, String replaceWith) {
		StringBuffer b = new StringBuffer();
		int p = 0, c = 0;
		
		while (c > -1) {
			if ((c = source.indexOf(removeThis, p)) > -1) {
				b.append(source.substring(p, c));
				b.append(replaceWith);
				p = c + 1;
			}
		}
		
		// include any remaining text
		if (p < source.length()) {
			b.append(source.substring(p));
		}
		
		return b.toString();
	}
}
