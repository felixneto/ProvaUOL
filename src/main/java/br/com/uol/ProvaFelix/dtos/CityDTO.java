package br.com.uol.ProvaFelix.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDTO {

	private Integer distance;
	private String title;
	private String locationType;
	private Integer woeid;
	private String lattLong;


}
