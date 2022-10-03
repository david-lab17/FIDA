package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Branch object")
@Data
public class BranchDto  {
    private Long id;
    @NotEmpty(message = "Name of the bank is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Branch name")
    private String branchName;
    @NotEmpty(message = "Branch code is required")
    @ApiModelProperty(value = "Branch code")
    private String branchCode;
    @ApiModelProperty(value = "Branch status")
    private boolean status=true;
    private Long bankId;


}
