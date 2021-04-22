package _20210325_Convert_To_DAO2.persistence.models;

public class User {
    private String name;

    public User(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
