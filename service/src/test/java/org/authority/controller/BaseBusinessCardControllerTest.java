package org.authority.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.authority.domain.BusinessCard;
import org.authority.mapper.BaseBusinessCardMapper;
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
public class BaseBusinessCardControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BaseBusinessCardMapper dao;

	// @Autowired
	private MockMvc mvc;

	@BeforeEach
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetPage() throws Exception {
		BusinessCard entity = new BusinessCard();
		entity.setId(UUID.randomUUID().toString());
		entity.setCompany("maWLwMVhQWQVLyAKpZlmJmZmCKFElXziWgHz");
		entity.setTitle("yeQhUKGEHbSgZfuhdrppdGSGgwAqHIFGSWCq");
		entity.setUsername("haTkaeJqeKzWkBINZoJVGLAeIjBuMyutOFIa");
		entity.setEmail("GIJVhzxOExRzFVkKhkFngYNpbaeoyaEtwMAW");
		entity.setWeb("FeWTJXzKFjtFmbyRRnHOUbsgPioqNVWqcIUz");
		entity.setFax("cjAbATVjXPOvlBQpPCGuernxsvSqtLAuSOrR");
		entity.setQq("pXitoozTfXtgXXHePvCZhfKvhjGeaeKshCbN");
		entity.setWebchat("YwxSQEWVddjsHESXYDFgRbKAjgRrzfUdZoQu");
		entity.setMobile("SnMOTcepkvnHFdKxgsrVcKCqmMPYQViJxqVf");
		entity.setTel("DqYbDgCqoYbXIFwOYFIxKKbSiLBPFiilnvWT");
		entity.setCreatedUserId("plFdChxnhbfUAzlpqkEIOHJVKkctTpaiJmto");
		entity.setCreatedBy("CqrHpHvUxYZXDXYDgqiEIujLpCCvMgNazrfH");
		entity.setCreatedIp("GrsiHfNOLHqShFzuJgCETROLAldknsEEtXrS");
		entity.setUpdatedUserId("efVseujfQLyrVCsypSTXoTRuLvqNaxWqADwt");
		entity.setUpdatedBy("purOsrdeMmUPbkuxIseivLtzrmSdsEojOPEn");
		entity.setUpdatedIp("EaYLRjMKVkdoPLDJVkCUjkCnxGPGuAXNnwpz");
		this.mvc.perform(post("/BaseBusinessCard/ListByPage").param("page", "1").param("limit", "20")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(JSON.toJSONString(entity))).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.[*].company").value(hasItem("maWLwMVhQWQVLyAKpZlmJmZmCKFElXziWgHz")))
				.andExpect(jsonPath("$.data.[*].createdBy").value(hasItem("CqrHpHvUxYZXDXYDgqiEIujLpCCvMgNazrfH")))
				.andExpect(jsonPath("$.data.[*].createdIp").value(hasItem("GrsiHfNOLHqShFzuJgCETROLAldknsEEtXrS")))
				.andExpect(jsonPath("$.data.[*].createdUserId").value(hasItem("plFdChxnhbfUAzlpqkEIOHJVKkctTpaiJmto")))
				.andExpect(jsonPath("$.data.[*].email").value(hasItem("GIJVhzxOExRzFVkKhkFngYNpbaeoyaEtwMAW")))
				.andExpect(jsonPath("$.data.[*].fax").value(hasItem("cjAbATVjXPOvlBQpPCGuernxsvSqtLAuSOrR")))
				.andExpect(jsonPath("$.data.[*].mobile").value(hasItem("SnMOTcepkvnHFdKxgsrVcKCqmMPYQViJxqVf")))
				.andExpect(jsonPath("$.data.[*].qq").value(hasItem("pXitoozTfXtgXXHePvCZhfKvhjGeaeKshCbN")))
				.andExpect(jsonPath("$.data.[*].tel").value(hasItem("DqYbDgCqoYbXIFwOYFIxKKbSiLBPFiilnvWT")))
				.andExpect(jsonPath("$.data.[*].title").value(hasItem("yeQhUKGEHbSgZfuhdrppdGSGgwAqHIFGSWCq")))
				.andExpect(jsonPath("$.data.[*].updatedBy").value(hasItem("purOsrdeMmUPbkuxIseivLtzrmSdsEojOPEn")))
				.andExpect(jsonPath("$.data.[*].updatedIp").value(hasItem("EaYLRjMKVkdoPLDJVkCUjkCnxGPGuAXNnwpz")))
				.andExpect(jsonPath("$.data.[*].updatedUserId").value(hasItem("efVseujfQLyrVCsypSTXoTRuLvqNaxWqADwt")))
				.andExpect(jsonPath("$.data.[*].username").value(hasItem("haTkaeJqeKzWkBINZoJVGLAeIjBuMyutOFIa")))
				.andExpect(jsonPath("$.data.[*].web").value(hasItem("FeWTJXzKFjtFmbyRRnHOUbsgPioqNVWqcIUz")))
				.andExpect(jsonPath("$.data.[*].webchat").value(hasItem("YwxSQEWVddjsHESXYDFgRbKAjgRrzfUdZoQu")));
	}

	@Test
	public void testGet() throws Exception {
		BusinessCard entity = new BusinessCard();
		entity.setId(UUID.randomUUID().toString());
		entity.setCompany("jvyeyhLcaJprLvphjQwKAemuDqIEHwVGeLDh");
		entity.setTitle("hLvYcAIExxodgvHRUufVezPFpYhCFUSFVQba");
		entity.setUsername("zGCxcmOSVUbUlTKyLYBoDannfENUgcDMveQl");
		entity.setEmail("OSZSsOgByQZTwiSemqWGKKkZLYzddmFLNvQR");
		entity.setWeb("tLSAWSPwGNuInoXGCoSIcHaSNKhcpjNHBSbS");
		entity.setFax("CDCPhHxrbZOGsVqpgURBiSJzEopchzNFEzdz");
		entity.setQq("tJnFtdQgAkVnfqLhJEYMdxcDJRuudXInrnEi");
		entity.setWebchat("ulTdeWKsTXIfOGopqlpcBuZeHNUXCnjzwbYA");
		entity.setMobile("LbLLbPozfzCBwOmiHTLSIlHYgpOGXgfYkOEB");
		entity.setTel("KPFXNQbkdUqGvOLpnTtTUNyKoKjkNTPPjyWK");
		entity.setCreatedUserId("jOAHwYgwWhRCWPHiASxGrfWoqpPHzLIYaqGv");
		entity.setCreatedBy("wJbckkMESiqgoRTlOZeAopfxSPOgLApNDCuk");
		entity.setCreatedIp("IZdgFDQhrlKCwyMXpfrkfwUnVwBFprEHEVmB");
		entity.setUpdatedUserId("FAYltkMlUVUgZuyMHyyeCBGwfSETUGyDnAsz");
		entity.setUpdatedBy("yTgVYhNcnGOtqNjpdiPgtroxulbWNbMjXSNB");
		entity.setUpdatedIp("RhiLIzkXeqUKCWYFcUTznNXiDHDaskbTzRRU");
		dao.insert(entity);
		this.mvc.perform(post("/BaseBusinessCard/Detail").param("id", entity.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$..company").value(hasItem("jvyeyhLcaJprLvphjQwKAemuDqIEHwVGeLDh")))
				.andExpect(jsonPath("$..createdBy").value(hasItem("wJbckkMESiqgoRTlOZeAopfxSPOgLApNDCuk")))
				.andExpect(jsonPath("$..createdIp").value(hasItem("IZdgFDQhrlKCwyMXpfrkfwUnVwBFprEHEVmB")))
				.andExpect(jsonPath("$..createdUserId").value(hasItem("jOAHwYgwWhRCWPHiASxGrfWoqpPHzLIYaqGv")))
				.andExpect(jsonPath("$..email").value(hasItem("OSZSsOgByQZTwiSemqWGKKkZLYzddmFLNvQR")))
				.andExpect(jsonPath("$..fax").value(hasItem("CDCPhHxrbZOGsVqpgURBiSJzEopchzNFEzdz")))
				.andExpect(jsonPath("$..mobile").value(hasItem("LbLLbPozfzCBwOmiHTLSIlHYgpOGXgfYkOEB")))
				.andExpect(jsonPath("$..qq").value(hasItem("tJnFtdQgAkVnfqLhJEYMdxcDJRuudXInrnEi")))
				.andExpect(jsonPath("$..tel").value(hasItem("KPFXNQbkdUqGvOLpnTtTUNyKoKjkNTPPjyWK")))
				.andExpect(jsonPath("$..title").value(hasItem("hLvYcAIExxodgvHRUufVezPFpYhCFUSFVQba")))
				.andExpect(jsonPath("$..updatedBy").value(hasItem("yTgVYhNcnGOtqNjpdiPgtroxulbWNbMjXSNB")))
				.andExpect(jsonPath("$..updatedIp").value(hasItem("RhiLIzkXeqUKCWYFcUTznNXiDHDaskbTzRRU")))
				.andExpect(jsonPath("$..updatedUserId").value(hasItem("FAYltkMlUVUgZuyMHyyeCBGwfSETUGyDnAsz")))
				.andExpect(jsonPath("$..username").value(hasItem("zGCxcmOSVUbUlTKyLYBoDannfENUgcDMveQl")))
				.andExpect(jsonPath("$..web").value(hasItem("tLSAWSPwGNuInoXGCoSIcHaSNKhcpjNHBSbS")))
				.andExpect(jsonPath("$..webchat").value(hasItem("ulTdeWKsTXIfOGopqlpcBuZeHNUXCnjzwbYA")));
	}

	@Test
	public void testSave() throws Exception {
		int databaseSizeBeforeCreate = dao.ListByPage(new BusinessCard()).size();
		BusinessCard entity = new BusinessCard();
		entity.setId(UUID.randomUUID().toString());
		entity.setCompany("ZpEySVaBNrUWfqIxQuPeopIKOXYHSumBsmxl");
		entity.setTitle("dLpbcuwiINGvmnWMfEQjebDQhPoUKqAuDEhi");
		entity.setUsername("sXVbjgxSSaHmpuDDFZAlGOKfFFvyNkrwPhsF");
		entity.setEmail("PmqiUhcTmCJoNYEuISWAlQhhkIkTTEQyhJLg");
		entity.setWeb("ErGrlogkIUxFGWnEtxjqNuYMrIjIrkvFNMYE");
		entity.setFax("OcjjxXrGWrvwIDUEHfruSfmYTxOdkPQBvOJu");
		entity.setQq("CrIaClUESvZKXMZwQmZpXdclaJjnYMGKgpOn");
		entity.setWebchat("pvISRVsNimedWEgFTYBLNbbXRLuFiSyKIVHu");
		entity.setMobile("MVHzUwAlsGjqSfnNPPQAeOkwiNLoXzQDXses");
		entity.setTel("VsndAOaucfxrkMcGqxiozsLuaNgjuFUlPwkE");
		entity.setCreatedUserId("UNEVjyILePZfpxcOObNIjIpRnxJllZAMvEaz");
		entity.setCreatedBy("gXiaNbQQqzsCRokoRUTBvbLaLjTlJPzdGGZT");
		entity.setCreatedIp("vpIVVkUcROFIrpnKYvRZNwlVJixJYjXzrhie");
		entity.setUpdatedUserId("VIdxAYUerRSrseLBknumtFmgMczTVaYujYOi");
		entity.setUpdatedBy("LgMWILQJtCnTmCWxrNfBtPBFmerXUVSGYhoi");
		entity.setUpdatedIp("tVhuyzSfuQkFxaQMbekeKxlXCceJbwzMiMrr");
		this.mvc.perform(post("/BaseBusinessCard/Create").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(entity))).andExpect(status().isOk());
		List<BusinessCard> baseBusinessCardEntityList = dao.ListByPage(new BusinessCard());
		assertThat(baseBusinessCardEntityList).hasSize(databaseSizeBeforeCreate + 1);
		BusinessCard testBaseBusinessCardEntity = baseBusinessCardEntityList
				.get(baseBusinessCardEntityList.size() - 1);
		assertThat(testBaseBusinessCardEntity.getCompany()).isEqualTo("ZpEySVaBNrUWfqIxQuPeopIKOXYHSumBsmxl");
		assertThat(testBaseBusinessCardEntity.getCreatedBy()).isEqualTo("gXiaNbQQqzsCRokoRUTBvbLaLjTlJPzdGGZT");
		assertThat(testBaseBusinessCardEntity.getCreatedIp()).isEqualTo("vpIVVkUcROFIrpnKYvRZNwlVJixJYjXzrhie");
		assertThat(testBaseBusinessCardEntity.getCreatedUserId()).isEqualTo("UNEVjyILePZfpxcOObNIjIpRnxJllZAMvEaz");
		assertThat(testBaseBusinessCardEntity.getEmail()).isEqualTo("PmqiUhcTmCJoNYEuISWAlQhhkIkTTEQyhJLg");
		assertThat(testBaseBusinessCardEntity.getFax()).isEqualTo("OcjjxXrGWrvwIDUEHfruSfmYTxOdkPQBvOJu");
		assertThat(testBaseBusinessCardEntity.getMobile()).isEqualTo("MVHzUwAlsGjqSfnNPPQAeOkwiNLoXzQDXses");
		assertThat(testBaseBusinessCardEntity.getQq()).isEqualTo("CrIaClUESvZKXMZwQmZpXdclaJjnYMGKgpOn");
		assertThat(testBaseBusinessCardEntity.getTel()).isEqualTo("VsndAOaucfxrkMcGqxiozsLuaNgjuFUlPwkE");
		assertThat(testBaseBusinessCardEntity.getTitle()).isEqualTo("dLpbcuwiINGvmnWMfEQjebDQhPoUKqAuDEhi");
		assertThat(testBaseBusinessCardEntity.getUpdatedBy()).isEqualTo("LgMWILQJtCnTmCWxrNfBtPBFmerXUVSGYhoi");
		assertThat(testBaseBusinessCardEntity.getUpdatedIp()).isEqualTo("tVhuyzSfuQkFxaQMbekeKxlXCceJbwzMiMrr");
		assertThat(testBaseBusinessCardEntity.getUpdatedUserId()).isEqualTo("VIdxAYUerRSrseLBknumtFmgMczTVaYujYOi");
		assertThat(testBaseBusinessCardEntity.getUsername()).isEqualTo("sXVbjgxSSaHmpuDDFZAlGOKfFFvyNkrwPhsF");
		assertThat(testBaseBusinessCardEntity.getWeb()).isEqualTo("ErGrlogkIUxFGWnEtxjqNuYMrIjIrkvFNMYE");
		assertThat(testBaseBusinessCardEntity.getWebchat()).isEqualTo("pvISRVsNimedWEgFTYBLNbbXRLuFiSyKIVHu");
		;
	}

	@Test
	public void testUpdate() throws Exception {
		BusinessCard entity = new BusinessCard();
		entity.setId(UUID.randomUUID().toString());
		entity.setCompany("HnYrvPyWtyuEAyBfTkrsDFqfsbZMluPQOqGY");
		entity.setTitle("cdGCiVoNlGKVWfPVnlPriCMyhatIgKhZzdYZ");
		entity.setUsername("RcqDJMStZwdDNamkFiUmEYjKymBiMlXimkst");
		entity.setEmail("WstXQCzhaFIpSTGOAsJNiwKDPgnhLsMTSEAk");
		entity.setWeb("nitxrFZjrndoEFvMmwXOHLkXcsjktjfHyZQv");
		entity.setFax("CUXDGkpETFMEMQYfALVPXlLLDzAlhUgLeZvu");
		entity.setQq("NnvmrRhzUDOrvHIjqfGOkSCaWvfXDtlzCBQA");
		entity.setWebchat("XCJByMxntNxCsVMOQvaXjyNitKCQKHqhnZYT");
		entity.setMobile("GyIHaqPbqLaRTSFDEFqVAthFoDqraUQmUvQN");
		entity.setTel("dxQTkQVSqLonkioMxZVYePtQABroOWnsukjD");
		entity.setCreatedUserId("MBaMgUsGPgGGvsjhTXgMjzOYEJfgNaIaJDza");
		entity.setCreatedBy("vjzAqMwsNeGEbTUAujxTalZFhbrnZRRPffoz");
		entity.setCreatedIp("aVGLiFMeAkpNYGbCdvVPbhynwtgoxqeSvcNF");
		entity.setUpdatedUserId("sSoFiQQIvOdjZXantCiJkBGaeVqRVwJtFNqJ");
		entity.setUpdatedBy("LoFIwAKBcusMHRBDOsbWekLwkTZfmdjvOyAr");
		entity.setUpdatedIp("xeavkyLguKgthexoJhLqScxkuFQbpjGHCsVa");
		dao.insert(entity);
		int databaseSizeBeforeUpdate = dao.ListByPage(new BusinessCard()).size();
		BusinessCard updatedEntity = dao.selectByPrimaryKey(entity.getId());
		
		updatedEntity.setCompany("jelJYinWAlTHXlszAFANjNqSqOiNcfpohzlO");
		updatedEntity.setTitle("nYvzYiOUdNCZKQnQISdOpaZBKzIaAwQTnZNX");
		updatedEntity.setUsername("qwUEHwegCyVAYHRmJwyFJnnLBpkUdTJBWKZE");
		updatedEntity.setEmail("JtxBuBmyVTuBfgtHcWIeIepzQGVWVnENasxr");
		updatedEntity.setWeb("YQaYqEFwDGCvlWjtuCwhZhElhdNVCCwnCjHh");
		updatedEntity.setFax("HgjXEinZHsRFsGVtjSLoFcRfUUpoayhGqorK");
		updatedEntity.setQq("zzDHcgiJHNHWeAmSYmwQPNzMqAnBObBLJplh");
		updatedEntity.setWebchat("lKjKnXgSQbddXCtmAmIuSLHmpNTOWxdEUQdt");
		updatedEntity.setMobile("LzUGcubFIapfpSzwLDwWRDazGsFeDLcDMoRd");
		updatedEntity.setTel("DSsIEQzJpKjHpssJlkpzjPlajsBIYTDOHwJk");
		updatedEntity.setCreatedUserId("hTYJiqRNQCyktkyHegNglpbAtnDLSBBfNGTz");
		updatedEntity.setCreatedBy("rXoaqgiELWSvbnybsYIQxdzApVieXAejHmJm");
		updatedEntity.setCreatedIp("DhRAytlaYzLhkuUmaEnUVSoqViUfikgAyDcq");
		updatedEntity.setUpdatedUserId("yaUiziPvhZXXKWeWqpBLQimzwiYRVNXGxEeL");
		updatedEntity.setUpdatedBy("oIFzQABHqRrBDBwMtOIwebMziRtyluSWUUiB");
		updatedEntity.setUpdatedIp("zUTPmqZaCGcPmNcfTpZNLLbBFoEdTpefZkVO");
		this.mvc.perform(post("/BaseBusinessCard/Edit").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(JSON.toJSONString(updatedEntity)))
				.andExpect(status().isOk());
		List<BusinessCard> baseBusinessCardEntityList = dao.ListByPage(new BusinessCard());
		assertThat(baseBusinessCardEntityList).hasSize(databaseSizeBeforeUpdate);
		BusinessCard testBaseBusinessCardEntity = baseBusinessCardEntityList
				.get(baseBusinessCardEntityList.size() - 1);
		assertThat(testBaseBusinessCardEntity.getCompany()).isEqualTo("jelJYinWAlTHXlszAFANjNqSqOiNcfpohzlO");
		assertThat(testBaseBusinessCardEntity.getCreatedBy()).isEqualTo("rXoaqgiELWSvbnybsYIQxdzApVieXAejHmJm");
		assertThat(testBaseBusinessCardEntity.getCreatedIp()).isEqualTo("DhRAytlaYzLhkuUmaEnUVSoqViUfikgAyDcq");
		assertThat(testBaseBusinessCardEntity.getCreatedUserId()).isEqualTo("hTYJiqRNQCyktkyHegNglpbAtnDLSBBfNGTz");
		assertThat(testBaseBusinessCardEntity.getEmail()).isEqualTo("JtxBuBmyVTuBfgtHcWIeIepzQGVWVnENasxr");
		assertThat(testBaseBusinessCardEntity.getFax()).isEqualTo("HgjXEinZHsRFsGVtjSLoFcRfUUpoayhGqorK");
		assertThat(testBaseBusinessCardEntity.getMobile()).isEqualTo("LzUGcubFIapfpSzwLDwWRDazGsFeDLcDMoRd");
		assertThat(testBaseBusinessCardEntity.getQq()).isEqualTo("zzDHcgiJHNHWeAmSYmwQPNzMqAnBObBLJplh");
		assertThat(testBaseBusinessCardEntity.getTel()).isEqualTo("DSsIEQzJpKjHpssJlkpzjPlajsBIYTDOHwJk");
		assertThat(testBaseBusinessCardEntity.getTitle()).isEqualTo("nYvzYiOUdNCZKQnQISdOpaZBKzIaAwQTnZNX");
		assertThat(testBaseBusinessCardEntity.getUpdatedBy()).isEqualTo("oIFzQABHqRrBDBwMtOIwebMziRtyluSWUUiB");
		assertThat(testBaseBusinessCardEntity.getUpdatedIp()).isEqualTo("zUTPmqZaCGcPmNcfTpZNLLbBFoEdTpefZkVO");
		assertThat(testBaseBusinessCardEntity.getUpdatedUserId()).isEqualTo("yaUiziPvhZXXKWeWqpBLQimzwiYRVNXGxEeL");
		assertThat(testBaseBusinessCardEntity.getUsername()).isEqualTo("qwUEHwegCyVAYHRmJwyFJnnLBpkUdTJBWKZE");
		assertThat(testBaseBusinessCardEntity.getWeb()).isEqualTo("YQaYqEFwDGCvlWjtuCwhZhElhdNVCCwnCjHh");
		assertThat(testBaseBusinessCardEntity.getWebchat()).isEqualTo("lKjKnXgSQbddXCtmAmIuSLHmpNTOWxdEUQdt");
		;
	}

	@Test
	public void testSetDeleted() throws Exception {
		BusinessCard entity = new BusinessCard();
		entity.setId(UUID.randomUUID().toString());
		entity.setCompany("OrlcdoFVojZBUwiXEJuYCOnSlPvlFfDhWjcE");
		entity.setTitle("QYrJFcKEuAWjsCWczSLOrhvjOnVcPnrPvXHU");
		entity.setUsername("gLGKMTGMzbTcYbyrSMaudcSroJQOIQNHySEn");
		entity.setEmail("SNASaGeKNeRyLCvoYFKDLcnbUJQghrGKbRUr");
		entity.setWeb("MeyOogFYYHJGGpTbfqOmrcEXKEbuXOhpYDYU");
		entity.setFax("LyVnVwywOtGzHzxKdAgZwXWbMXvCjkDijnjn");
		entity.setQq("CUnlIAQFziNQSxXEsmIWfYNujvixBDGGnjmO");
		entity.setWebchat("fTPFWnPgJidUmPhyTDkoayBzLRBPsJjJmQIk");
		entity.setMobile("CxLgXdOgmqbFquAotiHkJLXoVlQLIBuqlJzw");
		entity.setTel("oGqaIelaQcJtWXGxBNPhcWHMdgrGcdAbbyGN");
		entity.setCreatedUserId("jyZmGJcgIYunHEPBrXFDEhTaomdBEFTyAozT");
		entity.setCreatedBy("iLfqdPqsDIeAXDTIHcgHsvnGfZMisltpYTGm");
		entity.setCreatedIp("cGvtXBoeNrScyYcvuQBrySAyKJSYUjmBLubV");
		entity.setUpdatedUserId("pGLEoADwrLrpIxMZdzpBtXNJrYowvyhEXXlT");
		entity.setUpdatedBy("FNdzYHxWItVdZBMPXSKrNmZllCUUwCHJWCns");
		entity.setUpdatedIp("NroXSyNBJJrCqGwSMxnsfKxDamsSrRlNPuya");
		dao.insert(entity);
		this.mvc.perform(post("/BaseBusinessCard/SetDeleted").param("ids", entity.getId())
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk());
	}

}
