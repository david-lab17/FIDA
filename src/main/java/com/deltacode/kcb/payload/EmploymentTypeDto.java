package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Employment Type object")
@Data
public class EmploymentTypeDto {
    @ApiModelProperty(value = "Employment Type ID")
    private Long id;
    //name should not be null or empty
    //name should have at least 2 characters
    @NotEmpty(message = "Employment type name is required")
    @Size(min = 2, message = "Employment Type Name must have at least 2 characters")
    @ApiModelProperty(value = "Employment Type  name")
    private String employmentTypeName;
    @ApiModelProperty(value = "Employment Type status")
    private boolean status=true;
    @ApiModelProperty(value = "Employment Type creation date")
    private Date createdDate;

}
