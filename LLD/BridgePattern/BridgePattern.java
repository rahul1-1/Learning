
//LLL (Low level layer) - Engine interface and its implementations
// HLL (High level layer) - Car abstract class and its concrete implementations
interface Engine {
    void start();
}

class PetrolEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Petrol engine starting with ignition!");
    }
}

class DieselEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Diesel engine roaring to life!");
    }
}

class ElectricEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Electric engine powering up silently!");
    }
}

abstract class Car {
    protected Engine engine;
    public Car(Engine e) {
        this.engine = e;
    }
    public abstract void drive();
}

class Sedan extends Car {
    public Sedan(Engine e) {
        super(e);
    }

    @Override
    public void drive() {
        engine.start();
        System.out.println("Driving a Sedan on the highway.");
    }
}

class SUV extends Car {
    public SUV(Engine e) {
        super(e);
    }

    @Override
    public void drive() {
        engine.start();
        System.out.println("Driving an SUV off-road.");
    }
}

public class BridgePattern {
    public static void main(String[] args) {
        Engine petrolEng = new PetrolEngine();
        Engine dieselEng = new DieselEngine();
        Engine electricEng = new ElectricEngine();

        Car mySedan = new Sedan(petrolEng);
        Car mySUV = new SUV(electricEng);
        Car yourSUV = new SUV(dieselEng);

        mySedan.drive();   // Petrol engine + Sedan
        mySUV.drive();     // Electric engine + SUV
        yourSUV.drive();   // Diesel engine + SUV

    }
}