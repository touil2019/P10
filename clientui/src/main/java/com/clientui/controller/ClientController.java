package com.clientui.controller;

import com.clientui.beans.EmpruntBean;
import com.clientui.beans.LivreBean;
import com.clientui.beans.UtilisateurBean;
import com.clientui.proxies.MicroserviceLivreProxy;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MicroserviceLivreProxy livreProxy;

    @GetMapping("/")
    public String accueil(Model model,@RequestParam(name = "mc",defaultValue = "")String mc){

        List<LivreBean>livres = livreProxy.listeLivreRecherche(mc);
        model.addAttribute("livres", livres);
        model.addAttribute("mc",mc);


        return "accueil";
    }

   @RequestMapping("/details-livre/{id}")
    public String ficheLivre(@PathVariable Long id, Model model){


        LivreBean livre = livreProxy.recupererUnLivre(id);

        model.addAttribute("livre", livre);

       return "fiche-livre";
    }



    @RequestMapping(value = "/MonProfile", method = RequestMethod.GET)
    public String profil(Model model) {

        UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("utilisateur", utilisateur);

        List<EmpruntBean> listEmprunt= livreProxy.listeDEmpruntParUtilisateur(utilisateur.getUsername());
        model.addAttribute("listEmprunt",listEmprunt);

        model.addAttribute("livres",listEmprunt);

        return "/MonProfile";

    }

    @GetMapping(value = "/emprunt/prolongerEmprunt/{id}")
    public String prolongerEmprunt(@PathVariable("id")Long idEmprunt,Model model) {

        System.out.println("Appel Emprunt méthode prolongerEmprunt");

            UtilisateurBean utilisateur = (UtilisateurBean) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            livreProxy.prolongerEmprunt(idEmprunt);

            List<EmpruntBean> listEmprunt= livreProxy.listeDEmpruntParUtilisateur(utilisateur.getUsername());
            model.addAttribute("listEmprunt",listEmprunt);


            return "redirect:/MonProfile";
        }

}
