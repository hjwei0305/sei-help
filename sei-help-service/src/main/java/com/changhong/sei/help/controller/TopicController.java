package com.changhong.sei.help.controller;

import com.changhong.sei.core.context.ContextUtil;
import com.changhong.sei.core.context.SessionUser;
import com.changhong.sei.core.controller.BaseEntityController;
import com.changhong.sei.core.dto.ResultData;
import com.changhong.sei.core.dto.serach.PageResult;
import com.changhong.sei.core.dto.serach.Search;
import com.changhong.sei.core.dto.serach.SearchFilter;
import com.changhong.sei.core.dto.serach.SearchOrder;
import com.changhong.sei.core.service.BaseEntityService;
import com.changhong.sei.core.service.bo.OperateResultWithData;
import com.changhong.sei.help.api.TopicApi;
import com.changhong.sei.help.dto.CommentDto;
import com.changhong.sei.help.dto.TopicDto;
import com.changhong.sei.help.entity.Category;
import com.changhong.sei.help.entity.Comment;
import com.changhong.sei.help.entity.CommentLike;
import com.changhong.sei.help.entity.Topic;
import com.changhong.sei.help.service.*;
import com.changhong.sei.help.service.client.EDMDocumentManager;
import com.changhong.sei.help.utils.MDUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 话题(Topic)控制类
 *
 * @author sei
 * @since 2020-06-04 10:07:38
 */
@RestController
@Api(value = "TopicApi", tags = "话题服务")
@RequestMapping(path = "topic", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TopicController extends BaseEntityController<Topic, TopicDto> implements TopicApi {

    public static final String URL_REGEX = "^((https|http)?:\\/\\/)[^\\s]+";

    /**
     * 话题服务对象
     */
    @Autowired
    private TopicService topicService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentLikeService commentLikeService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private EDMDocumentManager documentManager;

    @Override
    public BaseEntityService<Topic> getService() {
        return topicService;
    }

    @Override
    @Transactional
    public ResultData<TopicDto> save(TopicDto topic) {
        if (Objects.isNull(topic)) {
            return ResultData.fail("参数为空, 创建话题失败");
        }

        SessionUser user = ContextUtil.getSessionUser();
        if (StringUtils.isNotEmpty(topic.getId())) {
            //查询数据库存在的记录
            Topic savedTopic = topicService.findOne(topic.getId());
            if (Objects.isNull(savedTopic)) {
                return ResultData.fail("00005");
            }
            if (!Objects.equals(user.getUserId(), savedTopic.getCreatorId())) {
                return ResultData.fail("不能修改别人的话题");
            }
            //删除以前的文档
//            ResultData<String> documentResult =documentManager.deleteBusinessInfos(topic.getId());
//            if (!documentResult.successful()){
//                return ResultData.fail(documentResult.getMessage());
//            }
            //只修改标题，连接，内容,是否匿名,文档数
            savedTopic.setTitle(topic.getTitle());
            savedTopic.setUrl(topic.getUrl());
            savedTopic.setContent(topic.getContent());
            savedTopic.setTabId(topic.getTabId());
            savedTopic.setBizId(topic.getTabId());
            savedTopic.setStatisId(topic.getStatisId());
            savedTopic.setAnonymous(topic.getAnonymous());
            topic = convertToDto(savedTopic);
        }
        String title = topic.getTitle();
        Topic topic1 = topicService.findByTitle(title);
        if (Objects.nonNull(topic1) && !Objects.equals(topic1.getId(), topic.getId())) {
            return ResultData.fail("话题标题已经存在");
        }
        String url = topic.getUrl();
        if (!StringUtils.isEmpty(url)) {
            Topic topicUrl = topicService.findByUrl(url);
            if (Objects.nonNull(topicUrl) && !Objects.equals(topic1.getId(), topic.getId())) {
                return ResultData.fail("分享的链接已经存在");
            }
            if (!check(url, URL_REGEX)) {
                return ResultData.fail("分享的不是标准URL地址");
            }
            topic.setUrl(Jsoup.clean(url, Whitelist.none()));
        }
        topic.setUserInfo(user.getUserName() + "[" + user.getAccount() + "]");
        topic.setTitle(Jsoup.clean(title, Whitelist.none()));
        OperateResultWithData<Topic> result = topicService.save(convertToEntity(topic));
        if (!result.successful()) {
            return ResultData.fail(result.getMessage());
        }
        //返回id
        topic.setId(result.getData().getId());
        Set<String> docIds = topic.getDocIds();
        if (!CollectionUtils.isEmpty(docIds)) {
            ResultData<String> docResult = documentManager.bindBusinessDocuments(result.getData().getId(), docIds);
            if (!docResult.successful()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultData.fail(docResult.getMessage());
            }
        }
        return ResultData.success(topic);
    }

    @Override
    @Transactional
    public ResultData delete(String id) {
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        Topic topic = topicService.findOne(id);
        if (Objects.isNull(topic)) {
            return ResultData.fail("00005");
        }
        if (Objects.equals(userId, topic.getCreatorId())) {
            ResultData result = super.delete(id);
            if (!result.successful()) {
                return result;
            }
            //删除关联评论
            commentService.deleteByTopicId(id);
            //删除评论点赞
            commentLikeService.deleteByCommentTopicId(id);
            //删除关联收藏
            collectService.deleteByTopicId(id);
            //删除通知
            notificationService.deleteByTopicId(id);
//            ResultData<String> docResult = documentManager.deleteBusinessInfos(id);
//            if (!docResult.successful()) {
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                return ResultData.fail(docResult.getMessage());
//            }
            return ResultData.success("00008");
        } else {
            return ResultData.fail("不能删除别人的话题");
        }
    }

    /**
     * 获取话题明细
     *
     * @param id       话题id
     * @param mdrender 判断内容是否要被渲染成html内容
     * @return 话题明细
     */
    @Override
    @Transactional
    public ResultData<Map<String, Object>> detail(String id, Boolean mdrender) {
        if (StringUtils.isEmpty(id)) {
            return ResultData.fail("00005");
        }
        // 查询topic
        Topic topic = topicService.findOne(id);
        if (Objects.isNull(topic)) {
            return ResultData.fail("00005");
        }

        // 更新话题点击次数
        topic.setView(topic.getView() + 1);
        topicService.save(topic);

        TopicDto topicDto = convertToDto(topic);
        // 回填分类名称
        Map<String, Category> categoryMap = categoryService.getCategory(topic.getTabId(), topic.getBizId(), topic.getStatisId());
        Category category = categoryMap.get(topic.getTabId());
        topicDto.setTabName(Objects.isNull(category) ? "" : category.getName());
        category = categoryMap.get(topic.getBizId());
        topicDto.setBizName(Objects.isNull(category) ? "" : category.getName());
        category = categoryMap.get(topic.getStatisId());
        topicDto.setStatisName(Objects.isNull(category) ? "" : category.getName());

        // 根据mdrender判断内容是否要被渲染成html内容
        if (mdrender) {
            topic.setContent(Jsoup.clean(MDUtil.render(topic.getContent()), Whitelist.relaxed()));
        }

        Map<String, Object> map = new HashMap<>();

        // 判断用户是否登录过，登录过的话，查询出来这个话题是否被收藏过
        SessionUser user = ContextUtil.getSessionUser();
        String userId = user.getUserId();
        if (collectService.findByUserIdAndTopicId(userId, id) != null) {
            map.put("collect", true);
        } else {
            map.put("collect", false);
        }

        if (topic.getAnonymous()) {
            topic.setUserInfo("匿名");
        }

        // 查询话题的评论
//        Search commentSearch = new Search();
//        commentSearch.addFilter(new SearchFilter("topic.id",id));
//        commentSearch.addSortOrder(new SearchOrder(Comment.CREATED_DATE, SearchOrder.Direction.ASC));
//        PageResult<Comment> comments = commentService.findByPage(commentSearch);
        List<Comment> comments = commentService.findByTopicId(id);
        ModelMapper mapper = new ModelMapper();
        //转化为DTO
        List<CommentDto> commentDtos = comments.stream().map(p -> mapper.map(p, CommentDto.class)).collect(Collectors.toList());
//        PageResult<CommentDto> pageResult = new PageResult<>();
//        pageResult.setPage(comments.getPage());
//        pageResult.setRecords(comments.getRecords());
//        pageResult.setTotal(comments.getTotal());
//        pageResult.setRows(commentDtos);
        //查找 该话题下当前用户点赞记录
        List<CommentLike> commentLikes = commentLikeService.findByTopicIdAndUserId(id, userId);
        Set<String> likedCommentIds = new HashSet<>(0);
        if (Objects.nonNull(commentLikes) && !commentLikes.isEmpty()) {
            likedCommentIds = commentLikes.stream().map(CommentLike::getComment).map(Comment::getId).collect(Collectors.toSet());
        }
        //lambda表达式需要fina变量
        Set<String> finalLikedCommentIds = likedCommentIds;
        commentDtos.forEach(comment -> {
            //判断该评论是否已被当前用户点赞
            if (finalLikedCommentIds.contains(comment.getId())) {
                comment.setLiked(Boolean.TRUE);
            }
            if (comment.getParentId() != null) {
                // todo 递归
                Comment parentComment = commentService.findOne(comment.getParentId());
                if (Objects.isNull(parentComment)) {
                    parentComment = new Comment();
                    parentComment.setContent("该评论已经删除");
                    parentComment.setId(comment.getParentId());
                }
                comment.setParentComment(mapper.map(parentComment, CommentDto.class));
            }
        });

        map.put("topic", topicDto);
        map.put("comments", commentDtos);
        return ResultData.success(map);
    }

    /**
     * 加精/取消加精
     *
     * @param id 话题id
     * @return 结果
     */
    @Override
    public ResultData<String> good(String id) {
        Topic topic = topicService.findOne(id);
        if (Objects.isNull(topic)) {
            return ResultData.fail("您要加精/取消加精的话题不存在");
        }
        topic.setGood(!topic.getGood());
        topicService.save(topic);
        return ResultData.success("00001");
    }

    /**
     * 置顶/取消置顶
     *
     * @param id 话题id
     * @return 结果
     */
    @Override
    public ResultData<String> top(String id) {
        Topic topic = topicService.findOne(id);
        if (Objects.isNull(topic)) {
            return ResultData.fail("您要置顶/取消置顶的话题不存在");
        }
        topic.setTop(!topic.getTop());
        topicService.save(topic);
        return ResultData.success("00001");
    }

    /**
     * 获取热门评论话题
     *
     * @return 热门评论话题
     */
    @Override
    public ResultData<List<TopicDto>> hot() {
        //获取评论top10的一周内的话题
        return ResultData.success(convertToDtos(topicService.findHotCommentedTopic()));
    }


    private boolean check(String text, String regex) {
        if (org.springframework.util.StringUtils.isEmpty(text)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            return matcher.matches();
        }
    }

}