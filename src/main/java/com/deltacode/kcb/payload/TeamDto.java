package com.deltacode.kcb.payload;

import com.deltacode.kcb.entity.Zone;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ApiModel(description = "Team object")
@Data
public class TeamDto {
    private Long id;
    private LocalDateTime createdDate;
    @NotEmpty(message = "Name is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Team name")
    private String teamName;
    @NotEmpty(message = "Team Manager is required")
    @Size(min = 2, message = "Team Manager Name must have at least 2 characters")
    @ApiModelProperty(value = "Team Manage Name")
    private String teamManager;
    @NotEmpty(message = "Status is required")
    @ApiModelProperty(value = "Team status")
    private Boolean status;
    @ApiModelProperty(value = "Team code")
    private String teamCode;
    @NotEmpty(message = "Description is required")
    @Size(min = 10, message = "Description Name must have at least 2 characters")
    @ApiModelProperty(value = " Team Description")
    private String description;
    @NotEmpty(message = "Zone is required")
    @ApiModelProperty(value = "Team  zone")
    private Zone zone;
}
