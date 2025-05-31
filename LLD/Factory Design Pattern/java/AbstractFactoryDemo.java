interface Car {
    void assemble();
}


class ToyotaSUV implements Car {
    @Override
    public void assemble() {
        System.out.println("Assembling a Toyota SUV.");
    }
}

class ToyotaSedan implements Car {
    @Override
    public void assemble() {
        System.out.println("Assembling a Toyota Sedan.");
    }
}

class ToyotaElectric implements Car {
    @Override
    public void assemble() {
        System.out.println("Assembling a Toyota Electric car.");
    }
}

class BMWSUV implements Car {
    @Override
    public void assemble() {
        System.out.println("Assembling a BMW SUV.");
    }
}

class BMWSedan implements Car {
    @Override
    public void assemble() {
        System.out.println("Assembling a BMW Sedan.");
    }
}

class BMWElectric implements Car {
    @Override
    public void assemble() {
        System.out.println("Assembling a BMW Electric car.");
    }
}

enum CarType {
    SUV, SEDAN, ELECTRIC
}

interface CarFactory {
    Car createCar(CarType type);
}

class ToyotaFactory implements CarFactory {
    @Override
    public Car createCar(CarType type) {
        switch (type) {
            case SUV:
                return new ToyotaSUV();
            case SEDAN:
                return new ToyotaSedan();
            case ELECTRIC:
                return new ToyotaElectric();
            default:
                throw new IllegalArgumentException("Unsupported Toyota car type: " + type);
        }
    }
}

class BMWFactory implements CarFactory {
    @Override
    public Car createCar(CarType type) {
        switch (type) {
            case SUV:
                return new BMWSUV();
            case SEDAN:
                return new BMWSedan();
            case ELECTRIC:
                return new BMWElectric();
            default:
                throw new IllegalArgumentException("Unsupported BMW car type: " + type);
        }
    }
}


public class AbstractFactoryDemo {
    public static void main(String[] args) {
        CarFactory toyotaFactory = new ToyotaFactory();
        CarFactory bmwFactory = new BMWFactory();

        Car toyotaSUV = toyotaFactory.createCar(CarType.SUV);
        Car toyotaElectric = toyotaFactory.createCar(CarType.ELECTRIC);

        Car bmwSedan = bmwFactory.createCar(CarType.SEDAN);
        Car bmwElectric = bmwFactory.createCar(CarType.ELECTRIC);

        toyotaSUV.assemble();
        toyotaElectric.assemble();
        bmwSedan.assemble();
        bmwElectric.assemble();
    }
}
