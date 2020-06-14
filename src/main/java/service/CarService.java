package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private final SessionFactory sessionFactory;

    private CarService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService(DBHelper.getSessionFactory());
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return new CarDao(sessionFactory.openSession()).getAllCars();
    }

    public void buyCar(String brand, String model, String licensePlate) {
        new CarDao(sessionFactory.openSession()).buyCar(brand, model, licensePlate);
    }

    public boolean addCar(String brand, String model, String licensePlate, Long price) {
        if (new CarDao(sessionFactory.openSession()).getCountBrandCars(brand) <= 10) {
            new CarDao(sessionFactory.openSession()).addCar(brand, model, licensePlate, price);
            return true;
        }
        return false;
    }

    public void deleteAll() {
        new CarDao(sessionFactory.openSession()).deleteAll();
    }

    public long getCostSoldCars() {
        return  new CarDao(sessionFactory.openSession()).getCostSoldCars();
    }

    public long getCountSoldCars() {
        return  new CarDao(sessionFactory.openSession()).getCountSoldCars();
    }

    public void changeDay() {
        new CarDao(sessionFactory.openSession()).deleteBuyCars();
    }
}
