package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.UserAccTypeDto;
import com.deltacode.kcb.payload.UserAccTypeResponse;
import org.springframework.http.ResponseEntity;

public interface UserAccTypeService {
    UserAccTypeDto createUserAccType(UserAccTypeDto userAccTypeDto);
    UserAccTypeResponse getAllUserAccTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    UserAccTypeDto getUserAccTypesById(Long id);
    ResponseEntity<?> updateUserAccTypes(UserAccTypeDto userAccTypeDto, Long id);
    ResponseEntity<?> deleteUserAccTypeById(Long id);
}
