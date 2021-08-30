package model;

import entity.Client;
import entity.Fournisseur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.engine.search.common.BooleanOperator;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import util.Utils;

import java.util.List;

public class ClientDAO extends AbstractDAO<Client> implements IDAO<Client> {

    public ClientDAO() {
        setClazz(Client.class);
    }

    public List<Client> filter(String str) throws InterruptedException {
        long id = 0;
        String name = "";

        if (Utils.isNumeric(str)) {
            id = Long.parseLong(str);
        } else {
            name = str;
            System.out.println(name);
        }

        final String finalName = name;
        final long finalId = id;

        if (str.isEmpty()) {
            return getAll();
        } else {
            Session session = getCurrentSession();
            SearchSession searchSession = Search.session(session);
            Transaction txn = session.beginTransaction();
            searchSession.massIndexer(Client.class).startAndWait();
            SearchResult<Client> result = searchSession.search(Client.class)
                    .where(f -> f.bool()
                            .should(f.simpleQueryString().fields( "nom", "prenom" )
                                    .matching(finalName)
                                    .defaultOperator( BooleanOperator.OR ))
                            .should((f.id().matching(finalId))))
                    .fetchAll();
            List<Client> hits = result.hits();
            txn.commit();
            session.close();
            return hits;
        }

    }
}
