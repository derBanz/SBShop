package data;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Address;
import model.Client;
import services.AddressService;
import repositories.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AddressInitializer {

    @Inject
    AddressService addressService;

    @Inject
    ClientRepository clientRepository;

    public void populateAddresses() {
        System.out.println("Generating Addresses...");
        List<Address> addresses = generateAddresses();
        System.out.println("Passing Addresses to Service...");
        addressService.createAddresses(addresses);
    }

    private List<Address> generateAddresses () {
        List<List<String>> addressNames = List.of(
                List.of(
                        "Keltenstr.", "14", "16354", "Hohenhausen", "Germany"
                ),
                List.of(
                        "Teutonenweg", "10/1", "22341", "Göppingen", "Germany"
                ),
                List.of(
                        "Germanenstr.", "2", "56442", "Horb a. Neckar", "Germany"
                ),
                List.of(
                        "Römerstr.", "33-2", "13595", "Sulz", "Germany"
                ),
                List.of(
                        "Flamenweg", "11", "89522", "Oberndorf", "Germany"
                ),
                List.of(
                        "Gotenweg", "4", "46678", "Furtwangen", "Germany"
                ),
                List.of(
                        "Bayernweg", "1", "22387", "Waldkirch", "Germany"
                ),
                List.of(
                        "Sachsenallee", "7", "67655", "Bald Wildbad", "Germany"
                ),
                List.of(
                        "Langobardenweg", "5/1", "34451", "Maulbronn", "Germany"
                ),
                List.of(
                        "Normannenstr.", "6/5", "43379", "Brackenheim", "Germany"
                ),
                List.of(
                        "Suebenweg", "1", "94328", "Backnang", "Germany"
                ),
                List.of(
                        "Friesenweg", "3", "46852", "Bachhagel", "Germany"
                ),
                List.of(
                        "Alemannenstr.", "5", "89643", "Mutlangen", "Germany"
                ),
                List.of(
                        "Frankenweg", "11", "13758", "Lorch", "Germany"
                ),
                List.of(
                        "Hessenweg", "14", "84732", "Murrhardt", "Germany"
                )
        );
        List<Address> addresses = new ArrayList<>();
        List<Client> clients = clientRepository.listAll();

        for (List<String> name : addressNames) {
            Client client = clients.get((int)Math.floor(Math.random() * (clients.size())));
            Address address = new Address( client, name.get(0), name.get(1), name.get(2), name.get(3), name.get(4) );
            addresses.add(address);
        }

        return addresses;
    }
}
