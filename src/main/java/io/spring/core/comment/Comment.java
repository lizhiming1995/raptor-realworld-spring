package io.spring.core.comment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.UUID;
/**
 * @Author：zhangchengxi
 * @Date：2018/9/6 19:48
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comment {
    private String id;
    private String body;
    private String userId;
    private String articleId;
    private DateTime createdAt;

    public Comment(String body, String userId, String articleId) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
        this.userId = userId;
        this.articleId = articleId;
        this.createdAt = new DateTime();
    }
}
