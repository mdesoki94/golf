package com.greta.golf;

import com.greta.golf.formdata.GolfFormDTO;
import com.greta.golf.models.Golf;
import com.greta.golf.models.Parcours;
import com.greta.golf.models.Role;
import com.greta.golf.models.User;
import com.greta.golf.service.GolfService;
import com.greta.golf.service.JpaUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("/persistence.test.properties")
class GolfApplicationTests {

    @Autowired
    GolfService golfService;
    JpaUserService jpaUserService;

//
//    @Test
//    @Transactional
//    @Rollback(value = true)
//    public void createGolf() {
//        int nbrGolft0 = golfService.getGolfs().size();
//        GolfFormDTO dto= new GolfFormDTO();
//        dto.setNom("test Golf");
//        dto.setLatitude("456465");
//        dto.setLongitude("456465");
//        golfService.saveGolfs(dto);
//        int nbrGolft1=golfService.getGolfs().size();
//        assertThat(nbrGolft1).isEqualTo(nbrGolft0+1);
//        Golf golf= golfService.getGolfs().get(nbrGolft1-1);
//        assertThat(golf.getNom()).isEqualTo("test Golf");
//
//    }
//
//    @Test
//    public void inscrireUtilisateur(){
//        User utilisateur = new User();
//        utilisateur.setLogin("Patrick");
//        utilisateur.setEmail("patrick@gmail.com");
//        utilisateur.setEmail("1234");
//        Role admin= this.golfService.getRoleByName("admin");
//        Role arbitre= this.golfService.getRoleByName("arbitre");
//        ArrayList<Role> roles=new ArrayList<>();
//        roles.add(admin);
//        roles.add(arbitre);
//        utilisateur.setRoles((Set<Role>) roles);
//        jpaUserService.save(utilisateur);
//        assertThat(utilisateur.getPassword()).isNotEqualTo("1234");
//
//    }


}
