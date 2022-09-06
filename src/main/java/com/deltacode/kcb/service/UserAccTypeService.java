package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.UserAccTypeDto;
import com.deltacode.kcb.payload.UserAccTypeResponse;

public interface UserAccTypeService {
    UserAccTypeDto createUserAccType(UserAccTypeDto userAccTypeDto);
    UserAccTypeResponse getAllUserAccTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    UserAccTypeDto getUserAccTypesById(Long id);
    UserAccTypeDto updateUserAccTypes(UserAccTypeDto userAccTypeDto,Long id);
    void deleteUserAccTypeById(Long id);
}
