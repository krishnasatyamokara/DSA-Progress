class KthLargest {

    private int k;
    private ArrayList<Integer> heap;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        heap = new ArrayList<>();

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // Step 1: Insert the new element
        heap.add(val);

        // Step 2: Heapify Up (Min Heap)
        heapifyUp(heap.size() - 1);

        // Step 3: Keep only the k largest elements
        if (heap.size() > k) {
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);

            // Step 4: Restore Min Heap
            heapifyDown(0);
        }

        // Step 5: Return the kth largest element
        return heap.get(0);
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;

            if (heap.get(i) < heap.get(parent)) {
                int temp = heap.get(i);
                heap.set(i, heap.get(parent));
                heap.set(parent, temp);

                i = parent;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heap.size() &&
            heap.get(left) < heap.get(smallest)) {
            smallest = left;
        }

        if (right < heap.size() &&
            heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }

        if (smallest != i) {
            int temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);

            heapifyDown(smallest);
        }
    }
}
