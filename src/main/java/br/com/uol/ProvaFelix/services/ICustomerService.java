package br.com.uol.ProvaFelix.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import br.com.uol.ProvaFelix.models.Customer;

@Service
public interface ICustomerService {

	Customer save(Customer customer);
	Customer save(Customer customer, HttpServletRequest request);
	List<Customer> findAll();
	Customer update(Customer customer);
	void removeById(String id);
	Customer getById(String id);
	
}
