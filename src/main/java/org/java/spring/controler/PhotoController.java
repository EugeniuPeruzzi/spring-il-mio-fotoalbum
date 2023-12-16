package org.java.spring.controler;

import java.util.List;

import org.java.spring.auth.db.pojo.User;
import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.CategoryService;
import org.java.spring.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.validation.Valid;

@Controller
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    
    @Autowired
    private CategoryService categoryService;
    
//    PHOTO INDEX
    @GetMapping
    public String getPhotos(Model model, @RequestParam(required = false) String name) {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            int userId = user.getId();

            List<Photo> photos = name == null
                                ? photoService.findByUserId(userId)
                                : photoService.findByName(name, userId);

            model.addAttribute("photos", photos);
            model.addAttribute("name", name == null ? "" : name);
            model.addAttribute("userId", userId);
            return "photoHTML/photoIndex";
        }

        // Se non riesci ad ottenere l'ID dall'utente autenticato, gestisci la situazione di conseguenza
        return "redirect:/login"; // O un'altra pagina di login o una pagina di errore, a seconda delle tue esigenze
    }

    
  
    
//    PHOTO SHOW
    @GetMapping("/photo/{id}")
    public String getPhoto(Model model, @PathVariable int id) {
        
        Photo photo = photoService.findById(id);
        
        List<Category> categories = photo.getCategories();
        
        model.addAttribute("photo", photo);
        
        model.addAttribute("categories", categories);
        
        return "photoHTML/photoShow";
    }
    
    @GetMapping("/photo/create")
    public String createPhoto(Model model) {
        
        Photo photo = new Photo();
        List<Category> categories = categoryService.findAll();
        
        model.addAttribute("photo", photo);
        model.addAttribute("categories", categories);
        
        return "photoHTML/photoCreate";
    }
    @PostMapping("/photo/create")
    public String storePhoto(Model model, @Valid @ModelAttribute Photo photo, BindingResult bindingResult) {

        return savePhoto(model, photo, bindingResult);
    }
    
    @GetMapping("photo/edit/{id}")
    public String editPhoto(Model model, @PathVariable int id) {
        
        Photo photo = photoService.findById(id);
        List<Category> categories = categoryService.findAll();
        
        model.addAttribute("photo", photo);
        model.addAttribute("categories", categories);
        
        return "photoHTML/photoCreate";
    }
    @PostMapping("photo/edit/{id}")
    public String updatePhoto(Model model, @Valid @ModelAttribute Photo photo, BindingResult bindingResult) {
        
        return savePhoto(model, photo, bindingResult);
    }

    @PostMapping("/photo/delete/{id}")
    public String deletePhoto(@PathVariable int id) {
        
        Photo photo = photoService.findById(id);
        photoService.delete(photo);
        
        System.out.println(photo);
        
        return "redirect:/";
    }
    
    private String savePhoto(Model model, @Valid @ModelAttribute Photo photo, BindingResult bindingResult) {

        System.out.println("Photo:\n" + photo);
        System.out.println("\n---------------\n");
        System.out.println("Error:\n" + bindingResult);

        if (bindingResult.hasErrors()) {

            System.out.println(bindingResult);

            model.addAttribute("photo", photo);
            return "photoHTML/photoCreate";
        }

        photoService.save(photo);

        return "redirect:/";
    }
}