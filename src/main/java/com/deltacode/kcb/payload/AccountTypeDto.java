package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Account Type object")
@Data
public class AccountTypeDto {
    @ApiModelProperty(value = "Acc Type ID")
    private Long id;
    //name should not be null or empty
    //name should have at least 2 characters
    @NotEmpty(message = "Name of the bank is required")
    @Size(min = 2, message = "Account Type Name must have at least 2 characters")
    @ApiModelProperty(value = "Acc Type  name")
    private String accountTypeName;
    @NotEmpty(message = "Acc Type code is required")
    @ApiModelProperty(value = "Acc Type code")
    private String accountTypeCode;
    @ApiModelProperty(value = "Acc Type status")
    private boolean status=true;
    @ApiModelProperty(value = "Acc Type creation date")
    private Date createdDate;

}
