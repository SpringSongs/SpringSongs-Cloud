package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.ApiSecurity;
import org.authority.mapper.BaseApiSecurityMapper;
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
public class BaseApiSecurityControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseApiSecurityMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}


	@Test
	public void testGetPage() throws Exception {
		ApiSecurity entity = new ApiSecurity();
		entity.setId(UUID.randomUUID().toString());
		entity.setAppkey("YiqfTVyHiIZZiukNpYVmTRBORficEQoWTIJV");
		entity.setAppsecurity("NIcNtEPrYjVgiZSdOSEEdVdqgUZAmflyTEKu");
		entity.setCreatedUserId("FpKNCgdleCiZcLnUfFespkbHtdhHJEwvUREc");
		entity.setCreatedBy("vmnKMbAeuJMTAGgeNwKjxOqyXhBsoZbtVvOO");
		entity.setCreatedIp("VQrMBLnPoddadFKpavDoABcjPbyCkKQqhrcY");
		entity.setUpdatedUserId("myNHLGcEXdwdweBVgBZPwoQxLNeECZomqOQq");
		entity.setUpdatedBy("oYMSjeQrvoHpJoQGAPEBRdHgWjWiWRwDVXeP");
		entity.setUpdatedIp("eFyrqVdOUvBZYklQyLHoeJNnDYwhBfeLTdPm");
		entity.setSortCode(11);
		entity.setDeletedFlag(false);
		entity.setEnableDelete(false);
		entity.setEnableEdit(false);
		this.mvc.perform(post("/BaseApiSecurity/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].appkey").value(hasItem("YiqfTVyHiIZZiukNpYVmTRBORficEQoWTIJV")))
				.andExpect(jsonPath("$.data.[*].appsecurity").value(hasItem("NIcNtEPrYjVgiZSdOSEEdVdqgUZAmflyTEKu")))
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("vmnKMbAeuJMTAGgeNwKjxOqyXhBsoZbtVvOO")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("VQrMBLnPoddadFKpavDoABcjPbyCkKQqhrcY")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("FpKNCgdleCiZcLnUfFespkbHtdhHJEwvUREc")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("oYMSjeQrvoHpJoQGAPEBRdHgWjWiWRwDVXeP")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("eFyrqVdOUvBZYklQyLHoeJNnDYwhBfeLTdPm")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("myNHLGcEXdwdweBVgBZPwoQxLNeECZomqOQq")));
	}

	@Test
	public void testGet() throws Exception {
		ApiSecurity entity = new ApiSecurity();
		entity.setId(UUID.randomUUID().toString());
		entity.setAppkey("MykGUEqPEExmJTnhBVZyoDanqTGNfwTqeOVg");
		entity.setAppsecurity("EEUEoZNrSeDclzSbHRGVMLHxortDzUBjPZQu");
		entity.setCreatedUserId("XUfmXdMEbcEKtWzSkqKuJuOrTalyVcWgnSxC");
		entity.setCreatedBy("EPDHmJofURFNCPLrAPQKvfSLvLqyGfQlnsKh");
		entity.setCreatedIp("wQdxanPUIMVvJOkovYCsVeMaiktdlNctPpBc");
		entity.setUpdatedUserId("cMpQQoBsLhRSWKTuiEURqfClzHNKSiyLPJLe");
		entity.setUpdatedBy("thvNiUpIggEmkRGMifHqNjyxuHTVOkmhtrEp");
		entity.setUpdatedIp("WZTsUisYMhMKeIeNrevSKdIczzVmkqLbQxAK");
		entity.setSortCode(11);
		entity.setDeletedFlag(false);
		entity.setEnableDelete(false);
		entity.setEnableEdit(false);
		dao.insert(entity);
		this.mvc.perform(post("/BaseApiSecurity/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..appkey").value(hasItem("MykGUEqPEExmJTnhBVZyoDanqTGNfwTqeOVg")))
				.andExpect(jsonPath("$..appsecurity").value(hasItem("EEUEoZNrSeDclzSbHRGVMLHxortDzUBjPZQu")))
				.andExpect(jsonPath("$..createdBy").value(hasItem("EPDHmJofURFNCPLrAPQKvfSLvLqyGfQlnsKh")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("wQdxanPUIMVvJOkovYCsVeMaiktdlNctPpBc")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("XUfmXdMEbcEKtWzSkqKuJuOrTalyVcWgnSxC")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("thvNiUpIggEmkRGMifHqNjyxuHTVOkmhtrEp")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("WZTsUisYMhMKeIeNrevSKdIczzVmkqLbQxAK")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("cMpQQoBsLhRSWKTuiEURqfClzHNKSiyLPJLe")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new ApiSecurity()).size();
		ApiSecurity entity = new ApiSecurity();
		entity.setId(UUID.randomUUID().toString());
		entity.setAppkey("EhqiSDUlUPxtWDsqSOkXcSaCPrGUMZQGeRVO");
		entity.setAppsecurity("YmkvrCNQhprVDdxsiIhToaVtkebRiEQtdAdC");
		entity.setCreatedUserId("nneScGSySTTsFFjtPNmWnruQNJbFMaSJwSdv");
		entity.setCreatedBy("XPAFoQJeFzSuFRzXYhNacrJUbHGASgjCtxGF");
		entity.setCreatedIp("cavAZpLLVYJQbFjVWxEPPOkyicvEgtbkyfCp");
		entity.setUpdatedUserId("HosdIPYbHsAHcnbfkDoyWoyoBRXIbUFJqkJI");
		entity.setUpdatedBy("nRYtPRIFUSRAwtDdChXeKOIYIQXYtRGMcgQZ");
		entity.setUpdatedIp("ZgnpZYuycdfOvursqjuOQiecDnyLndFpxnqQ");
		entity.setSortCode(11);
		entity.setDeletedFlag(false);
		entity.setEnableDelete(false);
		entity.setEnableEdit(false);
		this.mvc.perform(post("/BaseApiSecurity/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<ApiSecurity> baseApiSecurityEntityList = dao.ListByPage(new ApiSecurity());
		assertThat(baseApiSecurityEntityList).hasSize(databaseSizeBeforeCreate + 1);
		ApiSecurity testBaseApiSecurityEntity = baseApiSecurityEntityList
				.get(baseApiSecurityEntityList.size() - 1);
		assertThat(testBaseApiSecurityEntity.getAppkey()).isEqualTo("EhqiSDUlUPxtWDsqSOkXcSaCPrGUMZQGeRVO");
		assertThat(testBaseApiSecurityEntity.getAppsecurity()).isEqualTo("YmkvrCNQhprVDdxsiIhToaVtkebRiEQtdAdC");
		assertThat(testBaseApiSecurityEntity.getCreatedBy()).isEqualTo("XPAFoQJeFzSuFRzXYhNacrJUbHGASgjCtxGF");
		assertThat(testBaseApiSecurityEntity.getCreatedIp()).isEqualTo("cavAZpLLVYJQbFjVWxEPPOkyicvEgtbkyfCp");
		assertThat(testBaseApiSecurityEntity.getCreatedUserId()).isEqualTo("nneScGSySTTsFFjtPNmWnruQNJbFMaSJwSdv");
		assertThat(testBaseApiSecurityEntity.getUpdatedBy()).isEqualTo("nRYtPRIFUSRAwtDdChXeKOIYIQXYtRGMcgQZ");
		assertThat(testBaseApiSecurityEntity.getUpdatedIp()).isEqualTo("ZgnpZYuycdfOvursqjuOQiecDnyLndFpxnqQ");
		assertThat(testBaseApiSecurityEntity.getUpdatedUserId()).isEqualTo("HosdIPYbHsAHcnbfkDoyWoyoBRXIbUFJqkJI");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		ApiSecurity entity = new ApiSecurity();
		entity.setId(UUID.randomUUID().toString());
		entity.setAppkey("kmtBddCNXAhcFHTwpiZDsMKxYSbRyOVJlSsC");
		entity.setAppsecurity("gsTtjtEBXHgwUuFukkuLzzZUHbkvVpUyVTKo");
		entity.setCreatedUserId("cuDBGAFcKsyMVaSCuZKSbZOYODSiQbZXtyZP");
		entity.setCreatedBy("HoBeYhUwEdcNNSFYdAayTiSIcKyHyaLMoKhd");
		entity.setCreatedIp("ahovdwSmiwmdoELkMXrTMMonOFldjZQkpMcj");
		entity.setUpdatedUserId("EAtLJGbwbMYKrwuXmJDyxRlIBfFqSQfWODZd");
		entity.setUpdatedBy("PbRQSxvuqjBYBTyFjFghRADaAhLXAlFsZLtD");
		entity.setUpdatedIp("NnCbUPsztDnXBaHCyiKRHwopVLVOnlZqpZns");
		entity.setSortCode(11);
		entity.setDeletedFlag(false);
		entity.setEnableDelete(false);
		entity.setEnableEdit(false);
		dao.insert(entity);
		int databaseSizeBeforeUpdate =  dao.ListByPage(new ApiSecurity()).size();
		ApiSecurity updatedEntity = dao.selectByPrimaryKey(entity.getId());
		entity.setId(UUID.randomUUID().toString());
		updatedEntity.setAppkey("YDlSHxqsoLpltdgjwZDEntXxdfKlNphayghz");
		updatedEntity.setAppsecurity("UPOBdBwLRZhMFtVGoFOsuAUijqfFJNCTyxZj");
		updatedEntity.setCreatedUserId("LNPHHjfuHNTrmOZolDZQuxSPMhYpWRAiDKlq");
		updatedEntity.setCreatedBy("gMuXuJviVXOmslDqdoYkoLVfauHQEaJAfGob");
		updatedEntity.setCreatedIp("emkRdleSJFSyPRZYxBrdTpXGoPnpnGzwMqao");
		updatedEntity.setUpdatedUserId("TswsZHnhMGTUEzHMbPZoFmGRMeAPqkpHMAuY");
		updatedEntity.setUpdatedBy("oejKcPtPFmsOQMnFmTDoIVfHbYrPqdGhAAUL");
		updatedEntity.setUpdatedIp("lMObasRfvSzNdPuVFziocmmZZpiUMEIwjahY");
		this.mvc.perform(post("/BaseApiSecurity/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<ApiSecurity> baseApiSecurityEntityList =dao.ListByPage(new ApiSecurity());
		assertThat(baseApiSecurityEntityList).hasSize(databaseSizeBeforeUpdate);
		ApiSecurity testBaseApiSecurityEntity = baseApiSecurityEntityList
				.get(baseApiSecurityEntityList.size() - 1);
		assertThat(testBaseApiSecurityEntity.getAppkey()).isEqualTo("YDlSHxqsoLpltdgjwZDEntXxdfKlNphayghz");
		assertThat(testBaseApiSecurityEntity.getAppsecurity()).isEqualTo("UPOBdBwLRZhMFtVGoFOsuAUijqfFJNCTyxZj");
		assertThat(testBaseApiSecurityEntity.getCreatedBy()).isEqualTo("gMuXuJviVXOmslDqdoYkoLVfauHQEaJAfGob");
		assertThat(testBaseApiSecurityEntity.getCreatedIp()).isEqualTo("emkRdleSJFSyPRZYxBrdTpXGoPnpnGzwMqao");
		assertThat(testBaseApiSecurityEntity.getCreatedUserId()).isEqualTo("LNPHHjfuHNTrmOZolDZQuxSPMhYpWRAiDKlq");
		assertThat(testBaseApiSecurityEntity.getUpdatedBy()).isEqualTo("oejKcPtPFmsOQMnFmTDoIVfHbYrPqdGhAAUL");
		assertThat(testBaseApiSecurityEntity.getUpdatedIp()).isEqualTo("lMObasRfvSzNdPuVFziocmmZZpiUMEIwjahY");
		assertThat(testBaseApiSecurityEntity.getUpdatedUserId()).isEqualTo("TswsZHnhMGTUEzHMbPZoFmGRMeAPqkpHMAuY");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		ApiSecurity entity = new ApiSecurity();
		entity.setId(UUID.randomUUID().toString());
		entity.setAppkey("QFehfkVWhttLaDDCoOiUVecPbJTgLjBMkdGb");
		entity.setAppsecurity("CPSJiTGcgMKiiljFlFQibqIGztzFMctauAJR");
		entity.setCreatedUserId("JKOxLPgpNvwTZXwAzfxgikqToUDiTTEixhef");
		entity.setCreatedBy("SbpsrMPFHtsjyAkMZUgNnEawOkKfPbxmmyrO");
		entity.setCreatedIp("qJtYpKKwKlImzZMjubaRmApukHsFZicziVfa");
		entity.setUpdatedUserId("xXGcEiACqntsfcvWcCHItjDeGUCRjBqFfiLV");
		entity.setUpdatedBy("TIpPiwHWrQrskTERQoQgTVdiOesaucqIdWPh");
		entity.setUpdatedIp("ejKxygrGPmXdpQBzWYlOTZtXIDVNRsLLEaee");
		entity.setSortCode(11);
		entity.setDeletedFlag(false);
		entity.setEnableDelete(false);
		entity.setEnableEdit(false);
		dao.insert(entity);
		this.mvc.perform(post("/BaseApiSecurity/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
