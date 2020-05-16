package dao.impl;

import dao.ManDao;
import entity.humans.Man;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Реализация интерфейса для взаимодейсвия со списком мужчин

public class ManDaoImpl implements ManDao {

    private final ArrayList<Man> men;

    public ManDaoImpl(ArrayList<Man> men) {
        this.men = men;
    }

    @Override
    public void deleteAll() {
        men.clear();
    }

    @Override
    public void create(Man entity) {
        men.add(entity);
    }

    @Override
    public Optional<Man> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Man> findAll() {
        return men;
    }

    @Override
    public void update(Man entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
