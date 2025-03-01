package com.enndfp.eojbackendmodel.model.vo;

import cn.hutool.json.JSONUtil;
import com.enndfp.eojbackendmodel.model.dto.question.JudgeCase;
import com.enndfp.eojbackendmodel.model.dto.question.JudgeConfig;
import com.enndfp.eojbackendmodel.model.entity.Question;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目视图（脱敏）
 *
 * @auther Enndfp
 */
@Data
public class QuestionVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 题目标签列表
     */
    private List<String> tags;

    /**
     * 题目答案
     */
    private String answer;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 题目难度（0 - 简单、1 - 中等、2 - 困难）
     */
    private Integer difficulty;

    /**
     * 判题用例
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题配置
     */
    private JudgeConfig judgeConfig;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建用户信息
     */
    private UserVO userVO;

    /**
     * 包装类转对象
     *
     * @param questionVO 题目包装类
     * @return 题目对象
     */
    public static Question voToObj(QuestionVO questionVO) {
        // 1. 判断包装类是否为空
        if (questionVO == null) {
            return null;
        }

        // 2. 将包装类转换为对象
        Question question = new Question();
        BeanUtils.copyProperties(questionVO, question);
        List<String> tagList = questionVO.getTags();
        if (tagList != null) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }
        List<JudgeCase> judgeCaseVO = questionVO.getJudgeCase();
        if (judgeCaseVO != null) {
            question.setJudgeCase(JSONUtil.toJsonStr(judgeCaseVO));
        }
        JudgeConfig judgeConfigVO = questionVO.getJudgeConfig();
        if (judgeConfigVO != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(judgeConfigVO));
        }

        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question 题目对象
     * @return 题目包装类
     */
    public static QuestionVO objToVo(Question question) {
        // 1. 判断对象是否为空
        if (question == null) {
            return null;
        }

        // 2. 将对象转换为包装类
        QuestionVO questionVO = new QuestionVO();
        BeanUtils.copyProperties(question, questionVO);
        List<String> tagList = JSONUtil.toList(question.getTags(), String.class);
        questionVO.setTags(tagList);
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCases = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        questionVO.setJudgeCase(judgeCases);
        String judgeConfigStr = question.getJudgeConfig();
        questionVO.setJudgeConfig(JSONUtil.toBean(judgeConfigStr, JudgeConfig.class));

        return questionVO;
    }

    private static final long serialVersionUID = 1L;
}