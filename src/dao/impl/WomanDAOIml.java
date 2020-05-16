package dao.impl;

import dao.WomanDao;
import entity.humans.Woman;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Реализация интерфейса для взаимодейсвия со списком женщин
public class WomanDAOIml implements WomanDao {

    private final ArrayList<Woman> women;

    public WomanDAOIml(ArrayList<Woman> women) {
        this.women = women;
    }

    @Override
    public void deleteAll() {
        women.clear();
    }

    @Override
    public List<Woman> getAllPregnant() {
        return women.stream().filter(Woman::isPregnant).collect(Collectors.toList());
    }

    @Override
    public void makePregnant() {
        women.stream().filter(not(Woman::isPregnant)).findFirst().orElse(new Woman()).setPregnant(true);
    }

    @Override
    public void makeNotPregnant() {
        women.stream().filter(Woman::isPregnant).findFirst().orElse(new Woman()).setPregnant(true);
    }

    @Override
    public void create(Woman entity) {
        women.add(entity);
    }

    @Override
    public Optional<Woman> findById(Long id) {
        return Optional.ofNullable(women.get(Math.toIntExact(id)));
    }

    @Override
    public List<Woman> findAll() {
        return women;
    }

    @Override
    public void update(Woman entity) {

    }

    @Override
    public void delete(Long id) {

    }

    public static <T> Predicate<T> not(Predicate<T> t) {
        return t.negate();
    }
}
