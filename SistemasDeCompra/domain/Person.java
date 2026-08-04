package academy.devdojo.maratonajava.javacore.SistemasDeCompra.domain;

public abstract class Person {
    private final int id;
    private String name;
    private int age;
    private char role;

    public Person(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setName(String name ){
        this.name = name;
    }

    @Override
    public String toString(){
        return getId() + " - " + getName();
    }
}
