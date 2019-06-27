package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engineGet = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engineGet.process("cart/checkout.html", context, resp.getWriter());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderDaoMem OrderDaoData = OrderDaoMem.getInstance();


        OrderDaoData.add("fullname", req.getParameter("fullname"));
        OrderDaoData.add("email", req.getParameter("email"));
        OrderDaoData.add("address", req.getParameter("address"));
        OrderDaoData.add("city", req.getParameter("city"));
        OrderDaoData.add("state", req.getParameter("state"));
        OrderDaoData.add("zip", req.getParameter("zip"));

        System.out.println(OrderDaoData.getAll());

        resp.sendRedirect("/payment");
    }
}

