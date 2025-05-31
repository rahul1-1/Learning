#include <iostream>
using namespace std;

class Car {
public:
    virtual void assemble() = 0;  
    virtual ~Car() {} 
};

class ToyotaSUV : public Car {
public:
    void assemble() override {
        cout << "🚙 Assembling Toyota SUV!" << endl;
    }
};

class ToyotaSedan : public Car {
public:
    void assemble() override {
        cout << "🚗 Assembling Toyota Sedan!" << endl;
    }
};

class BMWSUV : public Car {
public:
    void assemble() override {
        cout << "🚙 Assembling BMW SUV!" << endl;
    }
};

class BMWSedan : public Car {
public:
    void assemble() override {
        cout << "🚗 Assembling BMW Sedan!" << endl;
    }
};

class CarFactory {
public:
    virtual Car* createSUV() = 0;    
    virtual Car* createSedan() = 0;  
    virtual ~CarFactory() {}       
};


class ToyotaFactory : public CarFactory {
public:
    Car* createSUV() override {
        return new ToyotaSUV();
    }

    Car* createSedan() override {
        return new ToyotaSedan();
    }
};

class BMWFactory : public CarFactory {
public:
    Car* createSUV() override {
        return new BMWSUV();
    }

    Car* createSedan() override {
        return new BMWSedan();
    }
};

int main() {
    CarFactory* toyotaFactory = new ToyotaFactory();
    CarFactory* bmwFactory = new BMWFactory();

    Car* toyotaSUV = toyotaFactory->createSUV();
    Car* toyotaSedan = toyotaFactory->createSedan();

    Car* bmwSUV = bmwFactory->createSUV();
    Car* bmwSedan = bmwFactory->createSedan();

    toyotaSUV->assemble();
    toyotaSedan->assemble();
    bmwSUV->assemble();
    bmwSedan->assemble();

    delete toyotaSUV;
    delete toyotaSedan;
    delete bmwSUV;
    delete bmwSedan;
    delete toyotaFactory;
    delete bmwFactory;

    return 0;
}