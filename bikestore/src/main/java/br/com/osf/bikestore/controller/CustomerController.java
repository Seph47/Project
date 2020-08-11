package br.com.osf.bikestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.osf.bikestore.entity.Customer;
import br.com.osf.bikestore.repository.CustomerRepository;
import br.com.osf.bikestore.exception.ResourceNotFound;

@RestController
@RequestMapping("/bikestore/customers")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;
	
	//get all customers
	@GetMapping
	public List<Customer> getAllCustomers(){
		return this.customerRepository.findAll();
	}

	//get customer by id
		@GetMapping("/{id}")
		public Customer getCustomerById(@PathVariable (value = "id") long customerId) {
			String message = "Sorry, no customer with id "+customerId+" was found.";
			return this.customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFound(message));
		}
		
		//create customer
		@PostMapping
		public Customer createCustomer(@RequestBody Customer customer) {
			return this.customerRepository.save(customer);
		}
		
		//update customer by id
		@PutMapping("/{id}")
		public Customer updateCustomer(@RequestBody Customer customer, @PathVariable ("id") long customerId) {
			String message = "Sorry, no customer with id "+customerId+" was found.";
			Customer existingCustomer = this.customerRepository.findById(customerId)
					.orElseThrow(()-> new ResourceNotFound(message));
			
			existingCustomer.setFirstName(customer.getFirstName());
			existingCustomer.setLastName(customer.getLastName());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setPassword(customer.getPassword());
			
			return this.customerRepository.save(existingCustomer);
		}
		
		//delete customer by id
		@DeleteMapping("/{id}")
		public ResponseEntity<Customer> deleteCustomer(@PathVariable ("id") long customerId) {
			String message = "Sorry, no customer with id "+customerId+" was found.";
			Customer existingCustomer = this.customerRepository.findById(customerId)
					.orElseThrow(()-> new ResourceNotFound(message));
			this.customerRepository.delete(existingCustomer);
			return ResponseEntity.ok().build();
		}
	
}
