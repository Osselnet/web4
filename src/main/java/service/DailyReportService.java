package service;

import DAO.DailyReportDao;
import model.DailyReport;

import java.util.List;

public class DailyReportService {

    private static DailyReportService dailyReportService;

    private final DailyReportDao dailyReportDao = new DailyReportDao();

    private DailyReportService() {

    }

    public static DailyReportService getInstance() {
        if (dailyReportService == null) {
            dailyReportService = new DailyReportService();
        }
        return dailyReportService;
    }

    public List<DailyReport> getAllDailyReports() {
        return dailyReportDao.getAllDailyReport();
    }

    public DailyReport getLastReport() {
        return dailyReportDao.getLastReport();
    }

    public void addNewDay(long soldCars, long earnings) {
        dailyReportDao.changeDay(soldCars, earnings);
    }

    public void deleteAll() {
        dailyReportDao.deleteAll();
    }
}
