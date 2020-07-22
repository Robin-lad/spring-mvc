/**
 * 
 */
package dev.hotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Reservation;

/**
 * @author robin
 *
 */
public interface ReservationRepository extends JpaRepository<Reservation, UUID>{
	
}
