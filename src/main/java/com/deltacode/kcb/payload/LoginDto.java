package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data

public class LoginDto {
    @ApiModelProperty(value = "Login staff no or email")
    private String usernameOrEmail;
    @ApiModelProperty(value = "Login password")
    private String password;
}
