package com.deltacode.kcb.payload;

import com.deltacode.kcb.utils.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ApiModel(description = "Liquidation Type object")
@Data
public class LiquidationTypeDto extends Auditable<String> {
    private Long id;

    @NotEmpty(message = "Name of the Liquidation is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Branch name")
    private String liquidationTypeName;
    @ApiModelProperty(value = "Liquidation status")
    private Boolean status=true;


}