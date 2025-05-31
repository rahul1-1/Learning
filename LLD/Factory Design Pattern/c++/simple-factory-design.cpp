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
        cout<<"🧀  Preparing Cheese Pizza with extra cheese!";
    }
};

class  VegPizza : public Pizza {
    public:
    void prepare() override{
        cout<<"🍕 Preparing Veg Pizza with fresh vegetables!";
    }
};

class NonVegPizza : public Pizza{
    public:
    void prepare() override {
        cout<<"🍗 Preparing Non-Veg Pizza with chicken and spices!";
    }
};


class PizzaFactory {
    public:
    static Pizza* createPizza(string type) {
        transform(type.begin(), type.end(), type.begin(), ::tolower);
        if (type == "veg")
            return new VegPizza();
        else if (type == "nonveg")
            return new NonVegPizza();
        else if (type == "cheese")
            return new CheesePizza();
        
        return nullptr;
    }
};

int main (){
     string choice;
    cout << "Enter pizza type (Veg/NonVeg/Cheese): ";
    cin >> choice;

    PizzaFactory* factory = new PizzaFactory();
    Pizza* pizza = factory->createPizza(choice);
    if(pizza){
        pizza->prepare();
        delete pizza;
    }else{
        cout<<choice<<"pizza is not available!"<<endl;
    }return 0;
    delete factory;
}