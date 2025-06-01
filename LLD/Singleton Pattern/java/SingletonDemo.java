    
     class  Singleton {
        private static Singleton instance = null;
        private Singleton(){
            System.out.println("Singleton Constructor called");
        }
        public static Singleton getInstance(){
            if(instance == null){
                instance = new Singleton();
            }
            return instance;
        }
    }

    public class SingletonDemo {
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


