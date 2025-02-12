package exp4.Centralised;
import java.util.ArrayList;



public class Process{
	private int id;
	public Site server;
	public ArrayList<Resource> resources;

	public Process(int priority,Site server){
		this.id = priority;
		this.server = server;
		this.resources = new ArrayList<>();
	}

	public void Allocate(Resource resource){
		boolean allocated = this.server.RequestResource(this, resource);
		
		if(allocated){
			this.resources.add(resource);
		}
	}

	public int getId(){
		return this.id;
	}

}
