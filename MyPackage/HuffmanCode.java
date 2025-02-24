package MyPackage;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HuffmanCode {
    private static HuffmanNode root;
    Comparator<HuffmanNode> frequenciesComperator = new Comparator<HuffmanNode>() {
        public int compare(HuffmanNode s1, HuffmanNode s2) {
            if (s1.frequency > s2.frequency)
                return 1;
            else if (s1.frequency < s2.frequency)
                return -1;
            return 0;
        }
    };
    PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(frequenciesComperator);
    public HuffmanCode(int[] frequencies) {
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                HuffmanNode node = new HuffmanNode((char) i, frequencies[i]);
                priorityQueue.add(node);
            }
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            // System.out.println(parent.frequency + "\n");
            priorityQueue.add(parent);
        }

        root = priorityQueue.poll();
    }

    static HashMap<Integer, String> values = new HashMap<>();

    public void traverse(HuffmanNode e, String code) {
        if (e == null)
            return;
        if (e.character != '\0') {
            int token = (int) e.character;
            values.put(token, code);
        }

        traverse(e.left, code + "0");

        traverse(e.right, code + "1");
    }

    public HashMap<Integer, String> returnthetype()
    {
        return values;
    }
    public HuffmanNode returnroot()
    {
        return root;
    }
}
