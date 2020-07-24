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

import dev.hotel.dto.CodeErreur;
import dev.hotel.dto.MessageErreurDto;
import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Reservation;
import dev.hotel.exception.ReservationException;
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
	

	public String getChambreDispo(String num) {
		Optional<Reservation> reserve = reservationRepository.chambreDispo(num);
		if(reserve.isEmpty()) {
			return "La chambre " + num + " est disponible.";
		}else {
			return "La chambre " + num + " est indisponible.";
		}
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
		
		if(!clientExiste(uuidClient)) {
			throw new ReservationException(new MessageErreurDto(CodeErreur.VALIDATION, "uuid client non trouvé"));
		}
		
		if(!chambresExistent((List<UUID>) uuidChambres)) {
			throw new ReservationException(new MessageErreurDto(CodeErreur.VALIDATION, "L'iuud de la ou les chambre(s) n'existe(nt) pas."));
		}
		
		Reservation reservation = new Reservation(dateDebut, dateFin, clientRepository.findById(uuidClient).get(), chambreRepository.findAllById(uuidChambres));
		
		Reservation reservationSave = this.reservationRepository.save(reservation);
		return reservationSave;
	}
}
