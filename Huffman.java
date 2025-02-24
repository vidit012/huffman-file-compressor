// Huffman Coding Project //

import MyPackage.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

import java.util.HashMap;

public class Huffman {

    private static HuffmanNode root;
    // private static void visualizeTree(HuffmanNode node, int depth) {
    //     if (node == null) {
    //         return;
    //     }

    //     // Print right subtree first (sideways visualization)
    //     visualizeTree(node.right, depth + 1);

    //     // Print current node with indentation
    //     for (int i = 0; i < depth; i++) {
    //         System.out.print("   ");
    //     }
    //     if (node.character != '\0') {
    //         System.out.println(node.character + ":" + node.frequency);
    //     } else {
    //         System.out.println("[" + node.frequency + "]");
    //     }

    //     // Print left subtree
    //     visualizeTree(node.left, depth + 1);
    // }

    
    

    public static void main(String[] args) {
        int[] frequencies = new int[10000];
        String filepath = args[0];
        StringBuilder sb = new StringBuilder("");
        String str = new String();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            int ch;
            while ((ch = br.read()) != -1) {
                frequencies[ch]++;
                sb.append((char) ch);
            }
            str = sb.toString();
            
        } catch (Exception e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        HuffmanCode huffmanCode = new HuffmanCode(frequencies);
        // visualizeTree(root, 0);
        root = huffmanCode.returnroot();
        // visualizeTree(root,0);
        huffmanCode.traverse(root, "");
        HashMap<Integer, String> values = new HashMap<>();
        values = huffmanCode.returnthetype();

        String filePath1 = "Media\\Output.text";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath1))) {
            for (HashMap.Entry<Integer, String> entry : values.entrySet()) {
                writer.write((int) entry.getKey());
                writer.newLine();
                writer.write(entry.getValue());
                writer.newLine();
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Compressed compress = new Compressed(str, values, "Media\\Compressed.text");
        compress.compress();
        Decompressed decompress = new Decompressed(root, "Media\\Compressed.text", "Media\\De.text");
        decompress.decompress();
    }
}
