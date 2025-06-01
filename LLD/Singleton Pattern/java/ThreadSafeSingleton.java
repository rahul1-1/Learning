  
     class  Singleton {
        private static Singleton instance = null;
        private static final Object lock = new Object();
        private Singleton(){
            System.out.println("Singleton Constructor called");
        }
        public static Singleton getInstance(){
            if(instance == null)  {
                synchronized (lock) { // Ensures thread safety
                if (instance == null) {
                    instance = new Singleton();
                }
            }
     }
        return instance;
        }
    }

    public class ThreadSafeSingleton {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        if (s1 == s2) {
            System.out.println("Both instances are the same!");
        } else {
            System.out.println("Different instances exist!");
        }
    }
}


