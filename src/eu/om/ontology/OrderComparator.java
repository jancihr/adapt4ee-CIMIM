package eu.om.ontology;

import java.util.Comparator;

import eu.om.ontology.model.Thing;

public class OrderComparator implements Comparator<Graph>{

	private Integer getOrder(String order){
		if(order == null) return -1;
		try{
			return Integer.parseInt(order);
		}
		catch(Exception e){
			return -1;
		}
	}
	
	@Override
	public int compare(Graph g1, Graph g2) {
		int o1 = getOrder(g1.value(Thing.order));
		int o2 = getOrder(g2.value(Thing.order));
		return (o1 > o2 ? 1 : (o1 == o2 ? 0 : -1));
	}
}
