class Solution {

    private ArrayList<Integer> heap = new ArrayList<>();

    public void push(int val) {
        heap.add(val);
        heapifyUp(heap.size() - 1);
    }

    public void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;

            if (heap.get(parent) > heap.get(i)) {
                int temp = heap.get(parent);
                heap.set(parent, heap.get(i));
                heap.set(i, temp);

                i = parent;
            } else {
                break;
            }
        }
    }

    public void heapifyDown(int i) {
        int n = heap.size();

        while (true) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && heap.get(left) < heap.get(smallest)) {
                smallest = left;
            }

            if (right < n && heap.get(right) < heap.get(smallest)) {
                smallest = right;
            }

            if (smallest == i) {
                break;
            }

            int temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);

            i = smallest;
        }
    }

    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        int rank = 1;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            push(arr[i]);
        }

        while (!heap.isEmpty()) {
            int min = heap.get(0);

            if (!map.containsKey(min)) {
                map.put(min, rank);
                rank++;
            }

            int last = heap.remove(heap.size() - 1);

            if (!heap.isEmpty()) {
                heap.set(0, last);
                heapifyDown(0);
            }
        }

        for (int i = 0; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }

        return arr;
    }
}
