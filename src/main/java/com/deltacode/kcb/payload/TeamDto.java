package com.deltacode.kcb.payload;

import com.deltacode.kcb.utils.Auditable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@ApiModel(description = "Team object")
@Data
public class TeamDto extends Auditable<String> {
    private Long id;
    @NotEmpty(message = "Name is required")
    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Team name")
    private String teamName;
    @NotEmpty(message = "Team Manager is required")
    @Size(min = 2, message = "Team Manager Name must have at least 2 characters")
    @ApiModelProperty(value = "Team Manage Name")
    private String teamManager;
    @ApiModelProperty(value = "Team status")
    private Boolean status;
    @ApiModelProperty(value = "Team code")
    private String teamCode;
    @Size(min = 10, message = "Description Name must have at least 2 characters")
    @ApiModelProperty(value = " Team Description")
    private String description;
    private Long zoneId;
    private Set<DSRDto> dsr;

}
