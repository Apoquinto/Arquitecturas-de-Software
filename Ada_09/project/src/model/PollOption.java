package model;

public class PollOption {
    private String name;
    private int count;

    public PollOption(String name) {
        this.name = name;
        this.count = 0;
    }

    public int getCount(){
        return this.count;
    }

    public void addVote(int noVotes){
        this.count = Math.max(this.count + noVotes, 0);
    }

    @Override
    public String toString() {
        return "PoolOption{" +
                "count=" + count +
                '}';
    }
}
