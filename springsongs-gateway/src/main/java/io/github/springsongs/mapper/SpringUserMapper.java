package io.github.springsongs.mapper;

import org.apache.ibatis.annotations.Param;

import io.github.springsongs.domain.SpringUser;

public interface SpringUserMapper {
	SpringUser getByUserName(@Param("userName") String userName);
}