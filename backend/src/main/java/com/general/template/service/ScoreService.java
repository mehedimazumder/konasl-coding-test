package com.general.template.service;

import com.general.template.domain.Score;
import com.general.template.domain.dto.ScoreDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScoreService {
    List<ScoreDTO> getLatestScores();

    Page<Score> search(String searchParam, int page, int size, String sortBy);
}
