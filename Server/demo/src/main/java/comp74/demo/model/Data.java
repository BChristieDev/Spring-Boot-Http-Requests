package comp74.demo.model;

public class Data {

    int id;
    String data;
    static int nextId = 1;

    //? Constructors
    public Data() { } 
    public Data(Integer id) { this.id = nextId++; } 

    //? Getters
    public int getId() { return this.id; }
    public String getData() { return this.data; }

    //? Setters
    public void setId(int id) { this.id = id; }
    public void setId() { this.id = nextId++; }
    public void setData(String data) { this.data = data; }

}
