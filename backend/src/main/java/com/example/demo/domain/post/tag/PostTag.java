package com.example.demo.domain.post.tag;


import com.example.demo.domain.post.Post;
import com.example.demo.domain.tag.Tag;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(
        name = "POST_TAG_UNIQUE",
        columnNames = {"post_id", "tag_id"} )})
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_tag_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public PostTag(Long postId, Long tagId) {
        this.post = Post.builder().id(postId).build();
        this.tag = Tag.builder().id(tagId).build();
    }
}
