package com.codecool.shop.eventHandler.buttonHandler;

import com.codecool.shop.dao.implementation.CartDaoMem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet()

public class AddButton extends HttpServlet {


@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter out = response.getWriter();
    String button = request.getParameter("button");






}
};
