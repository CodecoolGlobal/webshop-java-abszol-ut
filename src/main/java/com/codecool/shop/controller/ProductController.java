package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        HttpSession session = req.getSession();

        TemplateEngine engineGet = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ProductCategory defaultCategory = productCategoryDataStore.find(1);
        context.setVariable("category", defaultCategory);
        context.setVariable("products", productDataStore.getBy(defaultCategory));

        if (session.getAttribute("supplier") == null && session.getAttribute("category") == null) {
            session.setAttribute("supplier", null);
            session.setAttribute("category", defaultCategory);
        }

        engineGet.process("product/index.html", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());

        String addButton = req.getParameter("button");
        String supplierButton = req.getParameter("supplierbutton");
        String categoryButton = req.getParameter("categorybutton");

        HttpSession session = req.getSession();
        if (supplierButton != null) {
            Supplier selectedSupplier = supplierDataStore.find(Integer.parseInt(supplierButton));
            session.setAttribute("supplier", selectedSupplier);
            session.setAttribute("category", null);
        } else if (categoryButton != null) {

            ProductCategory selectedCategory = productCategoryDataStore.find(Integer.parseInt(categoryButton));
            session.setAttribute("category", selectedCategory);
            session.setAttribute("supplier", null);
        }

        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (addButton != null) {
            Product product = ProductDaoMem.getInstance().find(Integer.parseInt(addButton));
            CartDaoMem.getInstance().add(product);
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
