package br.com.uol.ProvaFelix.servicesimpl;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.uol.ProvaFelix.dtos.CityDTO;
import br.com.uol.ProvaFelix.dtos.IPVigilanteDTO;
import br.com.uol.ProvaFelix.dtos.TemperaturaDTO;
import br.com.uol.ProvaFelix.models.Weather;
import br.com.uol.ProvaFelix.repositories.WeatherRepository;
import br.com.uol.ProvaFelix.services.IWeatherService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class WeatherServiceImpl implements IWeatherService{

	@Value("${url.ipvigilante}")
	private String urlIPVigilante;
	
	@Value("${cidades.disponiveis}")
	private String urlCidadesDisponiveis;

	@Value("${temperatura.por.cidadeid}")
	private String urlTemperaturaPorCidadeId;
	
	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Weather save(Weather weather) {

		IPVigilanteDTO ipvigilante = localizaLatitudeLongitude(weather.getIpRequest());

		Calendar dataHoje = Calendar.getInstance();
		localizaTemperatura(weather, ipvigilante.getData().getLatitude(), ipvigilante.getData().getLongitude(), dataHoje);

		return weatherRepository.save(weather);
	}

	private void localizaTemperatura(Weather weather, String latitude, String longitude, Calendar data) {
		CityDTO[] listaCidades = listaCidades(latitude, longitude);

		Integer idCidade = listaCidades[0].getWoeid();
		
		localizaTemperaturaMinimaMaxima(data, idCidade, weather);
	}

	private void localizaTemperaturaMinimaMaxima(Calendar data, Integer idCidade, Weather weather) {
		int ano = data.get(Calendar.YEAR);
		int mes = data.get(Calendar.MONTH) + 1;
		int dia = data.get(Calendar.DAY_OF_MONTH);

		ResponseEntity<TemperaturaDTO[]> responseEntityTemperaturaDTO = restTemplate.getForEntity(urlTemperaturaPorCidadeId, TemperaturaDTO[].class, idCidade, ano, mes, dia);

		BigDecimal temperaturaMinima = new BigDecimal(Float.MAX_VALUE);
		BigDecimal temperaturaMaxima = new BigDecimal(Float.MIN_VALUE);

		for (TemperaturaDTO temperaturaDTO : responseEntityTemperaturaDTO.getBody()) {
			if (temperaturaDTO.getApplicableDate() == null) {
				continue;
			}
			
			if(temperaturaDTO.getMinTemp().compareTo(temperaturaMinima) < 0) {
				temperaturaMinima = temperaturaDTO.getMinTemp();
			}
			
			if(temperaturaDTO.getMaxTemp().compareTo(temperaturaMaxima) > 0) {
				temperaturaMaxima = temperaturaDTO.getMaxTemp();
			}
			
			if(weather.getDataInclusao() == null) {
				weather.setDataInclusao(temperaturaDTO.getApplicableDate());
			}
		}
		
		weather.setTemperaturaMinima(temperaturaMinima);
		weather.setTemperaturaMaxima(temperaturaMaxima);
	}

	private CityDTO[] listaCidades(String latitude, String longitude) {
		ResponseEntity<CityDTO[]> responseEntityCidadesDTO = restTemplate.getForEntity(urlCidadesDisponiveis, CityDTO[].class, latitude, longitude);
		CityDTO[] listaCidades = responseEntityCidadesDTO.getBody();
		return listaCidades;
	}

	private IPVigilanteDTO localizaLatitudeLongitude(String ipOrigem) {

		IPVigilanteDTO ipvigilantedto = restTemplate.getForObject(urlIPVigilante, IPVigilanteDTO.class, ipOrigem);

		return ipvigilantedto;
	}
	
}
