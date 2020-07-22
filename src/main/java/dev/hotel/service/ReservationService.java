/**
 * 
 */
package dev.hotel.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.repository.ChambreRepository;
import dev.hotel.repository.ClientRepository;
import dev.hotel.repository.ReservationRepository;

/**
 * @author robin
 *
 */
@Service
@Transactional
public class ReservationService {

	private ReservationRepository reservationRepository;
	private ClientRepository clientRepository;
	private ChambreRepository chambreRepository;

	public ReservationService(ReservationRepository reservationRepository, ClientRepository clientRepository, ChambreRepository chambreRepository) {
		super();
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.chambreRepository = chambreRepository;
	}
	
	public List<Reservation> getListReservation(){
		return reservationRepository.findAll();
	}
	
	public boolean clientExiste(UUID uuid) {
		Optional<Client> client = clientRepository.findById(uuid);
		if(client.isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean chambresExistent(List<UUID> chambres) {
		for(UUID u : chambres) {
			Optional<Chambre> chambre = chambreRepository.findById(u);
			if(chambre.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public Reservation creer(LocalDate dateDebut, LocalDate dateFin, UUID uuidClient, Iterable<UUID> uuidChambres) {
		
		Reservation reservation = new Reservation(dateDebut, dateFin, clientRepository.findById(uuidClient).get(), chambreRepository.findAllById(uuidChambres));
		
		Reservation reservationSave = this.reservationRepository.save(reservation);
		return reservationSave;
	}
}
