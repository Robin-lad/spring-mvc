/**
 * 
 */
package dev.hotel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@PostMapping
	public String ajoutReservation(@RequestBody @Valid CreerReservationDto reservation, BindingResult result) {

		if(result.hasErrors()) {
			throw new ReservationException(new MessageErreurDto(CodeErreur.VALIDATION, "Données incorrectes pour la création d'une réservation."));
		}
		
		if(!reservationService.clientExiste(reservation.getClientId())) {
			throw new ReservationException(new MessageErreurDto(CodeErreur.VALIDATION, "uuid client non trouvé"));
		}
		
		if(!reservationService.chambresExistent(reservation.getChambres())) {
			throw new ReservationException(new MessageErreurDto(CodeErreur.VALIDATION, "L'iuud de la ou les chambre(s) n'existe(nt) pas."));
		}
		
		Reservation reservationCreer = reservationService.creer(reservation.getDateDebut(), reservation.getDateFin(), reservation.getClientId(), reservation.getChambres());
		
//		ReservationDto reservationDto = new ReservationDto();
//		reservationDto.setUuid(reservationCreer.getUuid());
//		reservationDto.setDateDebut(reservationCreer.getDateDebut());
//		reservationDto.setDateFin(reservationCreer.getDateFin());
//		reservationDto.setClientId(reservationCreer.getClient().getUuid());
//		reservationDto.setUuidChambres(reservationCreer.);
		
		return "test";
	}
}
