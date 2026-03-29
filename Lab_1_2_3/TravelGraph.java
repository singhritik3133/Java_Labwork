package Lab_1_2_3;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelGraph {
    final private Map<City, List<City>> adjList;

    public TravelGraph() {                       
        this.adjList = new HashMap<>();          
    }

    public void addcity(City city) {
        adjList.putIfAbsent(city, new ArrayList<>());
    }

    public void addConnections(City c1, City c2) {
        adjList.get(c1).add(c2);
        adjList.get(c2).add(c1);
    }

    private String cityName;                      
    private List<String>[] cities;

    void addCities(String City, List<String>[] cities) {

    }

    List<String> visited = new ArrayList<>();
    Deque<String> stack = new ArrayDeque<>();
    Map<String, List<String>> for_travel = new HashMap<>();

    public void find_path(City start, City Dest) {
        Deque<City> stack = new ArrayDeque<>();
        Set<City> visited = new HashSet<>();      
        Map<City, City> for_travel = new HashMap<>();

        stack.push(start);                        
    }
}