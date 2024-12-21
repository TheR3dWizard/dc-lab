package Ring;
import java.util.ArrayList;
import java.util.List;

class Process{
	public int priority;
	public Boolean active;
	public Process next;
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

	public Process(int priority,Process next){
		this.priority = priority;
		this.active = true;
		this.next = next;
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


	public void SetNext(Process next){
		this.next = next;
	}

	//implement a timeout
	public Boolean GetStatus(){
		return this.isActive();
	}

	public void StartElection(){
		System.out.println("Process " + this.priority + " is starting election");
		if(this.isActive()){
			if(this.next.isActive()){
				List<Process> path = new ArrayList<Process>();
				path.add(this);
				this.next.StartElection(path);
			}
		}else{
			this.next.StartElection();
		}
	}

	public void StartElection(List<Process> path){
		System.out.println("Process " + this.priority + " is adding to list");
		if(this.isActive()){
			if(path.contains(this)){
			Process candidate = path.get(0);
			for(Process process:path){
				if(process.priority > candidate.priority){
					candidate = process;
				}
			}
			System.out.println("Process " + candidate.priority + " has won the election");
			candidate.makeCoord(candidate.priority);
			for(Process process:path){
				if(process.priority != candidate.priority){
					process.NewCoord(candidate);
				}
			}
			}else{
				path.add(this);
				this.next.StartElection(path);
			}
		}else{
			this.next.StartElection(path);
		}
	}

	public void makeCoord(int priority){
		if(this.priority == priority){
			this.BecomeCoord();
		}
		else{
			this.next.makeCoord(priority);
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

	public void PingCoordinator(){
		if(this.coord.isActive()){
			System.out.println("Process " + this.priority + " is pinging coordinator " + this.coord.priority);
		}
		else{
			System.out.println("Process " + this.priority + " has found coordinator " + this.coord.priority + " to be inactive");
			this.StartElection();
		}
	}

	public void printNode(int count){
		if(count == 0){
			System.out.println("Process " + this.priority + " is active: " + this.active);
			return;
		}
		System.out.println("Process " + this.priority + " is active: " + this.active);
		this.next.printNode(count-1);
	}
}

public class Network{
	public ArrayList<Process> system;
	public Process coordinator;

	public Network(ArrayList<Process> system){
		this.system = system;
		for(int i = 0; i < system.size(); i++){
			system.get(i).SetNext(system.get((i+1)%system.size()));
		}
	}
	
	public void AddProcess(Process process){
		Process end = this.system.get(this.system.size() - 1);
		end.SetNext(process);
		this.system.add(process);
		process.SetNext(this.system.get(0));
	}

	public void startSystem(){
		system.get(0).StartElection();
	}

	public void printSystem(){
		for(Process process: system){
			System.out.println(process.priority);
		}
	}

	public void printNodes(){
		this.system.get(0).printNode(this.system.size()-1);
	}

	public Process getCoordinator(){
		for(Process process: system){
			if(process.isCoord){
				return process;
			}
		}
		return null;
	}

	public Process getProcess(int priority){
		for(Process process: system){
			if(process.priority == priority){
				return process;
			}
		}
		return null;
	}

	public Process getFirstProcess(){
		return system.get(0);
	}

}

