package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entity.Categorie;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Produit;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import util.Utils;

public class ProduitDAO extends AbstractDAO<Produit> implements IDAO<Produit> {

    public ProduitDAO() {
        setClazz(Produit.class);
    }

    public Set<String> getPAs() {
        List<Produit> produitList = getAll();
        Set<String> paStringList = new HashSet<>();
        produitList.forEach(produit -> paStringList.add(produit.getPrincipeActif()));
        return paStringList;
    }

    public List<Produit> filter(String str, Categorie cat, String pa) throws InterruptedException {
        // TEST WHETHER STR GIVEN IN PARAMETER IS ID OR DESIGNATION
        long id = 0;
        String desig = "";
        if (Utils.isNumeric(str)) {
            id = Long.parseLong(str);
        } else {
            desig = "*" + str + "*";
            System.out.println(desig);
        }

        // CORRECT LOGIC ISSUES REGARDING COMBO BOXES
        if (cat != null && cat.getNom().equals("Toutes les categories")) {
            cat = null;
        }

        if (pa != null && pa.equals("Tous les principes actifs")) {
            pa = null;
        }

        Session session = getCurrentSession();
        SearchSession searchSession = Search.session(session);
        Transaction transaction = session.beginTransaction();
        searchSession.massIndexer(Produit.class).startAndWait();
        SearchResult<Produit> result;

        final String finalDesig = desig;
        final long finalId = id;
        final Categorie finalCat = cat;
        final String finalPa = pa;

        if (cat == null && pa == null) {
            result = searchSession.search(Produit.class)
                    .where(f -> f.bool()
                            .should(f.phrase()
                                    .field("designation")
                                    .matching(finalDesig).slop(3))
                            .should(f.wildcard()
                                    .field("designation")
                                    .matching(finalDesig))
                            .should((f.id().matching(finalId))))
                    .fetchAll();
        } else if (cat == null) {
            result = searchSession.search(Produit.class)
                    .where(f -> f.bool()
                            .must(f.bool()
                                    .should(f.wildcard()
                                            .field("designation")
                                            .matching(finalDesig))
                                    .should((f.id()
                                            .matching(finalId))))
                            .must(f.match()
                                    .field("principeActif")
                                    .matching(finalPa)))
                    .fetchAll();
        } else if (pa == null) {
            result = searchSession.search(Produit.class)
                    .where(f -> f.bool()
                            .must(f.bool()
                                    .should(f.wildcard()
                                            .field("designation")
                                            .matching(finalDesig))
                                    .should((f.id()
                                            .matching(finalId))))
                            .must(f.match()
                                    .field("categorie")
                                    .matching(finalCat)))
                    .fetchAll();
        } else {
            result = searchSession.search(Produit.class)
                    .where(f -> f.bool()
                            .must(f.bool()
                                    .should(f.wildcard()
                                            .field("designation")
                                            .matching(finalDesig))
                                    .should((f.id()
                                            .matching(finalId))))
                            .must(f.match()
                                    .field("categorie")
                                    .matching(finalCat))
                            .must(f.match()
                                    .field("principeActif")
                                    .matching(finalPa)))
                    .fetchAll();
        }
        assert result != null;
        List<Produit> hits = result.hits();
        transaction.commit();
        session.close();
        return hits;
    }
}