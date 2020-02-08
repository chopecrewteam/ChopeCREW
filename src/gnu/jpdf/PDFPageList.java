package gnu.jpdf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

/**
 * This object contains the document's pages.
 * 
 */
public class PDFPageList extends PDFObject {
	
	private static final long serialVersionUID = 1L;
	/**
	 * This holds the pages
	 */
	private Vector<PDFPage> pages;
	
	/**
	 * This constructs a PDF Pages object.
	 */
	public PDFPageList() {
		super("/Pages");
		pages = new Vector<PDFPage>();
	}
	
	/**
	 * This adds a page to the document.
	 * 
	 * @param page
	 *            PDFPage to add
	 */
	public void add(PDFPage page) {
		pages.addElement(page);
		
		// Tell the page of ourselves
		page.pdfPageList = this;
	}
	
	/**
	 * This returns a specific page. Used by the PDF class.
	 * 
	 * @param page
	 *            page number to return
	 * @return PDFPage at that position
	 */
	public PDFPage getPage(int page) {
		return (pages.elementAt(page));
	}
	
	/**
	 * @param os
	 *            OutputStream to send the object to
	 * @exception IOException
	 *                on error
	 */
	@Override
	public void write(OutputStream os) throws IOException {
		// Write the object header
		writeStart(os);
		
		// now the objects body
		
		// the Kids array
		os.write("/Kids ".getBytes());
		os.write(PDFObject.toArray(pages).getBytes());
		os.write("\n".getBytes());
		
		// the number of Kids in this document
		os.write("/Count ".getBytes());
		os.write(Integer.toString(pages.size()).getBytes());
		os.write("\n".getBytes());
		
		// finish off with its footer
		writeEnd(os);
	}
	
} // end class PDFPageList
