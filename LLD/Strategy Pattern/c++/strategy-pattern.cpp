#include <iostream>

using namespace std;


// Define the Drive Strategy interface
class DriveStrategy {
    public:
        virtual void drive() = 0;
        virtual ~DriveStrategy(){};
};

class NormalDrive : public DriveStrategy {
    public:
     void drive() override {
        cout << "🚗 Driving in Normal Mode!" << endl;
     }
};

class SportDrive : public DriveStrategy {
    public:
     void drive() override {
        cout << "🏎️ Driving in Sport Mode!" << endl;
     }
};

class OffRoadDrive : public DriveStrategy {
    public:
        void drive() override {
            cout<<"🚙 Driving in Off-Road Mode!" << endl;
        }
};

class DisplayStrategy {
    public:
        virtual void display() = 0;
        virtual ~DisplayStrategy(){};
};

class NormalDisplay : public DisplayStrategy {
    public:
        void display() override{
            cout <<"📺 Normal Display!" << endl;
        }
};

class AdvancedDisplay : public DisplayStrategy {
    public:
        void display() override{
            cout << "💻 Advanced Display!" << endl;
        }
};



class Car {
    private:
        DriveStrategy* driveStrategy;
        DisplayStrategy* displayStrategy;
    public:
    Car(DriveStrategy* strategy,DisplayStrategy* display): driveStrategy(strategy), displayStrategy(display) {} 
    void drive(){
        driveStrategy->drive();
    }
    void display(){
        displayStrategy->display();
    }

};

class NormalCar : public Car {
    public:
        NormalCar(DriveStrategy* strategy,DisplayStrategy* display) : Car(strategy,display) {} 
};
class SportCar : public Car {
    public:
        SportCar(DriveStrategy* strategy,DisplayStrategy* display) : Car(strategy,display) {}
};

int main(){
  
     cout <<"\n ----------------- Normal Car -----------------\n" << endl;
    NormalCar * normalCar1 =new NormalCar(new  NormalDrive(), new NormalDisplay());
    normalCar1->drive();
    normalCar1->display();

    cout <<"\n ----------------- Sport Car -----------------\n" << endl;
    SportCar * sportCar1 = new SportCar(new SportDrive(), new AdvancedDisplay());
    sportCar1->drive();
    sportCar1->display();

    cout <<"\n ----------------- Sport Car With Off-Road Capability -----------------\n" << endl;
    SportCar* offRoadCar1 = new SportCar(new OffRoadDrive(), new NormalDisplay());
    offRoadCar1->drive();
    offRoadCar1->display();

    NormalCar * normalCar2 = new NormalCar(new NormalDrive(), new AdvancedDisplay());
     cout <<"\n ----------------- Normal Car With Advanced Display -----------------\n" << endl;
    normalCar2->drive();
    normalCar2->display();


    delete normalCar1;
    delete sportCar1;
    delete offRoadCar1;
    delete normalCar2;
    return 0;
}