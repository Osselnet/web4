package servlet;

import service.CarService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProducerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String brand =  req.getParameter("brand");
        String model =  req.getParameter("model");
        String licensePlate =  req.getParameter("licensePlate");
        Long price = null;
        try {
            price =  Long.parseLong(req.getParameter("price"));
        } catch (NumberFormatException ignored) { }

        resp.setStatus(CarService.getInstance().addCar(brand, model, licensePlate, price) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_FORBIDDEN);
    }
}
