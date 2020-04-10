package dweauthorshipattribution.lexicon;

public class DicionarioException extends RuntimeException {
	private static final long serialVersionUID = 371800501557223147L;

	public DicionarioException(String message, Exception e) {
		super(message,e.getCause());
	}
}
