package Lab_1_2_3;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
	private int numVertices;
	private LinkedList<Integer>[]adjList;

	public Graph(int numVertices){
		if(numVertices<1) {
			throw new IllegalArgumentException(
	                "Number of vertices must be at least 1. Given: " + numVertices
		            );
		}
		this.numVertices=numVertices;
		adjList=new LinkedList[numVertices];
		for(int i=0;i<numVertices;i++) {
			adjList[i]= new LinkedList<>();
		}
	}
	void addEdge(int v1,int v2) {
		if(v1>=0 && v1<numVertices && v2>=0 && v2<numVertices ) {
			adjList[v1].add(v2);
			adjList[v2].add(v1);
		}
		else {
			 System.out.println("Invalid vertex number.");
		}
		
		
	}
	void bfs(int startVertex) {
		boolean[] visited=new boolean[(int)numVertices];
		Queue<Integer> q=new LinkedList<>();
		
		q.add(startVertex);
		visited[startVertex]=true;
		while(!q.isEmpty()) {
			int v=q.poll();
			System.out.println(v + " ");
			
			for(int n:adjList[v]) {
				if(!visited[n]) {
					visited[n]=true;
					q.add(n);
				}
			}
			
		}
		
		
		
	}


	public static void main(String[] args) {
        Graph g = new Graph(5); 

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        g.bfs(1); 
    }
}

