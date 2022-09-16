package com.deltacode.kcb.service;

import com.deltacode.kcb.payload.TeamDto;
import com.deltacode.kcb.payload.TeamResponse;

import java.util.List;

public interface TeamService {
    TeamDto createTeam(long zoneId, TeamDto teamDto);

    List<TeamDto> getTeamByZoneId(long zoneId);

    TeamResponse getAllTeams(int pageNo, int pageSize, String sortBy, String sortDir );

    TeamDto getTeamById(Long zoneId, Long teamId);

    TeamDto updateTeam(Long zoneId, long teamId, TeamDto teamDto);

    void deleteTeam(Long zoneId, Long teamId);
}
