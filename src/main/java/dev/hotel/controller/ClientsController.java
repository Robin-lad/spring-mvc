/**
 * 
 */
package dev.hotel.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;
/**
 * @author robin
 *
 */
@RestController
public class ClientsController {
	
	private ClientRepository clientRepository;

	public ClientsController(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}
	
	@GetMapping("clients")
	public ResponseEntity<List<Client>> getClients(@RequestParam("start") Integer start, @RequestParam("size") Integer size) {
		List<Client> clients = clientRepository.findAll(PageRequest.of(start, size)).toList();
		
		return ResponseEntity.status(200)
				.body(clients);
	}
	
	@GetMapping("client/{id}")
	public ResponseEntity<Client> getUUID(@PathVariable UUID id) {
		Client client = clientRepository.getByUUID(id);
		
		if(client == null) {
			return ResponseEntity.status(404)
					.body(null);
		}else {
			return ResponseEntity.status(200)
					.body(client);
		}
	}
}
