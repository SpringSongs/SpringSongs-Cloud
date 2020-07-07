package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.Aritlce;
import org.authority.mapper.BaseAritlceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class BaseAritlceControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseAritlceMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		Aritlce entity = new Aritlce();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("wnBteFDbGrqjCBRwDWwruYNuIssPRfIkhLQw");
		entity.setContents("FKMnHLlkHuOFjiNVEZSEZROslTQGpLNJaQDC");
		entity.setAuthor("hcFONdVXcpjzhvnALuOfHSCQxfZpoUQkNXev");
		entity.setComeFrom("RrtQHlznYKikvjnPxPfkxnSfDfXZJfhiZddY");
		entity.setComeFromLink("OWNYdLBtdQkhqvRvNPfAukiRDwiyrVzHAGHp");
		entity.setCreatedUserId("DRkPozJkhfpdpkaDpDysfjNlXpgZfroFnUNk");
		entity.setCreatedBy("qabNjCRxMiUqXOWeypSpXtCDNujVUHqIkhSF");
		entity.setCreatedIp("SePYAzisdZYiZZuYOIkPkBFVCGPypZKTHwvD");
		entity.setUpdatedUserId("UCCIwOqhEvvGnsXBTFnsOvmtvXjDnrtXwjtp");
		entity.setUpdatedBy("IvAkMckwmRcasREqgMoTvOQyINbZIHbcrXGv");
		entity.setUpdatedIp("XhqicQTFOytQuUKzgZDXjQVtTFTNMkaincdg");
		this.mvc.perform(post("/BaseAritlce/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].author").value(hasItem("hcFONdVXcpjzhvnALuOfHSCQxfZpoUQkNXev")))
				.andExpect(jsonPath("$.data.[*].comeFrom").value(hasItem("RrtQHlznYKikvjnPxPfkxnSfDfXZJfhiZddY")))
				.andExpect(jsonPath("$.data.[*].comeFromLink").value(hasItem("OWNYdLBtdQkhqvRvNPfAukiRDwiyrVzHAGHp")))
				.andExpect(jsonPath("$.data.[*].contents").value(hasItem("FKMnHLlkHuOFjiNVEZSEZROslTQGpLNJaQDC")))
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("qabNjCRxMiUqXOWeypSpXtCDNujVUHqIkhSF")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("SePYAzisdZYiZZuYOIkPkBFVCGPypZKTHwvD")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("DRkPozJkhfpdpkaDpDysfjNlXpgZfroFnUNk")))
				.andExpect(jsonPath("$.data.[*].title").value(hasItem("wnBteFDbGrqjCBRwDWwruYNuIssPRfIkhLQw")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("IvAkMckwmRcasREqgMoTvOQyINbZIHbcrXGv")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("XhqicQTFOytQuUKzgZDXjQVtTFTNMkaincdg")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("UCCIwOqhEvvGnsXBTFnsOvmtvXjDnrtXwjtp")));
	}

	@Test
	public void testGet() throws Exception {
		Aritlce entity = new Aritlce();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("QfmVfJwVSKbpgwQqHqDknMUnYiioTUXLwVDt");
		entity.setContents("ueTKQlovpTDJvnVuDlxVqUpUPGsWJoLJzTqZ");
		entity.setAuthor("siYQUPrXCqyGEvphoOeBlboVViIPAmHPnCma");
		entity.setComeFrom("ZdqwKBJdFxaWlUISvidlpyxntQrYYsyNWfdK");
		entity.setComeFromLink("ifaLNtWBHjdrYEnAtVbRslColSiVmaJpRFKo");
		entity.setCreatedUserId("uoDIlmmzCjdtaOSEeHMbeTVEXwUPYwHSHWNf");
		entity.setCreatedBy("HvRQPdKvjGIYkxgvjfysoPBfLoQQYhqNlSvL");
		entity.setCreatedIp("MShTQFsfepCxygAlDvURlocVhbIIEaROoGWK");
		entity.setUpdatedUserId("fiTAFVaqKCvKwgcySQICeNZFVrREzhWPlLki");
		entity.setUpdatedBy("rCsfuTrnGHqaCqoVjTXPJUSgTuVQTiipDswB");
		entity.setUpdatedIp("YUnTvltpJDcFLcvbAQZLKoYhbAgLurvrVxNq");
		dao.insert(entity);
		this.mvc.perform(post("/BaseAritlce/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..author").value(hasItem("siYQUPrXCqyGEvphoOeBlboVViIPAmHPnCma")))
				.andExpect(jsonPath("$..comeFrom").value(hasItem("ZdqwKBJdFxaWlUISvidlpyxntQrYYsyNWfdK")))
				.andExpect(jsonPath("$..comeFromLink").value(hasItem("ifaLNtWBHjdrYEnAtVbRslColSiVmaJpRFKo")))
				.andExpect(jsonPath("$..contents").value(hasItem("ueTKQlovpTDJvnVuDlxVqUpUPGsWJoLJzTqZ")))
				.andExpect(jsonPath("$..createdBy").value(hasItem("HvRQPdKvjGIYkxgvjfysoPBfLoQQYhqNlSvL")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("MShTQFsfepCxygAlDvURlocVhbIIEaROoGWK")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("uoDIlmmzCjdtaOSEeHMbeTVEXwUPYwHSHWNf")))
				.andExpect(jsonPath("$..title").value(hasItem("QfmVfJwVSKbpgwQqHqDknMUnYiioTUXLwVDt")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("rCsfuTrnGHqaCqoVjTXPJUSgTuVQTiipDswB")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("YUnTvltpJDcFLcvbAQZLKoYhbAgLurvrVxNq")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("fiTAFVaqKCvKwgcySQICeNZFVrREzhWPlLki")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new Aritlce()).size();
		Aritlce entity = new Aritlce();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("YPXVSxrrkfKXRlrjBjeLTSIYQtTzDzCaRVLA");
		entity.setContents("dgkWLIplPFiMlEdmrHHYwjqmdZfuQdzhCKgT");
		entity.setAuthor("soMjsBukBRSsmgDpPcOSiLmVXJBKzehZVUKP");
		entity.setComeFrom("dmiAvOZKEJnRXSCfYYppiuprAWwqXpPgTTbA");
		entity.setComeFromLink("eNBplCUvlUwmwbDcgnQhMSHLuBlNsKLHKMqb");
		entity.setCreatedUserId("PHKkyAhwTjbBKofDpDEMaCSvNUDjeaniJlZj");
		entity.setCreatedBy("ufhfERvLfhLweIBKCellQqGIbcKaEYjigvAl");
		entity.setCreatedIp("xQLYmbIAkRYzcDQyQRUJGfqFbEmvrgGwsblc");
		entity.setUpdatedUserId("FlTliItUmEFsurujomcpeyNAoAJMijujMuqB");
		entity.setUpdatedBy("lppfyhKqgioSOuCeOubAIDhZWbaILGrkKCmw");
		entity.setUpdatedIp("wMrtcaihQfUYwHfnlhZSUzLXXGpdhCIpqrbF");
		this.mvc.perform(post("/BaseAritlce/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<Aritlce> baseAritlceEntityList = dao.ListByPage(new Aritlce());
		assertThat(baseAritlceEntityList).hasSize(databaseSizeBeforeCreate + 1);
		Aritlce testBaseAritlceEntity = baseAritlceEntityList.get(baseAritlceEntityList.size() - 1);
		assertThat(testBaseAritlceEntity.getAuthor()).isEqualTo("soMjsBukBRSsmgDpPcOSiLmVXJBKzehZVUKP");
		assertThat(testBaseAritlceEntity.getComeFrom()).isEqualTo("dmiAvOZKEJnRXSCfYYppiuprAWwqXpPgTTbA");
		assertThat(testBaseAritlceEntity.getComeFromLink()).isEqualTo("eNBplCUvlUwmwbDcgnQhMSHLuBlNsKLHKMqb");
		assertThat(testBaseAritlceEntity.getContents()).isEqualTo("dgkWLIplPFiMlEdmrHHYwjqmdZfuQdzhCKgT");
		assertThat(testBaseAritlceEntity.getCreatedBy()).isEqualTo("ufhfERvLfhLweIBKCellQqGIbcKaEYjigvAl");
		assertThat(testBaseAritlceEntity.getCreatedIp()).isEqualTo("xQLYmbIAkRYzcDQyQRUJGfqFbEmvrgGwsblc");
		assertThat(testBaseAritlceEntity.getCreatedUserId()).isEqualTo("PHKkyAhwTjbBKofDpDEMaCSvNUDjeaniJlZj");
		assertThat(testBaseAritlceEntity.getTitle()).isEqualTo("YPXVSxrrkfKXRlrjBjeLTSIYQtTzDzCaRVLA");
		assertThat(testBaseAritlceEntity.getUpdatedBy()).isEqualTo("lppfyhKqgioSOuCeOubAIDhZWbaILGrkKCmw");
		assertThat(testBaseAritlceEntity.getUpdatedIp()).isEqualTo("wMrtcaihQfUYwHfnlhZSUzLXXGpdhCIpqrbF");
		assertThat(testBaseAritlceEntity.getUpdatedUserId()).isEqualTo("FlTliItUmEFsurujomcpeyNAoAJMijujMuqB");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		Aritlce entity = new Aritlce();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("UikBbFAZbTgXdRqtsexyJZoEZGbCykpiZswH");
		entity.setContents("ENbeppjtgEvHlOITjrkuRjaXdyibfZYuoqSd");
		entity.setAuthor("bsAgQWqawgRIqldewuZYQmfYcLZWxiwHGrUq");
		entity.setComeFrom("iwbjlPNPitFuTyMKNuwnonEmeGEQlRaWiSON");
		entity.setComeFromLink("SSRSEZXJtKzBlzlUtxpsgiJjvLpQbjxwKIYn");
		entity.setCreatedUserId("gvEpggcTFcMtHgyfZkjIITqyaOksDkkpkmlK");
		entity.setCreatedBy("RlumuZBCHmAnyaBchCstauWogaDTlVHcQOGG");
		entity.setCreatedIp("vAbskiskLjjKDVJfTpMtBmiYFwmvqSCLthpW");
		entity.setUpdatedUserId("yvmDYmqkFxNxAwGHrRWflhnIPFAWyOyVhUYF");
		entity.setUpdatedBy("xcOEZrjKZKXwIIoVaEvYoVhjrJvRVXAwYHER");
		entity.setUpdatedIp("freSnvheLNMswLKAMyrsoiifqOEihJbDDGFF");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new Aritlce()).size();
		Aritlce updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setTitle("CgFxUwsTpQzVbvivzrVBfBTCttxHczMhCfob");
		updatedEntity.setContents("jSljJEiqJBhUanERHUkWtOssATNyOqCmTzHA");
		updatedEntity.setAuthor("UgzrcGcothWCuCnpLGeoaMkaosUuOSHgwhIt");
		updatedEntity.setComeFrom("DjEbfQCDeTffCdjnzwzjkErEFoLcDNzmkhRc");
		updatedEntity.setComeFromLink("dSBNKYsFuxMngmqLkkdTUmzguDcFBmGcnWyG");
		updatedEntity.setCreatedUserId("mpYGKnIrzpmVCrSZSgeSSfnCnihfysIrKYsX");
		updatedEntity.setCreatedBy("sMdcCPhDHJrrutoRlyquJRheBRJDJgebupvH");
		updatedEntity.setCreatedIp("BYNEbTroFSNOBawZGaXDjfgbaDSsvxxsTbkI");
		updatedEntity.setUpdatedUserId("EVptmgDNogoKHJJnNEuoPQzqLyjXrpKnPwxK");
		updatedEntity.setUpdatedBy("mYNlGDPNgCnzNmcCUBIkOSmcggkeeUtjrxhu");
		updatedEntity.setUpdatedIp("lGpFJeZnMOaCnvsCjYSqgEJrPmeZLvpQQQkL");
		this.mvc.perform(post("/BaseAritlce/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<Aritlce> baseAritlceEntityList = dao.ListByPage(new Aritlce());
		assertThat(baseAritlceEntityList).hasSize(databaseSizeBeforeUpdate);
		Aritlce testBaseAritlceEntity = baseAritlceEntityList.get(baseAritlceEntityList.size() - 1);
		assertThat(testBaseAritlceEntity.getAuthor()).isEqualTo("UgzrcGcothWCuCnpLGeoaMkaosUuOSHgwhIt");
		assertThat(testBaseAritlceEntity.getComeFrom()).isEqualTo("DjEbfQCDeTffCdjnzwzjkErEFoLcDNzmkhRc");
		assertThat(testBaseAritlceEntity.getComeFromLink()).isEqualTo("dSBNKYsFuxMngmqLkkdTUmzguDcFBmGcnWyG");
		assertThat(testBaseAritlceEntity.getContents()).isEqualTo("jSljJEiqJBhUanERHUkWtOssATNyOqCmTzHA");
		assertThat(testBaseAritlceEntity.getCreatedBy()).isEqualTo("sMdcCPhDHJrrutoRlyquJRheBRJDJgebupvH");
		assertThat(testBaseAritlceEntity.getCreatedIp()).isEqualTo("BYNEbTroFSNOBawZGaXDjfgbaDSsvxxsTbkI");
		assertThat(testBaseAritlceEntity.getCreatedUserId()).isEqualTo("mpYGKnIrzpmVCrSZSgeSSfnCnihfysIrKYsX");
		assertThat(testBaseAritlceEntity.getTitle()).isEqualTo("CgFxUwsTpQzVbvivzrVBfBTCttxHczMhCfob");
		assertThat(testBaseAritlceEntity.getUpdatedBy()).isEqualTo("mYNlGDPNgCnzNmcCUBIkOSmcggkeeUtjrxhu");
		assertThat(testBaseAritlceEntity.getUpdatedIp()).isEqualTo("lGpFJeZnMOaCnvsCjYSqgEJrPmeZLvpQQQkL");
		assertThat(testBaseAritlceEntity.getUpdatedUserId()).isEqualTo("EVptmgDNogoKHJJnNEuoPQzqLyjXrpKnPwxK");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		Aritlce entity = new Aritlce();
		entity.setId(UUID.randomUUID().toString());
		entity.setTitle("zTxgRSsrVJdrkQrVjeydLsTMIGnJOydYivnl");
		entity.setContents("OVUtAsBqHQoscruBNsjlFmalwZSPeyhSCziJ");
		entity.setAuthor("DSaeETGtUiNnAghEvTJqeVDOasTujHeazueq");
		entity.setComeFrom("OhglGbmXvdxmcjHukRaFmHjJxSJzWlddoPgJ");
		entity.setComeFromLink("aDTAYusnWforMUgCJkObGcwNEOZPRIEbKjGf");
		entity.setCreatedUserId("hWXXwRbIwWCGJAzzFWnHmPLnnGReyubEErwX");
		entity.setCreatedBy("gVBxBrfVHWzSBIBTBEkzFJKlMcwNKUdsLKJU");
		entity.setCreatedIp("CoEjEpSfYSulUUwiDfyxNTIosmQrapRCByia");
		entity.setUpdatedUserId("hhJKCQLPHOhTkomfJokMIMuGBPkosnFnElNy");
		entity.setUpdatedBy("aUSVNstIRdpSPopktASFFSFtJwrCjuiBIFMP");
		entity.setUpdatedIp("FZQHysVDPwaoLaRXERWqOqLfTyHVeWTvSHxa");
		dao.insert(entity);
		this.mvc.perform(post("/BaseAritlce/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
