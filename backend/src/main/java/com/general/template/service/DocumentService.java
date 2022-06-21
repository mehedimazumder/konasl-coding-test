package com.general.template.service;

import com.general.template.domain.Document;

public interface DocumentService {
    Document addDocument(Document document);

    void deleteDocument(Document document);

    Document findById(Long id);

    Document findByUrl(String url);

    Document updateDocument(Document document);
}
