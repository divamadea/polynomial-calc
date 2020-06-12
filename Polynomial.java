package Polynomial;
/** Linked list implementation */
public class Polynomial implements List {
    private Link head; // Pointer to list header
    private Link tail; // Pointer to last element
    protected Link curr; // Access to current element
    private int cnt;
    
    /**
     * Constructors
     */
    Polynomial(int size) {
        this();
    } // Constructor -- Ignore size

    Polynomial() {
        curr = tail = head = new Link(null); // Create header
        cnt = 0;
    }

    /**
     * Remove all elements
     */
    public void clear() {
        head.setNext(null); // Drop access to links
        curr = tail = head = new Link(null); // Create header
        cnt = 0;
    }

    /**
     * Insert "it" at current position
     */
    public void insert(int it) {
        curr.setNext(new Link(it, curr.next()));
        if (tail == curr) {
            tail = curr.next(); // New tail
        }
        cnt++;
    }

    /**
     * Append "it" to list
     */
    public void append(int it) {
        tail = tail.setNext(new Link(it, null));
        cnt++;
    }
    /**
     * Return degree of Polinomial
     */
    public int degree () {
        if (cnt == 0)
            return 0;
        return cnt - 1;
    }
    /**
     * Remove and return current element
     */
    public int remove() {
        assert curr.next() != null : ""; // Nothing to remove
        int it = curr.next().element(); // Remember value
        if (tail == curr.next()) {
            tail = curr; // Removed last
        }
        curr.setNext(curr.next().next()); // Remove from list
        cnt--; // Decrement count
        return it; // Return value
    }

    /**
     * Set curr at list start
     */
    public void moveToStart() {
        curr = head;
    }

    /**
     * Set curr at list end
     */
    public void moveToEnd() {
        curr = tail;
    }

    /**
     * Move curr one step left; no change if now at front
     */
    public void prev() {
        if (curr == head) {
            return; // No previous element
        }
        Link temp = head;
        // March down list until we find the previous element
        while (temp.next() != curr) {
            temp = temp.next();
        }
        curr = temp;
    }

    /**
     * Move curr one step right; no change if now at end
     */
    public void next() {
        if (curr != tail) {
            curr = curr.next();
        }
    }

    /**
     * @return List length
     */
    public int length() {
        return cnt;
    }

    /**
     * @return The position of the current element
     */
    public int currPos() {
        Link temp = head;
        int i;
        for (i = 0; curr != temp; i++) {
            temp = temp.next();
        }
        return i;
    }

    /**
     * Move down list to "pos" position
     */
    public void moveToPos(int pos) {
        assert (pos >= 0) && (pos <= cnt) : "Position out of range";
        curr = head;
        for (int i = 0; i < pos; i++) {
            curr = curr.next();
        }
    }

    /**
     * @return Current element value
     */
    public int getValue() {
        return curr.next().element();
    }

    public static Polynomial addition(Polynomial A, Polynomial B) {
        Polynomial result = new Polynomial();
        A.moveToStart(); B.moveToStart();
        int min = Math.min(A.cnt, B.cnt);
        int max = Math.max(A.cnt, B.cnt);
        for (int i = 0; i < min; i++) {
            result.append(A.getValue() + B.getValue());
            A.next(); B.next();
        }
        if (A.cnt > B.cnt) {
            for (int i = min; i < max; i++) {
                result.append(A.getValue());
                A.next();
            }
        } else if (A.cnt < B.cnt) {
            for (int i = min; i < max; i++) {
                result.append(B.getValue());
                B.next();
            }
        } else {
            result.moveToEnd();
            while (result.tail.element() == 0) {
                result.prev();
                result.remove();
            }
        }
        return result;
    }
    
    public static Polynomial subtraction (Polynomial A, Polynomial B) {
        Polynomial result = new Polynomial();
        A.moveToStart(); B.moveToStart();
        int min = Math.min(A.cnt, B.cnt);
        int max = Math.max(A.cnt, B.cnt);
        for (int i = 0; i < min; i++) {
            result.append(A.getValue() - B.getValue());
            A.next(); B.next();
        }
        if (A.cnt > B.cnt) {
            for (int i = min; i < max; i++) {
                result.append(A.getValue());
                A.next();
            }
        } else if (A.cnt < B.cnt) {
            for (int i = min; i < max; i++) {
                result.append((-1) * B.getValue());
                B.next();
            }
        } else {
            result.moveToEnd();
            while (result.tail.element() == 0) {
                result.prev();
                result.remove();
            }
        }
        return result;
    }
    
    public static Polynomial multiplication(Polynomial A, Polynomial B) {
        Polynomial result = new Polynomial();
        int total = A.degree() + B.degree();
        for (int i = A.degree(); i < total; i++)
            A.append(0);
        for (int j = B.degree(); j < total; j++)
            B.append(0);
        for (int k = 0; k <= total; k++) {
            A.moveToStart(); B.moveToPos(k);
            int C_k = 0;
            for (int c = 0; c <= k; c++) {
                C_k += A.getValue() * B.getValue();
                A.next(); B.prev();
            }
            result.append(C_k);
        }
        return result;
    }

    public String toString() {
        curr = tail;
        String data = "";
        if (degree() > 1 && curr.element() == 1) {
            data = "x^" + degree();
            prev();
        } else if (degree() > 1 && curr.element() == -1) {
            data = "-x^" + degree();
            prev();
        } else if (degree() > 1) {
            data = curr.element() + "x^" + degree();
            prev();
        }
        for (int i = degree() - 1; i >= 2; i--) {
            if (curr.element() == 1)
                data += " + " + "x^" + i;
            else if (curr.element() == -1)
                data += " - " + "x^" + i;
            else if (curr.element() > 0)
                data += " + " + curr.element() + "x^" + i;
            else if (curr.element() < 0)
                data += " - " + (-1*curr.element()) + "x^" + i;
            prev();
        }
        if (curr.element() == 1 && degree() == 1)
            data += "x";
        else if (curr.element() > 0 && degree() == 1)
            data += curr.element() + "x";
        else if (curr.element() == 1)
            data += " + " + "x";
        else if (curr.element() == -1)
            data += " - " + "x";
        else if (curr.element() > 0)
            data += " + " + curr.element() + "x";
        else if (curr.element() < 0)
            data += " - " + (-1*curr.element()) + "x";
        prev();
        if (curr.element() > 0 && degree() > 0)
            data += " + " + curr.element();
        else if (curr.element() < 0 && degree() > 0)
            data += " - " + (-1*curr.element());
        else if (degree() == 0)
            data += curr.element();
        else
            data = "0";
        return data;
    }
}
