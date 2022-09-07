package com.deltacode.kcb.service;
import com.deltacode.kcb.payload.ComplaintTypeDto;
import com.deltacode.kcb.payload.ComplaintTypeResponse;
public interface ComplaintTypeService {
    ComplaintTypeDto createComplaintType(ComplaintTypeDto complaintTypeDto);
    ComplaintTypeResponse getAllComplaintTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    ComplaintTypeDto getComplaintTypesById(Long id);
    ComplaintTypeDto updateComplaintTypes(ComplaintTypeDto complaintTypeDto,Long id);
    void deleteComplaintTypeById(Long id);
}
