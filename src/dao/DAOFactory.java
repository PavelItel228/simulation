package dao;

import dao.impl.ArrayListDaoFactory;

public abstract class DAOFactory {
    public abstract HumanDao getHumanDao();
    public abstract WomanDao getWomanDao();
    public abstract ManDao getManDao();
    public abstract ChildrenDao getChildrenDao();
    public abstract PlantDAO getPlantDao();
    public abstract FruitPlantDao getFruitPlant();

    private static DAOFactory daoFactory;

    public static DAOFactory getInstance(){
        if (daoFactory == null) {
            synchronized (DAOFactory.class) {
                if (daoFactory == null){
                    daoFactory = new ArrayListDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}
