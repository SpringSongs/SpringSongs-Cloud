package io.github.springsongs.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.github.springsongs.domain.SpringUserSecurity;

@Mapper
public interface SpringUserSecurityMapper {
	SpringUserSecurity findByUserId(@Param("userId") String userId);
}