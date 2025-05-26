#include<bits/stdc++.h>
using namespace std;

class Car {
    public:
    virtual void startCar() = 0; // Pure virtual function
    virtual void stopCar() = 0;
    virtual void accelerat() = 0;
    virtual void  brake() = 0;
    virtual ~Car(){};
};

class SportCar : public Car {

    private:
    string brand;
    string model;
    bool isEngineOn ;
    int speed ;

    public:

    SportCar(string brand,string model){
        this->brand = brand;
        this->model =  model;
        this->isEngineOn = false;
        this->speed  = 0;
    }

    void startCar(){
        if(!isEngineOn){
            isEngineOn = true;
            cout<<"Engine started for "<<brand<<" "<<model<<endl;
        }else{
            cout<<"Engine already started for "<<brand<<" "<<model<<endl;
        }
    }
    
    void stopCar(){
        if(isEngineOn){
            isEngineOn=false;
            speed=0;
            cout<<"Engine stop for "<<brand<<" "<<model<<endl;
        }else{
             cout<<"Engine already started for "<<brand<<" "<<model<<endl;
        }
    }

    void accelerat(){
        if(isEngineOn){
            speed+=10;
            cout<<"Accelerating "<<brand<<" "<<model<<". Current speed: "<<speed<<" km/h"<<endl;
        }else{
            cout<<"Cannot accelerate. Engineis off for  "<<brand<<" "<<model<<endl;
        }
    }

    void brake(){
        if(isEngineOn && speed > 0){
            speed -= 10;
            if(speed < 0) speed = 0; 
            cout<<"Braking "<<brand<<" "<<model<<". Current speed: "<<speed<<" km/h"<<endl;
        }else if(!isEngineOn){
            cout<<"Cannot brake. Engine is off for "<<brand<<" "<<model<<endl;
        }else{
            cout<<"Already at minimum speed for "<<brand<<" "<<model<<endl;
        }
    }

};

int main() {
    SportCar myCar("Ferrari", "488 GTB");
    
    myCar.startCar();
    myCar.accelerat();
    myCar.accelerat();
    myCar.brake();
    myCar.stopCar();

    return 0;
}