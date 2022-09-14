package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Constituency;
import com.deltacode.kcb.entity.County;
import com.deltacode.kcb.entity.Ward;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.WardDto;
import com.deltacode.kcb.repository.ConstituencyRepository;
import com.deltacode.kcb.repository.WardRepository;
import com.deltacode.kcb.service.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class WardServiceImpl implements WardService {
    private final WardRepository wardRepository;
    private  final ModelMapper modelMapper;
    private final ConstituencyRepository constituencyRepository;

    public WardServiceImpl(WardRepository wardRepository, ModelMapper modelMapper, ConstituencyRepository constituencyRepository) {
        this.wardRepository = wardRepository;
        this.modelMapper = modelMapper;
        this.constituencyRepository = constituencyRepository;
    }


    @Override
    public WardDto createWard(long constituencyId, WardDto wardDto) {
        Ward ward =mapToEntity(wardDto);
        //set constituency to ward
        // retrieve constituency entity by id
        Constituency constituency = constituencyRepository.findById(constituencyId).orElseThrow(
                () -> new ResourceNotFoundException("Constituency", "id", constituencyId));

        // set constituency to ward entity
        ward.setConstituency(constituency);
        // ward entity to DB
        Ward newWard =  wardRepository.save(ward);
        return mapToDto(newWard);
    }

    @Override
    public List<WardDto> getWardByConstituencyId(long constituencyId) {
        // retrieve ward by constituencyId
        List<Ward> wards = wardRepository.findByConstituencyId(constituencyId);
        // convert list of ward entities to list of ward dto's
        return wards.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public WardDto getWardById(Long constituencyId, Long wardId) {
        // retrieve constituency by id
        Constituency constituency =constituencyRepository.findById(constituencyId).orElseThrow(
                () -> new ResourceNotFoundException("Constituency", "id", constituencyId));
        // retrieve ward by id
        Ward ward = wardRepository.findById(wardId).orElseThrow(
                () -> new ResourceNotFoundException("Ward", "id", wardId));
        // check if ward belongs to constituency
        if(!ward.getConstituency().equals(constituency)){
            throw new ResourceNotFoundException("Ward", "id", wardId);
        }
        return mapToDto(ward);

    }

    @Override
    public WardDto updateWard(Long constituencyId, long wardId, WardDto wardDto) {
        Constituency constituency =constituencyRepository.findById(constituencyId).orElseThrow(
                () -> new ResourceNotFoundException("Constituency", "id", constituencyId));
        Ward ward = wardRepository.findById(wardId).orElseThrow(
                () -> new ResourceNotFoundException("Ward", "id", wardId));
        if(!ward.getConstituency().equals(constituency)){
            throw new ResourceNotFoundException("Ward", "id", wardId);
        }
        ward.setWardName(wardDto.getWardName());
        ward.setWardCode(wardDto.getWardCode());
        ward.setDescription(wardDto.getDescription());
        Ward updatedWard = wardRepository.save(ward);
        return mapToDto(updatedWard);
    }

    @Override
    public void deleteWard(Long constituencyId, Long wardId) {
        Constituency constituency =constituencyRepository.findById(constituencyId).orElseThrow(
                () -> new ResourceNotFoundException("Constituency", "id", constituencyId));
        Ward ward = wardRepository.findById(wardId).orElseThrow(
                () -> new ResourceNotFoundException("Ward", "id", wardId));
        if(!ward.getConstituency().equals(constituency)){
            throw new ResourceNotFoundException("Ward", "id", wardId);
        }
        wardRepository.delete(ward);

    }
    //convert entity to dto
    private WardDto mapToDto(Ward ward) {

        return modelMapper.map(ward, WardDto .class);

    }
    //convert dto to entity
    private Ward mapToEntity(WardDto wardDto) {

        return modelMapper.map(wardDto, Ward.class);

    }
}