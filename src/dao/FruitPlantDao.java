package dao;

import entity.Plants.FruitPlant;
// Смотреть на GenericDao

public interface FruitPlantDao extends GenericDAO<FruitPlant>{
    public void deleteAll();
}
