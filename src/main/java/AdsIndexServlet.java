import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="AdsIndexServlet", urlPatterns = "/ads")
public class AdsIndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // talk to the AdsDAO to get a collection of all Ad objects
        List<Ad> ads = DaoFactory.getAdsDao().all();

        // do a .setAttribute on that list to pass the data into the view
        request.setAttribute("ads", ads);

        // render the .jsp and send the request/response data
        request.getRequestDispatcher("/ads/index.jsp").forward(request, response);
    }
}