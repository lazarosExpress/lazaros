package com.lazaros.controller;

import com.lazaros.beans.ProductBeans;
import com.lazaros.beans.SupplierBeans;
import com.lazaros.beans.CategoryBeans;
import com.lazaros.dao.ProductDAO;
import com.lazaros.dao.CategoryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/ProductController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ProductController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;
    private Gson gson = new Gson();

    public void init() {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("get" + action);

        if (action == null)
            action = "LIST";

        switch (action) {
            case "LIST":
                listProducts(request, response);
                break;
            case "SEARCH":
                searchProducts(request, response);
                break;
            case "LIST_JSON":
                listProductsJson(request, response);
                break;
            case "GET_CATEGORIES":
                getCategoriesJson(request, response);
                break;
            case "DETAILS":
                getProductDetails(request, response);
                break;
            case "DELETE":
                deleteProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    private void getProductDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("id"));
        ProductBeans product = productDAO.getProductByIdWithDetail(productId);
        if (product == null) {
            // Ürün bulunamadıysa hata mesajı göster veya başka bir sayfaya yönlendir.
            request.setAttribute("errorMessage", "Ürün bulunamadı.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/errorPage.jsp");
            dispatcher.forward(request, response);
            return;
        }
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/productDetail/productPages.jsp");
        dispatcher.forward(request, response);
    }

    private void getCategoriesJson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryBeans> categories = categoryDAO.getAllCategories();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String json = new Gson().toJson(categories);
        out.write(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("post" + action);
        switch (action) {
            case "ADD":
                try {
                    addProduct(request, response);
                } catch (Exception e) {
                    throw new ServletException(e);
                }
                break;
            case "DELETE":
                deleteProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }

    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInSupplier") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
            return;
        }

        SupplierBeans loggedInSupplier = (SupplierBeans) session.getAttribute("loggedInSupplier");
        int supplierId = loggedInSupplier.getSupplier_id();

        try {
            List<ProductBeans> products = productDAO.getProductsBySupplierId(supplierId);
            request.setAttribute("products", products);
            LOGGER.log(Level.INFO, "Products loaded for supplier: {0} - {1}", new Object[] { supplierId, products });
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error listing products", e);
            throw new ServletException("Error listing products", e);
        }
    }

    private void listProductsJson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInSupplier") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
            return;
        }

        SupplierBeans loggedInSupplier = (SupplierBeans) session.getAttribute("loggedInSupplier");
        int supplierId = loggedInSupplier.getSupplier_id();

        try {
            List<ProductBeans> products = productDAO.getProductsBySupplierId(supplierId);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(products);
            out.write(json);
            out.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error listing products", e);
            throw new ServletException("Error listing products", e);
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInSupplier") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
            return;
        }

        SupplierBeans loggedInSupplier = (SupplierBeans) session.getAttribute("loggedInSupplier");
        int supplierId = loggedInSupplier.getSupplier_id();

        String productName = request.getParameter("product_name");
        double productPrize = Double.parseDouble(request.getParameter("product_prize"));
        int productStock = Integer.parseInt(request.getParameter("product_stock"));
        String brandName = request.getParameter("brand_name");
        String productExplanation = request.getParameter("product_explanation");
        String productProperties = request.getParameter("product_properties");
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        Part filePart = request.getPart("product_imgUrl");
        String fileName = getFileName(filePart);
        // String uploadPath = getServletContext().getRealPath("") + File.separator +
        // "productImg";
        String uploadPath = System.getProperty("user.home") + "/Desktop/lazaros/express/src/main/webapp/productImg";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        filePart.write(uploadPath + File.separator + fileName);

        ProductBeans product = new ProductBeans();
        product.setProduct_name(productName);
        product.setProduct_prize(productPrize);
        product.setProduct_stock(productStock);
        product.setBrand_name(brandName);
        product.setProduct_explanation(productExplanation);
        product.setProduct_properties(productProperties);
        product.setCategory_id(categoryId);
        product.setSupplier_id(supplierId);
        product.setProduct_imgUrl(fileName);

        productDAO.addProduct(product);
        LOGGER.log(Level.INFO, "Product added: {0}", product);
        response.sendRedirect(request.getContextPath() + "/admin/productManagement.jsp");
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect(request.getContextPath() + "/admin/productManagement.jsp");
    }

    private void searchProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        List<ProductBeans> products = productDAO.searchProductsByName(query);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String json = gson.toJson(products);
        out.write(json);
        out.flush();
    }

}
