
import java.util.ArrayList;
import java.util.List;

interface  ISubscriber {
    void update();
}

interface IChannel {
    void subscribe(ISubscriber sub);
    void unSubscribe(ISubscriber sub);
    void notifySubscribers();
}

class Channel implements IChannel {
    private  String channelName;
    private String lastVideo;
    private List<ISubscriber> subscribers;

     Channel(String channelName) {
        this.channelName = channelName;
        this.subscribers = new ArrayList<>();
    }
    @Override
    public void subscribe(ISubscriber sub){
        if(sub != null && !subscribers.contains(sub)) {
            subscribers.add(sub);
            System.out.println(sub + " subscribed to " + channelName);
        }
    }

    @Override
    public void unSubscribe(ISubscriber sub){
        if(sub != null && subscribers.contains(sub)) {
            subscribers.remove(sub);
            System.out.println(sub + " unsubscribed from " + channelName);
        }
    }

    @Override
    public void notifySubscribers(){
        for(ISubscriber sub : subscribers) {
            sub.update();
        }
    }

    public void uploadVideo(String videoTitle) {
        this.lastVideo = videoTitle;
        System.out.println("New video uploaded: " + videoTitle + " on " + channelName);
        notifySubscribers();
    }

    public String getVideo(){
        return lastVideo;
    }

}

class Subscriber implements ISubscriber {
    private String  name;
    private Channel channel;

    public Subscriber(String name, Channel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public String toString() {
        return name;
    }

    
    @Override
    public void update() {
        System.out.println(name + " has been notified of a new video: " + channel.getVideo());
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        Channel techChannel = new Channel("Tech Insights");
        Subscriber alice = new Subscriber("Alice", techChannel);
        Subscriber bob = new Subscriber("Bob", techChannel);

        techChannel.subscribe(alice);
        techChannel.subscribe(bob);

        techChannel.uploadVideo("Understanding Design Patterns in Java");

        techChannel.unSubscribe(bob);
        techChannel.uploadVideo("Advanced Java Programming Techniques");
    }
}
