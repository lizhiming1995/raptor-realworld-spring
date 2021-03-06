package io.spring.application;

import io.spring.application.data.CommentData;
import io.spring.infrastructure.mybatis.readservice.UserRelationshipQueryService;
import io.spring.core.user.User;
import io.spring.infrastructure.mybatis.readservice.CommentReadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * @Author：zhangchengxi
 * @Date：2018/9/6 19:48
 */
@Service
public class CommentQueryService {
    private CommentReadService commentReadService;
    private UserRelationshipQueryService userRelationshipQueryService;

    public CommentQueryService(CommentReadService commentReadService, UserRelationshipQueryService userRelationshipQueryService) {
        this.commentReadService = commentReadService;
        this.userRelationshipQueryService = userRelationshipQueryService;
    }

    public Optional<CommentData> findById(String id, User user) {
        CommentData commentData = commentReadService.findById(id);
        if (commentData == null) {
            return Optional.empty();
        } else {
            commentData.getAuthor().setFollowing(
                userRelationshipQueryService.isUserFollowing(
                    user.getId(),
                    commentData.getAuthor().getId()));
        }
        return Optional.ofNullable(commentData);
    }

    public List<CommentData> findByArticleId(String articleId, User user) {
        List<CommentData> comments = commentReadService.findByArticleId(articleId);
        if (comments.size() > 0 && user != null) {
            Set<String> followingAuthors = userRelationshipQueryService.followingAuthors(user.getId(), comments.stream().map(commentData -> commentData.getAuthor().getId()).collect(Collectors.toList()));
            comments.forEach(commentData -> {
                if (followingAuthors.contains(commentData.getAuthor().getId())) {
                    commentData.getAuthor().setFollowing(true);
                }
            });
        }
        return comments;
    }
}
