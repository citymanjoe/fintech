package com.zenpay.fintech.model.dto.request;

import com.zenpay.fintech.model.dto.enumv.Gender;
import com.zenpay.fintech.model.dto.enumv.Type;
import com.zenpay.fintech.util.CustomValidator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class AccountCreationRequest {

    @NotEmpty(message = "Enter your full name.")
    @CustomValidator(message = "FullName cannot contain NonAlphabetic",type = Type.TEXT)
    private String fullName;

    @Email(message = "Enter a valid email address") //Either can serve. Just show both custom and Jakarta validation
    @CustomValidator(message = "Enter a valid email address", type = Type.EMAIL)
    private String email;

    @Digits(message="Phone Number should contain 13 digits.", fraction = 0, integer = 13)
    @CustomValidator(message = "Mobile Number must be {max} characters", type = Type.SIZE, max = 13)
    @CustomValidator(message = "Mobile Number must be Nigeria Line", type = Type.PHONE)
    private String mobileNo;

    private Gender gender;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Enter valid date.")
    private Date dateOfBirth;

    @NotEmpty(message = "Enter your resident address.")
    private String address;

    @Digits(message="Bank Verification Number should contain 11 digits.", fraction = 0, integer = 11)
    private String bankVerificationNo;

    @Digits(message="National Identification Number should contain 11 digits.", fraction = 0, integer = 11)
    private String nationalIdentificationNo;

}
