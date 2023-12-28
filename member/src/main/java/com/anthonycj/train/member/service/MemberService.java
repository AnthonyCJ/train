package com.anthonycj.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.anthonycj.train.member.domain.Member;
import com.anthonycj.train.member.domain.MemberExample;
import com.anthonycj.train.member.mapper.MemberMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    /**
     * 会员注册
     * @param mobile 电话号码
     * @return train_member数据库member表对应id
     */
    public long register(String mobile) {
        // 插入前先确认该电话号是否已经注册
        MemberExample memberExample = new MemberExample();
        // 创建条件 'where mobile =='
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);

        // 如果list非空，说明手机号已被注册，抛异常中断业务
        if (CollUtil.isNotEmpty(list)) {
            // return list.get(0).getId();
            throw new RuntimeException("手机号已注册");
        }

        Member member = new Member();
        // id先暂时使用系统的时间戳
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
