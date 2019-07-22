package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.File;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/confirmation")
public class ConfirmationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartDaoMem shoppingCartDataStore = CartDaoMem.getInstance();
        OrderDaoMem orderData = OrderDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        ObjectMapper mapper = new ObjectMapper();

        HashMap cartMap = shoppingCartDataStore.getProductsAndQty();
        HashMap orderMap = orderData.getAll();

        JSONObject sampleObject = new JSONObject();

        try {

            String json1 = mapper.writeValueAsString(cartMap);
            //sampleObject.putAll(cartMap);
            String json2 = mapper.writeValueAsString(orderMap);
            sampleObject.putAll(orderMap);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //try (FileWriter file = new FileWriter("./file1.txt")) {
        //    file.write(sampleObject.toJSONString());
        //}

        shoppingCartDataStore.resetCart();
        engine.process("confirmation/confirmation.html", context, resp.getWriter());
    }

}
