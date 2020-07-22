/**
 * 
 */
package dev.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.entite.Client;
import dev.hotel.repository.ClientRepository;
import dev.hotel.service.ClientService;

/**
 * @author robin
 *
 */
@WebMvcTest(ClientsController.class)
public class ClientsControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ClientService clientService;
	
	List<Client> clients = new ArrayList<>();
	
	@BeforeEach
	void setUp() {
		Client c1 = new Client("Odd", "Ross");
		c1.setUuid(UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844244b192"));
		clients.add(c1);
	}
	
	@Test
	void list() throws Exception{		
		
		Mockito.when(clientService.getListClientPage(PageRequest.of(0, 1))).thenReturn(clients);

		mockMvc.perform(MockMvcRequestBuilders.get("/clients?start=0&size=1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].uuid").value("dcf129f1-a2f9-47dc-8265-1d844244b192"))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Odd"))
		 	.andExpect(MockMvcResultMatchers.jsonPath("$[0].prenoms").value("Ross"));
	}
	
	@Test
	void findbyUUID() throws Exception{
		
		Mockito.when(clientService.getClientByUuid(UUID.fromString("dcf129f1-a2f9-47dc-8265-1d844244b192"))).thenReturn(Optional.of(clients.get(0)));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/clients/dcf129f1-a2f9-47dc-8265-1d844244b192"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("nom").value("Odd"))
			.andExpect(MockMvcResultMatchers.jsonPath("prenoms").value("Ross"));
	}
	
	@Test
	void createClientTest() throws Exception {
		Mockito.when(clientService.creer("Ladenburger", "Robin")).thenReturn(new Client("Ladenburger", "Robin"));
		
		String json = "{ \"nom\": \"Ladenburger\", \"prenoms\":\"Robin\" }";
		
		mockMvc.perform(MockMvcRequestBuilders.post("/clients").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("nom").value("Ladenburger"))
			.andExpect(MockMvcResultMatchers.jsonPath("prenoms").value("Robin"));
	}
}
