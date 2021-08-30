package model;

import entity.Fournisseur;
import entity.Produit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.engine.search.common.BooleanOperator;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import util.Utils;

import java.util.List;

public class FournisseurDAO extends AbstractDAO<Fournisseur> implements IDAO<Fournisseur> {

    public FournisseurDAO() {
        setClazz(Fournisseur.class);
    }

    public List<Fournisseur> filter(String str) throws InterruptedException {
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
            searchSession.massIndexer(Fournisseur.class).startAndWait();
            SearchResult<Fournisseur> result = searchSession.search(Fournisseur.class)
                    .where(f -> f.bool()
                            .should(f.simpleQueryString().fields( "nom", "prenom" )
                                    .matching(finalName)
                                    .defaultOperator( BooleanOperator.OR ))
                            .should((f.id().matching(finalId))))
                    .fetchAll();
            List<Fournisseur> hits = result.hits();
            txn.commit();
            session.close();
            return hits;
        }

    }
}
