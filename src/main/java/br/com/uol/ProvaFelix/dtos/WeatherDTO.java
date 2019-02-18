package br.com.uol.ProvaFelix.dtos;

import java.io.Serializable;

import br.com.uol.ProvaFelix.models.Weather;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerId;

	private String ipRequest;

	public WeatherDTO(Weather weather, String ipRequest) {
		this.customerId = weather.getCustomerId();
		this.ipRequest = ipRequest;
	}

	@Override
	public String toString() {
		return "ClimaDTO [clienteId=" + customerId + ", ipRequest=" + ipRequest + "]";
	}

}




