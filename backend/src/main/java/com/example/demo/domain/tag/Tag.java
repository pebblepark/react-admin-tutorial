package com.example.demo.domain.tag;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    private Long parentId;

    @Column(length = 1)
    @Builder.Default
    private String useYn = "Y";

    public void patch(Tag newTag) {
        if(newTag == null) return;

        if(newTag.getName() != null) {
            this.name = newTag.getName();
        }

        if(newTag.getParentId() != null) {
            this.parentId = newTag.getParentId();
        }

        if(newTag.getUseYn() != null) {
            this.useYn = newTag.getUseYn();
        }
    }
}
