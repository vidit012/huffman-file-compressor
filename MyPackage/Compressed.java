package MyPackage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Compressed {
    String Text;
    HashMap<Integer, String> HuffmanCodes;
    String OutputPath;
    public Compressed(String text, HashMap<Integer, String> huffmanCodes, String outputPath) {
        Text = text;
        HuffmanCodes = huffmanCodes;
        OutputPath = outputPath;
    }

    public void compress(){
        StringBuilder binaryString = new StringBuilder();
    
        for (char c : Text.toCharArray()) {
            String code = HuffmanCodes.get((int)c);
            if (code != null) {
                binaryString.append(code);
            } else {
                System.err.println("Error: No Huffman code found for character: " + c);
                return;
            }
        }
    
        
        int extraBits = 8 - (binaryString.length() % 8);
        if (extraBits < 8) {
            binaryString.append("0".repeat(extraBits));
        }
    
        
        if (binaryString.length() % 8 != 0) {
            System.err.println("Error: Binary string length is not a multiple of 8.");
            return; 
        }
    

        byte[] byteArray = new byte[binaryString.length() / 8];
        for (int i = 0; i < binaryString.length(); i += 8) {
            String byteString = binaryString.substring(i, i + 8);
            byteArray[i / 8] = (byte) Integer.parseInt(byteString, 2);
        }
        try (FileOutputStream fos = new FileOutputStream(OutputPath)) {
            fos.write(byteArray);
            System.out.println("File successfully compressed to " + OutputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
