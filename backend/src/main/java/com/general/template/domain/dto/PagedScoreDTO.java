package com.general.template.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PagedScoreDTO {
    private ScoreDTO content;
    private Integer totalPages;
    private Integer pageSize;
    private Integer pageNumber;
}
