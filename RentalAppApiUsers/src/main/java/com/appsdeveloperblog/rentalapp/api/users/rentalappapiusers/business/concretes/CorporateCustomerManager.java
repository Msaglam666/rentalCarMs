package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.concretes;

import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.abstracts.CorporateCustomerService;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.dtos.CorporateCustomerSearchListDto;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.bussines.BusinessRules;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.mapping.ModelMapperService;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.results.*;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.dataAccess.CorporateCustomerDao;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.entities.CorporateCustomer;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.CorporateCustomerRequest.CreateCorporateCustomerRequest;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.CorporateCustomerRequest.DeleteCorporateCustomerRequest;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.CorporateCustomerRequest.UpdateCorporateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

    private CorporateCustomerDao corporateCustomerDao;
    private ModelMapperService modelMapperService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, BCryptPasswordEncoder bCryptPasswordEncoder,
                                    ModelMapperService modelMapperService) {
        super();
        this.corporateCustomerDao = corporateCustomerDao;
        this.modelMapperService = modelMapperService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    public DataResult<List<CorporateCustomerSearchListDto>> getAll() {
        List<CorporateCustomer> result = this.corporateCustomerDao.findAll();
        List<CorporateCustomerSearchListDto> response = result.stream()
                .map(corporateCustomer -> modelMapperService.forDto()
                        .map(corporateCustomer, CorporateCustomerSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CorporateCustomerSearchListDto>>(response, "all corporate customer listed");
    }

    @Override
    public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
        Result resultCheck = BusinessRules.run(checkIfCompanyNameExists(createCorporateCustomerRequest.getCompanyName()));
        if (resultCheck != null) {
            return resultCheck;
        }
        CorporateCustomer result = modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
        result.setPassword(this.bCryptPasswordEncoder.encode(createCorporateCustomerRequest.getPassword()));
        this.corporateCustomerDao.save(result);
        return new SuccessResult("Corporate Customer Added");
    }

    @Override
    public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
        Result result = BusinessRules.run(checkIfCompanyNameExists(updateCorporateCustomerRequest.getCompanyName()),
                checkIfUserExists(updateCorporateCustomerRequest.getUserId()));
        if (result != null) {
            return result;
        }

        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
        corporateCustomer.setPassword(this.bCryptPasswordEncoder.encode(updateCorporateCustomerRequest.getPassword()));
        this.corporateCustomerDao.save(corporateCustomer);
        return new SuccessResult("Corporate Customer Updated");
    }

    @Override
    public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
        Result result = BusinessRules.run(checkIfUserExists(deleteCorporateCustomerRequest.getUserId()));
        if (result != null) {
            return result;
        }
        CorporateCustomer corporateCustomer = modelMapperService.forRequest().map(deleteCorporateCustomerRequest, CorporateCustomer.class);
        this.corporateCustomerDao.delete(corporateCustomer);
        return new SuccessResult("Corporate Customer Deleted");
    }
    private Result checkIfUserExists(int id) {
        var result = this.corporateCustomerDao.existsById(id);
        if (!result) {
            return new ErrorResult("CUSTOMER NOT FOUND");
        }
        return new SuccessResult();
    }

    private Result checkIfCompanyNameExists(String companyName) {
        if (!this.corporateCustomerDao.existsCorporateCustomerByCompanyName(companyName)) {
            return new ErrorResult("CUSTOMER IS ALREADY EXISTS");
        }
        return new SuccessResult();
    }
}
