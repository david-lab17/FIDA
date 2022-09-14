package com.deltacode.kcb.payload;

import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.entity.Team;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "Direct Sale Representative requirements")
public class DSRDto {
    private Long id;
    private LocalDateTime createdDate;
    @ApiModelProperty(value = "User's staff No", required = true)
    private String username;
    @ApiModelProperty(value = "User's first name", required = true)
    private String firstName;
    @ApiModelProperty(value = "Email")
    private String email;
    @ApiModelProperty(value = "Password", required = true)
    private String password;
    @ApiModelProperty(value = "User's last name", required = true)
    private String lastName;
    @ApiModelProperty(value = "User's last name", required = false)
    private String middleName;
    @ApiModelProperty(value = "Team ", required = true)
    private Team team;
    @ApiModelProperty(value = "Date of birth", required = true)
    private String dateOfBirth;
    @ApiModelProperty(value = "Phone number", required = true)
    private String phoneNumber;
    @ApiModelProperty(value = "Id Number", required = true)
    private Integer idNumber;
    @ApiModelProperty(value = "Role", required = true)
    private Boolean gender;
    @ApiModelProperty(value = "Role", required = true)
    private Boolean status;

}
