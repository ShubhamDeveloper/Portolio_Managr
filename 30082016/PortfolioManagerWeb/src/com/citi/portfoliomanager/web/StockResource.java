package com.citi.portfoliomanager.web;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal;

import com.citi.portfoliomanager.jpa.Stockname;
@Path("/stocks")
public class StockResource {
	private PortfolioManagerBeanLocal bean;

	public  StockResource() {
		try {
			InitialContext context = new InitialContext();
			bean = (PortfolioManagerBeanLocal) context.lookup(
					"java:app/PortfolioManagerEJB/PortfolioManagerBean!com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal");

		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	@GET
	@Produces("application/json")
	public List<Stockname> getAllStocks(){
		return bean.getAllStocks();
		
	}
	
}
