package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.Attachment;
import org.authority.mapper.BaseFileMapper;
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
public class BaseFileControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseFileMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		Attachment entity = new Attachment();
		entity.setId(UUID.randomUUID().toString());
		entity.setFolderId("QiXFAdWnuNWrTNkClnHyKoQEteoPRokEufgc");
		entity.setFolderName("jkdlvneCNHoPplGSRFnTawpCLFoSuLbvbsos");
		entity.setPath("nMMylbYcauJDhbqqBaBniQYHLGUHqwYZzKWK");
		entity.setCreatedUserId("pNBLvmjNaerGVduRBTeqzAZcbHtzavPFxDnm");
		entity.setCreatedBy("hTouONHASAtPvQGoSHWHUpyPwVmXqkmOsuVW");
		entity.setCreatedIp("RdcUOYYshGOSfvYwpEmPceoAElACRYxLkwEq");
		entity.setUpdatedUserId("xmiTsbfwkVGwrcMixFrdSEUfxMHXttAfEtmT");
		entity.setUpdatedBy("oKiKHhffclmrCyqgTAuhtXwkjyoIzMCDeMnU");
		entity.setUpdatedIp("iPBHabmfuuJMHchqfTxVGSvhLitjUOsFAPvV");
		this.mvc.perform(post("/BaseFile/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("hTouONHASAtPvQGoSHWHUpyPwVmXqkmOsuVW")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("RdcUOYYshGOSfvYwpEmPceoAElACRYxLkwEq")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("pNBLvmjNaerGVduRBTeqzAZcbHtzavPFxDnm")))
				.andExpect(jsonPath("$.data.[*].folderId").value(hasItem("QiXFAdWnuNWrTNkClnHyKoQEteoPRokEufgc")))
				.andExpect(jsonPath("$.data.[*].folderName").value(hasItem("jkdlvneCNHoPplGSRFnTawpCLFoSuLbvbsos")))
				.andExpect(jsonPath("$.data.[*].path").value(hasItem("nMMylbYcauJDhbqqBaBniQYHLGUHqwYZzKWK")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("oKiKHhffclmrCyqgTAuhtXwkjyoIzMCDeMnU")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("iPBHabmfuuJMHchqfTxVGSvhLitjUOsFAPvV")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("xmiTsbfwkVGwrcMixFrdSEUfxMHXttAfEtmT")));
	}

	@Test
	public void testGet() throws Exception {
		Attachment entity = new Attachment();
		entity.setId(UUID.randomUUID().toString());
		entity.setFolderId("vZmDszUxkecIPKHihKEPJKTzGPVqWmuwtbtc");
		entity.setFolderName("GaZUwngHoBRSwgTpeORlpLntqqcUCLhBZugp");
		entity.setPath("lHhnjOjJIUpbbEtJJgwgiYTyTttONVuQChFk");
		entity.setCreatedUserId("mDobLRRVwVCymkAUIIDbqTGjBUqfnbGmiqow");
		entity.setCreatedBy("SqzECxuiTUOzwrghuomSRTBAcIBZJMeTKJRX");
		entity.setCreatedIp("aOeYNNtkBwEenkEhCbyGFEaILtWKGLtVAPaQ");
		entity.setUpdatedUserId("tNOhWEnvbbWrDNwXkstjOZCNlrokwtLiMFrt");
		entity.setUpdatedBy("QbyOXRrGrgdbKWpShIFaFmeDoqGnMDDOukEF");
		entity.setUpdatedIp("pHATUBhNboQAwEJrsfFfmOCTnrHeeqjmhCmR");
		dao.insert(entity);
		this.mvc.perform(post("/BaseFile/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..createdBy").value(hasItem("SqzECxuiTUOzwrghuomSRTBAcIBZJMeTKJRX")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("aOeYNNtkBwEenkEhCbyGFEaILtWKGLtVAPaQ")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("mDobLRRVwVCymkAUIIDbqTGjBUqfnbGmiqow")))
				.andExpect(jsonPath("$..folderId").value(hasItem("vZmDszUxkecIPKHihKEPJKTzGPVqWmuwtbtc")))
				.andExpect(jsonPath("$..folderName").value(hasItem("GaZUwngHoBRSwgTpeORlpLntqqcUCLhBZugp")))
				.andExpect(jsonPath("$..path").value(hasItem("lHhnjOjJIUpbbEtJJgwgiYTyTttONVuQChFk")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("QbyOXRrGrgdbKWpShIFaFmeDoqGnMDDOukEF")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("pHATUBhNboQAwEJrsfFfmOCTnrHeeqjmhCmR")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("tNOhWEnvbbWrDNwXkstjOZCNlrokwtLiMFrt")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new Attachment()).size();
		Attachment entity = new Attachment();
		entity.setId(UUID.randomUUID().toString());
		entity.setFolderId("RzjWSdBdreWVhwyTagWAljCBqtpxCYNDILwN");
		entity.setFolderName("caSdaokFnyiwNgtAkBGBOOAHbjlZmnNxybzw");
		entity.setPath("PriEvXLSAfZdTGbdZcZogQxxAsoiZGxKzHMd");
		entity.setCreatedUserId("VecxYEUzwPiyiPNEAMeledmkNUjYlkCfuBQT");
		entity.setCreatedBy("qeqsGZjBJssvEUvMbMirjqbwyOJEjyymqmwp");
		entity.setCreatedIp("FYvLqtTPyUlHiYmTJJTaBEtgRJxaHxRwzvfO");
		entity.setUpdatedUserId("rxPVJPHCATjWIVkFFdvTizQOdJlWRNGHzYWG");
		entity.setUpdatedBy("ydMuszODUCJYTFgOWXzjNdwgmwyYDJywhriX");
		entity.setUpdatedIp("qDGPNEwjKekRermDisoudhuyWUqCicBEOgYK");
		this.mvc.perform(post("/BaseFile/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<Attachment> baseFileEntityList = dao.ListByPage(new Attachment());
		assertThat(baseFileEntityList).hasSize(databaseSizeBeforeCreate + 1);
		Attachment testBaseFileEntity = baseFileEntityList.get(baseFileEntityList.size() - 1);
		assertThat(testBaseFileEntity.getCreatedBy()).isEqualTo("qeqsGZjBJssvEUvMbMirjqbwyOJEjyymqmwp");
		assertThat(testBaseFileEntity.getCreatedIp()).isEqualTo("FYvLqtTPyUlHiYmTJJTaBEtgRJxaHxRwzvfO");
		assertThat(testBaseFileEntity.getCreatedUserId()).isEqualTo("VecxYEUzwPiyiPNEAMeledmkNUjYlkCfuBQT");
		assertThat(testBaseFileEntity.getFolderId()).isEqualTo("RzjWSdBdreWVhwyTagWAljCBqtpxCYNDILwN");
		assertThat(testBaseFileEntity.getFolderName()).isEqualTo("caSdaokFnyiwNgtAkBGBOOAHbjlZmnNxybzw");
		assertThat(testBaseFileEntity.getPath()).isEqualTo("PriEvXLSAfZdTGbdZcZogQxxAsoiZGxKzHMd");
		assertThat(testBaseFileEntity.getUpdatedBy()).isEqualTo("ydMuszODUCJYTFgOWXzjNdwgmwyYDJywhriX");
		assertThat(testBaseFileEntity.getUpdatedIp()).isEqualTo("qDGPNEwjKekRermDisoudhuyWUqCicBEOgYK");
		assertThat(testBaseFileEntity.getUpdatedUserId()).isEqualTo("rxPVJPHCATjWIVkFFdvTizQOdJlWRNGHzYWG");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		Attachment entity = new Attachment();
		entity.setId(UUID.randomUUID().toString());
		entity.setFolderId("mCDRYLefAQnAQFekvIqqHZpDpnIjXMvfeMtu");
		entity.setFolderName("qRMIebRdFAgUWNNTEkhhulFnekBnEJopinsC");
		entity.setPath("hEQMDwDMWzqpzhRbhwkZsFTPdpLZnPSCOzGp");
		entity.setCreatedUserId("ZCVyDTrIbSVcQomrUQzuWDVjCaDvQfHaLsyX");
		entity.setCreatedBy("aHOVJzUYlogVurWATvxzwkQeYPAUWWNuAySv");
		entity.setCreatedIp("QHCiktaWKzoHfuxzexNKopvvhBEBWPLcsVeb");
		entity.setUpdatedUserId("ATalYQaPAxXUhLwONzDzxHaYuQrztEKEcbFP");
		entity.setUpdatedBy("MRxqJpMKPdnVUJsKfcQnKkxYZmvubiTrrivD");
		entity.setUpdatedIp("dLUuaSrZypXpsYCXBpMwAuRzvTultGqPsfYJ");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new Attachment()).size();
		Attachment updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setFolderId("mdpHRLceSpUphIRiGXRWTTVnVVYXutJkKxbl");
		updatedEntity.setFolderName("VNQRHaCTBlCilOCYZMvBjWvAOUnJOrwqjhVj");
		updatedEntity.setPath("RbDrVXXJiwypMAmuFzrDWkChsNgqoIRMGDUO");
		updatedEntity.setCreatedUserId("LFfsVsJpppnltQGlYtyiDBNyntqtYVYSkKdw");
		updatedEntity.setCreatedBy("GaDlctlBSUxNMNhiSjSUVbhwKgweiUIRxVOa");
		updatedEntity.setCreatedIp("bVQSZsJzNQsQyJeJHRHWoNYNzBapgscCJNGa");
		updatedEntity.setUpdatedUserId("lIKKjIcPljwQXcDAYjLYvxKQRXUKSsgrzOvw");
		updatedEntity.setUpdatedBy("FEygVPbFguwTNnjVJsfNiGdxUTRzfVAmZqFo");
		updatedEntity.setUpdatedIp("VGwtcfMAmgkgMoIfbcRxqDRHKGgQoBHzUALV");
		this.mvc.perform(post("/BaseFile/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<Attachment> baseFileEntityList = dao.ListByPage(new Attachment());
		assertThat(baseFileEntityList).hasSize(databaseSizeBeforeUpdate);
		Attachment testBaseFileEntity = baseFileEntityList.get(baseFileEntityList.size() - 1);
		assertThat(testBaseFileEntity.getCreatedBy()).isEqualTo("GaDlctlBSUxNMNhiSjSUVbhwKgweiUIRxVOa");
		assertThat(testBaseFileEntity.getCreatedIp()).isEqualTo("bVQSZsJzNQsQyJeJHRHWoNYNzBapgscCJNGa");
		assertThat(testBaseFileEntity.getCreatedUserId()).isEqualTo("LFfsVsJpppnltQGlYtyiDBNyntqtYVYSkKdw");
		assertThat(testBaseFileEntity.getFolderId()).isEqualTo("mdpHRLceSpUphIRiGXRWTTVnVVYXutJkKxbl");
		assertThat(testBaseFileEntity.getFolderName()).isEqualTo("VNQRHaCTBlCilOCYZMvBjWvAOUnJOrwqjhVj");
		assertThat(testBaseFileEntity.getPath()).isEqualTo("RbDrVXXJiwypMAmuFzrDWkChsNgqoIRMGDUO");
		assertThat(testBaseFileEntity.getUpdatedBy()).isEqualTo("FEygVPbFguwTNnjVJsfNiGdxUTRzfVAmZqFo");
		assertThat(testBaseFileEntity.getUpdatedIp()).isEqualTo("VGwtcfMAmgkgMoIfbcRxqDRHKGgQoBHzUALV");
		assertThat(testBaseFileEntity.getUpdatedUserId()).isEqualTo("lIKKjIcPljwQXcDAYjLYvxKQRXUKSsgrzOvw");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		Attachment entity = new Attachment();
		entity.setId(UUID.randomUUID().toString());
		entity.setFolderId("asdLBpEAcNKoXqMZwBDVsDsReGyKCzKLldDU");
		entity.setFolderName("FvFdqDOfUXfvpCSjgNllenKzuiHSVDCnBEPN");
		entity.setPath("sObEWMgayqafSktdVMHJeiwpDQvggsWgczPc");
		entity.setCreatedUserId("xSLphYSRPEGtwdCKmgHzhjOlYSRpOrPmdDSN");
		entity.setCreatedBy("ThoFJsaqDcyJuVJvtwPTIYtCGwIktHZzVEhE");
		entity.setCreatedIp("qZBysnmNpjLNibEgeNxhMLHTBbMwoKrVZrFE");
		entity.setUpdatedUserId("QpHlNwPjxIUIkabuKanrcsAolMjMsaWodPSv");
		entity.setUpdatedBy("aGluCzMyklQDfuPVMPcfJEBSCosdBUGIKIWD");
		entity.setUpdatedIp("jbrWLemkYzOxSiRBOwbxlLXVPWeOTfiTangt");
		dao.insert(entity);
		this.mvc.perform(post("/BaseFile/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
