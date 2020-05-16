package dao.impl;

import dao.*;
import entity.Plants.FruitPlant;
import entity.Plants.Plant;
import entity.humans.Children;
import entity.humans.Human;
import entity.humans.Man;
import entity.humans.Woman;

import java.util.ArrayList;
// Фабрика которая отдает обьекты классов для взаимолейсвия со списками обьектов людей растений и тд

public class ArrayListDaoFactory extends DAOFactory {
    @Override
    public HumanDao getHumanDao() {
        return new HumanDAOIml(humans, diedByAge, diedByEnergy, diedBySatiety, diedByLost);
    }

    @Override
    public WomanDao getWomanDao() {
        return new WomanDAOIml(women);
    }

    @Override
    public ManDao getManDao() {
        return new ManDaoImpl(men);
    }

    @Override
    public ChildrenDao getChildrenDao() {
        return new ChildrenDaoImpl(children, childrenDead);
    }

    @Override
    public PlantDAO getPlantDao() {
        return new PlantDaoImpl(plants);
    }

    @Override
    public FruitPlantDao getFruitPlant() {
        return new FruitDaoImpl(fruitPlants);
    }


    ArrayList<Human> humans;
    ArrayList<Woman> women;
    ArrayList<Man> men;
    ArrayList<Human> diedByAge;
    ArrayList<Human> diedByEnergy;
    ArrayList<Human> diedBySatiety;
    ArrayList<Human> diedByLost;
    ArrayList<Children> children;
    ArrayList<Children> childrenDead;
    ArrayList<Plant> plants;
    ArrayList<FruitPlant> fruitPlants;


    public ArrayListDaoFactory(){
        plants = new ArrayList<>();
        fruitPlants = new ArrayList<>();
        childrenDead = new ArrayList<>();
        children = new ArrayList<>();
        men = new ArrayList<>();
        humans = new ArrayList<>();
        women = new ArrayList<>();
        diedByAge = new ArrayList<>();
        diedByEnergy = new ArrayList<>();
        diedBySatiety = new ArrayList<>();
        diedByLost = new ArrayList<>();
    }
}
