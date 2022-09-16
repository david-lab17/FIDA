package com.deltacode.kcb.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ApiModel(description = "Ward object")
@Data
public class WardDto {
    private Long id;
    private LocalDateTime createdDate;
    @NotEmpty(message = "Name of the Ward is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Ward name")
    private String wardName;
    @NotEmpty(message = "Ward code is required")
    @ApiModelProperty(value = "Ward code")
    private String wardCode;
    @ApiModelProperty(value="Constituency description")

    private String description;
    @ApiModelProperty(value = "Ward status")
    private boolean status=true;
    private Long constituencyId;


}
