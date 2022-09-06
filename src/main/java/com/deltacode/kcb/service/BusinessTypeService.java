package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.BusinessTypeDto;
import com.deltacode.kcb.payload.BusinessTypeResponse;

public interface BusinessTypeService {
    BusinessTypeDto createBusinessType(BusinessTypeDto businessTypeDto);
    BusinessTypeResponse getAllBusinessTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    BusinessTypeDto getBusinessTypesById(Long id);
    BusinessTypeDto updateBusinessTypes(BusinessTypeDto businessTypeDto,Long id);
    void deleteBusinessTypeById(Long id);
}
