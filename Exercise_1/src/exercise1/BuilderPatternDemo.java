package exercise1;

// Product class
class Computer {
    private String CPU;
    private String RAM;
    private String storage;

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", Storage=" + storage + "]";
    }
}

// Builder interface
interface ComputerBuilder {
    void buildCPU();
    void buildRAM();
    void buildStorage();
    Computer getComputer();
}

// Concrete Builder
class GamingComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public GamingComputerBuilder() {
        this.computer = new Computer();
    }

    public void buildCPU() {
        computer.setCPU("High-end Gaming CPU");
    }

    public void buildRAM() {
        computer.setRAM("32GB RAM");
    }

    public void buildStorage() {
        computer.setStorage("1TB SSD");
    }

    public Computer getComputer() {
        return computer;
    }
}

// Director
class ComputerDirector {
    private ComputerBuilder builder;

    public ComputerDirector(ComputerBuilder builder) {
        this.builder = builder;
    }

    public void constructComputer() {
        builder.buildCPU();
        builder.buildRAM();
        builder.buildStorage();
    }

    public Computer getComputer() {
        return builder.getComputer();
    }
}

// Main class
public class BuilderPatternDemo {
    public static void main(String[] args) {
        ComputerBuilder builder = new GamingComputerBuilder();
        ComputerDirector director = new ComputerDirector(builder);

        director.constructComputer();
        Computer computer = director.getComputer();
        System.out.println(computer);
    }
}
