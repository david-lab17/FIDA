package com.deltacode.kcb.payload;

import com.deltacode.kcb.entity.Constituency;
import com.deltacode.kcb.entity.County;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ApiModel(description = "Constituency object")
@Data
public class ConstituencyDto {
    private Long id;
    private LocalDateTime createdDate;
    @NotEmpty(message = "Name of the Constituency is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Constituency name")
    private String constituencyName;
    @NotEmpty(message = "Constituency code is required")
    @ApiModelProperty(value = "Constituency code")
    private String constituencyCode;
    @ApiModelProperty(value="Constituency description")
    private String description;
    @ApiModelProperty(value = "Constituency status")
    private boolean status=true;


}