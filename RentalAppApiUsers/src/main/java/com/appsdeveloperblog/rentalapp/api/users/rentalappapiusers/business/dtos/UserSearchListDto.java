package com.appsdeveloperblog.rentalapp.api.users.rentalappapiusers.business.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchListDto {
    private String email;
    private String password;
}
