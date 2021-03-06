package io.spring.infrastructure.mybatis.readservice;

import io.spring.application.data.CommentData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @Author：zhangchengxi
 * @Date：2018/9/6 19:48
 */
@Component
@Mapper
public interface CommentReadService {
    CommentData findById(@Param("id") String id);

    List<CommentData> findByArticleId(@Param("articleId") String articleId);
}
