package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Constituency;
import com.deltacode.kcb.entity.Team;
import com.deltacode.kcb.entity.Zone;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.ConstituencyDto;
import com.deltacode.kcb.payload.TeamDto;
import com.deltacode.kcb.payload.TeamResponse;
import com.deltacode.kcb.repository.TeamRepository;
import com.deltacode.kcb.repository.ZoneRepository;
import com.deltacode.kcb.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    private final ZoneRepository zoneRepository;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, ZoneRepository zoneRepository) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public TeamDto createTeam(long zoneId, TeamDto teamDto) {
        log.info("Creating Team");
        Team team = mapToEntity(teamDto);
        //set zone to team
       // retrieve zone entity by id
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(
                () -> new ResourceNotFoundException("Zone", "id", zoneId));
        //set zone to team entity
        team.setZone(zone);
        //team entity to DB
        Team newTeam = teamRepository.save(team);
        return mapToDto(newTeam);

    }

    @Override
    public TeamResponse getAllTeams(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all teams");
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<Team> team=teamRepository.findAll(pageable);
        //get content for page object
        List<Team> teamList=team.getContent();
        List<TeamDto> content=teamList.stream().map(team1 -> mapToDto(team1)).collect(Collectors.toList());
        TeamResponse teamResponse=new TeamResponse();
        teamResponse.setContent(content);
        teamResponse.setPageNo(team.getNumber());
        teamResponse.setPageSize(team.getSize());
        teamResponse.setTotalElements((int) team.getTotalElements());
        teamResponse.setTotalPages(team.getTotalPages());
        teamResponse.setLast(team.isLast());
        return teamResponse;
    }

    @Override
    public List<TeamDto> getTeamByZoneId(long zoneId) {
        log.info("Getting teams by zone id = {}", zoneId);
        //retrieve team by zoneId
        List<Team> teams = teamRepository.findByZoneId(zoneId);
        //convert list of team entities to list of team dto's
        return teams.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public TeamDto getTeamById(Long zoneId, Long teamId) {
        log.info("Getting team by id = {} and zone id = {}", teamId, zoneId);
        //retrieve zone by id
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(
                () -> new ResourceNotFoundException("Zone", "id", zoneId));
        //retrieve team by id
        Team team = teamRepository.findById(teamId).orElseThrow(
                () -> new ResourceNotFoundException("Team", "id", teamId));
        //check if team belongs to zone
        if (team.getZone().getId() != zone.getId()) {
            throw new ResourceNotFoundException("Team", "id", teamId);
        }
        return mapToDto(team);
    }

    @Override
    public TeamDto updateTeam(Long zoneId, long teamId, TeamDto teamDto) {
        log.info("Updating team by id = {} and zone id = {}", teamId, zoneId);
        //retrieve zone by id
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(
                () -> new ResourceNotFoundException("Zone", "id", zoneId));
        //retrieve team by id
        Team team = teamRepository.findById(teamId).orElseThrow(
                () -> new ResourceNotFoundException("Team", "id", teamId));
        //check if team belongs to zone
        if (team.getZone().getId() != zone.getId()) {
            throw new ResourceNotFoundException("Team", "id", teamId);
        }
        //update team entity
        team.setTeamName(teamDto.getTeamName());
        team.setTeamManager(teamDto.getTeamManager());
        team.setTeamCode(teamDto.getTeamCode());
        team.setDescription(teamDto.getDescription());
        team.setStatus(teamDto.getStatus());
        //save team entity to DB
        Team updatedTeam = teamRepository.save(team);
        return mapToDto(updatedTeam);

    }

    @Override
    public void deleteTeam(Long zoneId, Long teamId) {
        log.info("Deleting team by id = {} and zone id = {}", teamId, zoneId);
        //retrieve zone by id
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(
                () -> new ResourceNotFoundException("Zone", "id", zoneId));
        //retrieve team by id
        Team team = teamRepository.findById(teamId).orElseThrow(
                () -> new ResourceNotFoundException("Team", "id", teamId));
        //check if team belongs to zone
        if (team.getZone().getId() != zone.getId()) {
            throw new ResourceNotFoundException("Team", "id", teamId);
        }
        //delete team from DB
        teamRepository.delete(team);

    }

    //convert entity to dto
    private TeamDto mapToDto(Team team) {

        return modelMapper.map(team, TeamDto .class);
    }
    //convert dto to entity
    private Team mapToEntity(TeamDto teamDto) {

        return modelMapper.map(teamDto, Team.class);

    }
}
