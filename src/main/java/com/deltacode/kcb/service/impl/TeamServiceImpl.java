package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Team;
import com.deltacode.kcb.payload.TeamDto;
import com.deltacode.kcb.payload.TeamResponse;
import com.deltacode.kcb.repository.TeamRepository;
import com.deltacode.kcb.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TeamDto createTeam(TeamDto teamDto) {
        Team team = modelMapper.map(teamDto, Team.class);
        teamRepository.save(team);
        return modelMapper.map(team, TeamDto.class);
    }

    @Override
    public TeamResponse getAllTeams(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<Team> team = teamRepository.findAll(pageable);
        //get content for page object
        List<Team> listOfTeams = team.getContent();
        List<TeamDto> content = listOfTeams.stream().map(this::mapToDto).toList();
        TeamResponse teamResponse = new TeamResponse();
        teamResponse.setContent(content);
        teamResponse.setPageNo(team.getNumber());
        teamResponse.setPageSize(team.getSize());
        teamResponse.setTotalElements(team.getNumberOfElements());
        teamResponse.setTotalPages(team.getTotalPages());
        teamResponse.setLast(team.isLast());
        return teamResponse;
    }

    @Override
    public TeamDto getTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        return modelMapper.map(team, TeamDto.class);
    }

    @Override
    public TeamDto updateTeam(TeamDto teamDto, Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
        team.setTeamName(teamDto.getTeamName());
        team.setDescription(teamDto.getDescription());
        team.setTeamCode(teamDto.getTeamCode());
        team.setTeamManager(teamDto.getTeamManager());
        team.setStatus(teamDto.getStatus());
        team.setZone(teamDto.getZone());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDto.class);
    }

    @Override
    public void deleteTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
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
