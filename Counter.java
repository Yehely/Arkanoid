// 206530552 Moshe Yehely Israel.
package gameEnv;
/**
 * This class is a simple class that count integers.
 * @author Moshe Yehely Israel
 * @since 27-08-2024
 */
public class Counter {

    private int count;

    /**
     * constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * This method add number to current count.
     * @param number The number that the method add to the counter.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * This method subtract number from current count.
     * @param number The number that the method subtract from the counter.
     */
    void decrease(int number) {
        this.count -= number;
    }

    /**
     *  This method return the current count.
     * @return The current count.
     */
    public int getValue() {
        return this.count;
    }
}
