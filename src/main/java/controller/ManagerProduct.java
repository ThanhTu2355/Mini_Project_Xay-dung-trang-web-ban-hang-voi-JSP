/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.HoaDAO;
import dao.LoaiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Hoa;

/**
 *
 * @author PC
 */
@WebServlet(name = "ManagerProduct", urlPatterns = {"/ManagerProduct"})
@MultipartConfig
public class ManagerProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HoaDAO hoaDAO = new HoaDAO();
        LoaiDAO loaiDAO = new LoaiDAO();

        String action = "LIST";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }
        switch (action) {
            case "LIST":
                //Tra ve giao dien liet ke danh sach san pham quan tri
                request.setAttribute("dsHoa", hoaDAO.getAll());
                request.getRequestDispatcher("admin/list_product.jsp").forward(request, response);
                break;
            case "ADD":
                String method = request.getMethod();
                if (method.equalsIgnoreCase("get")) {
                    //tra ve gioa dien them moi san pham
                    request.setAttribute("dsLoai", loaiDAO.getAll());
                    request.getRequestDispatcher("admin/add_product.jsp").forward(request, response);
                } else {
                    //xu ly them moi san pham
                    //b1 Lay thong tin san pham
                    String tenhoa = request.getParameter("tenhoa");
                    double gia = Double.parseDouble(request.getParameter("gia"));
                    Part part = request.getPart("hinh");
                    int maloai = Integer.parseInt(request.getParameter("maloai"));

                    //b2 Xu ly upload file
                    String realpath = request.getServletContext().getRealPath("/assets/images/products");
                    String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    part.write(realpath + "/" + filename);

                    //3. Them san pham vao CSDL
                    Hoa objInsert = new Hoa(0, tenhoa, gia, filename, maloai, new Date(new java.util.Date().getTime()));
                    if (hoaDAO.Insert(objInsert)) {
                        //thong bao them thanh cong
                        request.setAttribute("success", "Thao tac them san pham thanh cong");
                    } else {
                        //thong bao them that bai
                        request.setAttribute("orror", "Thao tac them san pham that bai");
                    }
                    request.getRequestDispatcher("ManagerProduct?action=LIST").forward(request, response);
                }
                break;
            case "EDIT":
                //Tra ve giao dien cap nhat san pham
                break;
            case "DELETE":
                //Xu ly xoa san pham
                //b1 Lay ma san pham
                int mahoa = Integer.parseInt(request.getParameter("mahoa"));
                //2. Xoa san pham khoi CSDL
                if (hoaDAO.Delete(mahoa)) {
                    request.setAttribute("success", "Thao tac xoa san pham thanh cong");
                } else {
                    //thong bao them that bai
                    request.setAttribute("orror", "Thao tac xoa san pham that bai");
                }
                request.getRequestDispatcher("ManagerProduct?action=LIST").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}