package dailytasks.usedcars.repository;

import java.util.List;

public class Car {

    private String brand;
    private String type;
    private int age;
    private CarCondition condition;
    private List<KmState> kmStates;

    public Car(String brand, String type, int age, CarCondition condition, List<KmState> kmStates) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.condition = condition;
        this.kmStates = kmStates;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CarCondition getCondition() {
        return condition;
    }

    public void setCondition(CarCondition condition) {
        this.condition = condition;
    }

    public List<KmState> getKmStates() {
        return kmStates;
    }

    public void setKmStates(List<KmState> kmStates) {
        this.kmStates = kmStates;
    }
}
