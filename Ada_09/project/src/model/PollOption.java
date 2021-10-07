package model;

public class PollOption {
    private String name;
    private int count;

    public PollOption(String name) {
        this.name = name;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount(){
        return this.count;
    }

    public void addVote(int noVotes){
        this.count = Math.max(this.count + noVotes, 0);
    }

    @Override
    public String toString() {
        return "Se han registrado " + count + " votos para la opción " + name;
    }
}
