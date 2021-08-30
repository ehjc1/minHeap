// Name: Eugene Chew
// ID: 1351553

public class MyMinHeap {
    private int[] _heap;
    // the current number of items int the heap
    private int _size;
    // the maximum capacity of the heap
    private int _maxsize;

    // constructor for MyMinHeap
    public MyMinHeap() {
        // initialise the size of the heap to 20
        this._maxsize = 20;
        this._size = 0;
        _heap = new int[this._maxsize];
        _heap[0] = _size;
    }
    // a add method
    public void add(int x) {
        // store new empty slot in the array
        int newIndex = _size + 1;
        // check if we need to resize the heap
        if(newIndex > _maxsize - 1) {
            resize();
            _heap[newIndex] = x;
        }
        // check if the list is empty
        else if(_size == 0) {
            _heap[1] = x;
        }
        else {
            _heap[newIndex] = x;
        }
        upheap(newIndex);
        // increment the number of items int the heap
        _size++;
        _heap[0] = _size;
    }

    // a method that removes the root of the heap
    public void remove() {
        // store the root and the tail of the heap
        int root = 1;
        int tail = _size;
        try {
            // check if the heap is not empty
            // if its not swap the root with the head
            // reduce the size of the heap and call downheap
            // to ensure the heap is in heap order
            if(_size > 0) {
                swap(root, tail);
                _size--;
                downheap(root);
                _heap[0] = _size;
            }
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    // a method that reset the heap list
    public void clear() {
        _heap = new int[_maxsize];
        _size = 0;
    }

    // a method that returns the root
    public int get() {
        // check if the heap is empty
        // if so print error message
        if(_size == 0) {
            System.err.println("get() operation failed! ... heap is empty");
            return -1;
        }
        // else return the root of the heap
        else {
            return _heap[1];
        }
    }

    // a method that replace the root's value with the value passed in
    public void replace(int x) {
        if (_size == 0) {
            _heap[1] = x;
            _size ++;
        } else {
            _heap[1] = x;
            // reheap the heap
            downheap(1);
        }
    }

    // a method that returns true if the heap is empty
    // and vice versa
    public boolean isEmpty() {
        return _size == 0;
    }

    // a method that returns the size of the heap
    public int size() {
        return _size;
    }

    //#########################################################################################################################
    // Private Methods
    //#########################################################################################################################
    // a upheap method to heapify upwards
    private void upheap(int index) {
        // store the parent of the index
        int parent = (int)Math.floor(index/2);

        // check if we have a parent
        if(parent > 0) {
            // if we do, check if we are less than out parent
            if(_heap[index] < _heap[parent]) {
                // if so swap places with our parent
                swap(parent, index);
                // call upheap on the parent
                upheap(parent);
            }
            else {
                return;
            }
        }
    }

    // a method that checks if a node is a leaf node
    private boolean isLeaf(int index) {
        if(index > (int)Math.floor(_size/2) && index <= _size) {
            return true;
        }
        return false;
    }
    // a downheap method
    private void downheap(int index) {
        int leftChild = index * 2;
        int rightChild = (index*2) + 1;

        // check if we have children
        if(!isLeaf(index)) {
            if(leftChild < _size && rightChild <= _size) {
                // check if one of our child is less than us
                if (_heap[index] > _heap[leftChild] || _heap[index] >_heap[rightChild]) {
                    if(_heap[leftChild] < _heap[rightChild]) {
                        // swap places with left child
                        swap(index, leftChild);
                        // call ensure the collection is in heap order
                        downheap(leftChild);
                    }
                    else {
                        // swap places with right child
                        swap(index, rightChild);
                        downheap(rightChild);
                    }
                }
            }
            // there is no rightchild so we swap with the left child
            else if (leftChild == _size) {
                swap(index, leftChild);
                downheap(leftChild);
            }

        }
    }

    // a resize method
    private void resize() {
        _maxsize = _maxsize + (int)Math.floor((_size*0.2));
        // create new heap size
        int[] newHeap = new int[_maxsize];

        // a for loop that moves all data form the old heap to the new heap
        for(int i = 1; i <= _size; i++) {
            newHeap[i] = _heap[i];
        }
        // replace the old heap with new heap
        _heap = newHeap;
    }

    // a method that swaps position of 2 index/items in the list
    private void swap(int f, int s) {
        int temp = _heap[f];
        _heap[f] = _heap[s];
        _heap[s] = temp;
    }
}
