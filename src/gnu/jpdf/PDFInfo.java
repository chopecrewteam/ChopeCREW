package gnu.jpdf;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>
 * This class stores details of the author, the PDF generator etc. The values
 * are accessible via the PDFDocument class.
 * </p>
 */
public class PDFInfo extends PDFObject {
	
	private static final long serialVersionUID = 1L;
	
	private String author;
	private String creator;
	private String title;
	private String subject;
	private String keywords;
	
	/**
	 * This constructs a minimal info object
	 */
	public PDFInfo() {
		super(null);
	}
	
	/**
	 * @param title
	 *            Title of this document
	 */
	public PDFInfo(String title) {
		this();
		this.title = title;
	}
	
	/**
	 * Get the value of author.
	 * 
	 * @return value of author.
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Set the value of author.
	 * 
	 * @param v
	 *            Value to assign to author.
	 */
	public void setAuthor(String v) {
		this.author = v;
	}
	
	/**
	 * PDF has two values, a Creator and a Producer. The creator field is
	 * available for calling code. The producer is fixed by this library. Get
	 * the value of creator.
	 * 
	 * @return value of creator.
	 */
	public String getCreator() {
		return creator;
	}
	
	/**
	 * Set the value of creator.
	 * 
	 * @param v
	 *            Value to assign to creator.
	 */
	public void setCreator(String v) {
		this.creator = v;
	}
	
	/**
	 * Get the value of title.
	 * 
	 * @return value of title.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the value of title.
	 * 
	 * @param v
	 *            Value to assign to title.
	 */
	public void setTitle(String v) {
		this.title = v;
	}
	
	/**
	 * Get the value of subject.
	 * 
	 * @return value of subject.
	 */
	public String getSubject() {
		return subject;
	}
	
	/**
	 * Set the value of subject.
	 * 
	 * @param v
	 *            Value to assign to subject.
	 */
	public void setSubject(String v) {
		this.subject = v;
	}
	
	/**
	 * Get the value of keywords.
	 * 
	 * @return value of keywords.
	 */
	public String getKeywords() {
		return keywords;
	}
	
	/**
	 * Set the value of keywords.
	 * 
	 * @param v
	 *            Value to assign to keywords.
	 */
	public void setKeywords(String v) {
		this.keywords = v;
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
		
		if (author != null) {
			os.write("/Author (".getBytes());
			os.write(PDFStringHelper.makePDFString(author).getBytes());
			os.write(")\n".getBytes());
		}
		
		if (creator != null) {
			os.write("/Creator (".getBytes());
			os.write(PDFStringHelper.makePDFString(creator).getBytes());
			os.write(")\n".getBytes());
		}
		
		os.write("/Producer ".getBytes());
		os.write(PDFStringHelper.makePDFString("ChopeCREW").getBytes());
		os.write("\n".getBytes());
		
		if (title != null) {
			os.write("/Title ".getBytes());
			os.write(PDFStringHelper.makePDFString(title).getBytes());
			os.write("\n".getBytes());
		}
		
		if (subject != null) {
			os.write("/Subject (".getBytes());
			os.write(PDFStringHelper.makePDFString(subject).getBytes());
			os.write(")\n".getBytes());
		}
		
		if (keywords != null) {
			os.write("/Keywords (".getBytes());
			os.write(PDFStringHelper.makePDFString(keywords).getBytes());
			os.write(")\n".getBytes());
		}
		
		// finish off with its footer
		writeEnd(os);
	}
	
}
