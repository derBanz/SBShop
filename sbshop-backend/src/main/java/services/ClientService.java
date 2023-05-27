package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Client;
import repositories.ClientRepository;

import java.util.List;

@ApplicationScoped
public class ClientService {

    @Inject
    private ClientRepository clientRepository;

    @Transactional
    public List<Client> createClients(List<Client> clients) {
        for (Client client : clients) {
            clientRepository.persist(client);
        }
        return clients;
    }

    private Client createClient(Client client) {
        clientRepository.persist(client);
        return client;
    }

    public Client getClient(Long clientId) {
        return clientRepository.findById(clientId);
    }
}