package exp4.Centralised;

public class Resource {
    private int id;
    private Process heldby;

    public Resource(int id){
        this.id = id;
    }

    public int GetID(){
        return this.id;
    }

    public Process GetProcess(){
        return this.heldby;
    }

    public void AllocateTo(Process process){
        this.heldby = process;
    }

	public void Free(){
		this.heldby = null;
	}

}
