// 206530552 Moshe Yehely Israel.
package gameEnv;
/**
 * This interface represents hits notifiers.
 */
public interface HitNotifier {
    /**
     * This method remove HitListener as a listener to hit events.
     * @param hl The HitListener that need add.
     */
    void addHitListener(HitListener hl);
    /**
     * This method remove HitListener from hit events.
     * @param hl The HitListener that need to remove.
     */
    void removeHitListener(HitListener hl);

}
