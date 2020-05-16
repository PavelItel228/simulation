package dao;

import entity.humans.Children;

import java.util.List;

// Смотреть на GenericDao
public interface ChildrenDao extends GenericDAO<Children>{
     public List<Children> getAllDead();
     public void createDead(Children children);
     public void deleteAll();
}
