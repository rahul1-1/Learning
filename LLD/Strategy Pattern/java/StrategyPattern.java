interface  DriveStrategy {
     void drive();
    
}

class NormalDrive implements DriveStrategy {

    @Override
    public void drive(){
        System.out.println("Driving normally.");
    }
}

class SportDrive implements DriveStrategy {

    @Override
    public void drive(){
        System.out.println("Driving in sport mode.");
    }
}

class OffRoadDrive implements DriveStrategy {
    @Override
    public void drive(){
        System.out.println("Driving off-road.");
    }
}

class Car {
    private DriveStrategy driveStrategy;

    public Car(DriveStrategy driveStrategy){
        this.driveStrategy = driveStrategy;
    }

    void drive(){
        driveStrategy.drive();
    }
}

class NormalCar extends Car {
    public NormalCar() {
        super(new NormalDrive());
    }
}

class SportCar extends Car {
    public SportCar() {
        super(new SportDrive());
    }
}

class OffRoadCar extends Car {
    public OffRoadCar() {
        super(new OffRoadDrive());
    }
}

class StrategyPattern{
    public static void main(String[] args) {
        Car normalCar = new NormalCar();
        normalCar.drive(); // Output: Driving normally.

        Car sportCar = new SportCar();
        sportCar.drive(); // Output: Driving in sport mode.

        Car offRoadCar = new OffRoadCar();
        offRoadCar.drive(); // Output: Driving off-road.
    }
}