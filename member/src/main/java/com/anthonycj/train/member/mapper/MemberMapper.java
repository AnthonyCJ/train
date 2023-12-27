package com.anthonycj.train.member.mapper;

import com.anthonycj.train.member.domain.Member;
import com.anthonycj.train.member.domain.MemberExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    /**
     * 根据某个条件统计数量
     * @param example 某个条件
     * @return 数量
     */
    long countByExample(MemberExample example);

    /**
     * 根据某个条件删除
     * @param example 某个条件
     * @return 数量
     */
    int deleteByExample(MemberExample example);

    /**
     * 根据主键删除
     * @param id 主键
     * @return 数量
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据
     * @param record 插入的数据
     * @return 影响的行数
     */
    int insert(Member record);

    /**
     * 插入一行数据（仅将Member有值的属性生成sql语句）
     * @param record 插入的数据
     * @return 影响行数
     */
    int insertSelective(Member record);

    /**
     * 根据某个条件查询
     * @param example 查询条件
     * @return 符合条件的查询记录列表
     */
    List<Member> selectByExample(MemberExample example);

    /**
     * 根据主键查找
     * @param id 主键id
     * @return 对应记录
     */
    Member selectByPrimaryKey(Long id);

    /**
     * 根据某个条件更新
     * @param record 将record存储数据更新至数据库中，如果有属性为null，则不更新null对应的字段
     * @param example 查询条件
     * @return 影响行数
     */
    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    /**
     * 根据某个条件更新
     * @param record 将record存储数据更新至数据库中，包括值为null的属性
     * @param example 查询条件
     * @return 影响行数
     */
    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    /**
     * 根据主键更新（更新有值的字段）
     * @param record 更新record有值部分对应的字段
     * @return 更新行数
     */
    int updateByPrimaryKeySelective(Member record);

    /**
     * 根据主键更新（更新全部字段）
     * @param record 更新的数据
     * @return 更新行数
     */
    int updateByPrimaryKey(Member record);
}