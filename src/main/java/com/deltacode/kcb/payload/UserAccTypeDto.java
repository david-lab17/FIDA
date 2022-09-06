package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "User Acc Type object")
@Data
public class UserAccTypeDto {
    @ApiModelProperty(value = "User Acc Type ID")
    private Long id;
    //name should not be null or empty
    //name should have at least 2 characters
    @NotEmpty(message = "User Acc type name is required")
    @Size(min = 2, message = "User Acc Type Name must have at least 2 characters")
    @ApiModelProperty(value = "User Acc Type  name")
    private String userAccTypeName;
    @ApiModelProperty(value = "User Acc Type status")
    private boolean status=true;
    @ApiModelProperty(value = "User Acc Type creation date")
    private Date createdDate;

}
