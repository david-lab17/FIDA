package com.deltacode.kcb.service;
import com.deltacode.kcb.payload.ComplaintTypeDto;
import com.deltacode.kcb.payload.ComplaintTypeResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ComplaintTypeService {
    ComplaintTypeResponse getAllComplaintTypes(int pageNo, int pageSize, String sortBy, String sortDir );

    ComplaintTypeDto getComplaintTypesById(Long id);
    ComplaintTypeDto updateComplaintTypes(ComplaintTypeDto complaintTypeDto,Long id);
    void deleteComplaintTypeById(Long id);
    ResponseEntity<?> addComplain(String complainDetails, MultipartFile[] complainFiles,
                                  HttpServletRequest httpServletRequest );
}
