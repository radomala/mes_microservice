package com.saint_augustin.project;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.saint_augustin.project.controller.DossiersControllers;
import com.saint_augustin.project.repository.DossiersRepository;
import com.saint_augustin.project.service.DossiersService;
import com.saint_augustin.project.treenode.DossiersTREE;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DossiersControllerTest {

	//on inject la classe qu'on teste (DossiersController)
	@InjectMocks
	private DossiersControllers dossiersControllers;
	
	//on mock les services qui sont appellé par cette classe
	@Mock
	private DossiersService dossiersService;
	@Mock
	private DossiersRepository dossiersRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	void saveDossiersTestKO () {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("SUCCESS", true);
		map.put("MESSAGE", "sur eh");
		map.put("CODE_STATUS", HttpStatus.OK.value());
		
		doReturn(map).when(dossiersService).saveDossiers(any()); //pareil ===> when(dossiersService.saveDossiers(any())).thenReturn(map);
		ResponseEntity<Map<String, Object>> result= dossiersControllers.saveDossiers(any());
		
		System.out.println(result);
		assertThat(result).isNotNull();
	//	assertNotNull("non vide", result);
	}

	
	@Test
	void getDossiersTreeTest() {
		
		DossiersTREE dossiersTREE1 = new DossiersTREE();
		dossiersTREE1.setLabel("label1");
		dossiersTREE1.setLabel("label2");
		
		DossiersTREE dossiersTREE2 = new DossiersTREE();
		dossiersTREE2.setLabel("label1");
		dossiersTREE2.setLabel("label2");
		
		List<DossiersTREE> list = new ArrayList<DossiersTREE>();
		list.add(dossiersTREE1);
		list.add(dossiersTREE2);
		
		doReturn(list).when(dossiersService).getDossiersTREE();
		
		ResponseEntity<List<DossiersTREE>> result =  dossiersControllers.getDossiersTree();
		
		System.out.println(result);
		assertThat(result).isNotNull();
	//	assertNotNull("non vide", result);
		
	}
	
	@Test
	void deleteDossierTest () {
		doNothing().when(dossiersService).getDossiersTREETABLE_a_deleter(any());
		ResponseEntity<Boolean> result = dossiersControllers.deleteDossier(any());
	//	assertNotNull(result);
		
	}
	
	
	

}
