package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.CountyResponse;
import com.deltacode.kcb.payload.WardDto;
import com.deltacode.kcb.payload.WardResponse;
import com.deltacode.kcb.service.WardService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "Ward Controller Rest Api")
@RestController()

@RequestMapping(path = "/api/v1")
public class WardController {
    private final WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @ApiOperation(value = "Create Ward REST API")
    @PostMapping("/constituencies/{constituencyId}/ward")
    public ResponseEntity<WardDto> createWard(@PathVariable(value = "constituencyId") long constituencyId,
                                                      @Valid @RequestBody WardDto wardDto) {
        return new ResponseEntity<>(wardService.createWard(constituencyId, wardDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Fetching all Wards  Api")
    @GetMapping("/wards")
    public WardResponse getAllWards(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return wardService.getAllWards(pageNo,pageSize,sortBy,sortDir);
    }

    @ApiOperation(value = "Get All Ward By Constituency ID REST API")
    @GetMapping("/constituencies/{constituencyId}/ward")
    public List<WardDto> getWardByConstituencyId(@PathVariable(value = "constituencyId") Long constituencyId) {
        return wardService.getWardByConstituencyId(constituencyId);
    }

    @ApiOperation(value = "Get Single Ward By ID REST API")
    @GetMapping("/constituencies/{constituencyId}/ward/{id}")
    public ResponseEntity<WardDto> getWardById(@PathVariable(value = "constituencyId") Long constituencyId,
                                                               @PathVariable(value = "id") Long wardId){
        WardDto wardDto = wardService.getWardById(constituencyId, wardId);
        return new ResponseEntity<>(wardDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Ward By ID REST API")
    @PutMapping("/constituencies/{constituencyId}/ward/{id}")
    public ResponseEntity<WardDto> updateWard(@PathVariable(value = "constituencyId") Long constituencyId,
                                                              @PathVariable(value = "id") Long wardId,
                                                              @Valid @RequestBody WardDto wardDto){
        WardDto updatedWard = wardService.updateWard(constituencyId, wardId, wardDto);
        return new ResponseEntity<>(updatedWard, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Ward By ID REST API")
    @DeleteMapping("/constituency/{constituencyId}/ward/{id}")
    public ResponseEntity<String> deleteWard(@PathVariable(value = "constituencyId") Long constituencyId,
                                                             @PathVariable(value = "id") Long wardId){
        wardService.deleteWard(constituencyId, wardId);
        return new ResponseEntity<>("Ward deleted successfully", HttpStatus.OK);
    }
}
