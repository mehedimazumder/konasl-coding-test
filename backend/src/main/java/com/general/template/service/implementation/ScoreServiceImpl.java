package com.general.template.service.implementation;

import com.general.template.domain.Score;
import com.general.template.domain.dto.ScoreDTO;
import com.general.template.repository.ScoreRepository;
import com.general.template.service.ScoreService;
import com.general.template.service.mapping.ScoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ScoreServiceImpl implements ScoreService {
    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreMapper;

    @Override
    public List<ScoreDTO> getLatestScores() {
        List<Score> scores = scoreRepository.findLatestScores();
        return scoreMapper.mapResponse(scores);
    }

    @Override
    public Page<Score> search(String searchParam, int page, int size, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sortBy));
        return scoreRepository.findAllByTitleContainingOrDescriptionContaining(searchParam, searchParam, paging);
    }
}
