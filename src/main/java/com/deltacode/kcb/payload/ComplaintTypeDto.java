package com.deltacode.kcb.payload;

import com.deltacode.kcb.entity.UserAccType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "Complaint object")
@Data
public class ComplaintTypeDto {
    private org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
    @NotNull(message = "User userAccountTypeId cannot be null")
    private long userAccountTypeId;
    @NotNull(message = "Complain type ID  cannot be null")
    private long complainTypeId;
    @NotNull(message = "Account number cannot be null")
    private String accountNo;
    @NotNull(message = "Subject cannot be null")
    private String subject;
    @NotNull(message = "Message cannot be null")
    private String message;
//    private Long id;
//    @NotEmpty(message = "Name of the bank is required")
//    @Size(min = 2, message = "Name must have at least 2 characters")
    @ApiModelProperty(value = "Complaint Type name")
    private String complaintTypeName;
    @ApiModelProperty(value = "Complaint description ")
    private String description;
//    @ApiModelProperty(value = "Branch status")
//    private boolean status=true;


}

