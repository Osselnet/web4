package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class CarDao {

    private final Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    public List<Car> getAllCars() {
        List<Car> cars = (List<Car>) session.createQuery("from Car where sold = 0").list();
        session.close();
        return cars;
    }

    public void addCar(String brand, String model, String licensePlate, Long price) {
        Car car = new Car(brand, model, licensePlate, price);
        session.save(car);
        session.close();
    }

    public int getCountBrandCars(String brand) {
        Query query = session.createQuery("select count(C.id) from Car C where brand = :carsBrand and sold = 0");
        query.setParameter("carsBrand", brand);
        return Integer.parseInt(query.list().get(0).toString());
    }

    public void buyCar(String brand, String model, String licensePlate) {
        Query query = session.createQuery("update Car set sold = 1 where brand = :carsBrand and model = :carsModel and licensePlate = :carsLicensePlate");
        query.setParameter("carsBrand", brand);
        query.setParameter("carsModel", model);
        query.setParameter("carsLicensePlate", licensePlate);
        query.executeUpdate();
        session.close();
    }

    public void deleteAll() {
        session.createQuery("delete Car").executeUpdate();
        session.close();
    }

    public long getCostSoldCars() {
        Query query = session.createQuery("select sum(price) from Car where sold = 1");
        return Long.parseLong(query.list().get(0).toString());
    }

    public long getCountSoldCars() {
        Query query = session.createQuery("select count(*) from Car where sold = 1");
        return Long.parseLong(query.list().get(0).toString());
    }

    public void deleteBuyCars() {
        session.createQuery("delete Car where sold = 1").executeUpdate();
        session.close();
    }
}
