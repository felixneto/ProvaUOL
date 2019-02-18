package br.com.uol.ProvaFelix.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemperaturaDTO {

	private String id;

	@JsonProperty("weather_state_name")
	private String weatherStateName;

	@JsonProperty("weather_state_abbr")
	private String weatherStateAbbr;

	@JsonProperty("wind_direction_compass")
	private String windDirectionCompass;

	private String created;

	@JsonProperty("applicable_date")
	private String applicableDate;

	@JsonProperty("min_temp")
	private BigDecimal minTemp;

	@JsonProperty("max_temp")
	private BigDecimal maxTemp;

	@JsonProperty("the_temp")
	private Float theTemp;

	@JsonProperty("wind_speed")
	private Float windSpeed;

	@JsonProperty("wind_direction")
	private Float windDirection;
	
	@JsonProperty("air_pressure")
	private Float airPressure;
	
	private Integer humidity;

	private Object visibility;

	private Integer predictability;
}
