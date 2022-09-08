package com.deltacode.kcb.service.impl;

import com.deltacode.kcb.entity.Team;
import com.deltacode.kcb.entity.Ward;
import com.deltacode.kcb.payload.WardDto;
import com.deltacode.kcb.payload.WardResponse;
import com.deltacode.kcb.repository.WardRepository;
import com.deltacode.kcb.service.WardService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class WardServiceImpl implements WardService {
    private final WardRepository wardRepository;
    private  final ModelMapper modelMapper;

    public WardServiceImpl(WardRepository wardRepository, ModelMapper modelMapper) {
        this.wardRepository = wardRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WardDto createWard(WardDto wardDto) {
        Ward ward = modelMapper.map(wardDto, Ward.class);
        wardRepository.save(ward);
        return modelMapper.map(ward, WardDto.class);
    }

    @Override
    public WardResponse getAllWards(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        //create a pageable instance
        Page<Ward> ward = wardRepository.findAll(pageable);
        //get content for page object
        List<Ward> listOfWards = ward.getContent();
        List<WardDto> content = listOfWards.stream().map(this::mapToDto).toList();
        WardResponse wardResponse = new WardResponse();
        wardResponse.setContent(content);
        wardResponse.setPageNo(ward.getNumber());
        wardResponse.setPageSize(ward.getSize());
        wardResponse.setTotalElements(ward.getNumberOfElements());
        wardResponse.setTotalPages(ward.getTotalPages());
        wardResponse.setLast(ward.isLast());
        return wardResponse;
    }

    @Override
    public WardDto getWardById(Long id) {
        Ward ward = wardRepository.findById(id).orElseThrow(() -> new RuntimeException("Ward not found"));
        return modelMapper.map(ward, WardDto.class);
    }

    @Override
    public WardDto updateWard(WardDto wardDto, Long id) {
        Ward ward = wardRepository.findById(id).orElseThrow(() -> new RuntimeException("Ward not found"));
        ward.setWardName(wardDto.getWardName());
        ward.setWardCode(wardDto.getWardCode());
        ward.setDescription(wardDto.getDescription());
        wardRepository.save(ward);
        return modelMapper.map(ward, WardDto.class);
    }

    @Override
    public void deleteWardById(Long id) {
        //check if ward exists
        wardRepository.findById(id).orElseThrow(() -> new RuntimeException("Ward not found"));
        wardRepository.deleteById(id);

    }
    //convert entity to dto
    private WardDto mapToDto(Ward team) {

        return modelMapper.map(team, WardDto .class);

    }
    //convert dto to entity
    private Ward mapToEntity(WardDto wardDto) {

        return modelMapper.map(wardDto, Ward.class);

    }

}
