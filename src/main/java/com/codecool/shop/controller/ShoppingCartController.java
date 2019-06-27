package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/shopping-cart")
public class ShoppingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CartDaoMem shoppingCartDataStore = CartDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("shoppingCartProducts", shoppingCartDataStore.setAndGetProductsAndQty());
        context.setVariable("totalPrice", shoppingCartDataStore.getTotalPrice());
        shoppingCartDataStore.clearList();
        engine.process("shopping_cart/shopping_cart.html", context, resp.getWriter());
        System.out.println(shoppingCartDataStore.setAndGetProductsAndQty());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        String addButton = req.getParameter("add");
        String removeButton = req.getParameter("remove");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        CartDaoMem shoppingCartDataStore = CartDaoMem.getInstance();


        if (addButton != null) {

            shoppingCartDataStore.add(productDataStore.find(Integer.parseInt(addButton)));
            context.setVariable("shoppingCartProducts", shoppingCartDataStore.setAndGetProductsAndQty());
            context.setVariable("totalPrice", shoppingCartDataStore.getTotalPrice());
            shoppingCartDataStore.clearList();
            engine.process("shopping_cart/shopping_cart.html", context, resp.getWriter());

        } else if (removeButton != null) {
            shoppingCartDataStore.remove(productDataStore.find(Integer.parseInt(removeButton)));
            context.setVariable("shoppingCartProducts", shoppingCartDataStore.setAndGetProductsAndQty());
            context.setVariable("totalPrice", shoppingCartDataStore.getTotalPrice());
            shoppingCartDataStore.clearList();
            engine.process("shopping_cart/shopping_cart.html", context, resp.getWriter());


        }

    }
}
