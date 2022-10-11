package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.EmploymentTypeDto;
import com.deltacode.kcb.payload.EmploymentTypeResponse;
import org.springframework.http.ResponseEntity;

public interface EmploymentTypeService {
    EmploymentTypeDto createEmploymentType(EmploymentTypeDto employmentTypeDto);
    EmploymentTypeResponse getAllEmploymentTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    EmploymentTypeDto getEmploymentTypesById(Long id);
    EmploymentTypeDto updateEmploymentTypes(EmploymentTypeDto employmentTypeDto,Long id);
    ResponseEntity<?> deleteEmploymentTypeById(Long id);
}
