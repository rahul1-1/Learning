import java.util.ArrayList;
import java.util.List;

interface  ICommand {
    void execute();
    void undo();
}

class Light {
    public void lightOn(){
        System.out.println("Light is ON");
    }
    public void lightOff(){
        System.out.println("Light is OFF");
    }
}

class Fan {

    public void fanOn(){
        System.out.println("Fan is ON");
    }
    public void fanOff(){
        System.out.println("Fan is OFF");
    }
}

class LightCommand implements ICommand {
    private Light light;

    public LightCommand(Light l) {
        this.light = l;
    }

    @Override
    public void execute(){
        light.lightOn();
    }


    @Override
    public void undo(){
        light.lightOff();
    }
}

class FanCommand implements ICommand {
    private Fan fan;

    public FanCommand(Fan f){
        this.fan = f;
    }

    @Override
    public void execute(){
        fan.fanOn();
    }

    @Override
    public void undo(){
        fan.fanOff();
    }
}

class RemoteController{
    private List<ICommand> buttons;
    private List<Boolean> buttonPressed;

    public RemoteController() {
        buttons = new ArrayList<>();
        buttonPressed = new ArrayList<>();
    }

    public void addCommand(ICommand cmd) {
        buttons.add(cmd);
        buttonPressed.add(false); 
    }

    public void setCommand(int idx, ICommand cmd) {
        if (idx >= 0 && idx < buttons.size()) {
            buttons.set(idx, cmd);
            buttonPressed.set(idx, false);
        } else {
            System.out.println("Invalid button index: " + idx);
        }
    }

    public void pressButton(int idx) {
        if (idx >= 0 && idx < buttons.size() && buttons.get(idx) != null) {
            if (!buttonPressed.get(idx)) {
                buttons.get(idx).execute();
            } else {
                buttons.get(idx).undo();
            }
            buttonPressed.set(idx, !buttonPressed.get(idx));
        } else {
            System.out.println("No command assigned at button " + idx);
        }
    }

}

public class CommandPattern {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        RemoteController remote = new RemoteController();

        remote.addCommand(new LightCommand(livingRoomLight));
        remote.addCommand(new FanCommand(ceilingFan));

        System.out.println("--- Toggling Light Button 0 ---");
        remote.pressButton(0);  // ON
        remote.pressButton(0);  // OFF

        System.out.println("--- Toggling Fan Button 1 ---");
        remote.pressButton(1);  // ON
        remote.pressButton(1);  // OFF

        System.out.println("--- Pressing Unassigned Button 2 ---");
        remote.pressButton(2);
    }
}