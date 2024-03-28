package com.saint_augustin.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.saint_augustin.project.dto.DossiersDTO;
import com.saint_augustin.project.repository.DossiersRepository;
import com.saint_augustin.project.treenode.DossiersTREE;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class dossiersServiceTest2 {

	//RMQ : IL FAUT INJECTER AUSSI LE RIPOSITORY AVEC MOCK SI NON LES TEST NE PASSE PAS
	@InjectMocks
	private DossiersService  dossiersService;
	@Mock
	private DossiersRepository  dossiersRepository;
	
	
	 
	 
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	public DossiersDTO mockDossiersDto () {
        final DossiersDTO ds = new DossiersDTO();
        ds.setGf_dossier_id(1L);
		ds.setGf_dossier_label("test unitaire label");
		ds.setGf_dossier_parent((long) 404);
		ds.setGf_dossier_user_create("doda");
		
		return ds;
	}
	
	
	@Test
	public void saveDossier () {
		
		//GIVEN
		final DossiersDTO ds = mockDossiersDto();
		final boolean isSuccess = true;
		final Integer codestatus = 200;
		
		//WHEN
		Map<String, Object> result = dossiersService.saveDossiers(ds);
		
		assertNotNull(result);
		assertEquals(isSuccess, result.get("success"));
		assertEquals(200, result.get("code_status"));
		assertTrue(codestatus.equals(result.get("code_status")));
		
	}
	
	
	  @Test 
	  public void getDossiersTREETest() {
		  //WHEN 
		  final List <DossiersTREE> list = dossiersService.getDossiersTREE();
		  //THEN 
		  assertNull(list); 
		  assertTrue(list.size()!=0); 
	 
	  }
	  
	  @Test 
	  public void dossiersChildrenTREETREE() {
		  //GIVEN
		  final long idparent = 3;
		  final Integer level = 1;
		  //WHEN 
		  final List <DossiersTREE> listChildren = dossiersService.dossiersChildrenTREE(idparent, level);
		  //THEN 
		  assertEquals(5, listChildren.size());
	 
	  }
}
