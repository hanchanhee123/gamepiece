package gamepiece.user.tournament.domain;

import lombok.Data;

@Data
public class ResponseTemplate<T> {
	
	private String status;
	private String message;
	private T data;
	
	public ResponseTemplate(String status, String message, T data){
		this.status = status;
		this.message = message;
		this.data = data;
	}
}
