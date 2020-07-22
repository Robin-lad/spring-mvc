/**
 * 
 */
package dev.hotel.dto;

import java.util.UUID;

/**
 * @author robin
 *
 */
public class ClientDto extends CreerClientDto{

	private UUID uuid;

	/**
	 * Getter
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * Setter
	 * @param uuid the uuid to set
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	
}
