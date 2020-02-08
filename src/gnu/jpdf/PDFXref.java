package gnu.jpdf;

/**
 * <p>
 * This class is used to hold the xref information in the PDF Trailer block.
 * </p>
 * 
 * <p>
 * Basically, each object has an id, and an offset in the end file.
 * </p>
 * 
 * <p>
 * See the Adobe PDF Manual for more information. This class will normally not
 * be used directly by a developer
 * </p>
 */
public class PDFXref {
	
	/**
	 * The id of a PDF Object
	 */
	public int id;
	
	/**
	 * The offset within the PDF file
	 */
	public int offset;
	
	/**
	 * The generation of the object, usually 0
	 */
	public int generation;
	
	/**
	 * Creates a crossreference for a PDF Object
	 * 
	 * @param id
	 *            The object's ID
	 * @param offset
	 *            The object's position in the file
	 */
	public PDFXref(int id, int offset) {
		this(id, offset, 0);
	}
	
	/**
	 * Creates a crossreference for a PDF Object
	 * 
	 * @param id
	 *            The object's ID
	 * @param offset
	 *            The object's position in the file
	 * @param generation
	 *            The object's generation, usually 0
	 */
	public PDFXref(int id, int offset, int generation) {
		this.id = id;
		this.offset = offset;
		this.generation = generation;
	}
	
	/**
	 * @return The xref in the format of the xref section in the PDF file
	 */
	@Override
	public String toString() {
		String of = Integer.toString(offset);
		String ge = Integer.toString(generation);
		String rs = "0000000000".substring(0, 10 - of.length()) + of + " " + "00000".substring(0, 5 - ge.length()) + ge;
		if (generation == 65535) {
			return rs + " f ";
		}
		return rs + " n ";
	}
	
}
