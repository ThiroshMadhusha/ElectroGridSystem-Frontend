package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BillsAPI")
public class BillsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Bill billObj = new Bill();

	
	// -------------------------doPost---------------------------------------------
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				 throws ServletException, IOException 
				{ 
				 String output = billObj.insertBill(request.getParameter("billAccNo"), 
				 request.getParameter("cusName"), 
				 request.getParameter("billUnit"), 
				 request.getParameter("billAmount")); 
				 response.getWriter().write(output); 
				}

		// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request) 
			{ 
			 Map<String, String> map = new HashMap<String, String>(); 
			try
			 { 
				 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
				 String queryString = scanner.hasNext() ? 
				 scanner.useDelimiter("\\A").next() : ""; 
				 scanner.close(); 
				 String[] params = queryString.split("&"); 
				 for (String param : params) 
			 { 
			 
				 String[] p = param.split("="); 
				 map.put(p[0], p[1]); 
			 } 
			 } 
				catch (Exception e) 
			 { 
			 } 
				return map; 
		}

	// -------------------------doPut---------------------------------------------
		protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = billObj.updateBill(paras.get("hidItemIDSave").toString(), 
			 paras.get("billAccNo").toString(), 
			 paras.get("cusName").toString(), 
			 paras.get("billUnit").toString(), 
			 paras.get("billAmount").toString()); 
			 response.getWriter().write(output); 
			} 

	// -------------------------doDelete---------------------------------------------

		protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = billObj.deleteBill(paras.get("billID").toString()); 
			response.getWriter().write(output); 
			}


	
	}