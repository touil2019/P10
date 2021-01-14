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

		UtilisateurLivre test2 = new UtilisateurLivre("test2","testuser3@gmail.com","test2");
		Set<UtiRole> test2UserRole = new HashSet<>();
		test2UserRole.add(UtiRole.USER);
		test.setUserRoleList(test2UserRole);
		utilisateurDao.save(test2);

		UtilisateurLivre test3 = new UtilisateurLivre("test3","testuser4@gmail.com","test3");
		Set<UtiRole> test3UserRole = new HashSet<>();
		test3UserRole.add(UtiRole.USER);
		test.setUserRoleList(test3UserRole);
		utilisateurDao.save(test3);

		UtilisateurLivre test4 = new UtilisateurLivre("test4","testuser5@gmail.com","test4");
		Set<UtiRole> test4UserRole = new HashSet<>();
		test4UserRole.add(UtiRole.USER);
		test.setUserRoleList(test4UserRole);
		utilisateurDao.save(test4);

		UtilisateurLivre test5 = new UtilisateurLivre("test5","testuser6@gmail.com","test5");
		Set<UtiRole> test5UserRole = new HashSet<>();
		test5UserRole.add(UtiRole.USER);
		test.setUserRoleList(test5UserRole);
		utilisateurDao.save(test5);

		UtilisateurLivre test6 = new UtilisateurLivre("test6","testuser7@gmail.com","test6");
		Set<UtiRole> test6UserRole = new HashSet<>();
		test6UserRole.add(UtiRole.USER);
		test.setUserRoleList(test6UserRole);
		utilisateurDao.save(test6);

	}
}
