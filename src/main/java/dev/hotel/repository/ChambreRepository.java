/**
 * 
 */
package dev.hotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.hotel.entite.Chambre;

/**
 * @author robin
 *
 */
public interface ChambreRepository extends JpaRepository<Chambre, UUID>{

}
