package br.com.uol.ProvaFelix.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.uol.ProvaFelix.models.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
	public Customer findCustomerById(String id);

}
