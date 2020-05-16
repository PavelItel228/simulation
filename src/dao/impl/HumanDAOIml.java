package dao.impl;

import dao.HumanDao;
import entity.humans.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Реализация интерфейса для взаимодейсвия со списком людей

public class HumanDAOIml implements HumanDao {

    private final ArrayList<Human> humans;
    private final ArrayList<Human> diedByAge;
    private final ArrayList<Human> diedByEnergy;
    private final ArrayList<Human> diedBySatiety;
    private final ArrayList<Human> diedByLost;


    public HumanDAOIml(ArrayList<Human> humans, ArrayList<Human> diedByAge, ArrayList<Human> diedByEnergy, ArrayList<Human> diedBySatiety, ArrayList<Human> diedByLost) {
        this.humans = humans;
        this.diedByAge = diedByAge;
        this.diedByEnergy = diedByEnergy;
        this.diedBySatiety = diedBySatiety;
        this.diedByLost = diedByLost;
    }

    @Override
    public void create(Human entity) {
        humans.add(entity);
    }

    @Override
    public Optional<Human> findById(Long id) {
        return Optional.ofNullable(humans.get(Math.toIntExact(id)));
    }

    @Override
    public List<Human> findAll() {
        return humans;
    }

    @Override
    public void update(Human entity) {

    }

    @Override
    public void delete(Long id) {
        humans.remove(Math.toIntExact(id));
    }

    @Override
    public void deleteAll(boolean all) {
        humans.clear();
        if(all) {
            diedByAge.clear();
            diedByEnergy.clear();
            diedBySatiety.clear();
            diedByLost.clear();
        }
    }

    @Override
    public int getAgeOfAll() {
        return humans.stream().mapToInt(Human::getAge).sum();
    }

    @Override
    public void createDiedByAge(Human human) {
        diedByAge.add(human);
    }

    @Override
    public void createDiedByEnergy(Human human) {
        diedByEnergy.add(human);
    }

    @Override
    public void createDiedBySatiety(Human human) {
        diedBySatiety.add(human);
    }

    @Override
    public void createDiedByLost(Human human) {
        diedByLost.add(human);
    }

    @Override
    public List<Human> getAllDiedByAge() {
        return diedByAge;
    }

    @Override
    public List<Human> getAllDiedByEnergy() {
        return diedByEnergy;
    }

    @Override
    public List<Human> getAllDiedBySatiety() {
        return diedBySatiety;
    }

    @Override
    public List<Human> getAllDiedByLost() {
        return diedByLost;
    }

    @Override
    public int allDead() {
        return diedByLost.size() + diedBySatiety.size() + diedByEnergy.size() + diedByAge.size();
    }
}
