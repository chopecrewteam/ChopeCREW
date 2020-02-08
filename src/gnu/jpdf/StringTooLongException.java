package gnu.jpdf;

/**
 * <p>
 * This exception is thrown from {@link gnu.jpdf.BoundingBox} if the string
 * won't fit into the box
 * </p>
 */
public class StringTooLongException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String msg;
	
	/**
	 * <p>
	 * Normally this exception is constructed with a message containing
	 * information about the sizes of the parent and child box, and maybe the
	 * string that caused the overflow.
	 * </p>
	 * 
	 * @param msg
	 *            a <code>String</code>, some informative message for the logs
	 */
	public StringTooLongException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return msg;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
}
