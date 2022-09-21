package com.deltacode.kcb.payload;

import com.deltacode.kcb.utils.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "User Acc Type object")
@Data
public class UserAccTypeDto extends Auditable<String> {

    @NotEmpty(message = "User Acc type name is required")
    @Size(min = 2, message = "User Acc Type Name must have at least 2 characters")
    @ApiModelProperty(value = "User Acc Type  name")
    private String userAccTypeName;
    @ApiModelProperty(value = "User Acc Type status")
    private boolean status=true;


}
