package com.general.template.repository;

import com.general.template.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document findById (long id);

    Document findByUrl(String url);

    Document getFirstById(long id);
}
