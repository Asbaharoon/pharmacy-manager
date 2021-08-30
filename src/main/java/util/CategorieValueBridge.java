package util;

import entity.Categorie;
import org.hibernate.search.mapper.pojo.bridge.ValueBridge;
import org.hibernate.search.mapper.pojo.bridge.runtime.ValueBridgeToIndexedValueContext;

public class CategorieValueBridge implements ValueBridge<Categorie, String> {

    @Override
    public String toIndexedValue(Categorie categorie, ValueBridgeToIndexedValueContext context) {
        return categorie == null ? null : categorie.getNom();
    }
}


