package Bully;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create some processes
        Process p1 = new Process(5);
        Process p2 = new Process(3);
        Process p3 = new Process(7);
        Process p4 = new Process(2);
        Process p5 = new Process(6);
        Process p6 = new Process(4);
        Process p7 = new Process(8);
        Process p8 = new Process(1);

        // Create a system
        ArrayList<Process> system = new ArrayList<>();
        system.add(p8);
        system.add(p1);
        system.add(p3);
        system.add(p4);
        system.add(p5);
        system.add(p6);
        system.add(p7);
        system.add(p2);

        Network sys = new Network(system);
        // sys.printSystem();
        // Start the election process
        sys.startSystem();

        // Print the coordinator
        Process coord = sys.getCoordinator();
        System.out.println("Coordinator: " + coord.priority);

        coord.SwitchOff();
        sys.getFirstProcess().PingCoordinator();        
    }
}
