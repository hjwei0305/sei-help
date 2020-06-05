package com.changhong.sei.help.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.context.SessionUser;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.help.api.CommentApi;
import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.help.dto.CommentLikeDto;
import com.changhong.sei.help.entity.Comment;
import com.changhong.sei.help.entity.CommentLike;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.help.service.CommentLikeService;
import com.changhong.sei.help.service.CommentService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

import java.util.Date;
import java.util.Objects;

/**
 * 评论(Comment)控制类
 *
 * @author sei
 * @since 2020-06-04 10:09:46
 */
@RestController
@Api(value = "CommentApi", tags = "评论服务")
@RequestMapping(path = "comment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CommentController extends BaseEntityController<Comment, CommentDto> implements CommentApi {
    /**
     * 评论服务对象
     */
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentLikeService commentLikeService;

    @Override
    public BaseEntityService<Comment> getService() {
        return commentService;
    }

    /**
     * 点赞评论
     *
     * @param commentLikeDto 点赞信息
     * @return 结果
     */
    @Override
    @Transactional
    public ResultData<String> like(CommentLikeDto commentLikeDto) {
        String commentId = commentLikeDto.getCommentId();
        Comment comment = commentService.findOne(commentId);
        if (Objects.isNull(comment)) {
            return ResultData.fail("00004");
        }
        Topic topic = comment.getTopic();
        if (Objects.isNull(topic)) {
            return ResultData.fail("00005");
        }
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        CommentLike commentLike = new CommentLike();
        commentLike.setComment(comment);
        commentLike.setUserId(userId);
        commentLike.setUserInfo(user.getUserName() + "[" + user.getAccount() + "]");
        commentLike.setAnonymous(topic.getAnonymous());
        commentLike.setInTime(new Date());
        commentLikeService.save(commentLike);
        // 更新话题的评论数
        comment.setLikeCount(comment.getLikeCount() == null ? 1 : comment.getLikeCount() + 1);
        commentService.save(comment);
        return ResultData.success("00001");
    }

    /**
     * 取消点赞评论
     *
     * @param commentLikeDto 点赞信息
     * @return 结果
     */
    @Override
    @Transactional
    public ResultData<String> dislike(CommentLikeDto commentLikeDto) {
        String commentId = commentLikeDto.getCommentId();
        Comment comment = commentService.findOne(commentId);
        if (Objects.isNull(comment)) {
            return ResultData.fail("00004");
        }
        Topic topic = comment.getTopic();
        if (Objects.isNull(topic)) {
            return ResultData.fail("00005");
        }
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        CommentLike dbCommentLike = commentLikeService.findByUserIdAndCommentId(userId,commentId);
        if (Objects.isNull(dbCommentLike)) {
            return ResultData.fail("00006");
        }
        commentLikeService.delete(dbCommentLike.getId());
        // 更新话题的评论数
        comment.setLikeCount(comment.getLikeCount() == null ? 0 : comment.getLikeCount() - 1);
        commentService.save(comment);
        return ResultData.success("00001");
    }

    /**
     * 加精/取消加精
     *
     * @param commentId 评论id
     * @return
     */
    @Override
    public ResultData<String> good(String commentId) {
        Comment comment = commentService.findOne(commentId);
        if (Objects.isNull(comment)) {
            return ResultData.fail("00004");
        }
        if (Objects.isNull(comment.getGood())){
            comment.setGood(Boolean.TRUE);
        }else {
            comment.setGood(!comment.getGood());
        }

        commentService.save(comment);
        return ResultData.success("00001");
    }
}