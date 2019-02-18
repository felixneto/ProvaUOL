package br.com.uol.ProvaFelix.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uol.ProvaFelix.dtos.WeatherDTO;
import br.com.uol.ProvaFelix.models.Customer;
import br.com.uol.ProvaFelix.models.Weather;
import br.com.uol.ProvaFelix.repositories.CustomerRepository;
import br.com.uol.ProvaFelix.services.ICustomerService;
import lombok.AllArgsConstructor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private WeatherServiceImpl weatherService;

	public Customer getById(String id) {
	Customer customer = customerRepository.findCustomerById(id);
	if(customer == null) {
		throw new RuntimeException("Customer not found!");
	}
	
	return customer;
	}
	 
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	public void removeById(String id) {
		customerRepository.deleteById(id);
	}

	public Customer save(Customer customer, HttpServletRequest request) {
		Customer result = save(customer);

		WeatherDTO weatherDTO = new WeatherDTO(result.getId(), request.getRemoteAddr());
		Weather weather = new Weather(weatherDTO);

		weatherService.save(weather);
		return result;
	}

	public Customer update(Customer customer) {
		if(customer.getId() == null || customer.getId().isEmpty()) {
			throw new RuntimeException("Invalid Id");
		}
		
		return customerRepository.save(customer);
	}
}
