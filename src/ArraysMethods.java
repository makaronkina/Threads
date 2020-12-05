
import java.util.Arrays;

public class ArraysMethods {
    private static final int size = 10000000;
    private static final int h = size / 2;
    private float[] arr = new float[size];

    public void doSimpleArray() {
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Working time of simple array: " + (System.currentTimeMillis() - a));
    }

    public void doComplicatedArray() {
        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        long splitTime = System.currentTimeMillis();
        System.out.println("Array split time: " + (splitTime - a));

        Thread t1 = new Thread(new SuperThread(a1));
        Thread t2 = new Thread(new SuperThread(a2));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        long countTime = System.currentTimeMillis();


        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("Arrays merge time: " + (System.currentTimeMillis() - countTime));
        System.out.println("Working time of complicated array: " + (System.currentTimeMillis() - a));
    }
}
