package repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Client;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Client> {
}