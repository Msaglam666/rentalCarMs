package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.dataAccess;

import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.entities.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer> {
}
