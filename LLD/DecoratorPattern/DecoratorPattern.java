
interface  ICharacter {
    String getAbilities();
}

class Mario implements ICharacter {

    @Override
    public String getAbilities(){
        return  "Mario";
    }
}

// Abstract Decorator: CharacterDecorator "is-a" Character and "has-a" Character
abstract  class Decorator implements ICharacter {
    protected ICharacter character;
    public Decorator(ICharacter character){
        this.character = character;
    }

}

class HeightUp  extends Decorator {

    public HeightUp(ICharacter character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with HeightUp";
    }
}

class GunPowerUp extends Decorator  {
    public GunPowerUp(ICharacter c){
        super(c);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with GunPowerUp";
    }
}

class StarPowerUp extends Decorator {
    public StarPowerUp(ICharacter c){
        super(c);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with StarPowerUp";
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        ICharacter mario = new Mario();
        System.out.println(mario.getAbilities());

        // Adding HeightUp power
        ICharacter heightUpMario = new HeightUp(mario);
        System.out.println(heightUpMario.getAbilities());

        // Adding GunPowerUp power
        ICharacter gunPowerUpMario = new GunPowerUp(heightUpMario);
        System.out.println(gunPowerUpMario.getAbilities());

        // Adding StarPowerUp power
        ICharacter starPowerUpMario = new StarPowerUp(gunPowerUpMario);
        System.out.println(starPowerUpMario.getAbilities());
    }
}