package model;

import entity.Avoir;
import entity.Client;
import entity.Commande;

public class AvoirDAO extends AbstractDAO<Avoir> implements IDAO<Avoir> {
    public AvoirDAO() {
        setClazz(Avoir.class);
    }
}
