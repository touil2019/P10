package com.ocr.livre.proxies;

import com.ocr.livre.beans.UtilisateurBean;
import com.ocr.livre.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "microServiceUtilisateur", url = "localhost:9091",contextId = "microServiceUtilisateurProxy",
configuration = FeignConfig.class)
public interface MicroserviceUtilisateurProxy {

    @GetMapping(value = "/listeUtilisateur")
    List<UtilisateurBean> listeUtilisateur();

    @GetMapping(value = "/utilisateur/{username}")
    UtilisateurBean recupererUnUtilisateur(@PathVariable String username);



}