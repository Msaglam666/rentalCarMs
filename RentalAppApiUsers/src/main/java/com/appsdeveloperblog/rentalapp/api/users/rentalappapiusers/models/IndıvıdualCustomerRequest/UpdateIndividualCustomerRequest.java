package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.models.IndıvıdualCustomerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
    private int userId;
    private String firstName;
    private String lastName;

    @Email(message = "wrong e_mail")
    private String email;
    private String password;
    private LocalDate birthday;
}
