package com.general.template.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScoreDTO {
    private String title;

    private String link;

    private String description;

    private String guid;
}
