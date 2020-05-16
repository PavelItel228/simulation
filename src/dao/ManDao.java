package dao;

import entity.humans.Man;

// Смотреть на GenericDao

public interface ManDao extends GenericDAO<Man>{
    public void deleteAll();
}
