package com.bah.msd.mcc.repository;
import org.springframework.data.repository.CrudRepository;
import com.bah.msd.mcc.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
