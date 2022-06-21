package com.general.template.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class ScheduledTask {
    private final CricInfoParser cricInfoParser;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void getLatestCricInfo() throws IOException, SAXException, ParserConfigurationException {
        cricInfoParser.parse();
    }
}
