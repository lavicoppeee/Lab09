package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;



public class BordersDAO {


	public void loadAllCountries(Map<Integer, Country> idMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(!idMap.containsKey(rs.getInt("ccode"))) {
					Country country=new Country( rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
					idMap.put(country.getcCode(),country);
				}
			}
			
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	/*
	 * con un unica query ho creato il sottoinsieme che mi serviva
	 * rispetto all'anno
	 */
		public List<Border> getCountryPairs(Map<Integer, Country> idMap, int anno) {

			String sql = "select state1no as Country1, state2no as Country2 from contiguity where contiguity.conttype=1 and contiguity.year <= ?";

			List<Border> result = new LinkedList<Border>();

			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, anno);

				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					
					int c1Code = rs.getInt("Country1");
					int c2Code = rs.getInt("Country2");
					
				
					Country c1 = idMap.get(c1Code);
					Country c2 = idMap.get(c2Code);
					
				
					if (c1 != null && c2 != null) {
						result.add(new Border(c1, c2));
					} else {
						System.out.println("Error " + String.valueOf(c1Code) + " - " + String.valueOf(c2Code));
					}
					
					Border b = new Border(c1, c2);
					result.add(b);
				}

				conn.close();
				return result;

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Errore connessione al database");
				throw new RuntimeException("Error Connection Database");
			}
		}
	
				
				
				
		
	}

