package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import util.DBHelper;

import java.util.List;

import static java.util.Objects.nonNull;

public class CarDao {

    private final Session session;

    public CarDao() {
        this.session = DBHelper.getSessionFactory().openSession();
    }

    @SuppressWarnings("unchecked")
    public List<Car> getAllCars() {
        List<Car> cars = (List<Car>) session.createQuery("from Car where sold = 0").list();
        return cars;
    }

    public void addCar(String brand, String model, String licensePlate, Long price) {
        Car car = new Car(brand, model, licensePlate, price);
        session.save(car);
    }

    public long getCountBrandCars(String brand) {
        Query query = session.createQuery("select count(C.id) from Car C where brand = :carsBrand and sold = 0");
        query.setParameter("carsBrand", brand);
        return (Long) query.uniqueResult();
    }

    public void buyCar(String brand, String model, String licensePlate) {
        Query query = session.createQuery("update Car set sold = 1 where brand = :carsBrand and model = :carsModel and licensePlate = :carsLicensePlate");
        query.setParameter("carsBrand", brand);
        query.setParameter("carsModel", model);
        query.setParameter("carsLicensePlate", licensePlate);
        query.executeUpdate();
    }

    public void deleteAll() {
        session.createQuery("delete Car").executeUpdate();
    }

    public long getCostSoldCars() {
        long result = 0;
        Query query = session.createQuery("select sum(price) from Car where sold = 1");
        if (nonNull(query.uniqueResult())) {
            result = (Long) query.uniqueResult();
        }
        return result;
    }

    public long getCountSoldCars() {
        long result = 0;
        Query query = session.createQuery("select count(*) from Car where sold = 1");
        if (nonNull(query.uniqueResult())) {
            result = (Long) query.uniqueResult();
        }
        return result;
    }

    public void deleteBuyCars() {
        session.createQuery("delete Car where sold = 1").executeUpdate();
    }
}
