package com.deltacode.kcb.payload;

import com.deltacode.kcb.utils.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@ApiModel(description = "Account Type object")
@Data
public class AccountTypeDto extends Auditable<String> {
    private Long id;
    @NotEmpty(message = "Name of the Acc Type is required")
    @Size(min = 2, message = "Account Type Name must have at least 2 characters")
    @ApiModelProperty(value = "Acc Type  name")
    private String accountTypeName;
    @NotEmpty(message = "Acc Type code is required")
    @ApiModelProperty(value = "Acc Type code")
    private String accountTypeCode;
    @ApiModelProperty(value = "Acc Type status")
    private boolean status=true;


}
