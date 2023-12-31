package com.anthonycj.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.anthonycj.train.common.exception.BusinessException;
import com.anthonycj.train.common.exception.BusinessExceptionEnum;
import com.anthonycj.train.common.util.JwtUtil;
import com.anthonycj.train.common.util.SnowUtil;
import com.anthonycj.train.member.domain.Member;
import com.anthonycj.train.member.domain.MemberExample;
import com.anthonycj.train.member.mapper.MemberMapper;
import com.anthonycj.train.member.req.MemberLoginReq;
import com.anthonycj.train.member.req.MemberRegisterReq;
import com.anthonycj.train.member.req.MemberSendCodeReq;
import com.anthonycj.train.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    /**
     * 会员注册
     * @param req 封装请求数据对象
     * @return train_member数据库member表对应id
     */
    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        // 插入前先确认该电话号是否已经注册
        Member memberDB = selectByMobile(mobile);

        // 如果list非空，说明手机号已被注册，抛异常中断业务
        if (ObjectUtil.isNull(memberDB)) {
            // return list.get(0).getId();
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        // id生成使用hutool封装的雪花算法
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);

        // 如果手机号不存在，则插入一条记录
        if (ObjectUtil.isNull(memberDB)) {
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号存在，不插入记录");
        }

        // 生成验证码
        // String code = RandomUtil.randomString(4);
        String code = "8888";
        LOG.info("生成短信验证码：{}", code);

        // 保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        // 此处用日志信息代替
        LOG.info("保存短信记录表");

        // 对接短信通道，发送短信
        // 此处用日志信息代替
        LOG.info("对接短信通道");
    }

    /**
     * 用户登录
     * @param req 请求参数
     * @return 用户登录返回参数类
     */
    public MemberLoginResp login(MemberLoginReq req) {
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectByMobile(mobile);

        // 如果手机号不存在，则插入一条记录
        if (ObjectUtil.isNull(memberDB)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        // 校验短信验证码
        if (!"8888".equals(code)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        // 生成token
        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);
        return memberLoginResp;
    }

    /**
     * 封装使用手机号查询用户的操作
     * @param mobile 手机号
     * @return 用户存在返回member，不存在返回null
     */
    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }
}
