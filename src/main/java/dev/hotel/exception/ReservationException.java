/**
 * 
 */
package dev.hotel.exception;

import dev.hotel.dto.MessageErreurDto;

/**
 * @author robin
 *
 */
public class ReservationException extends RuntimeException {

	private MessageErreurDto messageErreur;

	public ReservationException(MessageErreurDto messageErreur) {
		super(messageErreur.getMessage());
		this.messageErreur = messageErreur;
	}

	/**
	 * Getter
	 * @return the messageErreur
	 */
	public MessageErreurDto getMessageErreur() {
		return messageErreur;
	}
	
	
}
