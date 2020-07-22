/**
 * 
 */
package dev.hotel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Reservation;
import dev.hotel.repository.ReservationRepository;

/**
 * @author robin
 *
 */
@RestController
public class ReservationController {

	private ReservationRepository reservationRepository;

	public ReservationController(ReservationRepository reservationRepository) {
		super();
		this.reservationRepository = reservationRepository;
	}
	
	@PostMapping("reservations")
	public ResponseEntity<?> ajoutReservation(@RequestBody Reservation r) {
		return null;
	}
	
}
