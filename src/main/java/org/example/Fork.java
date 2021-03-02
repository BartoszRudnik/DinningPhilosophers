package org.example;

public class Fork {

    private int id;
    private String owner;

    public Fork(){

    }

    public Fork(int id){

        this.id = id;

    }

    public Fork(int id, String owner){

        this.id = id;
        this.owner = owner;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
