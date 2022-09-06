package com.deltacode.kcb.controller;

import com.deltacode.kcb.payload.TeamDto;
import com.deltacode.kcb.payload.TeamResponse;
import com.deltacode.kcb.service.TeamService;
import com.deltacode.kcb.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*")
@Api(value = "Team Controller Rest Api")
@RestController()
@RequestMapping(path = "/dsr-team/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    //create team
    @ApiOperation(value = "Create Team Api")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TeamDto> createTeam(@Valid @RequestBody TeamDto teamDto){
        return  new ResponseEntity<>(teamService.createTeam(teamDto), HttpStatus.CREATED);
    }

    //get all teams
    @ApiOperation(value = "Fetching all Teams  Api")
    @GetMapping
    public TeamResponse getAllTeams(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value ="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false) String sortDir
    ){
        return teamService.getAllTeams(pageNo,pageSize,sortBy,sortDir);
    }
    //get team by id
    @ApiOperation(value = "Fetching  Team  Api by Id")

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable("id") Long id) {
        TeamDto teamDto = teamService.getTeamById(id);
        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }
    //update team
    @ApiOperation(value = "Updating Team  Api by Id")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TeamDto> updateTeam(@Valid @RequestBody TeamDto teamDto,@PathVariable(name ="id")long id){
        TeamDto teamResponse=teamService.updateTeam(teamDto,id);
        return new ResponseEntity<>(teamResponse, HttpStatus.OK);
    }

    //delete team
    @ApiOperation(value = "Delete Team by Id")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteTeam(@PathVariable(name = "id")long id){
        teamService.deleteTeamById(id);
        return new ResponseEntity<>("Team entity deleted successful.",HttpStatus.OK);
    }
}
