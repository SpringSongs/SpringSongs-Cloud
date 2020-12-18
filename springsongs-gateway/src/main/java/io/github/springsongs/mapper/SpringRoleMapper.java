package io.github.springsongs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.github.springsongs.domain.SpringRole;

public interface SpringRoleMapper {
	List<SpringRole> listRoleByUserId(@Param("userId") String userId);
}