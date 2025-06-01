#include <iostream>
#include <mutex>
using namespace std;

class Singleton {
    private:
    static Singleton* instance;
    static mutex mtx;
    Singleton(){
       cout << "Singleton Constructor called" << endl;
    }
    public:
    static Singleton* getInstance(){

        if(instance == nullptr){
            lock_guard<mutex> lock(mtx); 
            if(instance == nullptr) { // Double-checked locking
            instance = new Singleton();
            }
        }
        return instance;
     }
};

// Initialize static members
Singleton* Singleton::instance = nullptr;
mutex Singleton::mtx;

int main(){
    Singleton*  s1 = Singleton::getInstance();
    Singleton* s2  = Singleton::getInstance();
    if(s1 == s2){
        cout << "Both instances are the same." << endl;
    } else {
        cout << "Instances are different." << endl;
    }
}