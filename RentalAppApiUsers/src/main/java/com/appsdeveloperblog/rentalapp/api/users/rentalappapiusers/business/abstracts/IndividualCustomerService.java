package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.abstracts;

import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.dtos.IndividualCustomerSearchListDto;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.results.DataResult;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.results.Result;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.IndıvıdualCustomerRequest.CreateIndividualCustomerRequest;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.IndıvıdualCustomerRequest.DeleteIndividualCustomerRequest;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.IndıvıdualCustomerRequest.UpdateIndividualCustomerRequest;

import java.util.List;

public interface IndividualCustomerService {

    DataResult<List<IndividualCustomerSearchListDto>> getAll();
    Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);
    Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
    Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);

}
