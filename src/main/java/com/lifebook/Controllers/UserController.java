package com.lifebook.Controllers;

import com.cloudinary.utils.ObjectUtils;
import com.lifebook.Model.AppUser;
import com.lifebook.Model.Shopping.Cart;
import com.lifebook.Model.Shopping.Item;
import com.lifebook.Model.UserPost;
import com.lifebook.Repositories.*;
import com.lifebook.Service.CloudinaryConfig;
import com.lifebook.Service.NewsService;
import com.lifebook.Service.ShoppingService;
import com.lifebook.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    AppRoleRepository roles;

    @Autowired
    UserPostRepository posts;

    @Autowired
    InterestRepository interests;

    @Autowired
    AppUserRepository users;

    @Autowired
    CloudinaryConfig cloudc;

    @Autowired
    WeatherService weatherService;

    @Autowired
    NewsService newsService;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ShoppingService shoppingService;

    @Autowired
    CartRepository cartRepository;


    @RequestMapping("/")
    public String homePageLoggedIn(Authentication authentication, Model model) {

        if (users.findByUsername(authentication.getName()).getRoles().contains(roles.findByRole("ADMIN"))) {
            return "redirect:/admin/";
        }
        else {

            model.addAttribute("articles", newsService.personalized(authentication));
            return "index";
        }
    }

    @PostMapping("/newmessage")
    public String sendMessage(@ModelAttribute("post") UserPost post,
                              @RequestParam("file") MultipartFile file, Authentication authentication) {
        post.setCreator(users.findByUsername(authentication.getName()));
        if (!file.isEmpty()) {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                String uploadedName = (String) uploadResult.get("public_id");

                String transformedImage = cloudc.createUrl(uploadedName);
                post.setImageUrl(transformedImage);

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/users/profile";
            }
        }

        Date today = new Date();

        post.setDateOfPost(today.toString());
        posts.save(post);

        return "redirect:/users/profile";
    }

    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        AppUser user = users.findByUsername(authentication.getName());

        model.addAttribute("currentuser", user);
        UserPost post = new UserPost();
        model.addAttribute("post",post);
        //model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
        Set<AppUser> following = user.getFollowing();
        List<UserPost> posts = new ArrayList<>(user.getPosts());
        for (AppUser u: following) {
            posts.addAll(u.getPosts());
        }
        Collections.reverse(posts);
        model.addAttribute("articles", newsService.personalized(authentication));

        model.addAttribute("posts", posts.toArray());
        return "profile";
    }
    @RequestMapping("/findnews")
    public String findNews(HttpServletRequest request,Model model, Authentication authentication) {
        AppUser user = users.findByUsername(authentication.getName());

        model.addAttribute("currentuser", user);
        UserPost post = new UserPost();
        model.addAttribute("post",post);
      // model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
        System.out.println(weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday().get(1).getDate());
        Set<AppUser> following = user.getFollowing();
        List<UserPost> posts = new ArrayList<>(user.getPosts());
        for (AppUser u: following) {
            posts.addAll(u.getPosts());
        }
        Collections.reverse(posts);
        //model.addAttribute("articles", newsService.personalized(authentication));
         model.addAttribute("articles",newsService.articlesBySearch(request.getParameter("query")));
        model.addAttribute("posts", posts.toArray());
        return "profile";
    }


    @RequestMapping("/following")
    public String displayFollowing(Model model, Authentication authentication) {
        AppUser sessionUser =users.findByUsername(authentication.getName());
        model.addAttribute("currentuser", sessionUser);
        Set<AppUser> following = sessionUser.getFollowing();
        Set<UserPost> posts = new HashSet<>();
        for (AppUser u: following) {
            posts.addAll(u.getPosts());
        }
        model.addAttribute("posts", posts);
        return "following";
    }

    @RequestMapping("/follow/{id}")
    public String follow (@PathVariable("id") long id, Authentication auth) {

        AppUser detail = users.findById(id).get();
        AppUser sessionUser = users.findByUsername(auth.getName());
        if(sessionUser.getFollowing().contains(detail)){
            detail.setMyFriend(true);
        }

        sessionUser.getFollowing().add(detail);
        sessionUser.setNoOfFriends(sessionUser.getFollowing().size());
        users.save(sessionUser);
        return "redirect:/users/profile";
    }

    @RequestMapping("/unfollow/{id}")
    public String unfollow (@PathVariable("id") long id, Authentication auth) {

        AppUser detail = users.findById(id).get();
        AppUser sessionUser = users.findByUsername(auth.getName());
        detail.setMyFriend(false);
        sessionUser.getFollowing().remove(detail);
        sessionUser.setNoOfFriends(sessionUser.getFollowing().size());
        users.save(sessionUser);
        return "redirect:/users/profile";
    }

    @RequestMapping("/findpost")
    public String showResults(HttpServletRequest request, Model model, Authentication authentication) {
        model.addAttribute("posts", posts.findAllByContentContainingIgnoreCase(request.getParameter("query")));
        model.addAttribute("currentuser", users.findByUsername(authentication.getName()));
        return "results";
    }

    @RequestMapping("/additem")
    public String addRoom(Model model,Authentication authentication) {
        model.addAttribute("anItem", new Item());
        AppUser user = users.findByUsername(authentication.getName());
        model.addAttribute("currentuser", user);
       // model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
        model.addAttribute("articles", newsService.personalized(authentication));
        return "itemform";
    }
    @RequestMapping("/saveitem")
    public String saveItem(@ModelAttribute("anItem") Item item,@RequestParam("file") MultipartFile file, Model model,Authentication authentication) {
        AppUser user = users.findByUsername(authentication.getName());
        item.setItemOwner(user.getUsername());
        itemRepository.save(item);



        model.addAttribute("currentuser", user);

       // model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
       // System.out.println(weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday().get(1).getDate());
        model.addAttribute("articles", newsService.personalized(authentication));
        if (!file.isEmpty()) {
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                String uploadedName = (String) uploadResult.get("public_id");

                String transformedImage = cloudc.createUrl(uploadedName);
                item.setProductImage(transformedImage);

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/users/profile";
            }
        }
        itemRepository.save(item);
        //model.addAttribute("items", itemRepository.findByOwner(user));
        return "redirect:/users/";
    }

    @RequestMapping("/buyitem")
    public String buyItem( Model model,Authentication authentication) {


        AppUser user = users.findByUsername(authentication.getName());

        model.addAttribute("currentuser", user);

       // model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
        model.addAttribute("articles", newsService.personalized(authentication));

        Cart myCart=new Cart();
        user.setUserCart(myCart);
        // model.addAttribute("myCart",new Cart());
        users.save(user);
        model.addAttribute("items", shoppingService.filteredItem(user));

        return "displayitem";
    }

    @RequestMapping("/notification")
    public String purchaseHistory( Model model,Authentication authentication) {


        AppUser user = users.findByUsername(authentication.getName());

        model.addAttribute("currentuser", user);

       // model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
        model.addAttribute("articles", newsService.personalized(authentication));

        users.save(user);
        model.addAttribute("items",  shoppingService.filteredItem(user));
        model.addAttribute("cart",user.getUserCart());
        return "notification";
    }
    @RequestMapping("/addtocart/{id}")
    public String addToCart(HttpServletRequest request,@PathVariable("id") long id,  Model model, Authentication authentication){
       AppUser user= users.findByUsername(authentication.getName());
        //System.out.println(user.getUsername());
       // model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
        model.addAttribute("articles", newsService.personalized(authentication));
        Item item=itemRepository.findById(id).get();
       int amount= Integer.parseInt(request.getParameter("amount"));
       item.setNumOfItem(amount);
        item.setNumberInTheStock(item.getNumberInTheStock()-amount);
        if(item.getNumberInTheStock()<=0){
            item.setSoldout(true);
                 }
       user.getUserCart().getItemPurchased().add(item);
        users.save(user);
       // cartRepository.save(myCart);

        model.addAttribute("currentuser",user);
        Cart mycartnow=shoppingService.priceCalculator(user.getUserCart());
        model.addAttribute("cart", shoppingService.priceCalculator(user.getUserCart()));
        model.addAttribute("items",  shoppingService.filteredItem(user));
        return "displayitem";

    }
    @RequestMapping("/cancel")
    public String cancelShopping(Authentication authentication,Model model){
        AppUser user= users.findByUsername(authentication.getName());
       // model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
        model.addAttribute("articles", newsService.personalized(authentication));
        model.addAttribute("cart",shoppingService.cancelShoping(user.getUserCart()));
        model.addAttribute("currentuser",user);
        model.addAttribute("items", shoppingService.filteredItem(user));
        return "displayitem";
    }
    @RequestMapping("/checkout")
    public String checkout(Authentication authentication,Model model){
        AppUser user= users.findByUsername(authentication.getName());


       // model.addAttribute("weatherforcast",weatherService.fetchForcast(user.getZipCode(),7).getForecast().getForecastday());
        model.addAttribute("articles", newsService.personalized(authentication));
        user.getUserCart().setCheckout(true);
        users.save(user);
        model.addAttribute("cart", shoppingService.pricePlusNotification(user.getUserCart(),user));
        user.setUserCart(shoppingService.pricePlusNotification(user.getUserCart(),user));
        users.save(user);
        model.addAttribute("items",user.getUserCart().getItemPurchased());
        model.addAttribute("currentuser",user);

        return "checkout";
    }
    @RequestMapping("/check")
    public String checkPurchase(Authentication authentication,Model model){
        AppUser user= users.findByUsername(authentication.getName());
        Cart myCart=shoppingService.priceCalculator(user.getUserCart());
        if(myCart.isCheckout()){
            return "redirect:/logout";
        }
        else if (myCart.getNumItemPurchased()==0){
            return "redirect:/logout";
        }
        else {
             shoppingService.cancelShoping(user.getUserCart());
            return "redirect:/logout";

        }


    }

}