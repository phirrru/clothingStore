package com.project.clothing_store.controllers;

import com.project.clothing_store.models.Favorites;
import com.project.clothing_store.models.User;
import com.project.clothing_store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class FavoritesController {

    @Autowired
    private ClothesService clothesService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;


    @Autowired
    private FavoritesService favService;



    @GetMapping("/favorites")
    public String favorites(Model model,
                         @AuthenticationPrincipal User user){

        int userId = user.getId();
        model.addAttribute("types", typeService.getAllTypes());

        List<Favorites> adds = favService.getAddsByUserId(userId);

        model.addAttribute("favorites", adds);
        model.addAttribute("clothesService", clothesService);

        return "favorites";
    }

    @PostMapping("/favoritesDeleteAdd")
    public String deleteAdd(@RequestParam(value = "delButton") int addId){
        favService.deleteAddById(addId);
        return "redirect:/favorites";
    }


}

