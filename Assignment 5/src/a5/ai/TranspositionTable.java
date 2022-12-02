package a5.ai;

import cms.util.maybe.Maybe;

/**
 * A transposition table for an arbitrary game. It maps a game state
 * to a search depth and a heuristic evaluation of that state to the
 * recorded depth. Unlike a conventional map abstraction, a state is
 * associated with a depth, so that clients can look for states whose
 * entry has at least the desired depth.
 *
 * @param <GameState> A type representing the state of a game.
 */
public class TranspositionTable<GameState> {

    /**
     * Information about a game state, for use by clients.
     */
    public interface StateInfo {

        /**
         * The heuristic value of this game state.
         */
        int value();

        /**
         * The depth to which the game tree was searched to determine the value.
         */
        int depth();
    }

    /**
     * A Node is a node in a linked list of nodes for a chaining-based implementation of a hash
     * table.
     *
     * @param <GameState>
     */
    static private class Node<GameState> implements StateInfo {
        /**
         * The state
         */
        GameState state;
        /**
         * The depth of this entry. >= 0
         */
        int depth;
        /**
         * The value of this entry.
         */
        int value;
        /**
         * The next node in the list. May be null.
         */
        Node<GameState> next;

        Node(GameState state, int depth, int value, Node<GameState> next) {
            this.state = state;
            this.depth = depth;
            this.value = value;
            this.next = next;
        }

        public int value() {
            return value;
        }

        public int depth() {
            return depth;
        }
    }

    /**
     * The number of entries in the transposition table.
     */
    private int size;

    /**
     * The buckets array may contain null elements.
     * Class invariant:
     * All transposition table entries are found in the linked list of the
     * bucket to which they hash, and the load factor is no more than 1.
     */
    private Node<GameState>[] buckets;

    // TODO 1: implement the classInv() method. You may also
    // strengthen the class invariant. The classInv()
    // method is likely to be expensive, so you may want to turn
    // off assertions in this file, but only after you have the transposition
    // table fully tested and working.
    boolean classInv() {
        int a, b = 0;
        if(buckets == null){
            a = 1;
        } else if (buckets.length >= 0){
            a = 2;
        } else {
            a = 0;
        }
        if(a == 2 && (buckets.length/size) <= 1){
            b = 1;
        }
        return (size > 0) && (a == 1 || b == 1);
    }

    /** Creates: a new, empty transposition table. */
    TranspositionTable() {
        // TODO 2
        size = 0;
        buckets = new Node[1];
    }

    /** The number of entries in the transposition table. */
    public int size() {
        return size;
    }

    /**
     * Returns: the information in the transposition table for a given
     * game state, package in an Optional. If there is no information in
     * the table for this state, returns an empty Optional.
     */
    public Maybe<StateInfo> getInfo(GameState state) {
        // TODO 3
        assert classInv();
        int hash = state.hashCode();
        int i = bucketIndex(hash);
        if (!(buckets[i] == null)){
            Node head = buckets[i];
            while(!(head == null)){
                if(head.state.hashCode() == hash){
                    return Maybe.some(head);
                }
                head = head.next;
            }

        }
        return Maybe.none();
    }

    private int bucketIndex(int h){
        int ind = (h % (buckets.length));
        if (ind < 0){
            ind = -ind;
        }
        return ind;
    }

    /**
     * Effect: Add a new entry in the transposition table for a given
     * state and depth, or overwrite the existing entry for this state
     * with the new state and depth. Requires: if overwriting an
     * existing entry, the new depth must be greater than the old one.
     */
    public void add(GameState state, int depth, int value) {
        // TODO 4
        Node<GameState> n = new Node<>(state, depth, value, null);
        int i = state.hashCode() % buckets.length;
        Node<GameState> a = buckets[i];
        Node<GameState> b = buckets[i];
        if(a == null){
            buckets[i] = n;
            size++;
        } else {
            a = a.next;
            if(b.state.equals(state)){
                buckets[i] = n;
                n.next = a;
            } else if(a != null) {
                while(a != null){
                    if(a.state.equals(state)){
                        b.next = n;
                        n.next = a.next;
                        break;
                    }
                    b = b.next;
                    a = a.next;
                }
                if(a == null){
                    b.next = n;
                    size++;
                }
            }
        }
        grow(size);
        assert classInv();
    }

    /**
     * Effect: Make sure the hash table has at least {@code target} buckets.
     * Returns true if the hash table actually resized.
     */
    private boolean grow(int target) {
        // TODO 5
        assert classInv();
        if(buckets.length == target){
            Node<GameState>[] grown  = new Node[buckets.length*2];
            for(int i = 0; i <= buckets.length - 1; i++){
                grown[i] = buckets[i];
            }
            buckets = grown;
            return true;
        }
        return false;
    }

    // You may want to write some additional helper methods.


    /**
     * Estimate clustering. With a good hash function, clustering
     * should be around 1.0. Higher values of clustering lead to worse
     * performance.
     */
    double estimateClustering() {
        final int N = 500;
        int m = buckets.length, n = size;
        double sum2 = 0;
        for (int i = 0; i < N; i++) {
            int j = Math.abs((i * 82728353) % buckets.length);
            int count = 0;
            Node<GameState> node = buckets[j];
            while (node != null) {
                count++;
                node = node.next;
            }
            sum2 += count*count;
        }
        double alpha = (double)n/m;
        return sum2/(N * alpha * (1 - 1.0/m + alpha));
    }
}
