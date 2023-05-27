package data;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.inject.Inject;

@ApplicationScoped
public class DataInitializer {

    @Inject
    ArticleInitializer articleInitializer;
    @Inject
    ClientInitializer clientInitializer;
    @Inject
    AddressInitializer addressInitializer;

    @Transactional
    public void initData() {
        // Populate the database with articles and clients
        System.out.println("Starting initialization...");
        articleInitializer.populateArticles();
        clientInitializer.populateClients();
        addressInitializer.populateAddresses();
    }

}
