package com.general.template.service.mapping;

import com.general.template.domain.Score;
import com.general.template.domain.dto.ScoreDTO;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreMapper {

    public List<Score> map(NodeList nList){
        List<Score> scores = new ArrayList<>();

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                Score score = new Score();
                score.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                score.setLink(eElement.getElementsByTagName("link").item(0).getTextContent());
                score.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                score.setGuid(eElement.getElementsByTagName("guid").item(0).getTextContent());

                scores.add(score);
            }
        }
        return scores;
    }

    public List<ScoreDTO> mapResponse(List<Score> scores){
        return scores.stream().map(s -> ScoreDTO.builder().title(s.getTitle())
                                                                .description(s.getDescription())
                                                                .link(s.getLink())
                                                                .guid(s.getGuid()).build())
                                                        .collect(Collectors.toList());

    }
}
