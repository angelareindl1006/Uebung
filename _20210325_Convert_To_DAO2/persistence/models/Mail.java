package _20210325_Convert_To_DAO2.persistence.models;

public class Mail {
    private String address;
    private int id;

    public Mail(String address, int id){
        this.address = address;
        this.id = id;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

}
