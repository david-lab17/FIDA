package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.TeamDto;
import com.deltacode.kcb.payload.TeamResponse;
import com.deltacode.kcb.service.TeamService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@Api(value = "Team Controller Rest Api")
@RestController()
@RequestMapping(path = "/api/v1")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    //create team
    @ApiOperation(value = "Create Team REST API")
    @PostMapping("/zones/{zoneId}/team")
    public ResponseEntity<TeamDto> createTeam(@PathVariable(value = "countyId") long zoneId,
                                                              @Valid @RequestBody TeamDto teamDto) {
        return new ResponseEntity<>(teamService.createTeam(zoneId, teamDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Team By Zone ID REST API")
    @GetMapping("/zones/{zoneId}/team")
    public List<TeamDto> getTeamByZoneId(@PathVariable(value = "zoneId") Long zoneId) {
        return teamService.getTeamByZoneId(zoneId);
    }

    @ApiOperation(value = "Fetching all Teams  Api")
    @GetMapping("/teams")
    public TeamResponse getAllTeams(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return teamService.getAllTeams(pageNo,pageSize,sortBy,sortDir);
    }

    @ApiOperation(value = "Get Single Team By ID REST API")
    @GetMapping("/zones/{zoneId/team/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable(value = "zoneId") Long zoneId,
                                                               @PathVariable(value = "id") Long teamId){
        TeamDto teamDto = teamService.getTeamById(zoneId, teamId);
        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Team By ID REST API")
    @PutMapping("/zones/{zoneId}/team/{id}")
    public ResponseEntity<TeamDto> updateTeam(@PathVariable(value = "zoneId") Long zoneId,
                                                              @PathVariable(value = "id") Long teamId,
                                                              @Valid @RequestBody TeamDto teamDto){
        TeamDto updateTeam = teamService.updateTeam(zoneId, teamId, teamDto);
        return new ResponseEntity<>(updateTeam, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Team By ID REST API")
    @DeleteMapping("/zones/{zoneId}/team/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable(value = "zoneId") Long zoneId,
                                                     @PathVariable(value = "id") Long teamId){
        teamService.deleteTeam(zoneId, teamId);
        return new ResponseEntity<>("Team deleted successfully", HttpStatus.OK);
    }
}
