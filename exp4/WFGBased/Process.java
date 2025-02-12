package exp4.WFGBased;

import java.util.ArrayList;


public class Process{
	private int id;
	private Site server;
	private ArrayList<Resource> resources;
	private ArrayList<Resource> requested;


	public Process(int id,Site server){
		this.id = id;
		this.server = server;
		this.resources = new ArrayList<>();
		this.requested = new ArrayList<>();
	}

	public int getId(){
		return this.id;
	}

    public boolean SameSystem(Site s){
        return s==server;
    }

    public void Allocate(Resource r){
        resources.add(r);
    }

	public void AddRequest(Resource r){
		requested.add(r);
	}

	public void Relinquish(Resource r){
		resources.remove(r);
	}

	public boolean WaitingOn(Resource r){
		return requested.contains(r);
	}

	public ArrayList<Resource> GetResources(){
		return resources;
	}

}