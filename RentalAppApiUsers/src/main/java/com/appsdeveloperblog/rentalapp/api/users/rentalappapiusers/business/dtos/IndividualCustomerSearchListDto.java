package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerSearchListDto {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
}
