package pk.wieik.it.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pk.wieik.it.model.MNuser;
import pk.wieik.it.model.Tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@WebServlet(name = "MN", value = "/MN")
public class MN extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        ServletContext context = getServletContext();
        PrintWriter out = response.getWriter();
        String page = request.getParameter("page");
        page = Tools.parsePage(page, "main;quadratic;third");

        HttpSession session = request.getSession();
        String attribute1 = (String) session.getAttribute("attribute1");
        Integer attribute2 = (Integer) session.getAttribute("attribute2");
        if (attribute1 == null)
            attribute1 = "";
        if (attribute2 == null)
            attribute2 = 0;

        MNuser user = (MNuser) session.getAttribute("user");
        if (user == null) {
            user = new MNuser();
            session.setAttribute("user", user);
        }

        String template = Tools.getTemplate("index.html", context);

        template = Tools.fill(template, "HEADER", "header.html", context);
        //template = Tools.fill(template, "MENU", "menu.html", context);

        template = Tools.fill(template, "CONTENT", page + ".html", context);
        template = Tools.fill(template, "FOOTER", "footer.html", context);

        if (user.getPrivileges() > 0) {
            String loggedInMessage;
            if (user.getPrivileges() == 1) {
                loggedInMessage = "You are logged in as <b>user</b>";
            } else if (user.getPrivileges() == 2) {
                loggedInMessage = "You are logged in as <b>admin</b>";
            } else {
                loggedInMessage = "";
            }

            template = Tools.fill(template, "MENU", "menu2.html", context);
            template = template.replace("[[LOGIN]]", loggedInMessage);
        } else {
            template = Tools.fill(template, "MENU", "menu.html", context);
        }


        String[] jsFiles = {"script.js", "quadratic.js"};

        template = Tools.addScript(template, jsFiles);

        Integer value = 0;
        Cookie[] cookies = request.getCookies();
        Cookie counterCookie = new Cookie("counter", "0");
        for (Cookie ciastko : cookies) {
            if (ciastko.getName().equals("counter"))
                counterCookie = ciastko;
        }
        try {
            value = Integer.parseInt(counterCookie.getValue(), 0);
        } catch (NumberFormatException e) {
            value = 0;
        }
        value++;
        Cookie licznik = new Cookie("counter", value.toString());
        licznik.setMaxAge(60 * 60 * 24);
        response.addCookie(licznik);
        /*out.println("counter: " + value);*/


//        String page2 = request.getParameter("page");
        if (user.getPrivileges() > 0)
            page = Tools.parsePage(page, "main;quadratic;third;settings");

//        else
//            page2 = Tools.parsePage(page, "main;quadratic;third;");

//        user.setLogin("user0");
//        user.setPrivileges(0);
//        out.println(user);


        out.println(template);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        MNuser user = new MNuser();



        if ("user".equals(login) && "user".equals(password)) {
            user.setLogin(login);
            user.setPrivileges(1);
            session.setAttribute("user", user);


        }
        else if ("admin".equals(login) && "admin".equals(password)) {
            user.setLogin(login);
            user.setPrivileges(2);
            session.setAttribute("user", user);
        }

        else {
            session.removeAttribute("user");

            doGet(request, response);

            return;
        }

        doGet(request, response);


    }

}