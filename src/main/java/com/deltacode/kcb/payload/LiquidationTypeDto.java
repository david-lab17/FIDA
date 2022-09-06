package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Liquidation Type object")
@Data
public class LiquidationTypeDto {
    @ApiModelProperty(value = "Liquidation Type ID")
    private Long id;
    //name should not be null or empty
    //name should have at least 2 characters
    @NotEmpty(message = "Name of the Liquidation is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Branch name")
    private String liquidationTypeName;
    @ApiModelProperty(value = "Liquidation status")
    private boolean status=true;
    @ApiModelProperty(value = "Liquidation creation date")
    private Date createdDate;

}