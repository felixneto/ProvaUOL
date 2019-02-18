package br.com.uol.ProvaFelix.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.uol.ProvaFelix.models.Weather;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {
}
