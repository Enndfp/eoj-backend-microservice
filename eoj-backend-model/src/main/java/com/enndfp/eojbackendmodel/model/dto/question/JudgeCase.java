package com.enndfp.eojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * 判题用例
 *
 * @author Enndfp
 */
@Data
public class JudgeCase {

    /**
     * 输入用例
     */
    private String input;

    /**
     * 输出用例
     */
    private String output;
}
