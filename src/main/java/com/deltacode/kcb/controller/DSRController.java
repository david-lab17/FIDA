package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.DSRDto;
import com.deltacode.kcb.payload.DSRResponse;
import com.deltacode.kcb.service.DSRService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "DSR Rest Api")
@RestController()

@RequestMapping(path = "/api/v1")
public class DSRController {
    private final DSRService dsrService;

    public DSRController(DSRService dsrService) {
        this.dsrService = dsrService;
    }

    //create DSR
    @ApiOperation(value = "Create Dsr REST API")
    @PostMapping("/teams/{teamId}/dsr")
    public ResponseEntity<DSRDto> createDsr(@PathVariable(value = "teamId") long teamId,
                                              @Valid @RequestBody DSRDto dsrDto) {
        return new ResponseEntity<>(dsrService.createDSR(teamId, dsrDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Dsr By Team ID REST API")
    @GetMapping("/teams/{teamId}/dsr")
    public List<DSRDto> getDsrByTeamId(@PathVariable(value = "teamId") Long teamId) {
        return dsrService.getDSRByTeamId(teamId);
    }

    @ApiOperation(value = "Fetching all Dsr  Api")
    @GetMapping("/dsr")
    public DSRResponse getAllDSRs(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return dsrService.getAllDSRs(pageNo,pageSize,sortBy,sortDir);
    }

    @ApiOperation(value = "Get Single DSR By ID REST API")
    @GetMapping("/teams/{teamId}/dsr/{id}")
    public ResponseEntity<DSRDto> getDsrById(@PathVariable(value = "teamId") Long teamId,
                                               @PathVariable(value = "id") Long dsrId){
        DSRDto dsrDto = dsrService.getDSRById(teamId, dsrId);
        return new ResponseEntity<>(dsrDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Update DSR By ID REST API")
    @PutMapping("/teams/{teamId}/dsr/{id}")
    public ResponseEntity<DSRDto> updateDsr(@PathVariable(value = "teamId") Long teamId,
                                              @PathVariable(value = "id") Long dsrId,
                                              @Valid @RequestBody DSRDto dsrDto){
        DSRDto updatedDsr = dsrService.updateDSR(teamId, dsrId, dsrDto);
        return new ResponseEntity<>(updatedDsr, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete DSR By ID REST API")
    @DeleteMapping("/teams/{teamId}/dsr/{id}")
    public ResponseEntity<String> deleteDsr(@PathVariable(value = "teamId") Long teamId,
                                             @PathVariable(value = "id") Long dsrId){
        dsrService.deleteDSR(teamId, dsrId);
        return new ResponseEntity<>("DSR deleted successfully", HttpStatus.OK);
    }
}
