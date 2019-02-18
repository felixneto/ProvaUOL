package br.com.uol.ProvaFelix.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.uol.ProvaFelix.models.Customer;
import br.com.uol.ProvaFelix.services.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customer")
@Api(value = "Customer API", description = "CRUD Customer")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	
	@Autowired
    ICustomerService customerService;
    
    @GetMapping("/list")
	@ApiOperation(value = "List all customers", response = Iterable.class)
	public List<Customer> findAll() {
		return (List<Customer>) customerService.findAll();
	}

    
    @GetMapping(value = "/{id}")
	@Cacheable(value = "cliente", key = "#id")
    @ApiOperation(value = "Find a customer by Id", response = Customer.class)
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public Customer findCostumerById(@RequestParam(value = "id", required = true) String id) {
        return customerService.getById(id);
    }
    
    @PostMapping
    @ApiOperation(value = "Create a customer", response = Customer.class)
	public Customer save(@RequestBody Customer customer, HttpServletRequest request) {
		return customerService.save(customer, request);
	}
    
    @PutMapping
	@CachePut(value = "customer", key = "#cliente.id")
	@ApiOperation(value = "Update a customer", response = Customer.class)
	public Customer update(@RequestBody Customer customer) {
		return customerService.update(customer);
	}
    
    @DeleteMapping("/{id}")
	@CacheEvict(value = "cliente", key = "#id")
    @ApiOperation(value = "Delete a customer", response = String.class)
    public String deleteCustomerById(@PathVariable("id") String id) {
    	customerService.removeById(id);
		return "Customer removed";
    }
}
