package br.com.uol.ProvaFelix.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Data {

	private String ipv4;
	private String hostname;
	private String continentCode;
	private String continentName;
	private String countryIsoCode;
	private String countryName;
	private String subdivision1IsoCode;
	private String subdivision1Name;
	private Object subdivision2IsoCode;
	private Object subdivision2Name;
	private String cityName;
	private Object metroCode;
	private String timeZone;
	private Object postalCode;
	private String latitude;
	private String longitude;
	private Integer accuracyRadius;

}
