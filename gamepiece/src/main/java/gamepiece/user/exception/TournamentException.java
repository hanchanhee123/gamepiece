package gamepiece.user.exception;


public class TournamentException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public TournamentException(String message) {
		super(message);
	}
}
