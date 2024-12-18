import java.util.ArrayList;

class Process{
	public int priority;
	public Boolean active;
	public ArrayList<Process> system;
	public Boolean isCoord;
	public Process coord;
	
	public Process(int priority){
		this.priority = priority;
		this.active = true;
		this.isCoord = false;
	}

	public Process(int priority, Boolean active){
		this.priority = priority;
		this.active = active;
		this.isCoord = false;
	}

	public void SwitchOff(){
		this.active = false;
	}

	public void SwitchOn(){
		this.active = true;
	}

	public Boolean isActive(){
		return this.active;
	}

	public void AddToSystem(ArrayList<Process> system){
		this.system = system;
	}

	//implement a timeout
	public Boolean GetStatus(){
		return this.isActive();
	}

	public void StartElection(){
		ArrayList<Process> candidates = new ArrayList<Process>();
		for(Process process:this.system){
			if(process.priority > this.priority){
				candidates.add(process);
			}
		}
		Process coordinator = candidates.get(0);
		for(Process process:candidates){
			if(process.isActive()){
				if(process.priority > coordinator.priority){
					coordinator = process;
				}
			}
		}
		for(Process process:system){
			if(process == coordinator){
				process.BecomeCoord();
			}
			else{
				process.NewCoord(coordinator);
			}
		}
	}

	public void BecomeCoord(){
		this.isCoord = true;
		this.coord = this;
	}

	public void NewCoord(Process coord){
		this.isCoord = false;
		this.coord = coord;
	}
}

public class DCSystem{
	public ArrayList<Process> system;

	public DCSystem(ArrayList<Process> system){
		this.system = system;
	}
	
	public void AddProcess(Process process){
		this.system.add(process);
		for(Process process1: system){
			process1.AddToSystem(this.system);
		}
	}

	public void startSystem(){
		system.get(0).StartElection();
	}



}

