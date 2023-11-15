package com.halo.hamso.dto.admin;

import com.halo.hamso.dto.PageInfo;
import com.halo.hamso.repository.image.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagePageResDto {
    private PageInfo pageInfo;
    private List<Image> images;
}
