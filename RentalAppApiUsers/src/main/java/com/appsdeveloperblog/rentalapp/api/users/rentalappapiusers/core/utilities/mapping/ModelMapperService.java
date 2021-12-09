package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	ModelMapper forDto();
	ModelMapper forRequest();
}
