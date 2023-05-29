package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Address;
import model.Client;
import repositories.AddressRepository;
import repositories.ClientRepository;

import java.util.List;

@ApplicationScoped
public class AddressService {

    @Inject
    private ClientRepository clientRepository;
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
        Long clientId = address.getClientId();
        Client client = clientRepository.findById(clientId);
        address.setClient(client);
        return address;
    }

    public Address getAddress(Long addressId) {
        return addressRepository.findById(addressId);
    }

    public List<Address> getAddresses() { return addressRepository.listAll(); }

    @Transactional
    public Address update(Long addressId, Address address) {
        Address addressEdit = addressRepository.findById(addressId);
        if (addressEdit != null) {
            addressEdit.setStreet(address.getStreet());
            addressEdit.setHouseNumber(address.getHouseNumber());
            addressEdit.setZipCode(address.getZipCode());
            addressEdit.setCity(address.getCity());
            addressEdit.setCountry(address.getCountry());
        }
        return addressEdit;
    }

    @Transactional
    public boolean deleteAddress(Long addressId) {
        return addressRepository.deleteById(addressId);
    }
}