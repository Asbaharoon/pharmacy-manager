package model;

import entity.Commande;

public class CommandeDAO extends AbstractDAO<Commande> implements IDAO<Commande>  {
   public CommandeDAO() {
       setClazz(Commande.class);
   }
}
