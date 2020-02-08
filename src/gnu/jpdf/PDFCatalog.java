package gnu.jpdf;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * This class implements the PDF Catalog, also known as the root node
 * </p>
 */
public class PDFCatalog extends PDFObject {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * The pages of the document
	 */
	private PDFPageList pdfPageList;
	
	/**
	 * The outlines of the document
	 */
	private PDFOutline outlines;
	
	/**
	 * The initial page mode
	 */
	private int pagemode;
	
	/**
	 * This constructs a PDF Catalog object
	 * 
	 * @param pdfPageList
	 *            The PDFPageList object that's the root of the documents page
	 *            tree
	 * @param pagemode
	 *            How the document should appear when opened. Allowed values are
	 *            USENONE, USEOUTLINES, USETHUMBS or FULLSCREEN.
	 */
	public PDFCatalog(PDFPageList pdfPageList, int pagemode) {
		super("/Catalog");
		this.pdfPageList = pdfPageList;
		this.pagemode = pagemode;
	}
	
	/**
	 * This sets the root outline object
	 * 
	 * @param outline
	 *            The root outline
	 */
	protected void setOutline(PDFOutline outline) {
		this.outlines = outline;
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
		
		// the /Pages object
		os.write("/Pages ".getBytes());
		os.write(pdfPageList.toString().getBytes());
		os.write("\n".getBytes());
		
		// the Outlines object
		if (outlines != null) {
			os.write("/Outlines ".getBytes());
			os.write(outlines.toString().getBytes());
			os.write("\n".getBytes());
		}
		
		// the /PageMode setting
		os.write("/PageMode ".getBytes());
		os.write(PDFDocument.PDF_PAGE_MODES[pagemode].getBytes());
		os.write("\n".getBytes());
		
		// finish off with its footer
		writeEnd(os);
	}
}
