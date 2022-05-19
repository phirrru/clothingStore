package com.project.clothing_store.controllers;

import com.project.clothing_store.models.*;
import com.project.clothing_store.repo.ClothesRepository;
import com.project.clothing_store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private ClothesRepository clothesRepository;

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


    @GetMapping("/")
    public String mainPage(
            @AuthenticationPrincipal User user,
            Authentication authentication,
            @RequestParam(name = "typeId", required = false) Integer typeId,
            Model model) {

        String auth = user.getAuthorities().toString();


        model.addAttribute("authority", auth);
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("typeId", typeId);
        model.addAttribute("title", "Каталог");
        Iterable<Clothes> clothes = clothesRepository.findAll();
        model.addAttribute("clothes", clothes);


        if (typeId == null) {
//            model.addAttribute("products", products);
            model.addAttribute("clothes", clothes);
        }
        else{
            model.addAttribute("clothes", clothesService.getAllItemsByTypeId(typeId));
        }

        return "index";
    }

    @Controller
    public class ItemsController {

//        @GetMapping("/page/{id}")
//        public String clothesPage(
//                @AuthenticationPrincipal User user,
//                @PathVariable(value="id") int id,
//                Model model) {
//
//            Clothes item = clothesService.getItemById(id);
//
//            model.addAttribute("item", item);
//            model.addAttribute("itemId", id);
//            model.addAttribute("item_type", typeService.getTypeById(item.getTypeId()));
//            model.addAttribute("types", typeService.getAllTypes());
//
//            Optional<Clothes> clothes = Optional.ofNullable(clothesRepository.findById(id));
//            ArrayList<Clothes> res = new ArrayList<>();
//            clothes.ifPresent(res::add);
//            model.addAttribute("clothes", res);
//
//            return "item-desc";
//        }

        @GetMapping("/add")
        public String itemAddForm(Model model){
            return "itemAdd";
        }

        @PostMapping("/add")
        public String itemSave(@RequestParam String item_name,
                               @RequestParam String cover_link, @RequestParam int price,
                               @RequestParam int typeId, Model model){
            Clothes item = new Clothes(
                    item_name,
                    cover_link,
                    price,
                    typeId);

            clothesRepository.save(item);

            return "redirect:/";
        }

    }


    private int getUserId(Authentication authentication) {
        if (authentication == null)
            return 0;
        else
            return ((User)userService.loadUserByUsername(authentication.getName())).getId();
    }


    @PostMapping("/page/{id}")
    public String addItemToBasket(
            @RequestParam (required = false) String itemSize ,
            Authentication authentication,
            @PathVariable(value = "id") int itemId,
            Model model
    ) {
            int userId = getUserId(authentication);
            Basket basket = basketService.getPurchaseByUserIdAndItemId(userId, itemId);
            if (basket == null){
                Basket newBasket = new Basket();
                newBasket.setUserId(userId);
                newBasket.setItemId(itemId);
                newBasket.setItemCount(1);
                newBasket.setItemSize(itemSize);
                basketService.savePurchase(newBasket);
//                return "redirect:/page/" + itemId;
                return "redirect:/";
            }
            else{
                basket.setItemCount(basket.getItemCount() + 1);
                basketService.savePurchase(basket);
//                return "redirect:/page/" + itemId;
                return "redirect:/";
//            }
        }
    }

    @PostMapping("/favorites/{id}")
    public String addItemToFavs(
            Authentication authentication,
            @PathVariable(value = "id") int itemId,
            Model model
    ) {
        int userId = getUserId(authentication);
        Favorites favs = favService.getAddByUserIdAndItemId(userId, itemId);
        if (favs == null){
            Favorites newFavorites = new Favorites();
            newFavorites.setUserId(userId);
            newFavorites.setItemId(itemId);
            favService.saveAdd(newFavorites);
//                return "redirect:/page/" + itemId;
            return "redirect:/";
        }
        else{
            return "redirect:/";
//            }
        }
    }


    }
