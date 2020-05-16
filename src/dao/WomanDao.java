package dao;

import entity.humans.Woman;

import java.util.List;

// Смотреть на GenericDao

public interface WomanDao extends GenericDAO<Woman>{
    public void deleteAll();
    public List<Woman> getAllPregnant();
    public void makePregnant();
    public  void makeNotPregnant();
}
