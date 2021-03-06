package com.ocr.utilisateur;

import com.ocr.utilisateur.dao.UtilisateurDao;
import com.ocr.utilisateur.model.UtiRole;
import com.ocr.utilisateur.model.UtilisateurLivre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@EnableFeignClients("com.ocr.utilisateur")
@SpringBootApplication
@EnableDiscoveryClient
public class UtilisateurApplication {

@Autowired
private UtilisateurDao utilisateurDao;

	public static void main(String[] args) {
		SpringApplication.run(UtilisateurApplication.class, args);
	}

	@PostConstruct
	public void postConstruct(){


		UtilisateurLivre user = new UtilisateurLivre("user","user1@gmail.com","user");
		Set<UtiRole> userRole = new HashSet<>();
		userRole.add(UtiRole.USER);
		user.setUserRoleList(userRole);
		utilisateurDao.save(user);

		UtilisateurLivre admin = new UtilisateurLivre("admin","admin1@gmail.com","admin");
		Set<UtiRole> adminRole = new HashSet<>();
		adminRole.add(UtiRole.USER);
		adminRole.add(UtiRole.ADMIN);
		admin.setUserRoleList(adminRole);
		utilisateurDao.save(admin);

		UtilisateurLivre test = new UtilisateurLivre("test","testuser1@gmail.com","test");
		Set<UtiRole> testUserRole = new HashSet<>();
		testUserRole.add(UtiRole.USER);
		test.setUserRoleList(testUserRole);
		utilisateurDao.save(test);

		UtilisateurLivre test1 = new UtilisateurLivre("test1","testuser2@gmail.com","test1");
		Set<UtiRole> test1UserRole = new HashSet<>();
		test1UserRole.add(UtiRole.USER);
		test.setUserRoleList(test1UserRole);
		utilisateurDao.save(test1);



	}
}
