/**
 * 
 */
package dev.hotel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.CodeErreur;
import dev.hotel.dto.CreerReservationDto;
import dev.hotel.dto.MessageErreurDto;
import dev.hotel.entite.Reservation;
import dev.hotel.exception.ReservationException;
import dev.hotel.service.ReservationService;

/**
 * @author robin
 *
 */
@RestController
@RequestMapping("reservations")
public class ReservationController {

	private ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		super();
		this.reservationService = reservationService;
	}
	
	@GetMapping
	public List<Reservation> getList() {
		return reservationService.getListReservation();
	}
	
	@GetMapping("chambre/{num}")
	public String chambreDisponible(@PathVariable String num) {
		return reservationService.getChambreDispo(num);
	}
	
	@PostMapping
	public Reservation ajoutReservation(@RequestBody @Valid CreerReservationDto reservation, BindingResult result) {

		if(result.hasErrors()) {
			throw new ReservationException(new MessageErreurDto(CodeErreur.VALIDATION, "Données incorrectes pour la création d'une réservation."));
		}
		
		Reservation reservationCreer = reservationService.creer(reservation.getDateDebut(), reservation.getDateFin(), reservation.getClientId(), reservation.getChambres());
		
//		ReservationDto reservationDto = new ReservationDto();
//		reservationDto.setUuid(reservationCreer.getUuid());
//		reservationDto.setDateDebut(reservationCreer.getDateDebut());
//		reservationDto.setDateFin(reservationCreer.getDateFin());
//		reservationDto.setClientId(reservationCreer.getClient().getUuid());
//		reservationDto.setUuidChambres(reservationCreer.);
		
		return reservationCreer;
	}
}
