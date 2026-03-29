package Lab_1_2_3;

import java.util.Objects;

public class City {
	private final String name;
	private final String country;
	private final String timezone ;
	
	public City(String name ,String country,String timezone){
		this.name=name;
		this.country = country;
		this.timezone = timezone;
		
	}
	public String  getname() {
		return name;
	}
	public String getcountry() {
		return country;
	}
	public String gettimezone() {
		return timezone;
	}
	public boolean equals(Object obj) {
		if(this==obj)return true;
		if( obj==null || getClass()!=obj.getClass()) return false;
		
		City other =(City)obj;
		return Objects.deepEquals(other.name, name);
		
	}
	
	public int hashcode() {
		return name.hashCode();
		
	}
	
	public String toString() {
        return "City(name=" + name +
               ", country=" + country +
               ", timezone=" + timezone + ")";
    }
	
	public static void main(String [] args) {
		City c1=new City("Raj","Tokyo","20");
		City c2 = new City("Tokyo", "Japan", "UTC+9");

		System.out.println(c1);
		System.out.println(c1.equals(c2));
		
		
	}

}
