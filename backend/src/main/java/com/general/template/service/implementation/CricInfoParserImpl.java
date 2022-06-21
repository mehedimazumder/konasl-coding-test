package com.general.template.service.implementation;

import com.general.template.common.Constants;
import com.general.template.domain.Score;
import com.general.template.repository.ScoreRepository;
import com.general.template.service.CricInfoParser;
import com.general.template.service.mapping.ScoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class CricInfoParserImpl implements CricInfoParser {
    @Autowired
    private RestTemplate restTemplate;

    private final ScoreRepository scoreRepository;
    private final ScoreMapper scoreMapper;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void parse() throws ParserConfigurationException, IOException, SAXException {
        Optional<String> res = Optional.ofNullable(restTemplate.getForObject(Constants.LIVE_URL, String.class));
//        System.out.println(res);

        if(res.isPresent()){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(new StringReader(res.get())));

            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("item");

            List<Score> scoreList = scoreMapper.map(nList);

            save(scoreList);
        }
    }

    private void save(List<Score> scores){
        Predicate<Score> alreadyExist  = s -> scoreRepository.findFirstByTitle(s.getTitle()) != null;

        scores.forEach(s -> {
            if(!alreadyExist.test(s)){
                scoreRepository.save(s);
            }
        });
    }

}
