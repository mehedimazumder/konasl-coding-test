package com.general.template.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "scores", indexes = @Index(columnList = "created_at desc"))
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Score extends BaseEntity {
    @Column(unique = true)
    private String title;

    private String link;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String guid;
}
