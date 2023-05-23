package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Address;
import repositories.AddressRepository;

@ApplicationScoped
public class AddressService {

    @Inject
    private AddressRepository addressRepository;

    @Transactional
    public Address createAddress(Address address) {
        addressRepository.persist(address);
        return address;
    }
}