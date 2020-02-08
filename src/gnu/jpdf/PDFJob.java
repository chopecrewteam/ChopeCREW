package gnu.jpdf;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.PrintGraphics;
import java.awt.PrintJob;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * <p>
 * This class extends awt's PrintJob, to provide a simple method of writing PDF
 * documents.
 * </p>
 * 
 * <p>
 * You can use this with any code that uses Java's printing mechanism. It does
 * include a few extra methods to provide access to some of PDF's features like
 * annotations, or outlines.
 * </p>
 * 
 */
public class PDFJob extends PrintJob implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * This is the OutputStream the PDF file will be written to when complete
	 * Note: This is transient, as it's not valid after being Serialized.
	 */
	protected transient OutputStream os;
	
	/**
	 * This is the PDF file being constructed
	 */
	protected PDFDocument pdfDocument;
	
	/**
	 * This is the current page being constructed by the last getGraphics() call
	 */
	protected PDFPage page;
	
	/**
	 * This is the page number of the current page
	 */
	protected int pagenum;
	
	/**
	 * <p>
	 * This constructs the job. This method must be used when creating a
	 * template pdf file, ie one that is Serialised by one application, and then
	 * restored by another.
	 * </p>
	 */
	public PDFJob() {
		this(null);
	}
	
	/**
	 * <p>
	 * This constructs the job. This is the primary constructor that will be
	 * used for creating pdf documents with this package. The specified output
	 * stream is a handle to the .pdf file you wish to create.
	 * </p>
	 * 
	 * @param os
	 *            - <code>OutputStream</code> to use for the pdf output
	 */
	public PDFJob(OutputStream os) {
		this(os, "PDF Doc");
	}
	
	/**
	 * <p>
	 * This constructs the job. This is the primary constructor that will be
	 * used for creating pdf documents with this package. The specified output
	 * stream is a handle to the .pdf file you wish to create.
	 * </p>
	 * 
	 * <p>
	 * Use this constructor if you want to give the pdf document a name other
	 * than the default of "PDF Doc"
	 * </p>
	 * 
	 * @param os
	 *            - <code>OutputStream</code> to use for the pdf output
	 * @param title
	 *            a <code>String</code> value
	 */
	public PDFJob(OutputStream os, String title) {
		this.os = os;
		this.pdfDocument = new PDFDocument();
		pagenum = 0;
		pdfDocument.getPDFInfo().setTitle(title);
	}
	
	/**
	 * <p>
	 * This returns a graphics object that can be used to draw on a page. In
	 * PDF, this will be a new page within the document.
	 * </p>
	 * 
	 * @param orient
	 *            - the <code>int</code> Orientation of the new page, as defined
	 *            in <code>PDFPage</code>
	 * @return Graphics object to draw.
	 * @see PageFormat#PORTRAIT
	 * @see PageFormat#LANDSCAPE
	 * @see PageFormat#REVERSE_LANDSCAPE
	 */
	public Graphics getGraphics(int orient) {
		// create a new page
		page = new PDFPage(orient);
		pdfDocument.add(page);
		pagenum++;
		
		// Now create a Graphics object to draw onto the page
		return new graphic(page, this);
	}
	
	/**
	 * <p>
	 * This returns a graphics object that can be used to draw on a page. In
	 * PDF, this will be a new page within the document.
	 * </p>
	 * 
	 * @param pageFormat
	 *            PageFormat describing the page size
	 * @return Graphics object to draw.
	 */
	public Graphics getGraphics(PageFormat pageFormat) {
		// create a new page
		page = new PDFPage(pageFormat);
		pdfDocument.add(page);
		pagenum++;
		
		// Now create a Graphics object to draw onto the page
		return new graphic(page, this);
	}
	
	/**
	 * <p>
	 * This writes the PDF document to the OutputStream, finishing the document.
	 * </p>
	 */
	@Override
	public void end() {
		try {
			pdfDocument.write(os);
		}
		catch (IOException ioe) {
			// Ideally we should throw this. However, PrintJob doesn't throw
			// anything, so we will print the Stack Trace instead.
			ioe.printStackTrace();
		}
		finally {
			try {
				if (os != null) {
					os.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// This should mark us as dead
		os = null;
		pdfDocument = null;
	}
	
	/**
	 * <p>
	 * This returns a graphics object that can be used to draw on a page. In
	 * PDF, this will be a new page within the document.
	 * </p>
	 * 
	 * <p>
	 * This new page will by default be oriented as a portrait
	 * </p>
	 * 
	 * @return a <code>Graphics</code> object to draw to.
	 */
	@Override
	public Graphics getGraphics() {
		return getGraphics(PageFormat.PORTRAIT);
	}
	
	/**
	 * <p>
	 * Returns the page dimension
	 * </p>
	 * 
	 * @return a <code>Dimension</code> instance, the size of the page
	 */
	@Override
	public Dimension getPageDimension() {
		if (page == null) {
			System.err.println("PDFJob.getPageDimension(), page is null");
		}
		return page.getDimension();
	}
	
	/**
	 * <p>
	 * How about a setPageDimension(Rectangle media) ??
	 * </p>
	 */
	
	/**
	 * This returns the page resolution.
	 * 
	 * <p>
	 * This is the PDF (and Postscript) device resolution of 72 dpi (equivalent
	 * to 1 point).
	 * </p>
	 * 
	 * @return an <code>int</code>, the resolution in pixels per inch
	 */
	@Override
	public int getPageResolution() {
		return 72;
	}
	
	/**
	 * <p>
	 * In AWT's PrintJob, this would return true if the user requested that the
	 * file is printed in reverse order. For PDF's this is not applicable, so it
	 * will always return false.
	 * </p>
	 * 
	 * @return false
	 */
	@Override
	public boolean lastPageFirst() {
		return false;
	}
	
	// ======== END OF PrintJob extension ==========
	
	/**
	 * Returns the PDFDocument object for this document. Useful for gaining
	 * access to the internals of PDFDocument.
	 * 
	 * @return the PDF object
	 */
	public PDFDocument getPDFDocument() {
		return pdfDocument;
	}
	
	/**
	 * <p>
	 * Returns the current PDFPage being worked on. Useful for working on
	 * Annotations (like links), etc.
	 * </p>
	 * 
	 * @return the <code>PDFPage</code> currently being constructed
	 */
	public PDFPage getCurrentPage() {
		return page;
	}
	
	/**
	 * <p>
	 * Returns the current page number. Useful if you need to include one in the
	 * document
	 * </p>
	 * 
	 * @return the <code>int</code> current page number
	 */
	public int getCurrentPageNumber() {
		return pagenum;
	}
	
	/**
	 * <p>
	 * This method attaches an outline to the current page being generated. When
	 * selected, the outline displays the top of the page.
	 * </p>
	 * 
	 * @param title
	 *            a <code>String</code>, the title of the Outline
	 * @return a <code>PDFOutline</code> object that was created, for adding
	 *         sub-outline's if required.
	 */
	public PDFOutline addOutline(String title) {
		return page.addOutline(title);
	}
	
	/**
	 * <p>
	 * This method attaches an outline to the current page being generated. When
	 * selected, the outline displays the specified region.
	 * </p>
	 * 
	 * @param title
	 *            Outline title to attach
	 * @param x
	 *            Left coordinate of region
	 * @param y
	 *            Top coordinate of region
	 * @param w
	 *            width of region
	 * @param h
	 *            height of region
	 * @return the <code>PDFOutline</code> object created, for adding
	 *         sub-outline's if required.
	 */
	public PDFOutline addOutline(String title, int x, int y, int w, int h) {
		return page.addOutline(title, x, y, w, h);
	}
	
	/**
	 * <p>
	 * This inner class extends PDFGraphics for the PrintJob.
	 * </p>
	 * 
	 * <p>
	 * Like with java.awt, Graphics instances created with PrintJob implement
	 * the PrintGraphics interface. Here we implement that method, and overide
	 * PDFGraphics.create() method, so all instances have this interface.
	 * </p>
	 */
	class graphic extends PDFGraphics implements PrintGraphics {
		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		/**
		 * The PDFJob we are linked with
		 */
		private PDFJob job;
		
		/**
		 * @param page
		 *            to attach to
		 * @param job
		 *            PDFJob containing this graphic
		 */
		graphic(PDFPage page, PDFJob job) {
			super();
			this.init(page);
			this.job = job;
		}
		
		/**
		 * This is used by our version of create()
		 */
		graphic(PDFPage page, PDFJob job, PrintWriter pw) {
			super();
			this.init(page, pw);
			this.job = job;
		}
		
		/**
		 * This returns a child instance of this Graphics object. As with AWT,
		 * the affects of using the parent instance while the child exists, is
		 * not determined.
		 * 
		 * <p>
		 * This method is used to make a new Graphics object without going to a
		 * new page
		 * </p>
		 * 
		 * <p>
		 * Once complete, the child should be released with it's dispose()
		 * method which will restore the graphics state to it's parent.
		 * 
		 * @return Graphics object
		 */
		@Override
		public Graphics create() {
			closeBlock();
			graphic g = new graphic(getPage(), job, getWriter());
			
			// The new instance inherits a few items
			g.clipRectangle = new Rectangle(clipRectangle);
			
			return g;
		}
		
		/**
		 * This is the PrintGraphics interface
		 * 
		 * @return PrintJob for this object
		 */
		public PrintJob getPrintJob() {
			return job;
		}
		
	}
	
}
