
 interface IWeightMachine {
    public double getWeight();
}

class PoundWeightMachine  {
    public double getWeightInPounds() {
        return 150.0;
    }
}

class PoundToKgAdapter implements IWeightMachine{

    private PoundWeightMachine poundWeightMachine;

    public PoundToKgAdapter(PoundWeightMachine poundWeightMachine) {
        this.poundWeightMachine = poundWeightMachine;
    }

    @Override
    public double getWeight() {
        return poundWeightMachine.getWeightInPounds() * 0.453592; 
    }
}


class WeightMachineClient {
    public void displayWeight(IWeightMachine weightMachine) {
        double weightInKg = weightMachine.getWeight();
        System.out.println("Weight in kilograms: " + weightInKg + " kg");
    }
}

public class AdapterDesignPattern {
    public static void main(String[] args) {
        PoundWeightMachine legacyMachine = new PoundWeightMachine();
        
        IWeightMachine adapter = new PoundToKgAdapter(legacyMachine);
        
        WeightMachineClient client = new WeightMachineClient();
        
        client.displayWeight(adapter);
    }
}