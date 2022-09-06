package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Business Type object")
@Data
public class BusinessTypeDto {
    @NotEmpty(message = "Business name is required")
    @Size(min = 2, message = "Business Name must have at least 2 characters")
    @ApiModelProperty(value = "Business Type  name")
    private String businessTypeName;
    @ApiModelProperty(value = "Business Type status")
    private boolean status=true;

}
