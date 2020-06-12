package Polynomial;
class Link {
    private int element; // Value for this node
    Link next; // Pointer to next node in list

    // Constructors
    Link(int x, Link nextval) {
        element = x;
        next = nextval;
    }

    Link(Link nextval) {
        next = nextval;
    }

    Link next() {
        return next;
    } // Return next field

    Link setNext(Link nextval) // Set next field
    {
        return next = nextval;
    } // Return element field

    int element() {
        return element;
    } // Set element field

    int setElement(int x) {
        return element = x;
    }
}
