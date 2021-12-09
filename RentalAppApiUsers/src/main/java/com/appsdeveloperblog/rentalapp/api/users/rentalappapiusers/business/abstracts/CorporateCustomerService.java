package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.abstracts;

import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.dtos.CorporateCustomerSearchListDto;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.results.DataResult;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.results.Result;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.CorporateCustomerRequest.CreateCorporateCustomerRequest;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.CorporateCustomerRequest.DeleteCorporateCustomerRequest;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.CorporateCustomerRequest.UpdateCorporateCustomerRequest;

import java.util.List;

public interface CorporateCustomerService {

    DataResult<List<CorporateCustomerSearchListDto>> getAll();
    Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);
    Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);
    Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);
}
