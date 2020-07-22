/**
 * 
 */
package dev.hotel.exception;

import dev.hotel.dto.MessageErreurDto;

/**
 * @author robin
 *
 */
public class ClientException extends RuntimeException {

	private MessageErreurDto messageErreur;

	public ClientException(MessageErreurDto messageErreur) {
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
