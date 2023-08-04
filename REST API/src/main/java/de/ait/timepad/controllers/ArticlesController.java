package de.ait.timepad.controllers;

import de.ait.timepad.controllers.api.ArticlesApi;
import de.ait.timepad.dto.ArticleDto;
import de.ait.timepad.dto.NewArticleDto;
import de.ait.timepad.services.ArticlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ArticlesController implements ArticlesApi {

    private final ArticlesService articlesService;

    @Override
    public ResponseEntity<ArticleDto> addArticle(NewArticleDto newArticle) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articlesService.addArticle(newArticle));
    }

    // de adaugat getArticles

}
