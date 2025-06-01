#include <iostream>
using namespace std;

class Singleton {
    private:
    static Singleton* instance;
    Singleton(){
       cout << "Singleton Constructor called" << endl;
    }
    public:
    static Singleton* getInstance(){
        return instance;
     }
};

// Instantiating the singleton **at class load time**
Singleton* Singleton::instance = new Singleton();

int main(){
    Singleton*  s1 = Singleton::getInstance();
    Singleton* s2  = Singleton::getInstance();
    if(s1 == s2){
        cout << "Both instances are the same." << endl;
    } else {
        cout << "Instances are different." << endl;
    }
    return 0;
}