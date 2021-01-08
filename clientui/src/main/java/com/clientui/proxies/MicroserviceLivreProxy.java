package com.clientui.proxies;


import com.clientui.beans.EmpruntBean;
import com.clientui.beans.LivreBean;
import com.clientui.beans.ReservationBean;
import com.clientui.configuration.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "zuul-server",contextId = "microServiceLivreProxy",configuration= FeignConfig.class)
/**
 * Api Livre
 */
@Component
public interface
MicroserviceLivreProxy {
    /**
     *
     * liste de livres
     */
    @GetMapping(value = "/microservicelivre/Livres")
    List<LivreBean> listelivres();

    /**
     * recuperer un livre
     * @param id
     *
     */
    @GetMapping(value = "/microservicelivre/Livre/{id}")
    LivreBean recupererUnLivre(@PathVariable("id") Long id);

    /**
     * recherche un livre par mot cle mc
     * @param mc
     *
     */
    @GetMapping(value = "/microservicelivre/listeRecherche")
    List<LivreBean> listeLivreRecherche(@RequestParam(name = "mc")String mc);

    /**
     * suppression d un livre
     * @param id
     *
     */
    @GetMapping(value= "/microservicelivre/Livre/{id}/supprimer")
    List<LivreBean> supprimerUnlivre(@PathVariable("id") Long id);

    /**
     * liste d emprunt d un utilisateur
     * @param pseudoEmprunteur
     *
     */

    @GetMapping(value="/microservicelivre/emprunt/pseudo/{pseudoEmprunteur}")
    List<EmpruntBean> listeDEmpruntParUtilisateur(@PathVariable("pseudoEmprunteur") String pseudoEmprunteur );

    /**
     * prolonger l emprunt d un livre
     * @param idEmprunt
     *
     */
    @PutMapping("/microservicelivre/emprunt/{id}/prolonger")
    void prolongerEmprunt(@PathVariable ("id") Long idEmprunt);

    /**
     *
     * @param pseudoEmprunteur
     * @return
     */
    @GetMapping(value = "/microservicelivre/reservation/pseudo/{pseudoEmprunteur}")
    List<ReservationBean> listeReservationUtilisateur(@PathVariable("pseudoEmprunteur") String pseudoEmprunteur);

    /**
     *
     * @param id
     */
    @GetMapping(value="/microservicelivre/reservation/{id}/pseudo/{pseudoEmprunteur}/annuler")
    List<ReservationBean>annulerReservation(@PathVariable("id") Long id,@PathVariable("pseudoEmprunteur") String pseudoEmprunteur);

    @PostMapping("/microservicelivre/reservation/livre/{id}/utilisateur/{pseudoEmprunteur}")
    void creerUneReservation(@PathVariable("id") Long id,@PathVariable("pseudoEmprunteur") String pseudoEmprunteur);

    @GetMapping(value = "/microservicelivre/Livre/{id}/utilisateur/{pseudo}")
    LivreBean recupererUnLivreParUtilisateur(@PathVariable("id") Long id,@PathVariable("pseudo") String pseudo);
}
