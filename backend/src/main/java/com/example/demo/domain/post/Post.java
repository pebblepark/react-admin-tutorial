package com.example.demo.domain.post;

import com.example.demo.domain.common.AbstractAuditEntity;
import com.example.demo.domain.file.File;
import com.example.demo.domain.post.tag.PostTag;
import com.example.demo.domain.tag.Tag;
import com.example.demo.domain.user.User;
import com.example.demo.dto.common.FileDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post  extends AbstractAuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Lob
    private String body;

    @Builder.Default
    @ColumnDefault("0")
    private Integer views = 0;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id")
    private File file;

    @OneToMany(mappedBy = "post")
    private List<PostTag> tags = new ArrayList<>();

    public void addFile(File file) {
        this.file = file;
    }

    public void addTags(List<Tag> tags) {
        List<PostTag> postTags = new ArrayList<>();
        for (Tag tag : tags) {
            PostTag postTag = PostTag.builder()
                    .post(this)
                    .tag(tag)
                    .build();
            postTags.add(postTag);
        }
        this.tags = postTags;
    }

    public void patch(Post newPost) {
        if(newPost == null) return;
        if(newPost.getUser() != null) {
            this.user.patch(newPost.getUser());
        }
        if(newPost.getTitle() != null) {
            this.title = newPost.getTitle();
        }
        if(newPost.getBody() != null) {
            this.body = newPost.getBody();
        }
        if(newPost.getViews() != null) {
            this.views = newPost.getViews();
        }
        if(newPost.getFile() != null) {
            this.file = newPost.getFile();
        }
        if(newPost.getTags() != null) {
            this.tags = newPost.getTags();
        }
    }
}
