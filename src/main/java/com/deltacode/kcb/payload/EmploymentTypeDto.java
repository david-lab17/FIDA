package com.deltacode.kcb.payload;

import com.deltacode.kcb.utils.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@ApiModel(description = "Employment Type object")
@Data
public class EmploymentTypeDto extends Auditable<String> {
    private Long id;
    @NotEmpty(message = "Employment type name is required")
    @Size(min = 2, message = "Employment Type Name must have at least 2 characters")
    @ApiModelProperty(value = "Employment Type  name")
    private String employmentTypeName;
    @ApiModelProperty(value = "Employment Type status")
    private boolean status=true;

}
