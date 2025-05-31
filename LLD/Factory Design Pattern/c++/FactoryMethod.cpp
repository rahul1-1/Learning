#include<iostream>
#include<bits/stdc++.h>
using namespace std;

class Pizza {
public:
    virtual void prepare() = 0;
    virtual ~Pizza() {} 
};

class CheesePizza : public  Pizza {
    public:
    void prepare() override {
        cout<<"🧀  Preparing Cheese Pizza with extra cheese! \n";
    }
};

class  VegPizza : public Pizza {
    public:
    void prepare() override{
        cout<<"🍕 Preparing Veg Pizza with fresh vegetables! \n";
    }
};

class NonVegPizza : public Pizza{
    public:
    void prepare() override {
        cout<<"🍗 Preparing Non-Veg Pizza with chicken and spices! \n";
    }
};

class PizzaFactory {
public:
    virtual Pizza* createPizza() = 0;
};

class VegPizzaFactory : public PizzaFactory {
public:
    Pizza* createPizza() override { return new VegPizza(); }
};

class NonVegPizzaFactory : public PizzaFactory {
public:
    Pizza* createPizza() override { return new NonVegPizza(); }
};

class CheesePizzaFactory : public PizzaFactory {
public:
    Pizza* createPizza() override { return new CheesePizza(); }
};


int main (){
    PizzaFactory* vegFactory = new VegPizzaFactory();
    PizzaFactory* nonVegFactory = new NonVegPizzaFactory();

    Pizza* vegPizza = vegFactory->createPizza();
    Pizza* nonVegPizza = nonVegFactory->createPizza();

    vegPizza->prepare();
    nonVegPizza->prepare();

    delete vegPizza;
    delete nonVegPizza;
    delete vegFactory;
    delete nonVegFactory;

}