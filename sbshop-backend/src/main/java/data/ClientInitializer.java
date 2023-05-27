package data;

import jakarta.enterprise.context.ApplicationScoped;
import model.Client;
import services.ClientService;

import java.util.*;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClientInitializer {

    @Inject
    ClientService clientService;

    public void populateClients() {
        System.out.println("Generating Clients...");
        List<Client> clients = generateClients();
        System.out.println("Passing Clients to Service...");
        clientService.createClients(clients);
    }

    private List<Client> generateClients () {
        List<List<String>> clientNames = List.of(
                List.of(
                        "Smith", "Liam", "Mr"
                ),
                List.of(
                        "Johnson", "Emma", "Mrs"
                ),
                List.of(
                        "Taylor", "Oliver", "Mr"
                ),
                List.of(
                        "Jones", "Amelia", "Mrs"
                ),
                List.of(
                        "Davis", "Elijah", "Mr"
                ),
                List.of(
                        "Wilson", "William", "Mr"
                ),
                List.of(
                        "Rodriguez", "Mia", "Mrs"
                ),
                List.of(
                        "Martinez", "Benjamin", "Mr"
                ),
                List.of(
                        "Campbell", "Theodore", "Mr"
                )
        );
        List<Client> clients = new ArrayList<>();

        for (List<String> name : clientNames) {
            Random rnd = new Random();
            Date dob = new Date(-946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000)));
            Client client = new Client(name.get(0), name.get(1), name.get(2), dob);
            clients.add(client);
        }

        return clients;
    }
}
