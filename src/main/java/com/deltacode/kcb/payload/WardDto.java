package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Ward object")
@Data
public class WardDto {
    @NotEmpty(message = "Name of the Ward is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Ward name")
    private String wardName;
    @NotEmpty(message = "Ward code is required")
    @ApiModelProperty(value = "Ward code")
    private Integer wardCode;
    @ApiModelProperty(value="Ward description")
    private String description;
    @ApiModelProperty(value = "Ward status")
    private boolean status=true;


}
