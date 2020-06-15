package DAO;

import model.DailyReport;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.DBHelper;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao() {
        this.session = (Session) DBHelper.getSessionFactory();
    }

    public List<DailyReport> getAllDailyReport() {
        List<DailyReport> dailyReports = session.createQuery("from DailyReport").list();
        session.close();
        return dailyReports;
    }

    public DailyReport getLastReport() {
        try {
            Query query = session.createQuery("from DailyReport order by id desc");
            return (DailyReport) query.list().get(0);
        } catch (HibernateException | ClassCastException e) {
            return null;
        } finally {
            session.close();
        }
    }

    public void changeDay(long soldCars, long earnings) {
        DailyReport dailyReport = new DailyReport(earnings, soldCars);
        session.save(dailyReport);
        session.close();
    }

    public void deleteAll() {
        session.createQuery("delete DailyReport").executeUpdate();
        session.close();
    }
}
