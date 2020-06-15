package service;

import DAO.CarDao;
import model.Car;

import java.util.List;

public class CarService {

    private static CarService carService;

    private final CarDao carDao = new CarDao();

    private CarService() {

    }

    public static CarService getInstance() {
        if (carService == null) {
            carService = new CarService();
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    public void buyCar(String brand, String model, String licensePlate) {
        carDao.buyCar(brand, model, licensePlate);
    }

    public boolean addCar(String brand, String model, String licensePlate, Long price) {
        if (carDao.getCountBrandCars(brand) <= 10) {
            carDao.addCar(brand, model, licensePlate, price);
            return true;
        }
        return false;
    }

    public void deleteAll() {
        carDao.deleteAll();
    }

    public long getCostSoldCars() {
        return  carDao.getCostSoldCars();
    }

    public long getCountSoldCars() {
        return  carDao.getCountSoldCars();
    }

    public void changeDay() {
        carDao.deleteBuyCars();
    }
}
