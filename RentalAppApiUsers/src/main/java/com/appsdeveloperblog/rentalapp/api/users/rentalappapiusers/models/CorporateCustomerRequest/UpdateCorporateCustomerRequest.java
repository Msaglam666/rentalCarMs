package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.CorporateCustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
    @NotNull
    private int userId;
    @NotNull
    private String companyName;
    @NotNull
    private String taxNumber;
    @Email(message = "wrong e-mail type")
    private String email;
    @NotNull
    private String password;



}
