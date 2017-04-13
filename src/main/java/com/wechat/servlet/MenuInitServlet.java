package com.wechat.servlet;

import com.wechat.util.MenuUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wang_ on 2016-08-22.
 */
@WebServlet(name = "MenuInitServlet", urlPatterns = {"/MenuInitServlet"})
public class MenuInitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        int status = MenuUtil.createMenu();
        String msg = "";
        if(status==0){
            msg = "菜单创建成功！";
        }else{
            msg = "菜单创建失败！";
        }
        out.write(msg);
        out.flush();
    }
}
