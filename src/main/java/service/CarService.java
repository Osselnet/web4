package service;

import DAO.CarDao;
import model.Car;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class CarService {

    private static CarService carService;

    private CarService() {

    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService();
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return new CarDao().getAllCars();
    }

    public void buyCar(String brand, String model, String licensePlate) {
        new CarDao().buyCar(brand, model, licensePlate);
    }

    public boolean addCar(String brand, String model, String licensePlate, Long price) {
        if (new CarDao().getCountBrandCars(brand) <= 10) {
            new CarDao().addCar(brand, model, licensePlate, price);
            return true;
        }
        return false;
    }

    public void deleteAll() {
        new CarDao().deleteAll();
    }

    public long getCostSoldCars() {
        return  new CarDao().getCostSoldCars();
    }

    public long getCountSoldCars() {
        return  new CarDao().getCountSoldCars();
    }

    public void changeDay() {
        new CarDao().deleteBuyCars();
    }
}
