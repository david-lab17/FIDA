package com.deltacode.kcb.controller;
import com.deltacode.kcb.payload.ZoneDto;
import com.deltacode.kcb.payload.ZoneResponse;
import com.deltacode.kcb.service.ZoneService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@Api(value = "Zone Controller Rest Api")
@RestController()
@RequestMapping(path = "/dsr-team/zone")
public class ZoneController {
    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }
    @ApiOperation(value = "Create Zone Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ZoneDto> createZone(@Valid @RequestBody ZoneDto zoneDto){
        return  new ResponseEntity<>(zoneService.createZone(zoneDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Fetching all Zone  Api")
    @GetMapping
    public ZoneResponse getAllZones(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return zoneService.getAllZones(pageNo,pageSize,sortBy,sortDir);
    }
    @ApiOperation(value = "Fetching  Zone  Api by Id")
    @GetMapping("/{id}")
    public ResponseEntity<ZoneDto> getZoneById(@PathVariable Long id){
        return new ResponseEntity<>(zoneService.getZoneById(id),HttpStatus.OK);
    }
    //update zone
    @ApiOperation(value = "Update Zone Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ZoneDto updateBank(@Valid @RequestBody ZoneDto bankDto,@PathVariable Long id){
        return zoneService.updateZone(bankDto,id);
    }
    //delete zone
    @ApiOperation(value = "Delete Zone Api")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteZone(@PathVariable Long id){
        zoneService.deleteZoneById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
