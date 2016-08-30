package com.citi.portfoliomanager.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.citi.portfoliomanager.jpa.Category;
import com.citi.portfoliomanager.jpa.ClientPortfolio;
//import com.citi.portfoliomanager.jpa.Dummy;
import com.citi.portfoliomanager.jpa.MarketData;
import com.citi.portfoliomanager.jpa.Stockname;

@Local
public interface PortfolioManagerBeanLocal {
	public List<MarketData> sayHello();
		
	//public void putString();
	//public void putString(MarketData dummy);

//	public double getAllPortfolio();
	 public List<MarketData> getAllPortfolio();
	 public double getExposureByIndustry(String categoryName);

	String putIntoPortfolio(String stockName, int quantity, double price, Date date, String portfolioID);
	public void addNewTransaction(ClientPortfolio clientPortfolio);
	public List<Category> getAllCategories();
	public List<Stockname> getAllStocks();
}
