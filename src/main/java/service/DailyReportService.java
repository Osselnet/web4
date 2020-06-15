package service;

import DAO.DailyReportDao;
import model.DailyReport;
import org.hibernate.*;
import util.DBHelper;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private DailyReportService() {

    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService();
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        return new DailyReportDao().getAllDailyReport();
    }

    public DailyReport getLastReport() {
        return new DailyReportDao().getLastReport();
    }

    public void addNewDay(long soldCars, long earnings) {
        new DailyReportDao().changeDay(soldCars, earnings);
    }

    public void deleteAll() {
        new DailyReportDao().deleteAll();
    }
}
