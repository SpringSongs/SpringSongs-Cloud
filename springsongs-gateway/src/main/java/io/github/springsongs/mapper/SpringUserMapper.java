package io.github.springsongs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.github.springsongs.domain.SpringUser;

@Mapper
public interface SpringUserMapper {
	SpringUser getByUserName(@Param("userName") String userName);
}