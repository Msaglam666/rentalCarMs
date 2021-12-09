package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.dataAccess;

import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.entities.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer,Integer> {
    boolean existsCorporateCustomerByCompanyName(String companyName);

}
