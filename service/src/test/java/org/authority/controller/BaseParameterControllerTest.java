package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.Parameter;
import org.authority.mapper.BaseParameterMapper;
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
public class BaseParameterControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseParameterMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		Parameter entity = new Parameter();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("NkYnVBmguyHzXBAgTErvwDbJYohHfUYamaUm");
		entity.setTitle("oMkrTOCYcoxNijwBOsKmYakRuoeOZuRZoTuB");
		entity.setContent("qKUFmBBOrAoKeZUyaNtOLQWZOxjMJvOEKTZg");
		entity.setCreatedUserId("kBdLHbtlVsjBbXnmnxZOpBIKRRJnQkHIFfov");
		entity.setCreatedBy("bIErDnNrGmtntzrRreviHSXiAYmuNiGjSzRE");
		entity.setCreatedIp("YeLXeFcuvkgKgOOoVcCwzeHnySbdnGyuqNBY");
		entity.setUpdatedUserId("DpYAjaXlowBzLudOXESVIcwNRpGDGRnTPyAO");
		entity.setUpdatedBy("FAMGJvCFnQoqCNIYxXdJzQTmUmsjPKTkWouw");
		entity.setUpdatedIp("AUryLDZOKbgYUJpVXLJiomdhrzKhVnjUVFPc");
		this.mvc.perform(post("/BaseParameter/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].code").value(hasItem("NkYnVBmguyHzXBAgTErvwDbJYohHfUYamaUm")))
				.andExpect(jsonPath("$.data.[*].content").value(hasItem("qKUFmBBOrAoKeZUyaNtOLQWZOxjMJvOEKTZg")))
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("bIErDnNrGmtntzrRreviHSXiAYmuNiGjSzRE")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("YeLXeFcuvkgKgOOoVcCwzeHnySbdnGyuqNBY")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("kBdLHbtlVsjBbXnmnxZOpBIKRRJnQkHIFfov")))
				.andExpect(jsonPath("$.data.[*].title").value(hasItem("oMkrTOCYcoxNijwBOsKmYakRuoeOZuRZoTuB")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("FAMGJvCFnQoqCNIYxXdJzQTmUmsjPKTkWouw")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("AUryLDZOKbgYUJpVXLJiomdhrzKhVnjUVFPc")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("DpYAjaXlowBzLudOXESVIcwNRpGDGRnTPyAO")));
	}

	@Test
	public void testGet() throws Exception {
		Parameter entity = new Parameter();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("qCuCvvqwddtSwqWaYLpHfvSHFrTQvzFxjVBa");
		entity.setTitle("OpdYGNDmGhEJinaekdQqDoCtzLlsZfDGsRGM");
		entity.setContent("jwfZjSQABjZSNoosVlrEqvRPAXJMgSbTfuei");
		entity.setCreatedUserId("idLjQEkTocUAOEXccvKdosytzCqZfbSXHdUL");
		entity.setCreatedBy("FPEnHwCuilbJPcVfosCHGtsayBMAOuAWZEDp");
		entity.setCreatedIp("PMonDMZIZkUjCtBWdksWLajaLYyaEehquMhi");
		entity.setUpdatedUserId("SraqnabCWAAbbLtvlWORLDHYGzWpgMtxovMD");
		entity.setUpdatedBy("UVcnEulqtyPQWbQCVperwflavUXZMLpXzkBC");
		entity.setUpdatedIp("CXEvWcDiPcXSuFQAJrLbzMusNBqRhHQeHUTU");
		dao.insert(entity);
		this.mvc.perform(post("/BaseParameter/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..code").value(hasItem("qCuCvvqwddtSwqWaYLpHfvSHFrTQvzFxjVBa")))
				.andExpect(jsonPath("$..content").value(hasItem("jwfZjSQABjZSNoosVlrEqvRPAXJMgSbTfuei")))
				.andExpect(jsonPath("$..createdBy").value(hasItem("FPEnHwCuilbJPcVfosCHGtsayBMAOuAWZEDp")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("PMonDMZIZkUjCtBWdksWLajaLYyaEehquMhi")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("idLjQEkTocUAOEXccvKdosytzCqZfbSXHdUL")))
				.andExpect(jsonPath("$..title").value(hasItem("OpdYGNDmGhEJinaekdQqDoCtzLlsZfDGsRGM")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("UVcnEulqtyPQWbQCVperwflavUXZMLpXzkBC")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("CXEvWcDiPcXSuFQAJrLbzMusNBqRhHQeHUTU")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("SraqnabCWAAbbLtvlWORLDHYGzWpgMtxovMD")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new Parameter()).size();
		Parameter entity = new Parameter();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("RjjPInNaSVBPbCZwxEHKMjZvBDqdDVJiompk");
		entity.setTitle("ibIOWeSDiQqzFXYNFquLisDdHnDTwCeNXjvc");
		entity.setContent("GbPgKGIMVuUIszqnufeuNhMVfmulrCuLvJeA");
		entity.setCreatedUserId("puGLhEvIENodOdOOFjUEsYUitPbBmJHHXVeG");
		entity.setCreatedBy("jRxzOYSQtclsgSFojOiJVcfLgwAGgFcbNTWs");
		entity.setCreatedIp("QejRDsoVLRCHMmuTikddglLOsJEkxXoOuHdY");
		entity.setUpdatedUserId("XhuGcRhINRDIuoJiQMsasyMuRtmXSjlXzrxU");
		entity.setUpdatedBy("SaLfJZezmPwVIDybPInHtBjAnoUFnztIireN");
		entity.setUpdatedIp("uxCuYsYfJWczspGtObPdjkKIBbxBuJmHvTwr");
		this.mvc.perform(post("/BaseParameter/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<Parameter> baseParameterEntityList = dao.ListByPage(new Parameter());
		assertThat(baseParameterEntityList).hasSize(databaseSizeBeforeCreate + 1);
		Parameter testBaseParameterEntity = baseParameterEntityList.get(baseParameterEntityList.size() - 1);
		assertThat(testBaseParameterEntity.getCode()).isEqualTo("RjjPInNaSVBPbCZwxEHKMjZvBDqdDVJiompk");
		assertThat(testBaseParameterEntity.getContent()).isEqualTo("GbPgKGIMVuUIszqnufeuNhMVfmulrCuLvJeA");
		assertThat(testBaseParameterEntity.getCreatedBy()).isEqualTo("jRxzOYSQtclsgSFojOiJVcfLgwAGgFcbNTWs");
		assertThat(testBaseParameterEntity.getCreatedIp()).isEqualTo("QejRDsoVLRCHMmuTikddglLOsJEkxXoOuHdY");
		assertThat(testBaseParameterEntity.getCreatedUserId()).isEqualTo("puGLhEvIENodOdOOFjUEsYUitPbBmJHHXVeG");
		assertThat(testBaseParameterEntity.getTitle()).isEqualTo("ibIOWeSDiQqzFXYNFquLisDdHnDTwCeNXjvc");
		assertThat(testBaseParameterEntity.getUpdatedBy()).isEqualTo("SaLfJZezmPwVIDybPInHtBjAnoUFnztIireN");
		assertThat(testBaseParameterEntity.getUpdatedIp()).isEqualTo("uxCuYsYfJWczspGtObPdjkKIBbxBuJmHvTwr");
		assertThat(testBaseParameterEntity.getUpdatedUserId()).isEqualTo("XhuGcRhINRDIuoJiQMsasyMuRtmXSjlXzrxU");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		Parameter entity = new Parameter();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("bVCtxcyMmrpzBAAiQoBXvSsuAGFRTZffHKzX");
		entity.setTitle("aZswdpcPaLQMxZSIlhJUzhnRfhVAycrEqFTo");
		entity.setContent("pdLuBjtXggZButPPlNEhbYhUsShYBaWKdOqd");
		entity.setCreatedUserId("GetTzdlVgsIZhKmynRsFWyNJSbbZmOlcDhoU");
		entity.setCreatedBy("qKijjdlmweojOlIhRNVncSbIzoeNUgwHAmER");
		entity.setCreatedIp("JPCFCKBjluQfociTIhYWlshlybOelqeIEDRW");
		entity.setUpdatedUserId("ZPpKLSUqmUmEyXWnEqXZhtKfYvYfwXZKEXRZ");
		entity.setUpdatedBy("sEoqHlSVzrfpiRNWXQkbOpwiExLgxLqXzzXT");
		entity.setUpdatedIp("QsQcTzyuLiQBnjQEotKFVBejxkRMhFQGyslu");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new Parameter()).size();
		Parameter updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setCode("sksXdqbXuPKMvRXovkibPyXcqsGSrWnzporP");
		updatedEntity.setTitle("FOjerpnnhsNsExLymMEfrsNAQUToGNZFmtEY");
		updatedEntity.setContent("twuHcNnKrBOrdcWoDnLBHBMerLowPRIDuJUv");
		updatedEntity.setCreatedUserId("KgSWTzLDieOqEqOAVIWSHStEknWewZCkoNTh");
		updatedEntity.setCreatedBy("YdsACKflnhyjhAnmSewoKsxYPkyTBjmHlaPW");
		updatedEntity.setCreatedIp("iQvWOKKghRFcfrNtAhcEfyvSOjuitLEaZCzT");
		updatedEntity.setUpdatedUserId("FMCTgXngaDyfKrEOZQjDxvGMFXrawjAUVPOL");
		updatedEntity.setUpdatedBy("LPQRaTXqXSKMHjwDuOaGZRGCldgYjWFyLMCU");
		updatedEntity.setUpdatedIp("ssdJYkdqHTaEHnYitMXgTyicCpDbzpYZKttR");
		this.mvc.perform(post("/BaseParameter/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<Parameter> baseParameterEntityList = dao.ListByPage(new Parameter());
		assertThat(baseParameterEntityList).hasSize(databaseSizeBeforeUpdate);
		Parameter testBaseParameterEntity = baseParameterEntityList.get(baseParameterEntityList.size() - 1);
		assertThat(testBaseParameterEntity.getCode()).isEqualTo("sksXdqbXuPKMvRXovkibPyXcqsGSrWnzporP");
		assertThat(testBaseParameterEntity.getContent()).isEqualTo("twuHcNnKrBOrdcWoDnLBHBMerLowPRIDuJUv");
		assertThat(testBaseParameterEntity.getCreatedBy()).isEqualTo("YdsACKflnhyjhAnmSewoKsxYPkyTBjmHlaPW");
		assertThat(testBaseParameterEntity.getCreatedIp()).isEqualTo("iQvWOKKghRFcfrNtAhcEfyvSOjuitLEaZCzT");
		assertThat(testBaseParameterEntity.getCreatedUserId()).isEqualTo("KgSWTzLDieOqEqOAVIWSHStEknWewZCkoNTh");
		assertThat(testBaseParameterEntity.getTitle()).isEqualTo("FOjerpnnhsNsExLymMEfrsNAQUToGNZFmtEY");
		assertThat(testBaseParameterEntity.getUpdatedBy()).isEqualTo("LPQRaTXqXSKMHjwDuOaGZRGCldgYjWFyLMCU");
		assertThat(testBaseParameterEntity.getUpdatedIp()).isEqualTo("ssdJYkdqHTaEHnYitMXgTyicCpDbzpYZKttR");
		assertThat(testBaseParameterEntity.getUpdatedUserId()).isEqualTo("FMCTgXngaDyfKrEOZQjDxvGMFXrawjAUVPOL");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		Parameter entity = new Parameter();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("bKkqkgqWfOEdeGtUmgYTEcquZLpwUZbESPtp");
		entity.setTitle("PQrwhpgZSeRIlWXPxlTcerYvVlgkXbsPJRPF");
		entity.setContent("kfbMFRCAwLVOkmwIiUvRuUBHOcyrMrRmMLjC");
		entity.setCreatedUserId("obwsDsDwaAFlIvFapznbMxsnWNAvfDSBFGKy");
		entity.setCreatedBy("hhJiRjmXGclWBjWSBjLTJNNFecrjkPIInAgW");
		entity.setCreatedIp("CMrCFyrLYRxfvZcARKZGrgkHwkLzOgzAJvSi");
		entity.setUpdatedUserId("IHwbOHkXdrkqTpAgAMRoAnnivpQOoYYwIzAF");
		entity.setUpdatedBy("hgathQRLUzJNSFqPNhmxFYnHPuVIHyLkpGTz");
		entity.setUpdatedIp("JlwUjIEKIHWTYsznBwxoKkqaKPYLOxmYqBsf");
		dao.insert(entity);
		this.mvc.perform(post("/BaseParameter/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
