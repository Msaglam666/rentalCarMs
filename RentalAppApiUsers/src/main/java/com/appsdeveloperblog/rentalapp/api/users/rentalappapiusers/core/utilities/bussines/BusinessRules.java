package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.bussines;

import com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.core.utilities.results.Result;


public class BusinessRules {
	public static Result run(Result...logics) {
		for (Result logic : logics) {
			if (!logic.isSuccess()) {
				return logic;
			}
		}
		return null;
	}
}
