package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.EmploymentTypeDto;
import com.deltacode.kcb.payload.EmploymentTypeResponse;

public interface EmploymentTypeService {
    EmploymentTypeDto createEmploymentType(EmploymentTypeDto employmentTypeDto);
    EmploymentTypeResponse getAllEmploymentTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    EmploymentTypeDto getEmploymentTypesById(Long id);
    EmploymentTypeDto updateEmploymentTypes(EmploymentTypeDto employmentTypeDto,Long id);
    void deleteEmploymentTypeById(Long id);
}
