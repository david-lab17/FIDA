package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Bank object")
@Data
public class BankDto {
    private Long id;
    @NotEmpty(message = "Name of the bank is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Bank name")
    private String bankName;
    @NotEmpty(message = "Bank code is required")
    @ApiModelProperty(value = "Bank code")
    private String bankCode;
    @ApiModelProperty(value = "Bank status")
    private boolean status=true;



}
