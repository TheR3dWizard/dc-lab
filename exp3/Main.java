import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create some processes
        Process p1 = new Process(5);
        Process p2 = new Process(3);
        Process p3 = new Process(7);

        // Create a system
        ArrayList<Process> system = new ArrayList<>();
        system.add(p1);
        system.add(p2);
        system.add(p3);

        DCSystem sys = new DCSystem(system);

        // Start the election process
        sys.startSystem();

        // Print the coordinator
        for (Process process : system) {
            if (process.isCoord) {
                System.out.println("Coordinator: " + process.priority);
            }
        }
    }
}
