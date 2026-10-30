class BSTIterator {

    List<Integer> vinni = new ArrayList<>();
    int i = 0;

    public void vineetha(TreeNode root) {
        if (root == null)
            return;

        vineetha(root.left);
        vinni.add(root.val);
        vineetha(root.right);
    }

    public BSTIterator(TreeNode root) {
        vineetha(root);
    }

    public int next() {
        return vinni.get(i++);
    }

    public boolean hasNext() {
        return i < vinni.size();
    }
}