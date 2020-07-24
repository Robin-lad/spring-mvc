/**
 * 
 */
package dev.hotel.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;

/**
 * @author robin
 *
 */
@Service
@Transactional
public class ClientService {
	
	private ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}
	
	public List<Client> getListClient(){
		return clientRepository.findAll();
	}
	
	public List<Client> getListClientPage(PageRequest pageRequest){
		return clientRepository.findAll(pageRequest).toList();
	}
	
	public Optional<Client> getClientByUuid(UUID uuid) {
		Optional<Client> client = this.clientRepository.findById(uuid);
		return client;
	}
	
	public Client creer(String nom, String prenom) {
		Client client = new Client(nom, prenom);
		
		Client clientSave = this.clientRepository.save(client);
		
		return clientSave;
	}
}
