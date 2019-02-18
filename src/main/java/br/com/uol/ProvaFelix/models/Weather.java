package br.com.uol.ProvaFelix.models;


import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.uol.ProvaFelix.dtos.WeatherDTO;
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
@Document
public class Weather {
		
	@Id
	public String id;

	private String customerId;

	private String dataInclusao;

	private BigDecimal temperaturaMinima;

	private BigDecimal temperaturaMaxima;

	private String ipRequest;
	
	public Weather(WeatherDTO weatherDTO) {
		this.customerId = weatherDTO.getCustomerId();
		this.ipRequest = weatherDTO.getIpRequest();
	}

}
