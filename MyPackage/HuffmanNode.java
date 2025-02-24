package MyPackage;

public class HuffmanNode {
    public char character;
        public int frequency;

        public HuffmanNode left;
        public HuffmanNode right;

        public HuffmanNode(char c, int f) {
            this.character = c;
            this.frequency = f;

            this.left = null;
            this.right = null;
        }

        public HuffmanNode(int f, HuffmanNode a, HuffmanNode b) {
            this.left = a;
            this.right = b;
            this.frequency = f;
            this.character = '\0';
        }
}
