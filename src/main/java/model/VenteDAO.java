package model;

import entity.Vente;

public class VenteDAO extends AbstractDAO<Vente> implements IDAO<Vente> {

    public VenteDAO() {
        setClazz(Vente.class);
    }
}
