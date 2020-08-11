package com.lsy.service_ucenter.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : Lo Shu-ngan
 * @Classname memberDto
 * @Description 用户提交过来的注册对象
 * @Date 2020/08/11 18:17
 */
@Data
public class MemberDto {
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}
