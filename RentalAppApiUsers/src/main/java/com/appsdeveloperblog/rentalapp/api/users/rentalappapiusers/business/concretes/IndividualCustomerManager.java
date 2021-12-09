package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.concretes;

import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.abstracts.IndividualCustomerService;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.dtos.IndividualCustomerSearchListDto;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.bussines.BusinessRules;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.mapping.ModelMapperService;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.results.*;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.dataAccess.IndividualCustomerDao;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.IndıvıdualCustomerRequest.CreateIndividualCustomerRequest;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.IndıvıdualCustomerRequest.DeleteIndividualCustomerRequest;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.IndıvıdualCustomerRequest.UpdateIndividualCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.entities.IndividualCustomer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
    private IndividualCustomerDao individualCustomerDao;
    private ModelMapperService modelMapperService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, BCryptPasswordEncoder bCryptPasswordEncoder,
                                      ModelMapperService modelMapperService) {
        super();
        this.individualCustomerDao = individualCustomerDao;
        this.modelMapperService = modelMapperService;

        this.bCryptPasswordEncoder= bCryptPasswordEncoder;
    }

    @Override
    public DataResult<List<IndividualCustomerSearchListDto>> getAll() {
        List<IndividualCustomer> result = this.individualCustomerDao.findAll();
        List<IndividualCustomerSearchListDto> response = result.stream()
                .map(customerIndividual -> modelMapperService.forDto()
                        .map(customerIndividual, IndividualCustomerSearchListDto.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<IndividualCustomerSearchListDto>>(response, "All Individual Customer Listed");
    }

    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
        IndividualCustomer result = modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
        result.setPassword(this.bCryptPasswordEncoder.encode(createIndividualCustomerRequest.getPassword()));
        this.individualCustomerDao.save(result);
        return new SuccessResult("Individual Customer Added");
    }

    @Override
    public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {

        Result result = BusinessRules.run(checkIfUserExists(updateIndividualCustomerRequest.getUserId()));
        if (result != null) {
            return result;
        }

        IndividualCustomer individualCustomerResult = modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);
        individualCustomerResult.setPassword(this.bCryptPasswordEncoder.encode(updateIndividualCustomerRequest.getPassword()));
        this.individualCustomerDao.save(individualCustomerResult);
        return new SuccessResult("Individual Customer Updated");
    }

    @Override
    public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
        Result result = BusinessRules.run(checkIfUserExists(deleteIndividualCustomerRequest.getUserId()));
        if (result != null) {
            return result;
        }

        IndividualCustomer individualCustomerResult = modelMapperService.forRequest().map(deleteIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerDao.delete(individualCustomerResult);
        return new SuccessResult("CUSTOMER DELETE");
    }

    private Result checkIfUserExists(int id) {
        var result = this.individualCustomerDao.existsById(id);
        if (!result) {
            return new ErrorResult("User Not Found");
        }
        return new SuccessResult("USER FOUND");
    }
}
