package com.example.demo.domain.post.tag;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.tag.Tag;
import com.example.demo.dto.PostDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    @Query("select p.tag from PostTag p where p.post=:post")
    List<Tag> findByPost(Post post);

    @Modifying
    @Query("delete from PostTag p where p.post=:post")
    void deleteByPost(Post post);

    @Query("select p.post from PostTag p where p.tag=:tag")
    List<Post> findPostByTag(Tag tag);
}
