package com.deltacode.kcb.payload;

import com.deltacode.kcb.entity.Role;
import com.deltacode.kcb.utils.Auditable;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Set;

@ApiModel(description = "User object")
@Data
public class UserAppDto extends Auditable<String> {


    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String middleName;
    private String dateOfBirth;
    private Set<Role> roles;

}
