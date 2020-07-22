/**
 * 
 */
package dev.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.hotel.dto.MessageErreurDto;
import dev.hotel.exception.ClientException;

/**
 * @author robin
 *
 */
@ControllerAdvice
public class GestionErreurCtrlAdvice {
	
	@ExceptionHandler(ClientException.class)
	public ResponseEntity<MessageErreurDto> quandHotelException(ClientException ex) {
		return ResponseEntity.badRequest().body(ex.getMessageErreur());
	}
}
