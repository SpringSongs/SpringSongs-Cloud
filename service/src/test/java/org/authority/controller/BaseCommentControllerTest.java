package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.Comment;
import org.authority.mapper.BaseCommentMapper;
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
public class BaseCommentControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseCommentMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		Comment entity = new Comment();
		entity.setId(UUID.randomUUID().toString());
		entity.setContent("DTmyuXaWowENMcwWSiXRSTlMZlaqjdkOoaun");
		entity.setObjectId("KQfdLTgAjRblTuSuLERZdWTbLjfeqCGoJito");
		entity.setCreatedUserId("wULYfnzYZrcvcZyCxkfJmCzJuBFcTxpysnvm");
		entity.setCreatedBy("yqIzHbDpFmSNbUGxBNwzKvNXDfHhlynviXkL");
		entity.setCreatedIp("QWtuDiUYCduoVqUofOCeUPOmSHqHnHARvgWS");
		entity.setUpdatedUserId("XlDpBZYDnGMCVKUSrfESvfPUFbVFYSTvBJmN");
		entity.setUpdatedBy("NiAMwhbbKOmdIxPuiLyOjaJLOsSWrtJVYeaW");
		entity.setUpdatedIp("GTzdfZMreraVDgGPwCxCKlwaEWuJjBalJUWI");
		this.mvc.perform(post("/BaseComment/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].content").value(hasItem("DTmyuXaWowENMcwWSiXRSTlMZlaqjdkOoaun")))
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("yqIzHbDpFmSNbUGxBNwzKvNXDfHhlynviXkL")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("QWtuDiUYCduoVqUofOCeUPOmSHqHnHARvgWS")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("wULYfnzYZrcvcZyCxkfJmCzJuBFcTxpysnvm")))
				.andExpect(jsonPath("$.data.[*].objectId").value(hasItem("KQfdLTgAjRblTuSuLERZdWTbLjfeqCGoJito")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("NiAMwhbbKOmdIxPuiLyOjaJLOsSWrtJVYeaW")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("GTzdfZMreraVDgGPwCxCKlwaEWuJjBalJUWI")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("XlDpBZYDnGMCVKUSrfESvfPUFbVFYSTvBJmN")));
	}

	@Test
	public void testGet() throws Exception {
		Comment entity = new Comment();
		entity.setId(UUID.randomUUID().toString());
		entity.setContent("PgqBNHROELCJVGwKAUxiBHscEvkzfJVbeGRb");
		entity.setObjectId("SUwOwAObmaEpaDtuKrdXiLhyfdWjYSkzICka");
		entity.setCreatedUserId("KEkrbEEorbkAbfjdaniElratfehHzWCkaLjE");
		entity.setCreatedBy("jvnkALCzZcbtlNQtjkJXtJJOtfFdYStFiIZT");
		entity.setCreatedIp("WICSJNoSerZdtKySIvNJmSJTEGTvoDcDBpNH");
		entity.setUpdatedUserId("iFxqIZJDsywlEEpbrQLlVHNcoOwWAmWoLLWr");
		entity.setUpdatedBy("VGuPitzBYtKjWXXJOSpPEBCjVQHnFscAPfWn");
		entity.setUpdatedIp("CGdltuDBRIVBexJuFxxbswfkxPDSQRyqFYZY");
		dao.insert(entity);
		this.mvc.perform(post("/BaseComment/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..content").value(hasItem("PgqBNHROELCJVGwKAUxiBHscEvkzfJVbeGRb")))
				.andExpect(jsonPath("$..createdBy").value(hasItem("jvnkALCzZcbtlNQtjkJXtJJOtfFdYStFiIZT")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("WICSJNoSerZdtKySIvNJmSJTEGTvoDcDBpNH")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("KEkrbEEorbkAbfjdaniElratfehHzWCkaLjE")))
				.andExpect(jsonPath("$..objectId").value(hasItem("SUwOwAObmaEpaDtuKrdXiLhyfdWjYSkzICka")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("VGuPitzBYtKjWXXJOSpPEBCjVQHnFscAPfWn")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("CGdltuDBRIVBexJuFxxbswfkxPDSQRyqFYZY")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("iFxqIZJDsywlEEpbrQLlVHNcoOwWAmWoLLWr")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new Comment()).size();
		Comment entity = new Comment();
		entity.setId(UUID.randomUUID().toString());
		entity.setContent("HYvUIfQWRjiivpIOvzWEQqLpkIhyRQjPewKY");
		entity.setObjectId("CwjUetoENVbLBiJDqxylSxOKdkKXNxvToYQf");
		entity.setCreatedUserId("CIBxsVepbxqHyEmOFWYrepvUwJaBWAeRtqYV");
		entity.setCreatedBy("IxAZlqHBKgoiLFZDHSqDAzzFOQNqFpTvwJOw");
		entity.setCreatedIp("SMYdGFEaAAPyiZkHkRXkxBetPVvAczlcaIDp");
		entity.setUpdatedUserId("IEJhrsGBwxrXuDWwHIdlQPKLatWzdBKbLZmH");
		entity.setUpdatedBy("rNMXZUWwNHNMPpmnJgSdKiaAelZauRTXAxkq");
		entity.setUpdatedIp("vDcBYAnJRNsTAYDusWVgiZVvOpZZZwncmUrn");
		this.mvc.perform(post("/BaseComment/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<Comment> baseCommentEntityList = dao.ListByPage(new Comment());
		assertThat(baseCommentEntityList).hasSize(databaseSizeBeforeCreate + 1);
		Comment testBaseCommentEntity = baseCommentEntityList.get(baseCommentEntityList.size() - 1);
		assertThat(testBaseCommentEntity.getContent()).isEqualTo("HYvUIfQWRjiivpIOvzWEQqLpkIhyRQjPewKY");
		assertThat(testBaseCommentEntity.getCreatedBy()).isEqualTo("IxAZlqHBKgoiLFZDHSqDAzzFOQNqFpTvwJOw");
		assertThat(testBaseCommentEntity.getCreatedIp()).isEqualTo("SMYdGFEaAAPyiZkHkRXkxBetPVvAczlcaIDp");
		assertThat(testBaseCommentEntity.getCreatedUserId()).isEqualTo("CIBxsVepbxqHyEmOFWYrepvUwJaBWAeRtqYV");
		assertThat(testBaseCommentEntity.getObjectId()).isEqualTo("CwjUetoENVbLBiJDqxylSxOKdkKXNxvToYQf");
		assertThat(testBaseCommentEntity.getUpdatedBy()).isEqualTo("rNMXZUWwNHNMPpmnJgSdKiaAelZauRTXAxkq");
		assertThat(testBaseCommentEntity.getUpdatedIp()).isEqualTo("vDcBYAnJRNsTAYDusWVgiZVvOpZZZwncmUrn");
		assertThat(testBaseCommentEntity.getUpdatedUserId()).isEqualTo("IEJhrsGBwxrXuDWwHIdlQPKLatWzdBKbLZmH");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		Comment entity = new Comment();
		entity.setId(UUID.randomUUID().toString());
		entity.setContent("XjEXsdWqAUQTyoxnkuaPrOuZBGRKWTaWPRot");
		entity.setObjectId("KiVIiApnxFTmWMKebYIomMFOfPgIzJEjkQOC");
		entity.setCreatedUserId("XodnIzoioUsckskNXAFdeNoacGjstGZZgfdR");
		entity.setCreatedBy("UEwXuQycSZsrcWxBzWaTkZQgUHYKveLnXJzA");
		entity.setCreatedIp("cpGTkGCaZpgtNxOawbxIsBbhyUvGzpslLhUW");
		entity.setUpdatedUserId("eZeokgxdrGLCEbLBoXiESVufFVgScKFzomXt");
		entity.setUpdatedBy("fAXSbOHmtyItJkqoNCmKYuszEMvulyuKmmSI");
		entity.setUpdatedIp("yyYPiFdxmPSiCFVZguoGQYyMKVMpSODheYSd");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new Comment()).size();
		Comment updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setContent("koPPyCVlfzFedESoNowDLtkqwevlwyiZExvm");
		updatedEntity.setObjectId("WiJvIWROCkJFEapTWirdwixMNQoXhdkRVjAd");
		updatedEntity.setCreatedUserId("iRvIsVQuKPEoRFtCAeTZJItHVZzyXojdeFEs");
		updatedEntity.setCreatedBy("kQxEMAzNiaOAOsjYYEjBUwUOpkTqRKndcTMB");
		updatedEntity.setCreatedIp("YLwqCaMLpQhXWjhUczNaOJFjZULgtrqCWbPI");
		updatedEntity.setUpdatedUserId("PbEcabkYGiJBSVMjOgFzjznNddIwbtBVuoxb");
		updatedEntity.setUpdatedBy("jLclUFsbkEpCyoKwbDIgcNabkqqXlrbpRhTq");
		updatedEntity.setUpdatedIp("TmnKPfHpMSHQMOawDpnIwKmRqCnqrJtuqPce");
		this.mvc.perform(post("/BaseComment/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<Comment> baseCommentEntityList = dao.ListByPage(new Comment());
		assertThat(baseCommentEntityList).hasSize(databaseSizeBeforeUpdate);
		Comment testBaseCommentEntity = baseCommentEntityList.get(baseCommentEntityList.size() - 1);
		assertThat(testBaseCommentEntity.getContent()).isEqualTo("koPPyCVlfzFedESoNowDLtkqwevlwyiZExvm");
		assertThat(testBaseCommentEntity.getCreatedBy()).isEqualTo("kQxEMAzNiaOAOsjYYEjBUwUOpkTqRKndcTMB");
		assertThat(testBaseCommentEntity.getCreatedIp()).isEqualTo("YLwqCaMLpQhXWjhUczNaOJFjZULgtrqCWbPI");
		assertThat(testBaseCommentEntity.getCreatedUserId()).isEqualTo("iRvIsVQuKPEoRFtCAeTZJItHVZzyXojdeFEs");
		assertThat(testBaseCommentEntity.getObjectId()).isEqualTo("WiJvIWROCkJFEapTWirdwixMNQoXhdkRVjAd");
		assertThat(testBaseCommentEntity.getUpdatedBy()).isEqualTo("jLclUFsbkEpCyoKwbDIgcNabkqqXlrbpRhTq");
		assertThat(testBaseCommentEntity.getUpdatedIp()).isEqualTo("TmnKPfHpMSHQMOawDpnIwKmRqCnqrJtuqPce");
		assertThat(testBaseCommentEntity.getUpdatedUserId()).isEqualTo("PbEcabkYGiJBSVMjOgFzjznNddIwbtBVuoxb");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		Comment entity = new Comment();
		entity.setId(UUID.randomUUID().toString());
		entity.setContent("ZmpAKSSBRYrXwhxotbpyNjEgkDpHnZsQcgPH");
		entity.setObjectId("vZGJUFbMgYdhkAhCjXUqrqUMNTBPIEiEjjBw");
		entity.setCreatedUserId("EpSNSxYFVxufqRMYEvOUMyaaPpABVWBTlZUJ");
		entity.setCreatedBy("ydjrmSqQyJOVzTPevHhyAETjXUoNHTEUYGsf");
		entity.setCreatedIp("QvxTnJDfSciIWTkTMPQxusDuPIZvUIygHgwk");
		entity.setUpdatedUserId("EGhwrrYBeVgTAVSfjNriFjSZqbKXPAzpyPDV");
		entity.setUpdatedBy("uvvLytgeSSRQBQKDKZoyWjsZUONUOfKHvMnQ");
		entity.setUpdatedIp("IhlSwqQeNAjsVqCnyDhBYdjlhZJzoVXOQeKv");
		dao.insert(entity);
		this.mvc.perform(post("/BaseComment/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
