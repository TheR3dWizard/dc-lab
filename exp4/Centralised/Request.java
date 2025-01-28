package exp4.Centralised;

public class Request{

	public Process requester;
	public Process holder;
	public Resource resource;
	
	public Request(Process p1,Process p2,Resource r1){
		this.requester = p1;
		this.holder = p2;
		this.resource = r1;
	}
}
