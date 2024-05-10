package com.nhnacademy.batch.tag.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(name = "tag_name", unique = true)
    private String tagName;
}