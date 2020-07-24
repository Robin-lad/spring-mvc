/**
 * 
 */
package dev.hotel.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.hotel.dto.ClientDto;
import dev.hotel.dto.CodeErreur;
import dev.hotel.dto.CreerClientDto;
import dev.hotel.dto.MessageErreurDto;
import dev.hotel.entite.Client;
import dev.hotel.exception.ClientException;
import dev.hotel.service.ClientService;
/**
 * @author robin
 *
 */
@RestController
@RequestMapping("clients")
public class ClientsController {
	
	private ClientService clientService;

	public ClientsController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}
	
	@GetMapping
	public List<Client> getClients() {
		List<Client> clients = clientService.getListClient();
		return clients;
		
	}
	
	@GetMapping("page")
	public List<Client> getClientsPage(@RequestParam("start") Integer start, @RequestParam("size") Integer size) {
		List<Client> clients = clientService.getListClientPage(PageRequest.of(start, size));
		return clients;
		
	}
	
	@GetMapping("search/{nom}")
	public Optional<Client> getByName(@PathVariable String nom) {
		Optional<Client> client = clientService.getClientByName(nom);
		
		if(client.isEmpty()) {
			throw new ClientException(new MessageErreurDto(CodeErreur.VALIDATION, "Nom incorrect."));
		}
		
		return client;
	}
	
	@GetMapping("{uuid}")
	public Optional<Client> getUUID(@PathVariable UUID uuid) {
		Optional<Client> client = clientService.getClientByUuid(uuid);
		
		if(client.isEmpty()) {
			throw new ClientException(new MessageErreurDto(CodeErreur.VALIDATION, "UUID incorrect."));
		}
		
		return client;
	}
	
	@PostMapping
	public ClientDto creerClients(@RequestBody @Valid CreerClientDto client, BindingResult result){
		
		if(result.hasErrors()) {
			throw new ClientException(new MessageErreurDto(CodeErreur.VALIDATION, "Données incorrectes pour la création d'un client."));
		}
		
		Client clientCreer = clientService.creer(client.getNom(), client.getPrenom());
		
		ClientDto clientDto = new ClientDto();
		clientDto.setUuid(clientCreer.getUuid());
		clientDto.setNom(clientCreer.getNom());
		clientDto.setPrenom(clientCreer.getPrenoms());
		
		return clientDto;
	}
}
