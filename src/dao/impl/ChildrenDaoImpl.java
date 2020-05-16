package dao.impl;

import dao.ChildrenDao;
import entity.humans.Children;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Реализация интерфейса для взаимодейсвия со списком детей

public class ChildrenDaoImpl implements ChildrenDao {

    private final ArrayList<Children> allChildrens;
    private final ArrayList<Children> deadChildren;

    public ChildrenDaoImpl(ArrayList<Children> allChildrens, ArrayList<Children> deadChildren) {
        this.allChildrens = allChildrens;
        this.deadChildren = deadChildren;
    }

    @Override
    public List<Children> getAllDead() {
        return deadChildren;
    }

    @Override
    public void createDead(Children children) {
        deadChildren.add(children);
    }

    @Override
    public void deleteAll() {
        allChildrens.clear();
        deadChildren.clear();
    }

    @Override
    public void create(Children entity) {
        allChildrens.add(entity);
    }

    @Override
    public Optional<Children> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Children> findAll() {
        return allChildrens;
    }

    @Override
    public void update(Children entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
