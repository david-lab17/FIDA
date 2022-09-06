package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Zone;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.ZoneDto;
import com.deltacode.kcb.payload.ZoneResponse;
import com.deltacode.kcb.repository.ZoneRepository;
import com.deltacode.kcb.service.ZoneService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ZoneServiceImpl implements ZoneService {
    private final ZoneRepository zoneRepository;
    private final ModelMapper modelMapper;

    public ZoneServiceImpl(ZoneRepository zoneRepository, ModelMapper modelMapper) {
        this.zoneRepository = zoneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ZoneDto createZone(ZoneDto zoneDto) {
        //convert Dto to entity
        Zone zone = mapToEntity(zoneDto);
        Zone newZone = zoneRepository.save(zone);
        //convert entity to Dto
        return mapToDto(newZone);
    }

    @Override
    public ZoneResponse getAllZones(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<Zone> zones = zoneRepository.findAll(pageable);
        //get content for page object
        List<Zone> listOfZone = zones.getContent();
        List<ZoneDto> content = listOfZone.stream().map(this::mapToDto).toList();
        ZoneResponse zoneResponse = new ZoneResponse();
        zoneResponse.setContent(content);
        zoneResponse.setPageNo(zones.getNumber());
        zoneResponse.setPageSize(zones.getSize());
        zoneResponse.setTotalElements(zones.getNumberOfElements());
        zoneResponse.setTotalPages(zones.getTotalPages());
        zoneResponse.setLast(zones.isLast());
        return zoneResponse;
    }

    @Override
    public ZoneDto getZoneById(Long id) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id));
        return mapToDto(zone);
    }

    @Override
    public ZoneDto updateZone(ZoneDto zoneDto, Long id) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id));
        zone.setZoneName(zoneDto.getZoneName());
        zone.setZoneDescription(zoneDto.getZoneDescription());
        zone.setZoneCode(zoneDto.getZoneCode());
        zone.setStatus(zoneDto.getStatus());
        Zone updatedZone = zoneRepository.save(zone);
        return mapToDto(updatedZone);
    }

    @Override
    public void deleteZoneById(Long id) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id));
        zoneRepository.delete(zone);

    }
    //convert entity to dto
    private ZoneDto mapToDto(Zone zone) {

        return modelMapper.map(zone, ZoneDto .class);

    }
    //convert dto to entity
    private Zone mapToEntity(ZoneDto zoneDto) {

        return modelMapper.map(zoneDto, Zone.class);

    }
}

