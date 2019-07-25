package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.JDBC_implementation.CartDaoJdbc;
import com.codecool.shop.dao.JDBC_implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.JDBC_implementation.ProductDaoJdbc;
import com.codecool.shop.dao.JDBC_implementation.SupplierDaoJdbc;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    private ProductCategoryDao productCategoryDataStore;
    private SupplierDao supplierDataStore;
    private ProductDao productDataStore;
    private CartDao cart;
    private Initializer initializer = new Initializer();

    public void checkDbActive() {
        if (initializer.isDbActive()) {
            productCategoryDataStore = ProductCategoryDaoJdbc.getInstance();
            supplierDataStore = SupplierDaoJdbc.getInstance();
            productDataStore = ProductDaoJdbc.getInstance();
            cart = CartDaoJdbc.getInstance();
        } else {
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();
            productDataStore = ProductDaoMem.getInstance();
            cart = CartDaoMem.getInstance();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        checkDbActive();

        HttpSession session = req.getSession();

        TemplateEngine engineGet = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductCategory defaultCategory = productCategoryDataStore.find(1);
        context.setVariable("category", defaultCategory);
        try {
            context.setVariable("products", productDataStore.getBy(defaultCategory));

        } catch (DataAccessException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        if (session.getAttribute("supplier") == null && session.getAttribute("category") == null) {
            session.setAttribute("supplier", null);
            session.setAttribute("category", defaultCategory);
        }

        if (session.getAttribute("loginData") != null) {
            context.setVariable("loginData", "Welcome here, " + session.getAttribute("loginData"));
        } else {
            context.setVariable("loginData", "You're not logged in yet!");
        }

        engineGet.process("product/index.html", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (session.getAttribute("loginData") != null) {
            context.setVariable("loginData", "Welcome here, " + session.getAttribute("loginData"));
        } else {
            context.setVariable("loginData", "You're not logged in yet!");
        }

        checkDbActive();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());

        String addButton = req.getParameter("button");
        String supplierButton = req.getParameter("supplierbutton");
        String categoryButton = req.getParameter("categorybutton");


        if (supplierButton != null) {
            Supplier selectedSupplier = supplierDataStore.find(Integer.parseInt(supplierButton));
            session.setAttribute("supplier", selectedSupplier);
            session.setAttribute("category", null);
        } else if (categoryButton != null) {

            ProductCategory selectedCategory = productCategoryDataStore.find(Integer.parseInt(categoryButton));
            session.setAttribute("category", selectedCategory);
            session.setAttribute("supplier", null);
        }


        if (addButton != null && session.getAttribute("loginData") != null) {
            Product product = ProductDaoMem.getInstance().find(Integer.parseInt(addButton));
            cart.setUser(session.getAttribute("loginData").toString());
            cart.add(product);
        }

        if (session.getAttribute("supplier") != null) {
            Supplier selectedSupplier = (Supplier) session.getAttribute("supplier");
            context.setVariable("category", selectedSupplier);
            context.setVariable("products", productDataStore.getBy(selectedSupplier));
            engine.process("product/index.html", context, resp.getWriter());
        } else if (session.getAttribute("category") != null) {
            ProductCategory selectedCategory = (ProductCategory) session.getAttribute("category");
            context.setVariable("category", selectedCategory);
            context.setVariable("products", productDataStore.getBy(selectedCategory));
            engine.process("product/index.html", context, resp.getWriter());
        }
    }
}
