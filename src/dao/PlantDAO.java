package dao;

import entity.Plants.Plant;

// Смотреть на GenericDao

public interface PlantDAO extends GenericDAO<Plant>{
    public void deleteAll();
}
