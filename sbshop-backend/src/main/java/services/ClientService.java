package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Client;
import repositories.ClientRepository;

@ApplicationScoped
public class ClientService {

    @Inject
    private ClientRepository clientRepository;

    @Transactional
    public Client createClient(Client client) {
        clientRepository.persist(client);
        return client;
    }

    public Client getClient(Long clientId) {
        return clientRepository.findById(clientId);
    }
}