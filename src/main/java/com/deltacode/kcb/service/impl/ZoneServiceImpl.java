package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Zone;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.ZoneDto;
import com.deltacode.kcb.payload.ZoneResponse;
import com.deltacode.kcb.repository.ZoneRepository;
import com.deltacode.kcb.service.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("Creating zone");
        //convert Dto to entity
        Zone zone = mapToEntity(zoneDto);
        Zone newZone = zoneRepository.save(zone);
        //convert entity to Dto
        return mapToDto(newZone);
    }

    @Override
    public ZoneResponse getAllZones(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all zones");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<Zone> zones = zoneRepository.findAll(pageable);
        //get content for page object
        List<Zone> listOfZone = zones.getContent();
        List<ZoneDto> content = listOfZone.stream().map(zone -> mapToDto(zone)).collect(Collectors.toList());
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
        log.info("Getting zone by id = {}", id);
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id));
        return mapToDto(zone);
    }

    @Override
    public ResponseEntity<?> updateZone(ZoneDto zoneDto, Long id) {
        HashMap<String,Object> responseObject= new HashMap<>();
        HashMap<String,Object> responseParams =new HashMap<>();
        try {
            log.info("Updating zone by id = {}", id);
            Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id));
            zone.setZoneName(zoneDto.getZoneName());
            zone.setZoneDescription(zoneDto.getZoneDescription());
            zone.setZoneCode(zoneDto.getZoneCode());
            zone.setStatus(zoneDto.getStatus());
            Zone updatedZone = zoneRepository.save(zone);
            responseObject.put("status", "success");//set status
            responseObject.put("message", "Zone "
                    +zoneDto.getZoneName()+" successfully updated");//set message
            responseObject.put("data", responseParams);
            mapToDto(updatedZone);
            return ResponseEntity.ok(updatedZone);
        } catch (Exception e) {
            responseObject.put("status", "failed");
            responseObject.put("message", e.getMessage());
            return ResponseEntity.ok().body(responseObject);
        }

    }

    @Override
    public void deleteZoneById(Long id) {
        log.info("Deleting zone by id = {}", id);
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

