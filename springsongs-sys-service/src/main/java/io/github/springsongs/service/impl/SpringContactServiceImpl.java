package io.github.springsongs.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.github.springsongs.domain.SpringContact;
import io.github.springsongs.dto.SpringContactDTO;
import io.github.springsongs.enumeration.ResultCode;
import io.github.springsongs.exception.SpringSongsException;
import io.github.springsongs.mapper.SpringContactMapper;
import io.github.springsongs.service.ISpringContactService;
import io.github.springsongs.utils.Constant;

@Service
public class SpringContactServiceImpl implements ISpringContactService {

	static Logger logger = LoggerFactory.getLogger(SpringContactServiceImpl.class);

	@Autowired
	private SpringContactMapper springContactMapper;

	/**
	 *
	 * 物理删除
	 * 
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			springContactMapper.deleteByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	/**
	 *
	 * 保存
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void insert(SpringContactDTO record) {
		record.setId(UUID.randomUUID().toString());
		SpringContact springContact = new SpringContact();
		BeanUtils.copyProperties(record, springContact);
		try {
			springContactMapper.insert(springContact);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	/**
	 *
	 * 获取单项
	 * 
	 * @param id
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public SpringContactDTO selectByPrimaryKey(String id) {
		SpringContact springContact = null;
		try {
			springContact = springContactMapper.selectByPrimaryKey(id);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
		SpringContactDTO springContactDTO = new SpringContactDTO();
		BeanUtils.copyProperties(springContact, springContactDTO);
		return springContactDTO;
	}

	/**
	 *
	 * 更新
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void updateByPrimaryKey(SpringContactDTO springContactDTO) {
		SpringContact entity = springContactMapper.selectByPrimaryKey(springContactDTO.getId());
		if (null == entity) {
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		} else {
			entity.setCompany(springContactDTO.getCompany());
			entity.setTitle(springContactDTO.getTitle());
			entity.setUsername(springContactDTO.getUsername());
			entity.setEmail(springContactDTO.getEmail());
			entity.setWeb(springContactDTO.getWeb());
			entity.setFax(springContactDTO.getFax());
			entity.setQq(springContactDTO.getQq());
			entity.setWebchat(springContactDTO.getWebchat());
			entity.setMobile(springContactDTO.getMobile());
			entity.setTel(springContactDTO.getTel());
			entity.setSortCode(springContactDTO.getSortCode());
			try {
				springContactMapper.updateByPrimaryKey(entity);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
			}
		}
	}

	/**
	 *
	 * 分页查询
	 * 
	 * @param record
	 * @return Page<BaseBusinessCardEntity>
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public PageInfo<SpringContactDTO> getAllRecordByPage(SpringContactDTO springContactQuery,int page,int size) {
		if (size>Constant.MAX_PAGE_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		}
		
		PageHelper.startPage(page, size);
		List<SpringContact> springContacts = springContactMapper.listByPage(springContactQuery);
		List<SpringContactDTO> springContactDTOs = new ArrayList<>();
		springContacts.stream().forEach(springContact -> {
			SpringContactDTO springContactDTO = new SpringContactDTO();
			BeanUtils.copyProperties(springContact, springContactDTO);
			springContactDTOs.add(springContactDTO);
		});
		PageInfo springContactsPageInfo = new PageInfo<>(springContacts);
		springContactsPageInfo.setList(springContactDTOs);
		return springContactsPageInfo;
	}

	/**
	 *
	 * 逻辑删除
	 * 
	 * @param record
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void setDeleted(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > Constant.MAX_ITEM_SIZE) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springContactMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.SYSTEM_ERROR);
		}
	}

	/**
	 *
	 * Excel批量保存
	 * 
	 * @param list
	 * @return R
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@Override
	public void batchSaveExcel(List<String[]> list) {

	}

	@Override
	public void delete(List<String> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			throw new SpringSongsException(ResultCode.PARAMETER_NOT_NULL_ERROR);
		} else if (ids.size() > 1000) {
			throw new SpringSongsException(ResultCode.PARAMETER_MORE_1000);
		}
		try {
			springContactMapper.setDelete(ids);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new SpringSongsException(ResultCode.INFO_NOT_FOUND);
		}
	}
}
