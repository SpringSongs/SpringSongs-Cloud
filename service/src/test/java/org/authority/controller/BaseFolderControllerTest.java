package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.Folder;
import org.authority.mapper.BaseFolderMapper;
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
public class BaseFolderControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseFolderMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		Folder entity = new Folder();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("LlCmcIstsMnnzBHIBlsDxYbmdGhQPcWcATKz");
		entity.setDescription("EdwKhNtxmbgMPAGwqAazdFUOoRBXSMWRvHPU");
		entity.setDictionaryCode("mPQtqOoYPdACvqAHVqCcvOnGEumKQosvAkBw");
		entity.setDictionaryName("uZOYKKwdDFtHuXeWSoOvPdTTKDaOBGSZBsEu");
		entity.setCreatedUserId("TUKjUmglrXVPPjrKIGczVOlQHByZpGYIxwGt");
		entity.setCreatedBy("okgTFXijuKTrZKIhYEIhBJPQdktGqZlaEQWV");
		entity.setCreatedIp("RwDJSLXrwJfkjFIYIJKKjTNdcTFLxfIyshoS");
		entity.setUpdatedUserId("kizhuLzTGJUezhIopTvsczKBjmxTCYrygeUP");
		entity.setUpdatedBy("yIzZysYnJpHWrdZkDVuDLltSvokROGixHnZi");
		entity.setUpdatedIp("NVnoOZCJEGgzHyELJjCaEATfvHHYXgfToISP");
		this.mvc.perform(post("/BaseFolder/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("okgTFXijuKTrZKIhYEIhBJPQdktGqZlaEQWV")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("RwDJSLXrwJfkjFIYIJKKjTNdcTFLxfIyshoS")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("TUKjUmglrXVPPjrKIGczVOlQHByZpGYIxwGt")))
				.andExpect(jsonPath("$.data.[*].desc").value(hasItem("EdwKhNtxmbgMPAGwqAazdFUOoRBXSMWRvHPU")))
				.andExpect(jsonPath("$.data.[*].dictionaryCode").value(hasItem("mPQtqOoYPdACvqAHVqCcvOnGEumKQosvAkBw")))
				.andExpect(jsonPath("$.data.[*].dictionaryName").value(hasItem("uZOYKKwdDFtHuXeWSoOvPdTTKDaOBGSZBsEu")))
				.andExpect(jsonPath("$.data.[*].title").value(hasItem("LlCmcIstsMnnzBHIBlsDxYbmdGhQPcWcATKz")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("yIzZysYnJpHWrdZkDVuDLltSvokROGixHnZi")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("NVnoOZCJEGgzHyELJjCaEATfvHHYXgfToISP")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("kizhuLzTGJUezhIopTvsczKBjmxTCYrygeUP")));
	}

	@Test
	public void testGet() throws Exception {
		Folder entity = new Folder();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("KGQYneclkuOPFYGbJzMvdKibDRooTgOXVrjB");
		entity.setDescription("UMsPmMzoYVdtvcqzGGkEXLSJcGLMaYWVAMwR");
		entity.setDictionaryCode("LfBftIufUrfQBVggRNKMAWtFIKDCYAgfCkyo");
		entity.setDictionaryName("kLFewAOpLqoxMWxmskNUEgdFBrztBqdUAdBO");
		entity.setCreatedUserId("SKwPBxQvypqQeHVAKxkjHtyeDqBEStoXHUuN");
		entity.setCreatedBy("QiMSwPGoljprEUUGuPiEBWibEAnbGWjCcRDb");
		entity.setCreatedIp("llHGWtEDvJxBkUQdipBPtOYpNAiAeBOPNuDf");
		entity.setUpdatedUserId("KmTmmwBWgMuNBkGcqsseGiDweVWeHfZEYMtR");
		entity.setUpdatedBy("OViRrKdhubhRwJlGIXwlLTPcxQiUBKMtfRmz");
		entity.setUpdatedIp("zcerBKdlkepeyZyUZKGZvBwapRmhlrKqEpOJ");
		dao.insert(entity);
		this.mvc.perform(post("/BaseFolder/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..createdBy").value(hasItem("QiMSwPGoljprEUUGuPiEBWibEAnbGWjCcRDb")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("llHGWtEDvJxBkUQdipBPtOYpNAiAeBOPNuDf")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("SKwPBxQvypqQeHVAKxkjHtyeDqBEStoXHUuN")))
				.andExpect(jsonPath("$..desc").value(hasItem("UMsPmMzoYVdtvcqzGGkEXLSJcGLMaYWVAMwR")))
				.andExpect(jsonPath("$..dictionaryCode").value(hasItem("LfBftIufUrfQBVggRNKMAWtFIKDCYAgfCkyo")))
				.andExpect(jsonPath("$..dictionaryName").value(hasItem("kLFewAOpLqoxMWxmskNUEgdFBrztBqdUAdBO")))
				.andExpect(jsonPath("$..title").value(hasItem("KGQYneclkuOPFYGbJzMvdKibDRooTgOXVrjB")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("OViRrKdhubhRwJlGIXwlLTPcxQiUBKMtfRmz")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("zcerBKdlkepeyZyUZKGZvBwapRmhlrKqEpOJ")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("KmTmmwBWgMuNBkGcqsseGiDweVWeHfZEYMtR")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new Folder()).size();
		Folder entity = new Folder();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("sCRtYTkrgYSILttVqUCprjljXByBihJtDbBm");
		entity.setDescription("TWJjGxxFhUyNEKiPWmDYbMzmVYpKvuqgRSXe");
		entity.setDictionaryCode("qzsYrSDLthrgAfdKygWqrMgSqAvWNoiVPCqS");
		entity.setDictionaryName("FPodnyppeFZiOziYFGtTLyMlZyflFpMmbGPw");
		entity.setCreatedUserId("DUMrntyZwOwSuscCusStlwrnXdafZtKilRJn");
		entity.setCreatedBy("BKZNyHAzhFXTZCmRLpRMerydMrJZavXBRPqz");
		entity.setCreatedIp("ADksZaeEBcYXKTBZiyjSfLdWAtuCzGzkLfro");
		entity.setUpdatedUserId("PiBPeVsdlRdbsDQTFvFoSiZVjfRgNydlQjNu");
		entity.setUpdatedBy("lqPdxGGnUdaHzvrlNHutjEXoPocKHTwefkZH");
		entity.setUpdatedIp("WRlUgOBxXnimyCqfBbNDqWRbwIUrSkbiVpjl");
		this.mvc.perform(post("/BaseFolder/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<Folder> baseFolderEntityList = dao.ListByPage(new Folder());
		assertThat(baseFolderEntityList).hasSize(databaseSizeBeforeCreate + 1);
		Folder testBaseFolderEntity = baseFolderEntityList.get(baseFolderEntityList.size() - 1);
		assertThat(testBaseFolderEntity.getCreatedBy()).isEqualTo("BKZNyHAzhFXTZCmRLpRMerydMrJZavXBRPqz");
		assertThat(testBaseFolderEntity.getCreatedIp()).isEqualTo("ADksZaeEBcYXKTBZiyjSfLdWAtuCzGzkLfro");
		assertThat(testBaseFolderEntity.getCreatedUserId()).isEqualTo("DUMrntyZwOwSuscCusStlwrnXdafZtKilRJn");
		assertThat(testBaseFolderEntity.getDescription()).isEqualTo("TWJjGxxFhUyNEKiPWmDYbMzmVYpKvuqgRSXe");
		assertThat(testBaseFolderEntity.getDictionaryCode()).isEqualTo("qzsYrSDLthrgAfdKygWqrMgSqAvWNoiVPCqS");
		assertThat(testBaseFolderEntity.getDictionaryName()).isEqualTo("FPodnyppeFZiOziYFGtTLyMlZyflFpMmbGPw");
		assertThat(testBaseFolderEntity.getTitle()).isEqualTo("sCRtYTkrgYSILttVqUCprjljXByBihJtDbBm");
		assertThat(testBaseFolderEntity.getUpdatedBy()).isEqualTo("lqPdxGGnUdaHzvrlNHutjEXoPocKHTwefkZH");
		assertThat(testBaseFolderEntity.getUpdatedIp()).isEqualTo("WRlUgOBxXnimyCqfBbNDqWRbwIUrSkbiVpjl");
		assertThat(testBaseFolderEntity.getUpdatedUserId()).isEqualTo("PiBPeVsdlRdbsDQTFvFoSiZVjfRgNydlQjNu");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		Folder entity = new Folder();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("zxEDcTEHdaaeePCNEIfYxTgntBwwypohjDKZ");
		entity.setDescription("MjZVStaSEpYswSzNsDVPnkLtdqoRseDdrKJj");
		entity.setDictionaryCode("daafsPBbmsqzxltcVlKRlCKCaRZSktDHmjwx");
		entity.setDictionaryName("jfIBvOspFRfoQqwDQezkaZvDOMCrKcHKWaFq");
		entity.setCreatedUserId("TOnysUDDXmxSwxNEavkADLoyrpCuvkwlhwnx");
		entity.setCreatedBy("GWAIhcojCkMailKWmVvDVTCsHFnPOzhrjKsi");
		entity.setCreatedIp("vupJVhxCZTaWzHFvcfsvKRrCwTOrNmnvPqnd");
		entity.setUpdatedUserId("eYePRpuFPDKCjjfiHtKSOfrUlcIndSjnXXQl");
		entity.setUpdatedBy("ElHGpkFqsLMVkuhJpxUHPCxiSqLxCMqijWxw");
		entity.setUpdatedIp("JUVWmFNJeQMzMthYVnAIishiYliWOnXMJxHg");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new Folder()).size();
		Folder updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setTitle("ggGDNHIrdHETRKUqUmkSZAjQwfDBlgaSvBej");
		updatedEntity.setDescription("aYAiqRdjriCBcMZBXrkMswpcuGjnTaYYXuMV");
		updatedEntity.setDictionaryCode("fcgmGLmmasGZdmNLofxScPToWbsGyPtKQHqe");
		updatedEntity.setDictionaryName("DUjwkEWkQCvhbMiMdlmPSiqVbJUdEHQksYYF");
		updatedEntity.setCreatedUserId("DkGsQBdqMIPaiZOqGtsJyJceoGEZcbXQfxpL");
		updatedEntity.setCreatedBy("eVRhCiGfpiYBdPojPowfJURFGrzBulpBzdCs");
		updatedEntity.setCreatedIp("fIGvDEBgRDeBNsMtpQcqvcoTAUMHiPQKfcqw");
		updatedEntity.setUpdatedUserId("MSldmbdlKEMzQMAydVthclRLEIKsHZSTkJSa");
		updatedEntity.setUpdatedBy("tiimpXojSTnsbWnYaGuRmePuMvRjXblECsal");
		updatedEntity.setUpdatedIp("WSxZhYDkYmoWBwmBWoLRHJIARUPcxVUvWGCW");
		this.mvc.perform(post("/BaseFolder/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<Folder> baseFolderEntityList = dao.ListByPage(new Folder());
		assertThat(baseFolderEntityList).hasSize(databaseSizeBeforeUpdate);
		Folder testBaseFolderEntity = baseFolderEntityList.get(baseFolderEntityList.size() - 1);
		assertThat(testBaseFolderEntity.getCreatedBy()).isEqualTo("eVRhCiGfpiYBdPojPowfJURFGrzBulpBzdCs");
		assertThat(testBaseFolderEntity.getCreatedIp()).isEqualTo("fIGvDEBgRDeBNsMtpQcqvcoTAUMHiPQKfcqw");
		assertThat(testBaseFolderEntity.getCreatedUserId()).isEqualTo("DkGsQBdqMIPaiZOqGtsJyJceoGEZcbXQfxpL");
		assertThat(testBaseFolderEntity.getDescription()).isEqualTo("aYAiqRdjriCBcMZBXrkMswpcuGjnTaYYXuMV");
		assertThat(testBaseFolderEntity.getDictionaryCode()).isEqualTo("fcgmGLmmasGZdmNLofxScPToWbsGyPtKQHqe");
		assertThat(testBaseFolderEntity.getDictionaryName()).isEqualTo("DUjwkEWkQCvhbMiMdlmPSiqVbJUdEHQksYYF");
		assertThat(testBaseFolderEntity.getTitle()).isEqualTo("ggGDNHIrdHETRKUqUmkSZAjQwfDBlgaSvBej");
		assertThat(testBaseFolderEntity.getUpdatedBy()).isEqualTo("tiimpXojSTnsbWnYaGuRmePuMvRjXblECsal");
		assertThat(testBaseFolderEntity.getUpdatedIp()).isEqualTo("WSxZhYDkYmoWBwmBWoLRHJIARUPcxVUvWGCW");
		assertThat(testBaseFolderEntity.getUpdatedUserId()).isEqualTo("MSldmbdlKEMzQMAydVthclRLEIKsHZSTkJSa");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		Folder entity = new Folder();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("UOmVcQRHheOElCKeRpuRXuuZKkDwMYRlTkcA");
		entity.setDescription("XIqRiLpZHqbUMrrGwxEbSTcSOKcHxmiTqGSQ");
		entity.setDictionaryCode("iqKJAroFeSltIJqadDGGXJbzIvOfrLRSTcyi");
		entity.setDictionaryName("kIwRSfRDVEjsHULfkVvrGHaNQrMCJAvZzpLx");
		entity.setCreatedUserId("rTCajpwqAHYNVXqtclyNXcHooTgTDPZdyqgU");
		entity.setCreatedBy("dZhCBMiectSTWKkdvOMUEofDQZMstPVsAZpM");
		entity.setCreatedIp("nbwuvPkRFmQBYjtFefRKXNnlwcmqivsdArut");
		entity.setUpdatedUserId("czKNGSPumIHmQiDoVgsCwBlIQKDgMeipHzNO");
		entity.setUpdatedBy("LIzpZxGWivvlidNivEcNRnXgoXQLkssMIDDx");
		entity.setUpdatedIp("WojrXaTzUQiejMsHuIpYZLxwzppezzHeDphL");
		dao.insert(entity);
		this.mvc.perform(post("/BaseFolder/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
