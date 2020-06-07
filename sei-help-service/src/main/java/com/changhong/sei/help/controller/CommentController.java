package com.changhong.sei.help.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.context.SessionUser;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.log.LogUtil;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.help.api.CommentApi;
import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.help.dto.CommentLikeDto;
import com.changhong.sei.help.dto.NotificationAction;
import com.changhong.sei.help.dto.NotificationDto;
import com.changhong.sei.help.entity.Comment;
import com.changhong.sei.help.entity.CommentLike;
import com.changhong.sei.help.entity.Notification;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.help.service.CommentLikeService;
import com.changhong.sei.help.service.CommentService;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.help.service.NotificationService;
import com.changhong.sei.help.service.TopicService;
import com.changhong.sei.help.service.client.EDMDocumentManager;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
    private TopicService topicService;
    @Autowired
    private CommentLikeService commentLikeService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private EDMDocumentManager documentManager;

    @Override
    public BaseEntityService<Comment> getService() {
        return commentService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<CommentDto> save(CommentDto dto) {
        String topicId = dto.getTopic().getId();
        String content = dto.getContent();
        String parentId = dto.getParentId();
        Set<String> docIds = dto.getDocIds();
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        //更新
        if (StringUtils.isNotEmpty(dto.getId())){
            Comment savedComment = commentService.findOne(dto.getId());
            if (Objects.isNull(savedComment)) {
                return ResultData.fail("00004");
            }
            if (Objects.equals(userId, savedComment.getCreatorId())){
                return ResultData.fail("不能修改别人的评论");
            }
            //删除以前的文档
            if (CollectionUtils.isEmpty(docIds)) {
                ResultData<String> documentResult =documentManager.deleteBusinessInfos(dto.getId());
                if (!documentResult.successful()){
                    return ResultData.fail(documentResult.getMessage());
                }
            }
        }
        Topic topic = topicService.findOne(topicId);
        if (Objects.isNull(topic)) {
            return ResultData.fail("00005");
        }
        // 将内容里的 \n 转成 <br/>
        dto.setContent(content.replaceAll("\\n", "<br/>"));
        // 如果回复的评论id不为空，判断一下是否存在这个评论
        if (StringUtils.isNotBlank(parentId)) {
            Comment parentComment = commentService.findOne(parentId);
            if (Objects.isNull(parentComment)) {
                return ResultData.fail("00007");
            }
            //判断是否回复自己的评论
            if (!parentComment.getCreatorId().equals(userId)){
                String targetUserId = parentComment.getCreatorId();
                NotificationAction action = NotificationAction.REPLY;
                // 创建通知 给回复对象的作者发通知
                notificationService.create(topicId, userId, targetUserId, action);
            }
            //判断是否发通知给作者
            if (!topic.getCreatorId().equals(parentComment.getCreatorId())) {
                String targetUserId = topic.getCreatorId();
                NotificationAction action = NotificationAction.COMMENT;
                // 创建通知 给话题作者发通知
                notificationService.create(topicId, userId, targetUserId, action);
            }
        }else {
            if (!topic.getCreatorId().equals(userId)) {
                String targetUserId = topic.getCreatorId();
                NotificationAction action = NotificationAction.COMMENT;
                // 创建通知 给话题作者发通知
                notificationService.create(topicId, userId, targetUserId, action);
            }
        }
        //设置前端显示名称
        dto.setUserInfo(user.getUserName() + "[" + user.getAccount() + "]");
        dto.setDocCount(Objects.nonNull(docIds) ? docIds.size() : 0);
        // 更新话题的评论数
        topic.setCommentCount(topic.getCommentCount() + 1);
        //更新最后评论冗余信息
        topic.setLastCommentTime(new Date());
        if (dto.getAnonymous()){
            topic.setLastCommentUserInfo("匿名");
        }else {
            topic.setLastCommentUserInfo(user.getUserName() + "[" + user.getAccount() + "]");
        }
        OperateResultWithData<Topic> topicResult = topicService.save(topic);
        if (!topicResult.successful()){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultData.fail(topicResult.getMessage());
        }
        OperateResultWithData<Comment> commentResult = commentService.save(convertToEntity(dto));
        if (!topicResult.successful()){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultData.fail(commentResult.getMessage());
        }
        if (!CollectionUtils.isEmpty(docIds)) {
            ResultData<String> documentResult =documentManager.bindBusinessDocuments(commentResult.getData().getId(), docIds);
            if (!documentResult.successful()){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultData.fail(commentResult.getMessage());
            }
        }

        // 把保存成功的评论返回给前端
        return ResultData.success(dto);
    }

    /**
     * 点赞评论
     *
     * @param commentLikeDto 点赞信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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