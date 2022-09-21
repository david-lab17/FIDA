package com.deltacode.kcb.payload;

import com.deltacode.kcb.utils.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@ApiModel(description = "Bank object")
@Data
public class BankDto extends Auditable<String> {
    private Long id;
    @NotEmpty(message = "Name of the bank is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Bank name")
    private String bankName;
    @NotEmpty(message = "Bank code is required")
    @ApiModelProperty(value = "Bank code")
    private String bankCode;
    @ApiModelProperty(value = "Bank status")
    private boolean status=true;



}
