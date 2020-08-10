package br.com.osf.bikestoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.osf.bikestoreproject.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
