package exp4.Centralised;

import java.util.ArrayList;

public class Manager {
    public ArrayList<Site> sites;

    public Manager(ArrayList<Site> sites){
        this.sites = sites;
    }

    public boolean DetectDeadlock(){
        Graph<Process> gloablgraph = new Graph<>();
        for(Site site: sites){
            gloablgraph = gloablgraph.Union(gloablgraph, site.getGraph());
        }
        return gloablgraph.isCyclic();
    }
}
