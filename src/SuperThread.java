public class SuperThread implements Runnable {
    private final float[] arr;

    public SuperThread(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.printf("Counting time of %s: %s \n", Thread.currentThread().getName(), (System.currentTimeMillis() - a));
    }
}
