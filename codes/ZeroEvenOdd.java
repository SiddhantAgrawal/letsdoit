class ZeroEvenOdd {
    private int n;
    private boolean isOdd = true;
    
    private int oddValue = 1;
    private int evenValue = 2;
    
    private volatile Semaphore forZero = new Semaphore(0);
    private volatile Semaphore forOdd = new Semaphore(0);
    private volatile Semaphore forEven = new Semaphore(0);
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        forZero.release();
        
        for (int i = 1; i <= n; i++) {
            forZero.acquire();
            printNumber.accept(0);
            
            if (isOdd) {
                forOdd.release();
                isOdd = false;
            } else {
                forEven.release();
                isOdd = true;
            }
            
        }
        
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        
        for (int i = 1 ; i <= n/2 ; i++) {
            forEven.acquire();
            printNumber.accept(evenValue);
            evenValue += 2;
            forZero.release();
         }
        
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        // System.out.println("inside odd :    " + oddValue );
        
        int limit = (int) Math.ceil( n/2.0 );
        
        for (int i = 0 ; i < limit ; i++) {
            System.out.println("inside odd :    " + oddValue );
            forOdd.acquire();
            printNumber.accept(oddValue);
            oddValue += 2;
            forZero.release();
        }
        
    }
}
