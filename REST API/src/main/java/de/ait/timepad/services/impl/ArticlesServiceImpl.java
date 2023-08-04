package de.ait.timepad.services.impl;

import de.ait.timepad.dto.ArticleDto;
import de.ait.timepad.dto.NewArticleDto;
import de.ait.timepad.exceptions.IncorrectUserIdException;
import de.ait.timepad.models.Article;
import de.ait.timepad.models.User;
import de.ait.timepad.repositories.ArticlesRepository;
import de.ait.timepad.repositories.UsersRepository;
import de.ait.timepad.services.ArticlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static de.ait.timepad.dto.ArticleDto.from;

@RequiredArgsConstructor
@Service
public class ArticlesServiceImpl implements ArticlesService {

    private final UsersRepository usersRepository;

    private final ArticlesRepository articlesRepository;

    @Override
    public ArticleDto addArticle(NewArticleDto newArticle) {
        User user = usersRepository.findById(newArticle.getAboutUserId())
                .orElseThrow(() ->
                        new IncorrectUserIdException("Id <" + newArticle.getAboutUserId() + "> is not correct"));

        Article article = Article.builder()
                .text(newArticle.getText())
                .about(user)
                .publishDate(LocalDate.parse(newArticle.getPublishDate()))
                .build();

        articlesRepository.save(article);

        return from(article);
    }
}
