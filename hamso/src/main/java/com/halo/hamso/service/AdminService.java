package com.halo.hamso.service;


import com.halo.hamso.dto.PageInfo;
import com.halo.hamso.dto.admin.ImagePageResDto;
import com.halo.hamso.repository.image.Image;
import com.halo.hamso.repository.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ImageRepository imageRepository;

    public ImagePageResDto getBillImages(int page, int size){
        Page<Image> images = imageRepository.findAll(PageRequest.of(page,size));

        PageInfo pageInfo= PageInfo.builder()
                .page(page)
                .pageSize(size)
                .totalPages(images.getTotalPages())
                .totalNumber(images.getTotalElements())
                .build();

        List<Image> imageDto = images.getContent().stream().collect(Collectors.toList());


        return ImagePageResDto.builder()
                .pageInfo(pageInfo)
                .images(imageDto)
                .build();

    }


}
