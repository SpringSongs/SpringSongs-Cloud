package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.Dictionary;
import org.authority.mapper.BaseDictionaryMapper;
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
public class BaseDictionaryControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseDictionaryMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		Dictionary entity = new Dictionary();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("NsrXIPJJjPivAeYphvRiFkqvFRkVPvHzeHsp");
		entity.setTitle("tCrMsRIyCBPMBGwMgdwSmHTOnuRtkxDtbSXT");
		entity.setDescription("cThDAsBIXbJsztnqMQZcObntkaNzMcFksALy");
		entity.setCreatedUserId("zcTcdvTMOiDCHMhpheCKddwtVIygBCxKGOaa");
		entity.setCreatedBy("iHWSwKRrJDDzvUMmSkosUrzeTFUFDBWXRDvq");
		entity.setCreatedIp("vlAQxUBZgMIGzSJwDrUFlZebNXziJlNUigeA");
		entity.setUpdatedUserId("TfEFJqqXcPxKHHeHHaWjsdfOvVGVvxKCleDe");
		entity.setUpdatedBy("EXjEvuhKWbpKDzRWHAOTEFFJclBdxMWmSolo");
		entity.setUpdatedIp("KwrMmiwTyPMKjnDyXQflbynseMEHdCwGZIbf");
		this.mvc.perform(post("/BaseDictionary/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].code").value(hasItem("NsrXIPJJjPivAeYphvRiFkqvFRkVPvHzeHsp")))
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("iHWSwKRrJDDzvUMmSkosUrzeTFUFDBWXRDvq")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("vlAQxUBZgMIGzSJwDrUFlZebNXziJlNUigeA")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("zcTcdvTMOiDCHMhpheCKddwtVIygBCxKGOaa")))
				.andExpect(jsonPath("$.data.[*].desc").value(hasItem("cThDAsBIXbJsztnqMQZcObntkaNzMcFksALy")))
				.andExpect(jsonPath("$.data.[*].title").value(hasItem("tCrMsRIyCBPMBGwMgdwSmHTOnuRtkxDtbSXT")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("EXjEvuhKWbpKDzRWHAOTEFFJclBdxMWmSolo")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("KwrMmiwTyPMKjnDyXQflbynseMEHdCwGZIbf")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("TfEFJqqXcPxKHHeHHaWjsdfOvVGVvxKCleDe")));
	}

	@Test
	public void testGet() throws Exception {
		Dictionary entity = new Dictionary();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("ziaaCtmYHcUIilUgUSZoSgJAInysCtlOLLRa");
		entity.setTitle("GUikZOcrDqmJzvmwiWFPoMkthGYPbdwOxItc");
		entity.setDescription("vShpqZzeeuvKxvdESPEdPGvaHpXcJPDnNdWD");
		entity.setCreatedUserId("BHrivshCfxLRmuHmBhchYvTQqeawYJIopYIt");
		entity.setCreatedBy("rWdJvHUVhBuFXnyIKKJqpjafmZbIgNiRkfcU");
		entity.setCreatedIp("vFSKUekYtcOoOvLfxPEdVGaBabCMptieAuXd");
		entity.setUpdatedUserId("HerIqCaOJFbvioRUwwcPWJKqWrsgiYuqxBtL");
		entity.setUpdatedBy("czGobiTSvbvLdyewfYFvgvyuLnimbnYXnHZR");
		entity.setUpdatedIp("jtbcYogGSOJwSxHCBdjQNPPRvtLdWOYEgKxL");
		dao.insert(entity);
		this.mvc.perform(post("/BaseDictionary/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..code").value(hasItem("ziaaCtmYHcUIilUgUSZoSgJAInysCtlOLLRa")))
				.andExpect(jsonPath("$..createdBy").value(hasItem("rWdJvHUVhBuFXnyIKKJqpjafmZbIgNiRkfcU")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("vFSKUekYtcOoOvLfxPEdVGaBabCMptieAuXd")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("BHrivshCfxLRmuHmBhchYvTQqeawYJIopYIt")))
				.andExpect(jsonPath("$..desc").value(hasItem("vShpqZzeeuvKxvdESPEdPGvaHpXcJPDnNdWD")))
				.andExpect(jsonPath("$..title").value(hasItem("GUikZOcrDqmJzvmwiWFPoMkthGYPbdwOxItc")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("czGobiTSvbvLdyewfYFvgvyuLnimbnYXnHZR")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("jtbcYogGSOJwSxHCBdjQNPPRvtLdWOYEgKxL")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("HerIqCaOJFbvioRUwwcPWJKqWrsgiYuqxBtL")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new Dictionary()).size();
		Dictionary entity = new Dictionary();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("hCgNkcXQSxdiaiNecLAircTmXDLtlVCORifX");
		entity.setTitle("hjgDzskICbNlreBWQwXfabXBfbVjxekbqlto");
		entity.setDescription("AcWyDNruLiYPWpRXSwptGlZexivxCRCgVOeM");
		entity.setCreatedUserId("HTIzyrKGwxeCJdheZQnyKbZnccbhjrMosMFI");
		entity.setCreatedBy("PnboDovcsiXlWIYzCwVMIjlKybgiyrywEmxv");
		entity.setCreatedIp("KveqTAilzkHtPlZQYvkwXHuVUkJsrIYmmjEo");
		entity.setUpdatedUserId("RPQXKKcRijtiSGBGpaHOHPOeyosjTpTwUnam");
		entity.setUpdatedBy("jcynezrTioGrHuDdNlTiHeSQiXYBSXpXYbYJ");
		entity.setUpdatedIp("bPntVyfiWFVlWwFHSOSfZpJRnZIlfzSvyGIs");
		this.mvc.perform(post("/BaseDictionary/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<Dictionary> baseDictionaryEntityList = dao.ListByPage(new Dictionary());
		assertThat(baseDictionaryEntityList).hasSize(databaseSizeBeforeCreate + 1);
		Dictionary testBaseDictionaryEntity = baseDictionaryEntityList
				.get(baseDictionaryEntityList.size() - 1);
		assertThat(testBaseDictionaryEntity.getCode()).isEqualTo("hCgNkcXQSxdiaiNecLAircTmXDLtlVCORifX");
		assertThat(testBaseDictionaryEntity.getCreatedBy()).isEqualTo("PnboDovcsiXlWIYzCwVMIjlKybgiyrywEmxv");
		assertThat(testBaseDictionaryEntity.getCreatedIp()).isEqualTo("KveqTAilzkHtPlZQYvkwXHuVUkJsrIYmmjEo");
		assertThat(testBaseDictionaryEntity.getCreatedUserId()).isEqualTo("HTIzyrKGwxeCJdheZQnyKbZnccbhjrMosMFI");
		assertThat(testBaseDictionaryEntity.getDescription()).isEqualTo("AcWyDNruLiYPWpRXSwptGlZexivxCRCgVOeM");
		assertThat(testBaseDictionaryEntity.getTitle()).isEqualTo("hjgDzskICbNlreBWQwXfabXBfbVjxekbqlto");
		assertThat(testBaseDictionaryEntity.getUpdatedBy()).isEqualTo("jcynezrTioGrHuDdNlTiHeSQiXYBSXpXYbYJ");
		assertThat(testBaseDictionaryEntity.getUpdatedIp()).isEqualTo("bPntVyfiWFVlWwFHSOSfZpJRnZIlfzSvyGIs");
		assertThat(testBaseDictionaryEntity.getUpdatedUserId()).isEqualTo("RPQXKKcRijtiSGBGpaHOHPOeyosjTpTwUnam");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		Dictionary entity = new Dictionary();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("UiYmnwoAXzzwVmTOiMiUeRTDxIlZydUjePUg");
		entity.setTitle("zsSyjZVfmELXWzXDpgbjhLrknckqFuwGzdnh");
		entity.setDescription("DESocskbvUXpRhYCpRzydEYQUAOwJcRCMyfs");
		entity.setCreatedUserId("isTcTVPzRBrlcBSsoDiNLPcyJKHtBPQploAu");
		entity.setCreatedBy("mDIsABuCJkgnlFGZErHPqMSjFQFOOZzfHCce");
		entity.setCreatedIp("SGeStuCmVDpPXyVZdobzQAwpNtDPaHmtXpGZ");
		entity.setUpdatedUserId("kQecKrlKQiZINwjZIYaKwdMZHTndvbvMLHFX");
		entity.setUpdatedBy("dRKDYcLkyZDdZKcnZVOcOzUFUgSORQvBfCnP");
		entity.setUpdatedIp("auxIYhKRxApTdBDNHayMEidwnyLvjBfdaNeq");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new Dictionary()).size();
		Dictionary updatedEntity = dao.selectByPrimaryKey(entity.getId());
		updatedEntity.setCode("lFEuqkLpgWatvjytznzaggyKocxsnknKpIeg");
		updatedEntity.setTitle("sFQmqHFzmiCaqyURUVRyucYZFTOfZdCzYCQL");
		updatedEntity.setDescription("UWVZTUwMSrzkNHmIgrojePYhjuMSMufjDGeh");
		updatedEntity.setCreatedUserId("HmSfbectdMMCOIWIQrUmXxgtLXmoPcXSmAFy");
		updatedEntity.setCreatedBy("lZwinvRPnHPyDXkBZsdavOUlzcOiLXgitqhe");
		updatedEntity.setCreatedIp("pJXWNptDfwkbMyfFrjGGlmhgjBQSVGSpDuEI");
		updatedEntity.setUpdatedUserId("cpnrGQGIgZPyEIGvtTvTyGGhsZgyvRZllgWJ");
		updatedEntity.setUpdatedBy("KtvoksTJpnWcoNCFOzLMNFHaHPBCupvOukgc");
		updatedEntity.setUpdatedIp("xQbrGvnQzvBUFFjWNhogGlNIvQKMRPVqRCWW");
		this.mvc.perform(post("/BaseDictionary/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<Dictionary> baseDictionaryEntityList = dao.ListByPage(new Dictionary());
		assertThat(baseDictionaryEntityList).hasSize(databaseSizeBeforeUpdate);
		Dictionary testBaseDictionaryEntity = baseDictionaryEntityList
				.get(baseDictionaryEntityList.size() - 1);
		assertThat(testBaseDictionaryEntity.getCode()).isEqualTo("lFEuqkLpgWatvjytznzaggyKocxsnknKpIeg");
		assertThat(testBaseDictionaryEntity.getCreatedBy()).isEqualTo("lZwinvRPnHPyDXkBZsdavOUlzcOiLXgitqhe");
		assertThat(testBaseDictionaryEntity.getCreatedIp()).isEqualTo("pJXWNptDfwkbMyfFrjGGlmhgjBQSVGSpDuEI");
		assertThat(testBaseDictionaryEntity.getCreatedUserId()).isEqualTo("HmSfbectdMMCOIWIQrUmXxgtLXmoPcXSmAFy");
		assertThat(testBaseDictionaryEntity.getDescription()).isEqualTo("UWVZTUwMSrzkNHmIgrojePYhjuMSMufjDGeh");
		assertThat(testBaseDictionaryEntity.getTitle()).isEqualTo("sFQmqHFzmiCaqyURUVRyucYZFTOfZdCzYCQL");
		assertThat(testBaseDictionaryEntity.getUpdatedBy()).isEqualTo("KtvoksTJpnWcoNCFOzLMNFHaHPBCupvOukgc");
		assertThat(testBaseDictionaryEntity.getUpdatedIp()).isEqualTo("xQbrGvnQzvBUFFjWNhogGlNIvQKMRPVqRCWW");
		assertThat(testBaseDictionaryEntity.getUpdatedUserId()).isEqualTo("cpnrGQGIgZPyEIGvtTvTyGGhsZgyvRZllgWJ");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		Dictionary entity = new Dictionary();
		entity.setId(UUID.randomUUID().toString());
		entity.setCode("BAxuCnDPCmDIEVuTVuzirHURQgmOUNSUNkBK");
		entity.setTitle("NxJAsoThhNOjFBOhDEXOfDsYlEdgoNOzIYor");
		entity.setDescription("gTdztwaycbdKZSkxAyWlycSMjIFQZJBMADaf");
		entity.setCreatedUserId("cCgXSoBKmZZxuhvnchlFYeOyIvkYbfMUmNMX");
		entity.setCreatedBy("kSFvrFaYlDhCCPjvjhEbJBznzoaeffdWaFMZ");
		entity.setCreatedIp("MbRGEbMuThvvCcwdFzsFdsrThEQNzSbTrwep");
		entity.setUpdatedUserId("QEAnYHeHABcelQeZQJBfnIoILqTnOYRTnYIk");
		entity.setUpdatedBy("rDWrmEoRSZOXSGxXOpPJHbcMtnUPUdSRiPXw");
		entity.setUpdatedIp("mVXxoDwsddknVJilVOVTmNKxwkaqhALrJEMx");
		dao.insert(entity);
		this.mvc.perform(post("/BaseDictionary/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
