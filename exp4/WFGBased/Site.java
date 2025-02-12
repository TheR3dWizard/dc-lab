package exp4.WFGBased;

import exp4.Graph;


public class Site {
    private Process pex = new Process(-1, null);
    private Graph<Process> wfg;

    public Site(){
        wfg = new Graph<Process>();
        wfg.addNode(pex);
    }


    public void RequestResource(Process p,Resource r){
        System.out.println("Process "+p.getId()+" requests resource "+r.GetID());
        if(r.isHeld()){
            System.out.println("Resource "+r.GetID()+" is already being used by process "+r.GetProcess().getId()+ " so Deadlock Detection Started");
            wfg.addEdge(p, r.GetProcess());
            p.AddRequest(r);
            DetectDeadlock();
            return;
        }
        if(!p.SameSystem(this)){
            pex.Allocate(r);
            r.AllocateTo(pex);
            System.out.println("Allocated to process in other site");
        }
        else{
            p.Allocate(r);
            r.AllocateTo(p);
            System.out.println("Allocated to process in same site");
        }

    }

    public void RelinquishResource(Process p,Resource r){
        p.Relinquish(r);
        for(Process proc: wfg.GetNodes()){
            if(proc.WaitingOn(r)){
                // checks if proc needs any resource that p is holding, if it doesnt, then edge must be removed
                boolean check = true;
                for(Resource res: p.GetResources()){
                    if(proc.WaitingOn(res)){
                        System.out.println("Process "+ proc.getId()+" waiting resource "+res.GetID()+" from process "+p);
                        check = false;
                    }
                }
                if(check){
                    wfg.removeEdge(proc, p);
                }

                //requests the resource it was waiting on
                RequestResource(proc, r);
            }
        }
    }

    public void DetectDeadlock(){
        if(wfg.isCyclic()){
            System.out.println("Deadlock Detected!");
        }
        else{
            System.out.println("No Deadlock Detected");
        }
    }

        public Graph<Process> getGraph(){
            return wfg;
        }

}
