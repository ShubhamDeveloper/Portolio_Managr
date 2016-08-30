package com.citi.portfoliomanager.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.citi.portfoliomanager.ejb.PortfolioManagerBeanLocal;
import com.citi.portfoliomanager.jpa.ClientPortfolio;
import com.citi.portfoliomanager.jpa.ClientPortfolioPK;
//import com.citi.portfoliomanager.jpa.Dummy;
import com.citi.portfoliomanager.jpa.MarketData;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/portfolio")
public class PortfolioResource {

	private PortfolioManagerBeanLocal bean;

	public PortfolioResource() {
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
	public List<MarketData> getEntirePortfolio() {
		return bean.getAllPortfolio();
	}

	// @GET
	// @Produces("application/json")
	// @Path("/{categoryName}")
	// public Dummy getEntirePortfolio(@PathParam("categoryName")String
	// categoryName){
	// if (bean == null)
	// return null;
	// return bean.getProductsInCategory(categoryName);
	// }

	// POST TRY 1
	// @POST
	// @Path("/users")
	// @Produces("application/json")
	// @Consumes("application/json")
	// public String putHello() throws JsonParseException, JsonMappingException,
	// IOException{
	// ObjectMapper mapper = new ObjectMapper();
	//
	// Dummy user = mapper.readValue(new
	// File("C:\\Users\\Grad21\\Downloads\\portfolio.json"), Dummy.class);
	//
	//
	// return null;
	// }

/*	@POST
	@Produces("text/plain")
	@Consumes("application/json")
	public String putHello(String textField) {
		System.out.println(textField);
		return "Data recid";
	}*/

	@POST
	@Consumes("application/json")
	// @Produces("application/json")
	public void addToPortfolio(NewTransaction newTransaction) {
		// Transaction transaction = new Transaction();
		// transaction.setName()
		// return
		// bean.putIntoPortfolio(stockName,quantity,price,date,portfolioID);
		ClientPortfolio clientPortfolio = new ClientPortfolio();
		ClientPortfolioPK clientPortfolioPK = new ClientPortfolioPK();
		clientPortfolioPK.setStock_name(newTransaction.getStockName());
		clientPortfolioPK.setDate(newTransaction.getTransactionDate());
		clientPortfolio.setId(clientPortfolioPK);
		clientPortfolio.setBuy_Price(newTransaction.getBuyPrice());
		clientPortfolio.setPortfolio_id("122");
		clientPortfolio.setQuantity(newTransaction.getVolume());
		bean.addNewTransaction(clientPortfolio);
	
		System.out.println(newTransaction.getStockName());
	}
}

class NewTransaction {
	private String stockName;
	private int buyPrice;
	private Date transactionDate;
	private int volume;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

}
