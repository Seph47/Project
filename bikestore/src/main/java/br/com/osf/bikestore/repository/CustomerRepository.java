package br.com.osf.bikestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.osf.bikestore.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
