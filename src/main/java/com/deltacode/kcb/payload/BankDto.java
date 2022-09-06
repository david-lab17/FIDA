package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Bank object")
@Data
public class BankDto {
    @ApiModelProperty(value = "Bank ID")
    private Long id;
    //name should not be null or empty
    //name should have at least 2 characters
    @NotEmpty(message = "Name of the bank is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Bank name")
    private String bankName;
    @NotEmpty(message = "Bank code is required")
    @ApiModelProperty(value = "Bank code")
    private String bankCode;
    @ApiModelProperty(value = "Bank status")
    private boolean status=true;
    @ApiModelProperty(value = "Bank creation date")
    private Date createdDate;

}
