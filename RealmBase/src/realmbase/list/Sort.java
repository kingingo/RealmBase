package realmbase.list;

import java.util.Comparator;

import lombok.Getter;
import lombok.Setter;

public class Sort<T> implements Comparable<Sort<T>>{

	@Getter
	@Setter
	private T object;
	@Getter
	@Setter
	private int value;
	
	public Sort(T object,int value){
		this.object=object;
		this.value=value;
	}
	
	public int compareTo(Sort<T> compareFruit) {
		int compareQuantity = ((Sort<T>) compareFruit).getValue(); 
		return compareQuantity - this.getValue();
	}
	
	public static Comparator<Sort<?>> ABSTEIGEND(){
		return DESCENDING;
	}
	
	public static Comparator<Sort<?>> DESCENDING
	    = new Comparator<Sort<?>>() {
	
		public int compare(Sort<?> r1, Sort<?> r2) {
			return r2.getValue() - r1.getValue();
		}
	
	};
	
	public static Comparator<Sort<?>> AUFSTEIGEND(){
		return ASCENDING;
	}
	
	public static Comparator<Sort<?>> ASCENDING 
                          = new Comparator<Sort<?>>() {

	    public int compare(Sort<?> r1, Sort<?> r2) {
			return r1.getValue() - r2.getValue();
	    }

	};
}