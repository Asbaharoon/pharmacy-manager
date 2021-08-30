package model;

import entity.Client;

public class ClientDAO extends AbstractDAO<Client> implements IDAO<Client> {

    public ClientDAO() {
        setClazz(Client.class);
    }

}
