package io.github.springsongs.mapper;

import io.github.springsongs.domain.SpringAritlce;
import java.util.List;

public interface SpringAritlceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_aritlce
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_aritlce
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int insert(SpringAritlce record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_aritlce
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    SpringAritlce selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_aritlce
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    List<SpringAritlce> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_aritlce
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int updateByPrimaryKey(SpringAritlce record);
}