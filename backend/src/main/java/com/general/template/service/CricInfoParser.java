package com.general.template.service;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface CricInfoParser {
    void parse() throws ParserConfigurationException, IOException, SAXException;
}
