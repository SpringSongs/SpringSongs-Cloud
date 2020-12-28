package io.github.springsongs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import io.github.springsongs.domain.SpringRole;

@Mapper
public interface SpringRoleMapper {
	List<SpringRole> listRoleByUserId(@Param("userId") String userId);
}