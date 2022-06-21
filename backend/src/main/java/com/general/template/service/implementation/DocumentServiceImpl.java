package com.general.template.service.implementation;

import com.general.template.domain.Document;
import com.general.template.repository.DocumentRepository;
import com.general.template.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentRepository documentRepository;

    @Override
    public Document addDocument(Document document) {
        Document d =findByUrl(document.getUrl());
        if(d==null) {
            return documentRepository.save(document);
        }
        return d;
    }

    @Override
    public void deleteDocument(Document document) {
        documentRepository.delete(document);
    }

    @Override
    public Document findById(Long id) {
        return documentRepository.getFirstById(id);
    }

    @Override
    public Document findByUrl(String url) {
        return documentRepository.findByUrl(url);
    }

    @Override
    public Document updateDocument(Document document) {
        return documentRepository.save(document);
    }
}
