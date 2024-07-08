import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

@WebServlet("/hello.png")
public class GraphicServlet extends HttpServlet {
    
    private Random rand = new Random();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("image/png");

        BufferedImage image = new BufferedImage(640, 120, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        
        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        
        g.drawString("Hello World!", 100, 100);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        out.close();
    }
}