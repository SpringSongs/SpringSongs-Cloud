package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.Role;
import org.authority.mapper.BaseRoleMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BaseRoleControllerTest {
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseRoleMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		Role entity = new Role();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("ZnTXJtqlRHZeMUdqwXkIJsWLaydSolHPOTHk");
		entity.setDescription("ngLOuQPHvHjakasPetAQSvtXGERxxPHJSQRc");
		entity.setCreatedUserId("IDlIeOKZDHxntGsQVdKOzDnmBSpIPcLKYfXY");
		entity.setCreatedBy("sBuTsUYKNCZUpbVmsdefdQrBtiNnpHhcKdUL");
		entity.setCreatedIp("GxPtqwXgwYSkKnQDKsPdkzxQOWzbelSKPxyU");
		entity.setUpdatedUserId("rbjQkPZRknoRqLIyHKTdKlDMrhmVTtFpjsYW");
		entity.setUpdatedBy("JjzdvUbAeaxuUbYuudUhXkOtZqyKWDfFAoOi");
		entity.setUpdatedIp("EJmAtagplJlmCQcrdkrEVBLJWEwOahELoYnB");
		this.mvc.perform(post("/BaseRole/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("sBuTsUYKNCZUpbVmsdefdQrBtiNnpHhcKdUL")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("GxPtqwXgwYSkKnQDKsPdkzxQOWzbelSKPxyU")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("IDlIeOKZDHxntGsQVdKOzDnmBSpIPcLKYfXY")))
				.andExpect(jsonPath("$.data.[*].desc").value(hasItem("ngLOuQPHvHjakasPetAQSvtXGERxxPHJSQRc")))
				.andExpect(jsonPath("$.data.[*].title").value(hasItem("ZnTXJtqlRHZeMUdqwXkIJsWLaydSolHPOTHk")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("JjzdvUbAeaxuUbYuudUhXkOtZqyKWDfFAoOi")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("EJmAtagplJlmCQcrdkrEVBLJWEwOahELoYnB")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("rbjQkPZRknoRqLIyHKTdKlDMrhmVTtFpjsYW")));
	}

	@Test
	public void testGet() throws Exception {
		Role entity = new Role();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("DyllsAIAvGChkBgsOiDlaNVxsjGjeIsVqhCY");
		entity.setDescription("bAjZhUFcOMdTkztNfABqoAknjmSxrOhQPOye");
		entity.setCreatedUserId("EpEEDjiilLBaDIhecQUqqvgSiYPOFwTpyVWr");
		entity.setCreatedBy("bOjZJlABiYBNtPAHoHGOpnThDNmlphBFPBsU");
		entity.setCreatedIp("EDpaqLxLPyScYcGripNzACrsxuNIwFlIyiPR");
		entity.setUpdatedUserId("KdizAKGsYheJNXzpQftOtKJUCNdVRanzAqAP");
		entity.setUpdatedBy("sGYkbVmqcunmvdOJiXJnatAeVQJXglufHnit");
		entity.setUpdatedIp("iSwLUsWhgsfsJHZEWckhxmFzIguKXGBzdjgM");
		dao.insert(entity);
		this.mvc.perform(post("/BaseRole/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..createdBy").value(hasItem("bOjZJlABiYBNtPAHoHGOpnThDNmlphBFPBsU")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("EDpaqLxLPyScYcGripNzACrsxuNIwFlIyiPR")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("EpEEDjiilLBaDIhecQUqqvgSiYPOFwTpyVWr")))
				.andExpect(jsonPath("$..desc").value(hasItem("bAjZhUFcOMdTkztNfABqoAknjmSxrOhQPOye")))
				.andExpect(jsonPath("$..title").value(hasItem("DyllsAIAvGChkBgsOiDlaNVxsjGjeIsVqhCY")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("sGYkbVmqcunmvdOJiXJnatAeVQJXglufHnit")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("iSwLUsWhgsfsJHZEWckhxmFzIguKXGBzdjgM")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("KdizAKGsYheJNXzpQftOtKJUCNdVRanzAqAP")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new Role()).size();
		Role entity = new Role();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("tKwMWHROmqDReGROvzpqkhdzqbqGNPzUaNoH");
		entity.setDescription("NdqzlsKETrFclJuKzGCDNPtWfSwFHwjIMsBs");
		entity.setCreatedUserId("RRZOdzYUqwyJXCETOvgVWZQTdVowIhKneCiA");
		entity.setCreatedBy("RiXxNWqsNroyLxNCUMltKQGrRrQgHrCMkDaV");
		entity.setCreatedIp("rQVhorEKrYtHCsivEXqlQvGvgGSqChvBHYZA");
		entity.setUpdatedUserId("cYXGPZtSLKLszYpXtluIBbveKqrmbGHIoErH");
		entity.setUpdatedBy("pCudzbIyUPgwUJckMAVzuCExYBrBEAZzkVYD");
		entity.setUpdatedIp("EzgEwPkOXyhARkQQKXxBWeVibmePZssOUacs");
		this.mvc.perform(post("/BaseRole/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<Role> baseRoleEntityList = dao.ListByPage(new Role());
		assertThat(baseRoleEntityList).hasSize(databaseSizeBeforeCreate + 1);
		Role testBaseRoleEntity = baseRoleEntityList.get(baseRoleEntityList.size() - 1);
		assertThat(testBaseRoleEntity.getCreatedBy()).isEqualTo("RiXxNWqsNroyLxNCUMltKQGrRrQgHrCMkDaV");
		assertThat(testBaseRoleEntity.getCreatedIp()).isEqualTo("rQVhorEKrYtHCsivEXqlQvGvgGSqChvBHYZA");
		assertThat(testBaseRoleEntity.getCreatedUserId()).isEqualTo("RRZOdzYUqwyJXCETOvgVWZQTdVowIhKneCiA");
		assertThat(testBaseRoleEntity.getDescription()).isEqualTo("NdqzlsKETrFclJuKzGCDNPtWfSwFHwjIMsBs");
		assertThat(testBaseRoleEntity.getTitle()).isEqualTo("tKwMWHROmqDReGROvzpqkhdzqbqGNPzUaNoH");
		assertThat(testBaseRoleEntity.getUpdatedBy()).isEqualTo("pCudzbIyUPgwUJckMAVzuCExYBrBEAZzkVYD");
		assertThat(testBaseRoleEntity.getUpdatedIp()).isEqualTo("EzgEwPkOXyhARkQQKXxBWeVibmePZssOUacs");
		assertThat(testBaseRoleEntity.getUpdatedUserId()).isEqualTo("cYXGPZtSLKLszYpXtluIBbveKqrmbGHIoErH");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		Role entity = new Role();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("izPBOjixCDzdUrOjYqgOpHyyjLshTIEmVpSJ");
		entity.setDescription("BZGgqmhXkYQqOBdiSmDojENaIoGUEKnnPhPo");
		entity.setCreatedUserId("xhUNXEbzpkcrYOOFkXfrGMFqZxrztXMCnvtE");
		entity.setCreatedBy("XfctDBbytlGrWykkaDZzERMbrJGjXdfVbNIA");
		entity.setCreatedIp("BOjgtUVRNgyQOMDfSQNkNZpQMiVVViBaFwNt");
		entity.setUpdatedUserId("OfbJYpITiwYDoDNxyLnNtscQxUwDRpnJCPWa");
		entity.setUpdatedBy("uvjBTrnWLCGorcltINIHirfzUiAaoRVQrOwR");
		entity.setUpdatedIp("kTPINySszRorMTdeyVCTUSIXOmNIlevczoHo");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new Role()).size();
		Role updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setTitle("dUlHLBaqWMFzYCqOLziVGIQRRwyOSVUXQxkm");
		updatedEntity.setDescription("feyoNklSmzQrUwyVBPlspnPUGMZeDteUWgSc");
		updatedEntity.setCreatedUserId("GhpltAsGUsHMcGzfLIEXTOxDfIYAShgwLyJu");
		updatedEntity.setCreatedBy("LaeBKmpNxAHVBpOWSRULnbyrQjHWZOxKKDTa");
		updatedEntity.setCreatedIp("NCQdaGrGWEKNQipfzJFNXyBoifPMvaTPhURT");
		updatedEntity.setUpdatedUserId("ggjOIsKvgwyqWwGlgwRBtTfyfMoTuHaabNLv");
		updatedEntity.setUpdatedBy("iRAmGQMKTFtLXngCoBdTVFSVmrnfqQvGGomX");
		updatedEntity.setUpdatedIp("LWQuqMwqgXbIVWBrxuSeSKREVEqKlRpfMMaF");
		this.mvc.perform(post("/BaseRole/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<Role> baseRoleEntityList = dao.ListByPage(new Role());
		assertThat(baseRoleEntityList).hasSize(databaseSizeBeforeUpdate);
		Role testBaseRoleEntity = baseRoleEntityList.get(baseRoleEntityList.size() - 1);
		assertThat(testBaseRoleEntity.getCreatedBy()).isEqualTo("LaeBKmpNxAHVBpOWSRULnbyrQjHWZOxKKDTa");
		assertThat(testBaseRoleEntity.getCreatedIp()).isEqualTo("NCQdaGrGWEKNQipfzJFNXyBoifPMvaTPhURT");
		assertThat(testBaseRoleEntity.getCreatedUserId()).isEqualTo("GhpltAsGUsHMcGzfLIEXTOxDfIYAShgwLyJu");
		assertThat(testBaseRoleEntity.getDescription()).isEqualTo("feyoNklSmzQrUwyVBPlspnPUGMZeDteUWgSc");
		assertThat(testBaseRoleEntity.getTitle()).isEqualTo("dUlHLBaqWMFzYCqOLziVGIQRRwyOSVUXQxkm");
		assertThat(testBaseRoleEntity.getUpdatedBy()).isEqualTo("iRAmGQMKTFtLXngCoBdTVFSVmrnfqQvGGomX");
		assertThat(testBaseRoleEntity.getUpdatedIp()).isEqualTo("LWQuqMwqgXbIVWBrxuSeSKREVEqKlRpfMMaF");
		assertThat(testBaseRoleEntity.getUpdatedUserId()).isEqualTo("ggjOIsKvgwyqWwGlgwRBtTfyfMoTuHaabNLv");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		Role entity = new Role();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("tuuGOyUrjPTgBMUspSqUjzKoJiBYZTcPLtoC");
		entity.setDescription("zxfvXmVjFSSwllIgDKrOHPAijtjRhCsPYvUC");
		entity.setCreatedUserId("aExUixAarJXnuEyiLRImsqUGBFghBZtgwnxO");
		entity.setCreatedBy("UKDptDHimqANtMFzTIZanZIxLdlEcsLmbppV");
		entity.setCreatedIp("hpgJxcYDGYmKlxyRpmEXZMLcXbdssuwImyTB");
		entity.setUpdatedUserId("zqEFwTphYxsUQwlvBcPmpoLMKltnYJtRSCCg");
		entity.setUpdatedBy("BbZptRzJtmHsaohGnQPeMWOFsjUXwIkPsLgt");
		entity.setUpdatedIp("bDacjQdELwzSptvwAVzQdlAWejoLqCnTvuNw");
		dao.insert(entity);
		this.mvc.perform(post("/BaseRole/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
