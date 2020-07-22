/**
 * 
 */
package dev.hotel.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.hotel.entite.Chambre;
import dev.hotel.entite.Client;
import dev.hotel.entite.Hotel;
import dev.hotel.entite.Reservation;
import dev.hotel.service.ReservationService;

/**
 * @author robin
 *
 */
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ReservationService reservationService;
	
	@BeforeEach
	void setUp() {
		
	}
	
	@Test
	void createClientTest() throws Exception {
		List<UUID> uuidChambre = new ArrayList<>();
		uuidChambre.add(UUID.fromString("43793061-f70b-44b9-a855-adc66a2efb9f"));
		uuidChambre.add(UUID.fromString("0a0d4672-a273-4e1f-b399-8272ae81296f"));
		
		
		Hotel h1 = new Hotel("Ibis Budget", 2);
		h1.setUuid(UUID.fromString("dcff0dbd-0413-46b6-b37a-cff5b92dc20c"));
		Chambre c1 = new Chambre("I2", 20f, h1);
		c1.setUuid(UUID.fromString("0a0d4672-a273-4e1f-b399-8272ae81296f"));
		
		Hotel h2 = new Hotel("Première Classe", 0);
		h2.setUuid(UUID.fromString("8539d931-fe53-4b57-a128-1afc4ea9b21f"));
		Chambre c2 = new Chambre("P2", 20f, h2);
		c2.setUuid(UUID.fromString("43793061-f70b-44b9-a855-adc66a2efb9f"));
		List<Chambre> chambres = new ArrayList<>();
		chambres.add(c1);
		chambres.add(c2);
		Client cl1 = new Client("Etienne", "Joly");
		cl1.setUuid(UUID.fromString("91defde0-9ad3-4e4f-886b-f5f06f601a0d"));
		
		Reservation r1 = new Reservation(LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 5), cl1, chambres); 
		
		Mockito.when(reservationService.creer(LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 5), UUID.fromString("91defde0-9ad3-4e4f-886b-f5f06f601a0d"), uuidChambre)).thenReturn(r1);
		
		String json = "{ \"dateDebut\": \"2020-08-20\", \"dateFin\":\"2020-08-22\", \"clientId\": \"91defde0-9ad3-4e4f-886b-f5f06f601a0d\", \"chambres\": [ \"43793061-f70b-44b9-a855-adc66a2efb9f\",\"0a0d4672-a273-4e1f-b399-8272ae81296f\"] }";
		mockMvc.perform(MockMvcRequestBuilders.post("/reservations").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
