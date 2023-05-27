package repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Address;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<Address> {
}