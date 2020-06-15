package DAO;

import model.DailyReport;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.DBHelper;

import java.util.List;

public class DailyReportDao {

    private final Session session;

    public DailyReportDao() {
        this.session = DBHelper.getSessionFactory().openSession();
    }

    @SuppressWarnings("unchecked")
    public List<DailyReport> getAllDailyReport() {
        return (List<DailyReport>) session.createQuery("from DailyReport").list();
    }

    public DailyReport getLastReport() {
        try {
            Query query = session.createQuery("from DailyReport order by id desc");
             return (DailyReport) query.uniqueResult();
        } catch (HibernateException | ClassCastException e) {
            return null;
        }
    }

    public void changeDay(long soldCars, long earnings) {
        DailyReport dailyReport = new DailyReport(earnings, soldCars);
        session.save(dailyReport);
    }

    public void deleteAll() {
        session.createQuery("delete DailyReport").executeUpdate();
    }
}
