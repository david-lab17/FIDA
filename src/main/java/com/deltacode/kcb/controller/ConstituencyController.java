package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.ConstituencyDto;
import com.deltacode.kcb.service.ConstituencyService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "Constituency Controller Rest Api")
@RestController()

@RequestMapping(path = "/api/v1")
public class ConstituencyController {
    private final ConstituencyService constituencyService;

    public ConstituencyController(ConstituencyService constituencyService) {
        this.constituencyService = constituencyService;
    }


    @ApiOperation(value = "Create Constituency REST API")
    @PostMapping("/counties/{countyId}/constituency")
    public ResponseEntity<ConstituencyDto> createConstituency(@PathVariable(value = "countyId") long countyId,
                                                    @Valid @RequestBody ConstituencyDto constituencyDto) {
        return new ResponseEntity<>(constituencyService.createConstituency(countyId, constituencyDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Constituency By County ID REST API")
    @GetMapping("/counties/{countyId}/constituency")
    public List<ConstituencyDto> getConstituencyByCountyId(@PathVariable(value = "countyId") Long countyId) {
        return constituencyService.getConstituencyByCountyId(countyId);
    }

    @ApiOperation(value = "Get Single Constituency By ID REST API")
    @GetMapping("/counties/{countyId}/constituency/{id}")
    public ResponseEntity<ConstituencyDto> getConstituencyById(@PathVariable(value = "countyId") Long countyId,
                                                     @PathVariable(value = "id") Long constituencyId){
        ConstituencyDto constituencyDto = constituencyService.getConstituencyById(countyId, constituencyId);
        return new ResponseEntity<>(constituencyDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Constituency By ID REST API")
    @PutMapping("/counties/{countyId}/constituency/{id}")
    public ResponseEntity<ConstituencyDto> updateConstituency(@PathVariable(value = "countyId") Long countyId,
                                                    @PathVariable(value = "id") Long constituencyId,
                                                    @Valid @RequestBody ConstituencyDto constituencyDto){
        ConstituencyDto updatedConstituency = constituencyService.updateConstituency(countyId, constituencyId, constituencyDto);
        return new ResponseEntity<>(updatedConstituency, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Constituency By ID REST API")
    @DeleteMapping("/counties/{countyId}/constituency/{id}")
    public ResponseEntity<String> deleteConstituency(@PathVariable(value = "countyId") Long countyId,
                                                @PathVariable(value = "id") Long constituencyId){
        constituencyService.deleteConstituency(countyId, constituencyId);
        return new ResponseEntity<>("Constituency deleted successfully", HttpStatus.OK);
    }
}
