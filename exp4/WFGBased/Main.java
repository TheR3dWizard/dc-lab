package exp4.WFGBased;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Site s1 = new Site();
        Site s2 = new Site();

        Process p1 = new Process(1, s1);
        Process p2 = new Process(2, s1);
        Process p3 = new Process(3, s1);
        Process p4 = new Process(4, s2);
        Process p5 = new Process(5, s2);

        Resource r1 = new Resource(1);
        Resource r2 = new Resource(2);
        Resource r3 = new Resource(3);

        s1.RequestResource(p1, r1);
        s1.RequestResource(p1, r2);
        s1.RequestResource(p2, r3);
        s1.RequestResource(p2, r1);
        s1.RequestResource(p1, r3);


        s1.getGraph().printGraph();
    }
}
