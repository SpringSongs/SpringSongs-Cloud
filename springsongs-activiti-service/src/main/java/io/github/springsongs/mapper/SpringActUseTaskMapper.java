package io.github.springsongs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.github.springsongs.domain.SpringActUseTask;

@Mapper
public interface SpringActUseTaskMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_use_task
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_use_task
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    int insert(SpringActUseTask record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_use_task
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    SpringActUseTask selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_use_task
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    List<SpringActUseTask> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_use_task
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    int updateByPrimaryKey(SpringActUseTask record);

	List<SpringActUseTask> listUserTaskByProcDefKey(@Param("procDefKey") String procDefKey);

	void delete(@Param("procDefKey") String procDefKey);

	void deleteAll();

	SpringActUseTask findUserTaskByTaskDefKey(@Param("key") String key);
}