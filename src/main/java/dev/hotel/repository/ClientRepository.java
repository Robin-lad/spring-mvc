/**
 * 
 */
package dev.hotel.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.hotel.entite.Client;


/**
 * @author robin
 *
 */
public interface ClientRepository extends JpaRepository<Client, UUID> {

}
