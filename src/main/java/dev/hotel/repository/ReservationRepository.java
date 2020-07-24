/**
 * 
 */
package dev.hotel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.hotel.entite.Reservation;

/**
 * @author robin
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, UUID>{

	/**
	 * 
	 */
	@Query("select r from Reservation r join r.chambres c where c.numero = ?1")
	Optional<Reservation> chambreDispo(String numero);
	
}
