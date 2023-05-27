package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Address;
import repositories.AddressRepository;

import java.util.List;

@ApplicationScoped
public class AddressService {

    @Inject
    private AddressRepository addressRepository;

    @Transactional
    public List<Address> createAddresses(List<Address> addresses) {
        for (Address address : addresses) {
            createAddress(address);
        }
        return addresses;
    }
    @Transactional
    public Address createAddress(Address address) {
        addressRepository.persist(address);
        return address;
    }

    public Address getArticle(Long addressId) {
        return addressRepository.findById(addressId);
    }
}