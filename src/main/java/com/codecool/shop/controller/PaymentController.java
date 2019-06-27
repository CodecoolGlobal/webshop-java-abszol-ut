package com.codecool.shop.controller;


import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engineGet = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engineGet.process("cart/payment.html", context, resp.getWriter());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderDaoMem OrderDaoData = OrderDaoMem.getInstance();


        OrderDaoData.add("cardname", req.getParameter("cardname"));
        OrderDaoData.add("cardnumber", req.getParameter("cardnumber"));
        OrderDaoData.add("expmonth", req.getParameter("expmonth"));
        OrderDaoData.add("expyear", req.getParameter("expyear"));
        OrderDaoData.add("cvv", req.getParameter("cvv"));

        System.out.println(OrderDaoData.getAll());

        resp.sendRedirect("/confirmation");
    }
}
