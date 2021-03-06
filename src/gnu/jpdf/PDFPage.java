package gnu.jpdf;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.print.PageFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Vector;

/**
 * <p>
 * This class defines a single page within a document. It is linked to a single
 * PDFGraphics object
 * </p>
 * 
 */
public class PDFPage extends PDFObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default page format (Letter size with 1 inch margins and Portrait
	 * orientation)
	 */
	private static final PageFormat DEF_FORMAT = new PageFormat();
	
	/**
	 * This is this page format, ie the size of the page, margins, and rotation
	 */
	protected PageFormat pageFormat;
	
	/**
	 * This is the pages object id that this page belongs to. It is set by the
	 * pages object when it is added to it.
	 */
	protected PDFObject pdfPageList;
	
	/**
	 * This holds the contents of the page.
	 */
	protected Vector<PDFObject> contents;
	
	/**
	 * Object ID that contains a thumbnail sketch of the page. -1 indicates no
	 * thumbnail.
	 */
	protected PDFObject thumbnail;
	
	/**
	 * This holds any Annotations contained within this page.
	 */
	protected Vector<PDFObject> annotations;
	
	/**
	 * This holds any resources for this page
	 */
	protected Vector<String> resources;
	
	// JM
	protected Vector<String> imageResources;
	
	/**
	 * The fonts associated with this page
	 */
	protected Vector<PDFFont> fonts;
	
	/**
	 * The xobjects or other images in the pdf
	 */
	// protected Vector xobjects;
	/**
	 * These handle the procset for this page. Refer to page 140 of the PDF
	 * Reference manual NB: Text is handled when the fonts Vector is null, and a
	 * font is created refer to getFont() to see where it's defined
	 */
	protected boolean hasImageB, hasImageC, hasImageI;
	protected procset procset;
	
	/**
	 * This constructs a Page object, which will hold any contents for this
	 * page.
	 * 
	 * <p>
	 * Once created, it is added to the document via the PDF.add() method. (For
	 * Advanced use, via the PDFPages.add() method).
	 * 
	 * <p>
	 * This defaults to a4 media.
	 */
	public PDFPage() {
		super("/Page");
		pageFormat = DEF_FORMAT;
		contents = new Vector<PDFObject>();
		thumbnail = null;
		annotations = new Vector<PDFObject>();
		resources = new Vector<String>();
		// JM
		imageResources = new Vector<String>();
		fonts = new Vector<PDFFont>();
		procset = null;
	}
	
	/**
	 * Constructs a page using A4 media, but using the supplied orientation.
	 * 
	 * @param orientation
	 *            Orientation: 0, 90 or 270
	 * @see PageFormat#PORTRAIT
	 * @see PageFormat#LANDSCAPE
	 * @see PageFormat#REVERSE_LANDSCAPE
	 */
	public PDFPage(int orientation) {
		this();
		setOrientation(orientation);
	}
	
	/**
	 * Constructs a page using the supplied media size and orientation.
	 * 
	 * @param pageFormat
	 *            PageFormat describing the page size
	 */
	public PDFPage(PageFormat pageFormat) {
		this();
		this.pageFormat = pageFormat;
	}
	
	/**
	 * Adds to procset.
	 * 
	 * @param proc
	 *            the String to be added.
	 */
	public void addToProcset(String proc) {
		if (procset == null) {
			addProcset();
		}
		procset.add(proc);
	}
	
	/**
	 * This returns a PDFGraphics object, which can then be used to render on to
	 * this page. If a previous PDFGraphics object was used, this object is
	 * appended to the page, and will be drawn over the top of any previous
	 * objects.
	 * 
	 * @return a new PDFGraphics object to be used to draw this page.
	 */
	public PDFGraphics getGraphics() {
		try {
			PDFGraphics g = new PDFGraphics();
			g.init(this);
			return g;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Returns a PDFFont, creating it if not yet used.
	 * 
	 * @param type
	 *            Font type, usually /Type1
	 * @param font
	 *            Font name
	 * @param style
	 *            java.awt.Font style, ie Font.NORMAL
	 * @return a PDFFont object.
	 */
	public PDFFont getFont(String type, String font, int style) {
		// Search the fonts on this page, and return one that matches this
		// font.
		// This keeps the number of font definitions down to one per font/style
		for (PDFFont ft : fonts) {
			if (ft.equals(type, font, style)) {
				return ft;
			}
		}
		
		// Ok, the font isn't in the page, so create one.
		
		// We need a procset if we are using fonts, so create it (if not
		// already created, and add to our resources
		if (fonts.size() == 0) {
			addProcset();
			procset.add("/Text");
		}
		
		// finally create and return the font
		PDFFont f = pdfDocument.getFont(type, font, style);
		fonts.addElement(f);
		return f;
	}
	
	/**
	 * Returns the page's PageFormat.
	 * 
	 * @return PageFormat describing the page size in device units (72dpi)
	 */
	public PageFormat getPageFormat() {
		return pageFormat;
	}
	
	/**
	 * Gets the dimensions of the page.
	 * 
	 * @return a Dimension object containing the width and height of the page.
	 */
	public Dimension getDimension() {
		return new Dimension((int) pageFormat.getWidth(), (int) pageFormat.getHeight());
	}
	
	/**
	 * Gets the imageable area of the page.
	 * 
	 * @return a Rectangle containing the bounds of the imageable area.
	 */
	public Rectangle getImageableArea() {
		return new Rectangle((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY(), (int) (pageFormat.getImageableX() + pageFormat.getImageableWidth()), (int) (pageFormat.getImageableY() + pageFormat.getImageableHeight()));
	}
	
	/**
	 * Sets the page's orientation.
	 * 
	 * <p>
	 * Normally, this should be done when the page is created, to avoid
	 * problems.
	 * 
	 * @param orientation
	 *            a PageFormat orientation constant: PageFormat.PORTRAIT,
	 *            PageFormat.LANDSACPE or PageFromat.REVERSE_LANDSACPE
	 */
	public void setOrientation(int orientation) {
		pageFormat.setOrientation(orientation);
	}
	
	/**
	 * Returns the pages orientation: PageFormat.PORTRAIT, PageFormat.LANDSACPE
	 * or PageFromat.REVERSE_LANDSACPE
	 * 
	 * @see java.awt.print.PageFormat
	 * @return current orientation of the page
	 */
	public int getOrientation() {
		return pageFormat.getOrientation();
	}
	
	/**
	 * This adds an object that describes some content to this page.
	 * 
	 * <p>
	 * <b>Note:</b> Objects that describe contents must be added using this
	 * method _AFTER_ the PDF.add() method has been called.
	 * 
	 * @param ob
	 *            PDFObject describing some contents
	 */
	public void add(PDFObject ob) {
		contents.addElement(ob);
	}
	
	/**
	 * This adds an Annotation to the page.
	 * 
	 * <p>
	 * As with other objects, the annotation must be added to the pdf document
	 * using PDF.add() before adding to the page.
	 * 
	 * @param ob
	 *            Annotation to add.
	 */
	public void addAnnotation(PDFObject ob) {
		annotations.addElement(ob);
	}
	
	/** Contains the text strings for the xobjects. */
	private Vector<String> xobjects = new Vector<String>();
	
	/**
	 * This adds an XObject resource to the page. The string should be of the
	 * format /Name ObjectNumber RevisionNumber R as in /Image1 13 0 R .
	 * 
	 * @param inxobject
	 *            the XObject resource to be added.
	 */
	public void addXObject(String inxobject) {
		xobjects.addElement(inxobject);
	}
	
	/**
	 * This adds a resource to the page.
	 * 
	 * @param resource
	 *            String defining the resource
	 */
	public void addResource(String resource) {
		resources.addElement(resource);
	}
	
	// JM
	/**
	 * This adds an image resource to the page.
	 * 
	 * @param resource
	 *            the XObject resource to be added.
	 */
	public void addImageResource(String resource) {
		imageResources.addElement(resource);
	}
	
	/**
	 * This adds an object that describes a thumbnail for this page.
	 * <p>
	 * <b>Note:</b> The object must already exist in the PDF, as only the object
	 * ID is stored.
	 * 
	 * @param thumbnail
	 *            PDFObject containing the thumbnail
	 */
	public void setThumbnail(PDFObject thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	/**
	 * This method attaches an outline to the current page being generated. When
	 * selected, the outline displays the top of the page.
	 * 
	 * @param title
	 *            Outline title to attach
	 * @return PDFOutline object created, for addSubOutline if required.
	 */
	public PDFOutline addOutline(String title) {
		PDFOutline outline = new PDFOutline(title, this);
		pdfDocument.add(outline);
		pdfDocument.getOutline().add(outline);
		return outline;
	}
	
	/**
	 * This method attaches an outline to the current page being generated. When
	 * selected, the outline displays the top of the page.
	 * 
	 * <p>
	 * Note: If the outline is not in the top level (ie below another outline)
	 * then it must <b>not</b> be passed to this method.
	 * 
	 * @param title
	 *            Outline title to attach
	 * @param x
	 *            Left coordinate of region
	 * @param y
	 *            Bottom coordinate of region
	 * @param w
	 *            Width of region
	 * @param h
	 *            Height coordinate of region
	 * @return PDFOutline object created, for addSubOutline if required.
	 */
	public PDFOutline addOutline(String title, int x, int y, int w, int h) {
		int xy1[] = cxy(x, y + h);
		int xy2[] = cxy(x + w, y);
		PDFOutline outline = new PDFOutline(title, this, xy1[0], xy1[1], xy2[0], xy2[1]);
		pdfDocument.add(outline);
		pdfDocument.getOutline().add(outline);
		return outline;
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
		
		// the /Parent pages object
		os.write("/Parent ".getBytes());
		os.write(pdfPageList.toString().getBytes());
		os.write("\n".getBytes());
		
		// the /MediaBox for the page size
		os.write("/MediaBox [".getBytes());
		os.write(Integer.toString(0).getBytes());
		os.write(" ".getBytes());
		os.write(Integer.toString(0).getBytes());
		os.write(" ".getBytes());
		os.write(Integer.toString((int) pageFormat.getWidth()).getBytes());
		os.write(" ".getBytes());
		os.write(Integer.toString((int) pageFormat.getHeight()).getBytes());
		os.write("]\n".getBytes());
		
		// Rotation (if not zero)
		// if(rotate!=0) {
		// os.write("/Rotate ".getBytes());
		// os.write(Integer.toString(rotate).getBytes());
		// os.write("\n".getBytes());
		// }
		
		// Now the resources
		os.write("/Resources << ".getBytes());
		// fonts
		if (fonts.size() > 0) {
			// os.write("/Font << ".getBytes());
			os.write("\n/Font << ".getBytes());
			for (PDFFont font : fonts) {
				os.write(font.getName().getBytes());
				os.write(" ".getBytes());
				os.write(font.toString().getBytes());
				os.write(" ".getBytes());
			}
			os.write(">> ".getBytes());
		}
		// Now the XObjects
		if (xobjects.size() > 0) {
			os.write("\n/XObject << ".getBytes());
			for (String str : xobjects) {
				os.write(str.getBytes());
				os.write(" ".getBytes());
			}
			os.write(">> ".getBytes());
		}
		// Any other resources
		for (String str : resources) {
			os.write(str.getBytes());
			os.write(" ".getBytes());
		}
		// JM
		if (imageResources.size() > 0) {
			os.write("/XObject << ".getBytes());
			for (String str : imageResources) {
				os.write(str.getBytes());
				os.write(" ".getBytes());
			}
			os.write(" >> ".getBytes());
		}
		os.write(">>\n".getBytes());
		
		// The thumbnail
		if (thumbnail != null) {
			os.write("/Thumb ".getBytes());
			os.write(thumbnail.toString().getBytes());
			os.write("\n".getBytes());
		}
		
		// the /Contents pages object
		if (contents.size() > 0) {
			if (contents.size() == 1) {
				PDFObject ob = contents.elementAt(0);
				os.write("/Contents ".getBytes());
				os.write(ob.toString().getBytes());
				os.write("\n".getBytes());
			}
			else {
				os.write("/Contents [".getBytes());
				os.write(PDFObject.toArray(contents).getBytes());
				os.write("\n".getBytes());
			}
		}
		
		// The /Annots object
		if (annotations.size() > 0) {
			os.write("/Annots ".getBytes());
			os.write(PDFObject.toArray(annotations).getBytes());
			os.write("\n".getBytes());
		}
		
		// finish off with its footer
		writeEnd(os);
	}
	
	/**
	 * This creates a procset and sets up the page to reference it
	 */
	private void addProcset() {
		if (procset == null) {
			pdfDocument.add(procset = new procset());
			resources.addElement("/ProcSet " + procset);
		}
	}
	
	/**
	 * This defines a procset
	 */
	public class procset extends PDFObject {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Vector<String> set;
		
		/**
		 * Creates a new procset object.
		 */
		public procset() {
			super(null);
			set = new Vector<String>();
			
			// Our default procset (use addElement not add, as we dont want a
			// leading space)
			set.addElement("/PDF");
		}
		
		/**
		 * @param proc
		 *            Entry to add to the procset
		 */
		public void add(String proc) {
			set.addElement(" " + proc);
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
			// writeStart(os);
			
			os.write(Integer.toString(objser).getBytes());
			os.write(" 0 obj\n".getBytes());
			
			// now the objects body
			os.write("[".getBytes());
			for (String str : set) {
				os.write(str.getBytes());
			}
			os.write("]\n".getBytes());
			
			// finish off with its footer
			// writeEnd(os);
			os.write("endobj\n".getBytes());
		}
	}
	
	/**
	 * This utility method converts the y coordinate from Java to User space
	 * within the page.
	 * 
	 * @param x
	 *            Coordinate in Java space
	 * @param y
	 *            Coordinate in Java space
	 * @return y Coordinate in User space
	 */
	public int cy(int x, int y) {
		return cxy(x, y)[1];
	}
	
	/**
	 * This utility method converts the y coordinate from Java to User space
	 * within the page.
	 * 
	 * @param x
	 *            Coordinate in Java space
	 * @param y
	 *            Coordinate in Java space
	 * @return x Coordinate in User space
	 */
	public int cx(int x, int y) {
		return cxy(x, y)[0];
	}
	
	/**
	 * This utility method converts the Java coordinates to User space within
	 * the page.
	 * 
	 * @param x
	 *            Coordinate in Java space
	 * @param y
	 *            Coordinate in Java space
	 * @return array containing the x & y Coordinate in User space
	 */
	public int[] cxy(int x, int y) {
		int r[] = new int[2];
		r[0] = x;
		r[1] = (int) pageFormat.getHeight() - y;
		return r;
	}
	
}
