package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	//inizializzo tutto ciò che mi serve 
	//rispetto alle disposizioni
	
	private SimpleGraph<Country, DefaultEdge> graph;
	private Map<Integer, Country> idMap;
	private BordersDAO dao;
	private List<Country> countries;
	
	//costruttore
 	public Model() {
		dao=new BordersDAO();
		
	
	}

 	/**
 	 * CREO IL GRAFO
 	 * -vertici, insieme delle nazioni 
 	 * -archi, i confini che esistono in quel lasso di tempo specificato
 	 * @param x
 	 */
	public void creaGrafo(int x) {
		
		idMap=new HashMap<Integer, Country>();
		this.dao.loadAllCountries(idMap);
		this.graph=new SimpleGraph<>(DefaultEdge.class);
		
		
		List<Border> confini = dao.getCountryPairs(idMap, x);

		if (confini.isEmpty()) {
			throw new RuntimeException("No country for specified year");
		}


		for (Border b : confini) {
			graph.addVertex(b.getC1());
			graph.addVertex(b.getC2());
			graph.addEdge(b.getC1(), b.getC2());
		}

		//System.out.format("Inseriti: %d vertici, %d archi\n", graph.vertexSet().size(), graph.edgeSet().size());

		
		
		
		}
	
	/**
	 * l'insieme di stati(nodi) nel grafo
	 * @return
	 */
	public Collection<Country> getCountries() {
		// TODO Auto-generated method stub
		return this.graph.vertexSet();
	}
	
	/**
	 * elenco degli stati, indicando il numero di stati al confine 
	 * @return
	 */
	public Map<Country, Integer> getCountryList() {
		
		if (graph == null) {
			throw new RuntimeException("Grafo non esistente");
		}

		Map<Country, Integer> stati = new HashMap<Country, Integer>();
		for (Country country : graph.vertexSet()) {
			stati.put(country, graph.degreeOf(country)); //ritorna il grado del vertice 
		}
		return stati;
	}
	
	/**
	 * numero di componenti connnesse nel grafo
	 * 
	 * @return
	 */
	public int getNumberOfConnectedComponents() {
		
		if (graph == null) {
			throw new RuntimeException("Grafo non esistente");
		}

		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(graph);
		return ci.connectedSets().size();
	}
	
	private List<Country> visitaProfondita(Country selectedCountry) {

		List<Country> visited = new LinkedList<Country>();

		GraphIterator<Country, DefaultEdge> dfv = new DepthFirstIterator<Country, DefaultEdge>(graph, selectedCountry);
		while (dfv.hasNext()) {
			visited.add(dfv.next());
		}

		return visited;
	}

	public List<Country> getVicini(Country c) {

		if (!graph.vertexSet().contains(c)) {
			throw new RuntimeException("Lo stato selezionato non è nel grafo");
		}

		List<Country> vicini = this.visitaProfondita(c);
	

		return vicini;
	}

}
