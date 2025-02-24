package MyPackage;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Decompressed {

    HuffmanNode Root;
    String InputPath;
    String OutputPath;
    public Decompressed(HuffmanNode root, String inputPath, String outputPath) {
        Root = root;
        InputPath = inputPath;
        OutputPath = outputPath;
    }
    public void decompress(){
        try (FileInputStream fis = new FileInputStream(InputPath);
             BufferedInputStream bis = new BufferedInputStream(fis);
             DataInputStream dis = new DataInputStream(bis);
             BufferedWriter writer = new BufferedWriter(new FileWriter(OutputPath))) {

            HuffmanNode current = Root;
            int bit;
            StringBuilder binaryString = new StringBuilder();

            // Read the file byte by byte
            while (dis.available() > 0) {
                int byteRead = dis.read();
                String byteString = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0');
                binaryString.append(byteString);
            }

            
            for (int i = 0; i < binaryString.length(); i++) {
                bit = Character.getNumericValue(binaryString.charAt(i));
                current = (bit == 0) ? current.left : current.right;

                
                if (current.left == null && current.right == null) {
                    writer.write(current.character);
                    current = Root;
                }
            }
            System.out.println("File successfully decompressed to " + OutputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
