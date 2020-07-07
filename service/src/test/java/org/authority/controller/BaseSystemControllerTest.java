package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.System;
import org.authority.mapper.BaseSystemMapper;
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
public class BaseSystemControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseSystemMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		System entity = new System();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("HiRbQLWPdxJydzTjxeBUWTkioRSXlSpqYYsT");
		entity.setCreatedUserId("YBDmaNkziRVXOdcBcMrLBLjVgpslTejXxjdO");
		entity.setCreatedBy("oymVOABaCBjTpXZTBtXuMluHjXJXmWfQULyY");
		entity.setCreatedIp("jSqAiRUEbtwWPXFRwMygzzPRHqGOfidAkdTp");
		entity.setUpdatedUserId("ISwXPrJVbEsuEsEFALyvLLzcJxqmTZulfMgS");
		entity.setUpdatedBy("rruroXXqrUDzLJARrRYcJKHhrgmtRJZvkXcK");
		entity.setUpdatedIp("heTqjtnKYynAZLipLEKvmjHucmGfjcpVBNSy");
		this.mvc.perform(post("/BaseSystem/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("oymVOABaCBjTpXZTBtXuMluHjXJXmWfQULyY")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("jSqAiRUEbtwWPXFRwMygzzPRHqGOfidAkdTp")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("YBDmaNkziRVXOdcBcMrLBLjVgpslTejXxjdO")))
				.andExpect(jsonPath("$.data.[*].title").value(hasItem("HiRbQLWPdxJydzTjxeBUWTkioRSXlSpqYYsT")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("rruroXXqrUDzLJARrRYcJKHhrgmtRJZvkXcK")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("heTqjtnKYynAZLipLEKvmjHucmGfjcpVBNSy")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("ISwXPrJVbEsuEsEFALyvLLzcJxqmTZulfMgS")));
	}

	@Test
	public void testGet() throws Exception {
		System entity = new System();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("YDyordlgmpZjBnsiDlsZsdxscKWodkMxGtre");
		entity.setCreatedUserId("KujhjtKuIuxzUECJuYfXkBjLFGOjuzOjwSQh");
		entity.setCreatedBy("VEDJsXyTuEleKpZxLyrSYKxvZDlxvObglCLD");
		entity.setCreatedIp("noEZINjjUxiQdgvFqbBsaOMSAjPUKFiLKCgt");
		entity.setUpdatedUserId("pHoUqreKlRAWQlyegaHYZaMiShQyImeHiOla");
		entity.setUpdatedBy("IFXfhOavVMkKGzNvmKhRzeXqypiHEuFVZTTt");
		entity.setUpdatedIp("ikVWcyIdjqdlPbxSVbAWXAtqfmMvkmFNuGSp");
		dao.insert(entity);
		this.mvc.perform(post("/BaseSystem/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..createdBy").value(hasItem("VEDJsXyTuEleKpZxLyrSYKxvZDlxvObglCLD")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("noEZINjjUxiQdgvFqbBsaOMSAjPUKFiLKCgt")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("KujhjtKuIuxzUECJuYfXkBjLFGOjuzOjwSQh")))
				.andExpect(jsonPath("$..title").value(hasItem("YDyordlgmpZjBnsiDlsZsdxscKWodkMxGtre")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("IFXfhOavVMkKGzNvmKhRzeXqypiHEuFVZTTt")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("ikVWcyIdjqdlPbxSVbAWXAtqfmMvkmFNuGSp")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("pHoUqreKlRAWQlyegaHYZaMiShQyImeHiOla")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new System()).size();
		System entity = new System();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("brktPGipwLMyVBraUkKOEjTPoIKWpAFgSpLw");
		entity.setCreatedUserId("oHAmBAcGVCnCWrNUasZAbRndrLwLYSraMGul");
		entity.setCreatedBy("KyBkcFgCJxQkXFzJhCDpgdenhvlBOuEakLcM");
		entity.setCreatedIp("OWNDjizaoenaysGcVvzIfGKaVwIYfzXTBKep");
		entity.setUpdatedUserId("jCfebvFOpaPnDaPGPBEqZJOivbmrTTpDCfJr");
		entity.setUpdatedBy("qkWpMFmSiyLucvmmrHkpDbONVHuNNRObuozE");
		entity.setUpdatedIp("vaiLZpjFMZKArWVbfPSZadMZzJmPLAEQjDJr");
		this.mvc.perform(post("/BaseSystem/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<System> baseSystemEntityList = dao.ListByPage(new System());
		assertThat(baseSystemEntityList).hasSize(databaseSizeBeforeCreate + 1);
		System testBaseSystemEntity = baseSystemEntityList.get(baseSystemEntityList.size() - 1);
		assertThat(testBaseSystemEntity.getCreatedBy()).isEqualTo("KyBkcFgCJxQkXFzJhCDpgdenhvlBOuEakLcM");
		assertThat(testBaseSystemEntity.getCreatedIp()).isEqualTo("OWNDjizaoenaysGcVvzIfGKaVwIYfzXTBKep");
		assertThat(testBaseSystemEntity.getCreatedUserId()).isEqualTo("oHAmBAcGVCnCWrNUasZAbRndrLwLYSraMGul");
		assertThat(testBaseSystemEntity.getTitle()).isEqualTo("brktPGipwLMyVBraUkKOEjTPoIKWpAFgSpLw");
		assertThat(testBaseSystemEntity.getUpdatedBy()).isEqualTo("qkWpMFmSiyLucvmmrHkpDbONVHuNNRObuozE");
		assertThat(testBaseSystemEntity.getUpdatedIp()).isEqualTo("vaiLZpjFMZKArWVbfPSZadMZzJmPLAEQjDJr");
		assertThat(testBaseSystemEntity.getUpdatedUserId()).isEqualTo("jCfebvFOpaPnDaPGPBEqZJOivbmrTTpDCfJr");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		System entity = new System();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("xfAYcxesUqMjwkhmMXXUwkdzFDmVdvjpwmXb");
		entity.setCreatedUserId("thzECdNfNGiuMkSDWOwFhQFFylFxrylDNOOb");
		entity.setCreatedBy("UNSFTAVOCsUWaGgeGdCYpIZIEmlfmHFGedMk");
		entity.setCreatedIp("xqhCSOKoxRgCyHncxATJtqIDtovupemoedeg");
		entity.setUpdatedUserId("lhMVCjeUZnTXUvPnwuDneroGCItlHvaUAieT");
		entity.setUpdatedBy("xSZJpvdAlqOJwTYBkdsqZenevzukSeBynRmg");
		entity.setUpdatedIp("EEsilqmyHtgMSLpFSmwHjevLyDkCAYnQmRHj");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new System()).size();
		System updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setTitle("SyyaszLWfDKuGKOrGZWPIWqtoIWaFxWlLxHC");
		updatedEntity.setCreatedUserId("HGNYdVjnGshKRUeyqRACdHsUiJVoEfxaZnGl");
		updatedEntity.setCreatedBy("zTQxWOyrcToDvrrPcnVpobswLcIRkJvyTenP");
		updatedEntity.setCreatedIp("eSLVPCWNXkKDQXvnMtMDlEBdGzhbWMixWZtx");
		updatedEntity.setUpdatedUserId("xfaFxSUUFsKrLaaJBUZfvnGXMhDqCPzJSDoZ");
		updatedEntity.setUpdatedBy("NFxghcxgheQMVBbsUhamyrlczuoFbWnSZmro");
		updatedEntity.setUpdatedIp("IfXuEMcgFKuGrBrlQdccyBhqJFpnIqaDjSyB");
		this.mvc.perform(post("/BaseSystem/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<System> baseSystemEntityList = dao.ListByPage(new System());
		assertThat(baseSystemEntityList).hasSize(databaseSizeBeforeUpdate);
		System testBaseSystemEntity = baseSystemEntityList.get(baseSystemEntityList.size() - 1);
		assertThat(testBaseSystemEntity.getCreatedBy()).isEqualTo("zTQxWOyrcToDvrrPcnVpobswLcIRkJvyTenP");
		assertThat(testBaseSystemEntity.getCreatedIp()).isEqualTo("eSLVPCWNXkKDQXvnMtMDlEBdGzhbWMixWZtx");
		assertThat(testBaseSystemEntity.getCreatedUserId()).isEqualTo("HGNYdVjnGshKRUeyqRACdHsUiJVoEfxaZnGl");
		assertThat(testBaseSystemEntity.getTitle()).isEqualTo("SyyaszLWfDKuGKOrGZWPIWqtoIWaFxWlLxHC");
		assertThat(testBaseSystemEntity.getUpdatedBy()).isEqualTo("NFxghcxgheQMVBbsUhamyrlczuoFbWnSZmro");
		assertThat(testBaseSystemEntity.getUpdatedIp()).isEqualTo("IfXuEMcgFKuGrBrlQdccyBhqJFpnIqaDjSyB");
		assertThat(testBaseSystemEntity.getUpdatedUserId()).isEqualTo("xfaFxSUUFsKrLaaJBUZfvnGXMhDqCPzJSDoZ");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		System entity = new System();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("ehxXliHjKbubQSSfSzysEnydgvnidkuKSVKW");
		entity.setCreatedUserId("azsWpOTyViWrhckmZgLoySmjpmANVZXLxAAl");
		entity.setCreatedBy("fFxQNnApZpxlNlPBmlQxxtzSLgTSURiNBYgX");
		entity.setCreatedIp("jitPTBVjxhSiJvFaJZpIYzSdcdNNlyerzjvz");
		entity.setUpdatedUserId("zzvIpkhKhcWmxdhmkfmePIWhQPnenQNGpAGn");
		entity.setUpdatedBy("RDklnlvFBqCNniZKsEgiYapnwugsQIJQwuDz");
		entity.setUpdatedIp("GESPweWLycsrFYzadadrwnezjdcpnTDrzHmR");
		dao.insert(entity);
		this.mvc.perform(post("/BaseSystem/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}
}
