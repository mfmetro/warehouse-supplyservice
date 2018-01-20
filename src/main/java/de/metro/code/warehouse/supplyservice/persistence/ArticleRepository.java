package de.metro.code.warehouse.supplyservice.persistence;

import java.util.List;
import java.util.Optional;

import de.metro.code.warehouse.supplyservice.persistence.model.Article;

public interface ArticleRepository {

    Article save(Article article);

    List<Article> findAll();

    Optional<Article> getRandomArticle();

    int getSize();
}