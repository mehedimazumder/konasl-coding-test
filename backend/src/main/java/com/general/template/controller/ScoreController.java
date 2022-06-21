package com.general.template.controller;

import com.general.template.common.response.ResponseHandler;
import com.general.template.domain.Score;
import com.general.template.domain.dto.ScoreDTO;
import com.general.template.service.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scores")
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ScoreController {
    private final ScoreService scoreService;

    @GetMapping("/latest")
    public ResponseEntity<Object> latestInfo() throws Exception {
        List<ScoreDTO> scores = scoreService.getLatestScores();
        return ResponseHandler.generateResponse("Latest scores by date", HttpStatus.OK, scores);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchInfo(@RequestParam String keyword,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "1") int size,
                                             @RequestParam(defaultValue = "title") String sortBy) throws Exception {
        Page<Score> scores = scoreService.search(keyword, page, size, sortBy);
        return ResponseHandler.generateResponse("Search by keyword", HttpStatus.OK, scores);
    }

}
