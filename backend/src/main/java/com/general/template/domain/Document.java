package com.general.template.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "documents")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Document extends BaseEntity{
    public Document(String url) {
        this.url = url;
    }

    @Column(columnDefinition="LONGTEXT")
    private String url;
    private String documentType;
}
