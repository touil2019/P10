package com.clientui.controller;

import com.clientui.beans.LivreBean;
import com.clientui.proxies.MicroserviceLivreProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ConnexionController {

    @Autowired
    private MicroserviceLivreProxy livreProxy;

    @RequestMapping(value = "/connexion", method = RequestMethod.GET)
    public ModelAndView loginGet(Model model,
                                 @RequestParam(value = "error", required = false) String error,
                                 @RequestParam(value = "logout", required = false) String logout) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String errorMessage = null;
        if (error != null) {

            errorMessage = "Pseudo ou mot de passe incorrects !";
        }
        if (logout != null) {
            errorMessage = "Vous avez été déconecté avec succès !";

        }
        model.addAttribute("errorMessge", errorMessage);

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return new ModelAndView("redirect:/MonProfile");
        }
        return new ModelAndView("Connexion");
    }

    @RequestMapping("/logout")
    public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        List<LivreBean> livres = livreProxy.listelivres();
        model.addAttribute("livres",livres);
        return new ModelAndView("Accueil");
    }
}