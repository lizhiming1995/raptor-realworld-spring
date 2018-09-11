package io.spring.core.comment;

import java.util.Optional;
/**
 * @Author：zhangchengxi
 * @Date：2018/9/6 19:48
 */
public interface  CommentRepository {
    void save(Comment comment);

    Optional<Comment> findById(String articleId, String id);

    void remove(Comment comment);
}
