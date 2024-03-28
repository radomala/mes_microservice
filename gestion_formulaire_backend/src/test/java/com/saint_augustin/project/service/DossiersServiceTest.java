package com.saint_augustin.project.service;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DossiersServiceTest {

	//on inject la classe qu'on teste (DossiersController)
		@InjectMocks
		private DossiersService dossiersService;
		@Mock
		private DossiersRepository dossiersRepository;
		
		@BeforeEach
		public void init() {
			MockitoAnnotations.initMocks(this);
		}
		
		@Test
		void saveDossier() {
			
			DossiersDTO dossiersDTO = new DossiersDTO();
			dossiersDTO.setGf_dossier_label("test");
			
			//when(dossiersRepository.save(any())).thenReturn(result ou method);
			when(dossiersRepository.save(any())).thenReturn(null);
		
			Map<String, Object> result =dossiersService.saveDossiers(dossiersDTO);
			System.out.println(result);
			assertThat(result).isNotNull();
		//	assertNotNull("non vide", result);
			
		}
		
		

}
