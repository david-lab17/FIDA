package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Complaint object")
@Data
public class ComplaintTypeDto {
    private Long id;
    @NotEmpty(message = "Name of the bank is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Complaint Type name")
    private String complaintTypeName;
    @ApiModelProperty(value = "Complaint description ")
    private String description;
    @ApiModelProperty(value = "Branch status")
    private boolean status=true;


}

