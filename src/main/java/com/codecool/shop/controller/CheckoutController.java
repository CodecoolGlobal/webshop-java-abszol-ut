package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("The checkout's GET method was invoked.");
        TemplateEngine engineGet = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engineGet.process("cart/checkout.html", context, resp.getWriter());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("The checkout's POST method was invoked.");

        OrderDaoMem OrderDaoData = OrderDaoMem.getInstance();


        OrderDaoData.add("fullname", req.getParameter("fullname"));
        OrderDaoData.add("email", req.getParameter("email"));
        OrderDaoData.add("address", req.getParameter("address"));
        OrderDaoData.add("city", req.getParameter("city"));
        OrderDaoData.add("state", req.getParameter("state"));
        OrderDaoData.add("zip", req.getParameter("zip"));

        System.out.println(OrderDaoData.getAll());

        logger.info("Checkout data: FULLNAME: {}, EMAIL: {}, ADDRESS: {}, CITY: {}, STATE: {}, ZIP: {} ",
                req.getParameter("fullname"),
                req.getParameter("email"),
                req.getParameter("address"),
                req.getParameter("city"),
                req.getParameter("state"),
                req.getParameter("zip"));
        resp.sendRedirect("/payment");
    }
}

