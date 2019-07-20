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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/shopping-cart")
public class ShoppingCartController extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        logger.info("The shopping cart's GET method have been invocated.");
        CartDaoMem shoppingCartDataStore = CartDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("shoppingCartProducts", shoppingCartDataStore.setAndGetProductsAndQty());
        context.setVariable("totalPrice", shoppingCartDataStore.getTotalPrice());
        shoppingCartDataStore.clearList();
        engine.process("shopping_cart/shopping_cart.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("The shopping cart's POST method have been invocated.");

        ProductDao productDataStore = ProductDaoMem.getInstance();
        String addButton = req.getParameter("add");
        String removeButton = req.getParameter("remove");
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        CartDaoMem shoppingCartDataStore = CartDaoMem.getInstance();


        if (addButton != null) {

            shoppingCartDataStore.add(productDataStore.find(Integer.parseInt(addButton)));
            logger.info("One more {} was ADDED to the cart. Cart size: {} ",productDataStore.find(Integer.parseInt(addButton)).getName(), shoppingCartDataStore.getCartSize()+1);
            context.setVariable("shoppingCartProducts", shoppingCartDataStore.setAndGetProductsAndQty());
            context.setVariable("totalPrice", shoppingCartDataStore.getTotalPrice());
            shoppingCartDataStore.clearList();
            resp.sendRedirect("/shopping-cart");

//            engine.process("shopping_cart/shopping_cart.html", context, resp.getWriter());

        } else if (removeButton != null) {
            shoppingCartDataStore.remove(productDataStore.find(Integer.parseInt(removeButton)));
            logger.info("One {} was DELETED from the cart. Cart size: {} ",productDataStore.find(Integer.parseInt(removeButton)).getName(), shoppingCartDataStore.getCartSize());
            context.setVariable("shoppingCartProducts", shoppingCartDataStore.setAndGetProductsAndQty());
            context.setVariable("totalPrice", shoppingCartDataStore.getTotalPrice());
            shoppingCartDataStore.clearList();
            resp.sendRedirect("/shopping-cart");
//            engine.process("shopping_cart/shopping_cart.html", context, resp.getWriter());


        }

    }
}
