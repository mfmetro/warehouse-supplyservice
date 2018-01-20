package de.metro.code.warehouse.supplyservice.components;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import de.metro.code.warehouse.supplyservice.persistence.ArticleRepository;
import de.metro.code.warehouse.supplyservice.persistence.SupplyRepository;
import de.metro.code.warehouse.supplyservice.persistence.model.Article;
import de.metro.code.warehouse.supplyservice.persistence.model.Supply;
import de.metro.code.warehouse.supplyservice.persistence.model.SupplyItem;

@Component
public class SupplyGenerator {

    private static final Logger log = LoggerFactory.getLogger(SupplyGenerator.class);

    private static final int MAX_NUMBER_OF_ARTICLES = 5;
    private static final List<Integer> deliveryQuantities = Arrays.asList(5, 10, 25, 50);

    private final Random random = new Random();

    @Value(value = "${supplyGenerator.probability:0.075}")
    private double probability;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SupplyRepository supplyRepository;

    @Scheduled(initialDelayString = "${supplyGenerator.initialDelay:10000}", fixedRateString = "${supplyGenerator.fixedRate:1000}")
    public void createRandomSupply() {
        if (random.nextDouble() < probability) {
            final int numberOfArticles = random.nextInt(MAX_NUMBER_OF_ARTICLES) + 1;
            final Supply supply = createSupply(numberOfArticles);
            supplyRepository.save(supply);
            log.info("new supply delivery generated: " + supply);
        }
    }

    private Supply createSupply(final int numberOfArticles) {
        final Set<Article> articles = getRandomArticles(numberOfArticles);
        final List<SupplyItem> supplyItems = articles.stream()
            .map(article -> SupplyItem.builder()
                .withArticle(article)
                .withQuantity(getRandomQuantity())
                .build())
            .collect(Collectors.toList());

        final Supply supply = Supply.builder()
            .withId(UUID.randomUUID().toString())
            .withArrivalTime(LocalDateTime.now())
            .withSupplyItems(supplyItems)
            .build();

        return supply;
    }

    private int getRandomQuantity() {
        return deliveryQuantities.get(random.nextInt(deliveryQuantities.size()));
    }

    private Set<Article> getRandomArticles(final int numberOfArticles) {
        final int repositorySize = articleRepository.getSize();
        if (numberOfArticles > repositorySize) {
            throw new RuntimeException("unable to select " + numberOfArticles + " out of " + repositorySize);
        }

        final Set<Article> articles = new HashSet<>();

        do {
            final Article article = articleRepository
                .getRandomArticle()
                .orElseThrow(() -> new RuntimeException("no articles present in article repository"));

            articles.add(article);
        } while (articles.size() < numberOfArticles);

        return articles;
    }
}
