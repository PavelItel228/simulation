package dao.impl;

import dao.FruitPlantDao;
import entity.Plants.FruitPlant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Реализация интерфейса для взаимодейсвия со списком фруктових растений

public class FruitDaoImpl implements FruitPlantDao {

    private final ArrayList<FruitPlant> fruitPlants;

    public FruitDaoImpl(ArrayList<FruitPlant> fruitPlants) {
        this.fruitPlants = fruitPlants;
    }

    @Override
    public void create(FruitPlant entity) {
        fruitPlants.add(entity);
    }

    @Override
    public Optional<FruitPlant> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<FruitPlant> findAll() {
        return fruitPlants;
    }

    @Override
    public void update(FruitPlant entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void deleteAll() {
        fruitPlants.clear();
    }
}
