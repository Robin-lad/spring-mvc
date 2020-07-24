/**
 * 
 */
package dev.hotel.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.hotel.entite.Client;


/**
 * @author robin
 *
 */
public interface ClientRepository extends JpaRepository<Client, UUID> {

	/**
	 * 
	 * @param nom
	 * @return
	 */
	@Query("select c from Client c where c.nom = ?1")
	Optional<Client> findByName(String nom);

}
