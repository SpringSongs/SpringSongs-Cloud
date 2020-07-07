package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.Module;
import org.authority.mapper.BaseModuleMapper;
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
public class BaseModuleControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseModuleMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		Module entity = new Module();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("MjwNXFiWtUoWYTjYfVSAtgTcUCdnaTDbqbca");
		entity.setTitle("QLVyvfyodOVxYlRUnFiuurFYCJtIqeNLsnIH");
		entity.setFormName("LQMgEuTSHuKFTesfdSPRhJpgJkiwRjhHtGPk");
		entity.setVueUrl("bATsabtwPiOoNkvdrCgvGrmDTkIbondDBrdD");
		entity.setAngularUrl("eRcsdWDAeOOZfDkipnnflcuPIVdQOqYPbcSf");
		entity.setParentId("TSoQuYdiIaQeBHVSZDTIjKSNbPnQQfcJhDBn");
		entity.setParentName("RdXchTmZoscnzkHzZkYJkzmwXOoqVNloPNBr");
		entity.setSystemId("GcKxjWGWmnXbTmjlHZbirfiFWexUbrchpOgi");
		entity.setCreatedUserId("vQMXnzBIIavjqbFyTSiYvsifKLKyjKALlDwP");
		entity.setCreatedBy("XVDqnxYjBSowMgJwMrtcnAOOxpmypRxFnthz");
		entity.setCreatedIp("hsVFcjlVmIWQUUoPwgmUKWpcWYJHZzysntCn");
		entity.setUpdatedUserId("zHYeAVfxUDCECXvzVaagSyOUfMVMotDzlrow");
		entity.setUpdatedBy("gOgvQaUAaWLPQiHxcYPGaBGmczKCEmEeGUTJ");
		entity.setUpdatedIp("woshSnwczOZKLSpICpKDdLLDZaGibvflFtFL");
		this.mvc.perform(post("/BaseModule/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].angularUrl").value(hasItem("eRcsdWDAeOOZfDkipnnflcuPIVdQOqYPbcSf")))
				.andExpect(jsonPath("$.data.[*].code").value(hasItem("MjwNXFiWtUoWYTjYfVSAtgTcUCdnaTDbqbca")))
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("XVDqnxYjBSowMgJwMrtcnAOOxpmypRxFnthz")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("hsVFcjlVmIWQUUoPwgmUKWpcWYJHZzysntCn")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("vQMXnzBIIavjqbFyTSiYvsifKLKyjKALlDwP")))
				.andExpect(jsonPath("$.data.[*].formName").value(hasItem("LQMgEuTSHuKFTesfdSPRhJpgJkiwRjhHtGPk")))
				.andExpect(jsonPath("$.data.[*].parentId").value(hasItem("TSoQuYdiIaQeBHVSZDTIjKSNbPnQQfcJhDBn")))
				.andExpect(jsonPath("$.data.[*].parentName").value(hasItem("RdXchTmZoscnzkHzZkYJkzmwXOoqVNloPNBr")))
				.andExpect(jsonPath("$.data.[*].systemId").value(hasItem("GcKxjWGWmnXbTmjlHZbirfiFWexUbrchpOgi")))
				.andExpect(jsonPath("$.data.[*].title").value(hasItem("QLVyvfyodOVxYlRUnFiuurFYCJtIqeNLsnIH")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("gOgvQaUAaWLPQiHxcYPGaBGmczKCEmEeGUTJ")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("woshSnwczOZKLSpICpKDdLLDZaGibvflFtFL")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("zHYeAVfxUDCECXvzVaagSyOUfMVMotDzlrow")))
				.andExpect(jsonPath("$.data.[*].vueUrl").value(hasItem("bATsabtwPiOoNkvdrCgvGrmDTkIbondDBrdD")));
	}

	@Test
	public void testGet() throws Exception {
		Module entity = new Module();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("VkCDZvITglIZfgwkNzdoEnjArKrzgecALXbY");
		entity.setTitle("nWZxRmcMJKexEADDmfmQLsXfxnYdAiSJgUBN");
		entity.setFormName("tYdYuBHlRRqlDDgarCTIShQZjcOxEnEiFMpn");
		entity.setVueUrl("mEJaWyEBUKgceLseyrYiyWujAofSCIFfOwhb");
		entity.setAngularUrl("HJQVxeHHuGXQHGgbrKrVkysaZskvkQCkhyzP");
		entity.setParentId("JPzCNeiASbmfqZfIAFaxCYjWQKwCcPGZrFkZ");
		entity.setParentName("PAzWSRHNaenMedotCPuyMfoplORkUETICDwq");
		entity.setSystemId("qgzoQSkfmXFjuDFUnYCulPboNKtKeCJQTOGt");
		entity.setCreatedUserId("OIXmGsWnKbOnCUzOkPiwFGMLuyfjJjGNhKFJ");
		entity.setCreatedBy("wrRyDOStAIqipWPmfzJqiBqEhYUGsPJBzYQu");
		entity.setCreatedIp("cibGPDjxLOurkoxkRQPTykyJgCOyxfGngeai");
		entity.setUpdatedUserId("JkErCLFEUeKChqYJDDEOlKrBtgqhygdflxIP");
		entity.setUpdatedBy("DJJIOTVVezMTjDvWMpTDACVXzTfeWQmhvenP");
		entity.setUpdatedIp("rlcKdWnyvOyWVrrfPlDeXNtiRgDovVCbWoov");
		dao.insert(entity);
		this.mvc.perform(post("/BaseModule/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..angularUrl").value(hasItem("HJQVxeHHuGXQHGgbrKrVkysaZskvkQCkhyzP")))
				.andExpect(jsonPath("$..code").value(hasItem("VkCDZvITglIZfgwkNzdoEnjArKrzgecALXbY")))
				.andExpect(jsonPath("$..createdBy").value(hasItem("wrRyDOStAIqipWPmfzJqiBqEhYUGsPJBzYQu")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("cibGPDjxLOurkoxkRQPTykyJgCOyxfGngeai")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("OIXmGsWnKbOnCUzOkPiwFGMLuyfjJjGNhKFJ")))
				.andExpect(jsonPath("$..formName").value(hasItem("tYdYuBHlRRqlDDgarCTIShQZjcOxEnEiFMpn")))
				.andExpect(jsonPath("$..parentId").value(hasItem("JPzCNeiASbmfqZfIAFaxCYjWQKwCcPGZrFkZ")))
				.andExpect(jsonPath("$..parentName").value(hasItem("PAzWSRHNaenMedotCPuyMfoplORkUETICDwq")))
				.andExpect(jsonPath("$..systemId").value(hasItem("qgzoQSkfmXFjuDFUnYCulPboNKtKeCJQTOGt")))
				.andExpect(jsonPath("$..title").value(hasItem("nWZxRmcMJKexEADDmfmQLsXfxnYdAiSJgUBN")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("DJJIOTVVezMTjDvWMpTDACVXzTfeWQmhvenP")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("rlcKdWnyvOyWVrrfPlDeXNtiRgDovVCbWoov")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("JkErCLFEUeKChqYJDDEOlKrBtgqhygdflxIP")))
				.andExpect(jsonPath("$..vueUrl").value(hasItem("mEJaWyEBUKgceLseyrYiyWujAofSCIFfOwhb")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new Module()).size();
		Module entity = new Module();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("MGzAOcxYHlQLfIxXzvLoWwAuLlVdBjihLmrB");
		entity.setTitle("BLBvgpqIeyqloyvUeJlmewnKrVvFbBldYwNu");
		entity.setFormName("IavFyTxnWSneLzZpjbrraHDzcJJLmMiLWCbF");
		entity.setVueUrl("XRpKbNSlgPSVWVtanURaXjifJRhrUUzOzFTb");
		entity.setAngularUrl("JlXcmJxSCdUncYmbVfcLMAqGAZIERbTJVoJq");
		entity.setParentId("BwnheZcsBdndFPXtzHrxjXXmWaPvRexFJGyg");
		entity.setParentName("JyNrnTaPKhcbzgruuJoihAxCXorHRMfTroeL");
		entity.setSystemId("lfCmzeiXpXuCHhdwoCwAWcRGkEfMveewPJpu");
		entity.setCreatedUserId("bBfTjNyhgmxxrfLSGKqVIRbeBbsjyxIySkue");
		entity.setCreatedBy("ruVJFeQxnRbWHKgNmHEzcRioVjJDKGvNkxcE");
		entity.setCreatedIp("MTGUvzDnlVYyXRjWiPcIkYytBRkdMUUNabKL");
		entity.setUpdatedUserId("JQbGlPfWAQCpQIZRsNkwCTmIVxpAnlnrSiVE");
		entity.setUpdatedBy("RbODKCrjxkMsIwNeQxofRCLnMXgKQgtEOoPy");
		entity.setUpdatedIp("NEOLQoUwtnhcDAjWZkWTNSZXudgvWIaybUmR");
		this.mvc.perform(post("/BaseModule/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<Module> baseModuleEntityList = dao.ListByPage(new Module());
		assertThat(baseModuleEntityList).hasSize(databaseSizeBeforeCreate + 1);
		Module testBaseModuleEntity = baseModuleEntityList.get(baseModuleEntityList.size() - 1);
		assertThat(testBaseModuleEntity.getAngularUrl()).isEqualTo("JlXcmJxSCdUncYmbVfcLMAqGAZIERbTJVoJq");
		assertThat(testBaseModuleEntity.getCode()).isEqualTo("MGzAOcxYHlQLfIxXzvLoWwAuLlVdBjihLmrB");
		assertThat(testBaseModuleEntity.getCreatedBy()).isEqualTo("ruVJFeQxnRbWHKgNmHEzcRioVjJDKGvNkxcE");
		assertThat(testBaseModuleEntity.getCreatedIp()).isEqualTo("MTGUvzDnlVYyXRjWiPcIkYytBRkdMUUNabKL");
		assertThat(testBaseModuleEntity.getCreatedUserId()).isEqualTo("bBfTjNyhgmxxrfLSGKqVIRbeBbsjyxIySkue");
		assertThat(testBaseModuleEntity.getFormName()).isEqualTo("IavFyTxnWSneLzZpjbrraHDzcJJLmMiLWCbF");
		assertThat(testBaseModuleEntity.getParentId()).isEqualTo("BwnheZcsBdndFPXtzHrxjXXmWaPvRexFJGyg");
		assertThat(testBaseModuleEntity.getParentName()).isEqualTo("JyNrnTaPKhcbzgruuJoihAxCXorHRMfTroeL");
		assertThat(testBaseModuleEntity.getSystemId()).isEqualTo("lfCmzeiXpXuCHhdwoCwAWcRGkEfMveewPJpu");
		assertThat(testBaseModuleEntity.getTitle()).isEqualTo("BLBvgpqIeyqloyvUeJlmewnKrVvFbBldYwNu");
		assertThat(testBaseModuleEntity.getUpdatedBy()).isEqualTo("RbODKCrjxkMsIwNeQxofRCLnMXgKQgtEOoPy");
		assertThat(testBaseModuleEntity.getUpdatedIp()).isEqualTo("NEOLQoUwtnhcDAjWZkWTNSZXudgvWIaybUmR");
		assertThat(testBaseModuleEntity.getUpdatedUserId()).isEqualTo("JQbGlPfWAQCpQIZRsNkwCTmIVxpAnlnrSiVE");
		assertThat(testBaseModuleEntity.getVueUrl()).isEqualTo("XRpKbNSlgPSVWVtanURaXjifJRhrUUzOzFTb");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		Module entity = new Module();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("ZryUnohMDluLAYGXqwzfBUcykFEdjiRSBfsu");
		entity.setTitle("LbyNozRckWdAlByCIRpPHtXGwSOGrkFTHrPe");
		entity.setFormName("KeWFxvhNQkZcniaJjowHokyGTHIkUDwDhrbB");
		entity.setVueUrl("sXeqdBGyGOlwemjVEQZGZtzuXPWgpwnsvhTg");
		entity.setAngularUrl("rBqwQYTSghtfSIWOBRSXrWkgRMPvbZPCjLjJ");
		entity.setParentId("ImzzsSdmhMZhUzvhOUGqNFqtSGVyvCxqlaDK");
		entity.setParentName("ggUNKvoEzRfuKqeEmVSHlVvnxgfQBteGoZCB");
		entity.setSystemId("YOoFlWABoYiJkluMPqYUHVWwUDYhkjjsOLwK");
		entity.setCreatedUserId("tKNcnPyPsvhvfAVKxQoLFVqaSSlXdFOkcwBv");
		entity.setCreatedBy("eReCQKgAbtwkTfAJtTEIqAbSKPgvSjoreHjL");
		entity.setCreatedIp("mUzxZlQAaLyzTcLTEUJWkpjTpnEOBlbWcema");
		entity.setUpdatedUserId("yBvysftRbwsrKrthTHyFBoUFSmWrMTGVLtDI");
		entity.setUpdatedBy("IyyZkwvxerUvTOTNyLPyFdJuMJzOIPJNVljO");
		entity.setUpdatedIp("WEIBcjvsXFkQYLZDxNgkRmoRYYseHCriJKOe");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new Module()).size();
		Module updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setCode("JweNdsixaqBNNVihYyBqirastrTSUTXDYzyT");
		updatedEntity.setTitle("UKFeOHxkwQjfNLRMQzfhotsWlILrUzpwNfUQ");
		updatedEntity.setFormName("qteCDxgGTvYebpcGizkLFQRDsXAWknSGcsuG");
		updatedEntity.setVueUrl("AGayTXhKzswiijqpWiKeFZjCPWVKkzkprmqe");
		updatedEntity.setAngularUrl("orlZsEGcfENNYWqVixEWfOwsMpQBSrgKcIGn");
		updatedEntity.setParentId("wnaHkQxdgViNHtPmPGJdzcwUYhwIZAruYAMl");
		updatedEntity.setParentName("nnvfjoRcTCgOdrJCJGmhUzQQporKShWXlpdE");
		updatedEntity.setSystemId("NNRqFXZXvMrdoocYHaGzuPCvovFVHJFkHNeE");
		updatedEntity.setCreatedUserId("EBbwUOktzXtrACyoOWuzefijUrUDzlFKDrts");
		updatedEntity.setCreatedBy("XwYLMiXcZQCCHEBRztJxmQMDcifIZAmNcjoo");
		updatedEntity.setCreatedIp("XENPvtaIttDWVUxQvJyydNFXrmjoazDaNbrf");
		updatedEntity.setUpdatedUserId("ANdDCVlZEWNkLVxbPdCXNAZoRYMDnKKRgFcN");
		updatedEntity.setUpdatedBy("HbUpwFocBdFDMIWodvXIirebRfvHwEktEAOX");
		updatedEntity.setUpdatedIp("OuHiUJcGRpCAtNRWwmpKrnRjPZsnYNiRtXJY");
		this.mvc.perform(post("/BaseModule/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<Module> baseModuleEntityList = dao.ListByPage(new Module());
		assertThat(baseModuleEntityList).hasSize(databaseSizeBeforeUpdate);
		Module testBaseModuleEntity = baseModuleEntityList.get(baseModuleEntityList.size() - 1);
		assertThat(testBaseModuleEntity.getAngularUrl()).isEqualTo("orlZsEGcfENNYWqVixEWfOwsMpQBSrgKcIGn");
		assertThat(testBaseModuleEntity.getCode()).isEqualTo("JweNdsixaqBNNVihYyBqirastrTSUTXDYzyT");
		assertThat(testBaseModuleEntity.getCreatedBy()).isEqualTo("XwYLMiXcZQCCHEBRztJxmQMDcifIZAmNcjoo");
		assertThat(testBaseModuleEntity.getCreatedIp()).isEqualTo("XENPvtaIttDWVUxQvJyydNFXrmjoazDaNbrf");
		assertThat(testBaseModuleEntity.getCreatedUserId()).isEqualTo("EBbwUOktzXtrACyoOWuzefijUrUDzlFKDrts");
		assertThat(testBaseModuleEntity.getFormName()).isEqualTo("qteCDxgGTvYebpcGizkLFQRDsXAWknSGcsuG");
		assertThat(testBaseModuleEntity.getParentId()).isEqualTo("wnaHkQxdgViNHtPmPGJdzcwUYhwIZAruYAMl");
		assertThat(testBaseModuleEntity.getParentName()).isEqualTo("nnvfjoRcTCgOdrJCJGmhUzQQporKShWXlpdE");
		assertThat(testBaseModuleEntity.getSystemId()).isEqualTo("NNRqFXZXvMrdoocYHaGzuPCvovFVHJFkHNeE");
		assertThat(testBaseModuleEntity.getTitle()).isEqualTo("UKFeOHxkwQjfNLRMQzfhotsWlILrUzpwNfUQ");
		assertThat(testBaseModuleEntity.getUpdatedBy()).isEqualTo("HbUpwFocBdFDMIWodvXIirebRfvHwEktEAOX");
		assertThat(testBaseModuleEntity.getUpdatedIp()).isEqualTo("OuHiUJcGRpCAtNRWwmpKrnRjPZsnYNiRtXJY");
		assertThat(testBaseModuleEntity.getUpdatedUserId()).isEqualTo("ANdDCVlZEWNkLVxbPdCXNAZoRYMDnKKRgFcN");
		assertThat(testBaseModuleEntity.getVueUrl()).isEqualTo("AGayTXhKzswiijqpWiKeFZjCPWVKkzkprmqe");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		Module entity = new Module();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("ewIzFfmXAdFniCteiDwOBjCSJNtvBDSJDqtK");
		entity.setTitle("cwvmbEEqbrXYrtcEnTgkTSXGUdZSqJFSNiQL");
		entity.setFormName("rQbSbIgFclJaRVKwRjStDOqDEkABbATpfnNA");
		entity.setVueUrl("ZxwsUjoCDdMcaXXunkSSwCACVfuBCOWIFrvM");
		entity.setAngularUrl("pGhPJbrElTonQwFVvHcUNrEMImqkdkwyUcOO");
		entity.setParentId("yvAwLlHKvnCEeoiuUhQyTheIVzrfJeSuWvsM");
		entity.setParentName("vqQthlyiJMZxtmkkvBoeVyyeiWSdRYfiIDPR");
		entity.setSystemId("VWbMwEIvGNuqzaqZJsgHlNEQJmpBGWegQRrt");
		entity.setCreatedUserId("IxylMhbedvSSruOfoukcMLWTJFLRiKoLUEZh");
		entity.setCreatedBy("LAlDruHQVsuqKAHWFXGUawKgDNqIPrwnqFUC");
		entity.setCreatedIp("IaHBwEjpIIBBKCuhvMLqywAMRMupZodlMVKN");
		entity.setUpdatedUserId("fddHBLbiPrJvxpJBXSisDQIhsXguIzrKGCvn");
		entity.setUpdatedBy("rQNzlxwhgVSqLCqgdGYtDWypTblIDjbNLSKg");
		entity.setUpdatedIp("NIBFwXOgoFrBilpkrIgsvTbmlfxTOuRAyUPW");
		dao.insert(entity);
		this.mvc.perform(post("/BaseModule/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
