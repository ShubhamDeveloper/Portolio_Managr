package com.citi.portfoliomanager.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.citi.portfoliomanager.jpa.Category;
import com.citi.portfoliomanager.jpa.ClientPortfolio;
import com.citi.portfoliomanager.jpa.MarketData;
import com.citi.portfoliomanager.jpa.Stockname;




/**
 * Session Bean implementation class PortfolioManagerBean
 */
@Stateful
@Remote(PortfolioManagerBeanRemote.class)
@Local(PortfolioManagerBeanLocal.class)
public class PortfolioManagerBean implements PortfolioManagerBeanRemote, PortfolioManagerBeanLocal {

    /**
     * Default constructor. 
     */
	 @PersistenceContext(name="PortfolioManagerJPA-PU")
	private EntityManager entityManager;
    public PortfolioManagerBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public List<MarketData> sayHello(){
    	String sql = "SELECT d FROM MarketData AS d";
        System.out.println(sql);
        TypedQuery<MarketData> query = entityManager.createQuery(sql, MarketData.class);

        // Execute the query, and get a collection of products back.
        List<MarketData> MarketDataList = query.getResultList();

        return MarketDataList;
    	
    }
    
    public List<MarketData> getAllPortfolio(){
//    	MarketData MarketData = new MarketData();
//    	return MarketData.getHighPrice();
    	String sql = "Select s FROM MarketData as s";
    	TypedQuery<MarketData> query = entityManager.createQuery(sql, MarketData.class);
    	return query.getResultList();
    }
//    @Override
//    public void putString(MarketData MarketData){
//    	entityManager.persist(MarketData);
//    	
//    }

	@Override
	public double getExposureByIndustry(String categoryName) {
		String sql = "";
		TypedQuery<ClientPortfolio> query = entityManager.createQuery(sql, ClientPortfolio.class);
		List<ClientPortfolio> stocks = query.getResultList();
		double totalExposureByIndustry = 0.0;
		for(ClientPortfolio stock: stocks){
			totalExposureByIndustry+=stock.getQuantity()*stock.getBuy_Price();
		}
		return totalExposureByIndustry;
	}
	
	@Override
	public String putIntoPortfolio(String stockName,int quantity, double price, Date date, String portfolioID){
		
		return null;
	}

	@Override
	public void addNewTransaction(ClientPortfolio clientPortfolio) {
		System.out.println(clientPortfolio.getBuy_Price());
		entityManager.persist(clientPortfolio);
		
	}

	@Override
	public List<Category> getAllCategories() {
		String sql = "SELECT c FROM Category as c";
		TypedQuery<Category> query = entityManager.createQuery(sql, Category.class);
		return query.getResultList();
	}

	@Override
	public List<Stockname> getAllStocks() {
		
		String sql = "SELECT s FROM Stockname as s";
		TypedQuery<Stockname> query = entityManager.createQuery(sql, Stockname.class);
		return query.getResultList();
	}

}
