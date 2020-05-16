package dao.impl;

import dao.PlantDAO;
import entity.Plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Реализация интерфейса для взаимодейсвия со списком растений

public class PlantDaoImpl implements PlantDAO {

    private final ArrayList<Plant> plants;

    public PlantDaoImpl(ArrayList<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public void deleteAll() {
        plants.clear();
    }

    @Override
    public void create(Plant entity) {
        plants.add(entity);
    }

    @Override
    public Optional<Plant> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Plant> findAll() {
        return plants;
    }

    @Override
    public void update(Plant entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
