package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel(description = "Sign up requirements")
public class SignUpDto {
    @ApiModelProperty(value = "User's staff No", required = true)
    private String username;
    @ApiModelProperty(value = "User's first name", required = true)
    private String firstName;
    @ApiModelProperty(value = "Email")
    private String email;
    @ApiModelProperty(value = "Password")
    private String password;
    @ApiModelProperty(value = "User's last name", required = true)
    private String lastName;
    @ApiModelProperty(value = "Middle name", required = false)
    private String middleName;
    @ApiModelProperty(value = "Date of birth", required = true)
    private String dateOfBirth;
    @ApiModelProperty(value = "Phone number", required = true)
    private String phoneNumber;

}
