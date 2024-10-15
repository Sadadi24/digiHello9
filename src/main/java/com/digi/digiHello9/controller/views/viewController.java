package com.digi.digiHello9.controller.views;

import com.digi.digiHello9.exception.CustomException;
import com.digi.digiHello9.services.VilleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {
    @Autowired
    private VilleServices villeServices;
    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", "Hello World!");
        return "index";
    }

    @GetMapping("/villes")
    public String listVilles(Model model) throws CustomException {



        model.addAttribute("villes", villeServices.extractVilles());
        return "villes";
    }
}
