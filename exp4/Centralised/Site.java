package exp4.Centralised;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Site {
    public ArrayList<Process> system;
    public ArrayList<Resource> used;
    public HashMap<Process,ArrayList<Resource>> table;
    private ArrayList<Request> requests;
    private Graph<Process> graph;


    public Site(ArrayList<Process> processes,ArrayList<Resource> resources){
        this.system = processes;
        this.used = resources;
        table = new HashMap<>();
        graph = new Graph<>();
        for (Process process : processes) {
            graph.addNode(process);
        }
    }

    public boolean RequestResource(Process process,Resource resource){
        if (used.contains(resource)){
            Process holder = resource.GetProcess();
            System.out.println("Resource in use, cannot be allocated");
            requests.add(new Request(process, holder, resource));
            graph.addEdge(process, holder);
            this.DetectDeadlock();
            return false;
        }
        
        used.add(resource);
        if(table.containsKey(process)){
            table.get(process).add(resource);
            used.add(resource);
            resource.AllocateTo(process);
        }
        else{
            ArrayList<Resource> used = new ArrayList<>();
            used.add(resource);
            table.put(process, used);
        }
        return true;
    }

    public void RelinquishResource(Process process,Resource resource){
		used.remove(resource);
		table.get(process).remove(resource);
		resource.Free();
		for(Request req: requests){
			if(req.holder == process && req.resource == resource){
				RequestResource(req.requester,resource);
			}
		}
    }

    public boolean DetectDeadlock(){
        return graph.isCyclic();
    }

    public Graph<Process> getGraph(){
        return this.graph;
    }

}	
	

