package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Constituency;
import com.deltacode.kcb.entity.County;
import com.deltacode.kcb.entity.Ward;
import com.deltacode.kcb.exception.ResourceNotFoundException;
import com.deltacode.kcb.payload.ConstituencyDto;
import com.deltacode.kcb.payload.ConstituencyResponse;
import com.deltacode.kcb.payload.WardDto;
import com.deltacode.kcb.payload.WardResponse;
import com.deltacode.kcb.repository.ConstituencyRepository;
import com.deltacode.kcb.repository.WardRepository;
import com.deltacode.kcb.service.WardService;
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
        log.info("Creating ward");
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
    public WardResponse getAllWards(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.info("Getting all wards");
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<Ward> ward=wardRepository.findAll(pageable);
        //get content for page object
        List<Ward> WardList=ward.getContent();
        List<WardDto> content=WardList.stream().map(this::mapToDto).toList();
        WardResponse wardResponse=new WardResponse();
        wardResponse.setContent(content);
        wardResponse.setPageNo(ward.getNumber());
        wardResponse.setPageSize(ward.getSize());
        wardResponse.setTotalElements((int) ward.getTotalElements());
        wardResponse.setTotalPages(ward.getTotalPages());
        wardResponse.setLast(ward.isLast());
        return wardResponse;
    }

    @Override
    public List<WardDto> getWardByConstituencyId(long constituencyId) {
        log.info("Getting wards by constituency id {}", constituencyId);
        // retrieve ward by constituencyId
        List<Ward> wards = wardRepository.findByConstituencyId(constituencyId);
        // convert list of ward entities to list of ward dto's
        return wards.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public WardDto getWardById(Long constituencyId, Long wardId) {
        log.info("Getting ward by id {}", wardId);
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
        log.info("Updating ward by id {}", wardId);
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
        log.info("Deleting ward by id {}", wardId);
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
