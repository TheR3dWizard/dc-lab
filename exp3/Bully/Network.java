package Bully;
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
		System.out.println("Process " + this.priority + " is starting election");
		ArrayList<Process> candidates = new ArrayList<Process>();
		for(Process process:this.system){
			if(process.priority > this.priority){
				candidates.add(process);
			}
		}
		Process coordinator = this;
		for(Process process:candidates){
			if(process.isActive()){
				if(process.priority > coordinator.priority){
					System.out.println("Process " + this.priority + " has found a higher priority process " + process.priority);
					coordinator = process;
				}
			}
		}
		System.out.println("Process " + this.priority + " has elected process " + coordinator.priority + " as coordinator");
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

	public void PingCoordinator(){
		if(this.coord.isActive()){
			System.out.println("Process " + this.priority + " is pinging coordinator " + this.coord.priority);
		}
		else{
			System.out.println("Process " + this.priority + " has found coordinator " + this.coord.priority + " to be inactive");
			this.StartElection();
		}
	}

}

public class Network{
	public ArrayList<Process> system;
	public Process coordinator;

	public Network(ArrayList<Process> system){
		this.system = system;
		for(Process process1: system){
			process1.AddToSystem(this.system);
		}
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

	public void printSystem(){
		for(Process process: system){
			System.out.println(process.priority);
		}
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

