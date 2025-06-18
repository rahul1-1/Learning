#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class ISubscriber {
    public:
        virtual void update() = 0;
        virtual ~ISubscriber(){};
};
class IChannel {
    public:
        virtual void subscribe(ISubscriber* sub) = 0;
        virtual void unSubscribe(ISubscriber* sub) = 0;
        virtual void notify() = 0;

        virtual ~IChannel(){};
};

class Channel : public IChannel {
    private:
        string name;
        string lastVideo;
        vector<ISubscriber*>subscribers;

    public:
        Channel(const string& name) : name(name){};

        void subscribe(ISubscriber* sub) override {
            if(find(subscribers.begin(), subscribers.end(), sub) == subscribers.end()) {
                subscribers.push_back(sub);
            }
        }

        void unSubscribe(ISubscriber* sub) override{
            auto it = find(subscribers.begin(),subscribers.end(),sub);
            if(it!= subscribers.end()){
                subscribers.erase(it);
            }
        }
        void notify() override {
            for(auto& sub : subscribers) {
                sub->update();
            }
        }
        void uploadVideo(const string& video) {
            lastVideo = video;
            cout << "Channel: " << name << " uploaded a new video: " << video << endl;
            notify();
        }

        string getVideo() {
            return lastVideo;
        }

        string getName() {
            return name;
        }

};

class Subscriber : public ISubscriber {
    private:
        string name;
        Channel* channel;
    public:

    Subscriber( const string &name,Channel *channel) : name(name),channel(channel){};
    void update() override{
        cout  << "👋 Hey " << name << " New video uploaded on channel: " << channel->getName() <<" , "<<channel->getVideo() <<endl;
    }
};

int main() {
    Channel* channel = new Channel("CoderWorld");

    Subscriber* subs1 = new Subscriber("Ram", channel);
    Subscriber* subs2 = new Subscriber("Alice", channel);

    channel->subscribe(subs1);
    channel->subscribe(subs2);

    channel->uploadVideo("Observer Pattern Tutorial");

    channel->unSubscribe(subs1);

    channel->uploadVideo("Observer Patter Notes");

    // Clean up
    delete subs1;
    delete subs2;
    delete channel;
    return 0;
}


/* OUTPUT:
 Channel: CoderWorld uploaded a new video: Observer Pattern Tutorial
👋 Hey Ram New video uploaded on channel: CoderWorld , Observer Pattern Tutorial
👋 Hey Syam New video uploaded on channel: CoderWorld , Observer Pattern Tutorial
Channel: CoderWorld uploaded a new video: Observer Patter Notes
👋 Hey Syam New video uploaded on channel: CoderWorld , Observer Patter Notes
 */