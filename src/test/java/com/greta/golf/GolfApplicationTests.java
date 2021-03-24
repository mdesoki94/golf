package com.greta.golf;

import com.greta.golf.models.Golf;
import com.greta.golf.models.Parcours;
import com.greta.golf.service.GolfService;
import com.greta.golf.service.PopulateService;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("/persistence.test.properties")
class GolfApplicationTests {

	@Autowired
	GolfService golfService;
	@Autowired
	PopulateService populateService;

//	@Test
//	@Transactional
//	public void testServiceSaveGolf() {
//		golfService.createGolfByName("test");
//		Golf g =golfService.getGolfByName("test");
//		assertThat(g.getId()).isGreaterThan(0);
//	}
//
//	@Test
//	@Transactional
//	public void testParCely() {
//	Golf cely =golfService.getGolfByName("Cely");
//		Parcours parcours=cely.getParcours().stream().findFirst().get();
//		int parCely=parcours.getPar();
//		assertThat(parcours.getPar()).isGreaterThanOrEqualTo(69);
//		assertThat(parcours.getPar()).isLessThanOrEqualTo(72);
//	}
	@Test
	@Transactional
	public void testCreateGolf() {
		Golf golf = populateService.createGolf("48.4596 2.5324","Cely");
		assertThat(golf.getId()).isNotNull();
		assertThat(golf.getId()).isGreaterThan(0);
		assertThat(golf.getNom()).isNotNull();
		assertThat(golf.getNom()).isNotBlank();// Verifie si ce n'est pas une chaine de caractere vide.
		assertThat(golf.getCoordGPS()).isNotNull();
		assertThat(golf.getCoordGPS()).isNotBlank();
	}
}
