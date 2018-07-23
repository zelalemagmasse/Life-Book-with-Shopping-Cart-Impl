package com.lifebook.Service;

import com.lifebook.Model.AppUser;
import com.lifebook.Model.Article;
import com.lifebook.Model.Articles;
import com.lifebook.Model.Interest;
import com.lifebook.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class NewsService {

    @Autowired
    AppUserRepository users;

    public Iterable<Article> articlesByCategory(String category) {
        RestTemplate fromApi = new RestTemplate();
        Articles articles = fromApi.getForObject("https://newsapi.org/v2/top-headlines?country=us&category=" + category + "&apiKey=3b28a13d11f14ee085aa28e77986059a", Articles.class);
        return articles.getArticles();
    }

    //    public Iterable<Article> articlesByInterests(String interests){
//        RestTemplate fromApi = new RestTemplate();
//        Articles articles = fromApi.getForObject("https://newsapi.org/v2/top-headlines?q="+interests+"&apiKey=3b28a13d11f14ee085aa28e77986059a", Articles.class);
//        return articles.getArticles();
//    }
    public Iterable<Article> articlesBySearch(String searchterms) {
        RestTemplate fromApi = new RestTemplate();
        Articles articles = fromApi.getForObject("https://newsapi.org/v2/everything?q=" + searchterms + "&from=" + LocalDate.now().minusDays(1) + "&sortBy=relevancy&apiKey=3b28a13d11f14ee085aa28e77986059a", Articles.class);

        return articles.getArticles();
    }

    public Iterable<Article> defaultArticles() {
        RestTemplate fromApi = new RestTemplate();
        Articles articles = fromApi.getForObject("https://newsapi.org/v2/top-headlines?country=us&apiKey=3b28a13d11f14ee085aa28e77986059a", Articles.class);

        return articles.getArticles();
    }

    public Iterable<Article> personalized(Authentication authentication) {
        AppUser thisUser = users.findByUsername(authentication.getName());
        Set<Article> articles = new HashSet<>();

        for (Interest interest : thisUser.getInterests()) {
            for (Article article : articlesByCategory(interest.getName()))
                articles.add(article);
        }

        return articles;
    }
}
