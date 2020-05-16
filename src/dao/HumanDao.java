package dao;

import entity.humans.Human;

import java.util.List;

// Смотреть на GenericDao

public interface HumanDao extends GenericDAO<Human>{
    public void deleteAll(boolean all);
    public int getAgeOfAll();
    public void createDiedByAge(Human human);
    public void createDiedByEnergy(Human human);
    public void createDiedBySatiety(Human human);
    public void createDiedByLost(Human human);
    public List<Human> getAllDiedByAge();
    public List<Human> getAllDiedByEnergy();
    public List<Human> getAllDiedBySatiety();
    public List<Human> getAllDiedByLost();
    public int allDead();
}
