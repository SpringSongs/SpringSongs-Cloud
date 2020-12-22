package io.github.springsongs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.github.springsongs.dto.UriRoleCode;

public interface SpringResourceMapper {
   
	List<UriRoleCode> listAllRoleModules(@Param("roleCodes")List<String> roleCodes);

}