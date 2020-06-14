package servlet;

import service.CarService;
import service.DailyReportService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewDayServlet extends HttpServlet {

    CarService carService = CarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        long soldCars = carService.getCountSoldCars();
        long earnings = carService.getCostSoldCars();

        DailyReportService.getInstance().addNewDay(soldCars, earnings);
        carService.changeDay();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
