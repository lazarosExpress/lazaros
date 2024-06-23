package com.lazaros.controller;

import com.google.gson.Gson;
import com.lazaros.beans.*;
import com.lazaros.dao.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/AddressController")
public class AddressController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AddressController.class.getName());
    private AddressDAO addressDAO;
    private ProvincesDAO provincesDAO;
    private DistrictsDAO districtsDAO;
    private SubDistrictsDAO subDistrictsDAO;
    private NeighbourhoodDAO neighbourhoodDAO;

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        addressDAO = new AddressDAO(); // addressDAO nesnesini başlatıyoruz
        provincesDAO = new ProvincesDAO();
        districtsDAO = new DistrictsDAO();
        subDistrictsDAO = new SubDistrictsDAO();
        neighbourhoodDAO = new NeighbourhoodDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBeans user = (CustomerBeans) session.getAttribute("loggedInCustomer");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
            return;
        }

        int customerId = user.getCustomer_id();
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        System.out.println("Address Controller GET: " + action);

        switch (action) {
            case "list":
                listAddresses(request, response, customerId);
                break;
            case "listProvinces":
                listProvinces(request, response);
                break;
            case "listDistricts":
                listDistrictsByProvince(request, response);
                break;
            case "listSubDistricts":
                listSubDistrictsByDistricts(request, response);
                break;
            case "listNeighbourhood":
                listNeighbourhoodBySubDistricts(request, response);
                break;
            case "LISTUPDATEADDRESS":
                listUpdateAddress(request, response);
                break;
            case "delete":
                deleteAddress(request, response, customerId);
                break;
            default:
                listAddresses(request, response, customerId);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerBeans user = (CustomerBeans) session.getAttribute("loggedInCustomer");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
            return;
        }

        int customerId = user.getCustomer_id();
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        System.out.println("Address Controller POST: " + action);

        switch (action) {
            case "insert":
                insertAddress(request, response, customerId);
                break;
            case "update":
                updateAddress(request, response, customerId);
                break;
            default:
                listAddresses(request, response, customerId);
                break;
        }
    }

    private void listAddresses(HttpServletRequest request, HttpServletResponse response, int customerId)
            throws ServletException, IOException {
        try {
            List<AddressBeans> addresses = addressDAO.getAddressesByCustomerId(customerId);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(addresses);
            out.write(json);
            out.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error listing addresses", e);
            throw new ServletException("Error listing addresses", e);
        }
    }

    private void listProvinces(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ProvincesBeans> listProvinces = provincesDAO.getAllProvinces();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(listProvinces);
            out.write(json);
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading provinces");
        }
    }

    private void listDistrictsByProvince(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int provinceId = Integer.parseInt(request.getParameter("province_id"));
        try {
            List<DistrictsBeans> listDistricts = districtsDAO.getDistrictsByProvince(provinceId);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(listDistricts);
            out.write(json);
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading districts");
        }
    }

    private void listSubDistrictsByDistricts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int districtsId = Integer.parseInt(request.getParameter("districts_id"));
        try {
            List<SubDistrictsBeans> listSubDistricts = subDistrictsDAO.getSubDistrictsByDistricts(districtsId);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(listSubDistricts);
            out.write(json);
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading subdistricts");
        }
    }

    private void listNeighbourhoodBySubDistricts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int subDistrictsId = Integer.parseInt(request.getParameter("subDistricts_id"));
        try {
            List<NeighbourhoodBeans> listNeighbourhoods = neighbourhoodDAO
                    .getNeighbourhoodsBySubDistricts(subDistrictsId);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(listNeighbourhoods);
            out.write(json);
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading neighbourhoods");
        }
    }

    private void deleteAddress(HttpServletRequest request, HttpServletResponse response, int customerId)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        addressDAO.deleteAddress(id, customerId);
        response.sendRedirect("AddressController?action=list");
    }

    private void insertAddress(HttpServletRequest request, HttpServletResponse response, int customerId)
            throws ServletException, IOException {
        String firstName = request.getParameter("address_customerFirstName");
        String lastName = request.getParameter("address_customerLastName");
        String phoneNumber = request.getParameter("address_customerPhoneNumber");
        String description = request.getParameter("address_description");
        String title = request.getParameter("address_title");
        String provinceStr = request.getParameter("province");
        String districtsStr = request.getParameter("district");
        String subDistrictsStr = request.getParameter("subdistrict");
        String neighbourhoodStr = request.getParameter("neighbourhood");

        // Gerekli alanların doğrulanması
        if (provinceStr == null || provinceStr.isEmpty() ||
                districtsStr == null || districtsStr.isEmpty() ||
                subDistrictsStr == null || subDistrictsStr.isEmpty() ||
                neighbourhoodStr == null || neighbourhoodStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tüm alanlar doldurulmalıdır.");
            return;
        }

        int provincesId = Integer.parseInt(provinceStr);
        int districtsId = Integer.parseInt(districtsStr);
        int subDistrictsId = Integer.parseInt(subDistrictsStr);
        int neighbourhoodId = Integer.parseInt(neighbourhoodStr);

        AddressBeans newAddress = new AddressBeans(0, firstName, lastName, phoneNumber, description, title, provincesId,
                districtsId, subDistrictsId, neighbourhoodId, customerId);
        addressDAO.addAddress(newAddress);
        response.sendRedirect(request.getContextPath() + "/views/address.jsp");
    }

    private void updateAddress(HttpServletRequest request, HttpServletResponse response, int customerId)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("address_id"));
        String firstName = request.getParameter("address_customerFirstName");
        String lastName = request.getParameter("address_customerLastName");
        String phoneNumber = request.getParameter("address_customerPhoneNumber");
        String description = request.getParameter("address_description");
        String title = request.getParameter("address_title");
        String provinceStr = request.getParameter("province");
        String districtsStr = request.getParameter("district");
        String subDistrictsStr = request.getParameter("subdistrict");
        String neighbourhoodStr = request.getParameter("neighbourhood");

        // Gerekli alanların doğrulanması
        if (provinceStr == null || provinceStr.isEmpty() ||
                districtsStr == null || districtsStr.isEmpty() ||
                subDistrictsStr == null || subDistrictsStr.isEmpty() ||
                neighbourhoodStr == null || neighbourhoodStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tüm alanlar doldurulmalıdır.");
            return;
        }

        int provincesId = Integer.parseInt(provinceStr);
        int districtsId = Integer.parseInt(districtsStr);
        int subDistrictsId = Integer.parseInt(subDistrictsStr);
        int neighbourhoodId = Integer.parseInt(neighbourhoodStr);

        AddressBeans address = new AddressBeans(id, firstName, lastName, phoneNumber, description, title, provincesId,
                districtsId, subDistrictsId, neighbourhoodId, customerId);
        addressDAO.updateAddress(address);

        response.sendRedirect(request.getContextPath() + "/views/address.jsp");
    }

    private void listUpdateAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int addressId = Integer.parseInt(request.getParameter("address_id"));
        AddressBeans address = addressDAO.getAddressById(addressId);
        if (address != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(address);
            out.write(json);
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Address not found");
        }
    }

}
