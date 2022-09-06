package com.deltacode.kcb.service;
import com.deltacode.kcb.payload.TeamDto;
import com.deltacode.kcb.payload.TeamResponse;
public interface TeamService {
    TeamDto createTeam(TeamDto teamDto);
    TeamResponse getAllTeams(int pageNo, int pageSize, String sortBy, String sortDir );
    TeamDto getTeamById(Long id);
    TeamDto updateTeam(TeamDto teamDto,Long id);
    void deleteTeamById(Long id);
}
