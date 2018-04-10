import java.io.*;

public class AES{
    public static void main(String[] args){
        String filePlaintext = readFile("plaintext.txt");
        String fileKey = readFile("key.txt");

        

        System.out.println(filePlaintext);
        System.out.println(fileKey);

        String[][] plaintext = lineToMatrix(filePlaintext);
        String[][] key = lineToMatrix(fileKey);

        
        
    }

    public static void printMatrix(String[][] out){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(out[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String[][] lineToMatrix(String line){
        String[] tmp = line.split(" ");
        String[][] out = new String[4][4];
        for(int i = 0; i < tmp.length; i++){
            out[i/4][i%4] = tmp[i];
        }
        return out;
    }

    public static String readFile(String filename){
        String line = null;
        String out = "";

        try{
            FileReader fr = new FileReader(filename);
            BufferedReader bf = new BufferedReader(fr);

            while((line = bf.readLine()) != null){
                out += line;
            }
            bf.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                filename + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error filename file '" 
                + filename + "'"); 
        }

        return out;
    }

    
    
    
    private final static String[] hexSymbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public final static int BITS_PER_HEX_DIGIT = 4;

    public static String toHexFromByte(final byte b)
    {
        byte leftSymbol = (byte)((b >>> BITS_PER_HEX_DIGIT) & 0x0f);
        byte rightSymbol = (byte)(b & 0x0f);

        return (hexSymbols[leftSymbol] + hexSymbols[rightSymbol]);
    }

    public static String toHexFromBytes(final byte[] bytes)
    {
        if(bytes == null || bytes.length == 0)
        {
            return ("");
        }

        // there are 2 hex digits per byte
        StringBuilder hexBuffer = new StringBuilder(bytes.length * 2);

        // for each byte, convert it to hex and append it to the buffer
        for(int i = 0; i < bytes.length; i++)
        {
            hexBuffer.append(toHexFromByte(bytes[i]));
        }

        return (hexBuffer.toString());
    }

    public static void fileToHex(String inputFile, String outputFile) throws IOException
    {
        try
        {
            FileInputStream fis = new FileInputStream(new File(inputFile));
            BufferedWriter fos = new BufferedWriter(new FileWriter(new File(outputFile)));

            byte[] bytes = new byte[800];
            int value = 0;
            do
            {
                value = fis.read(bytes);
                fos.write(toHexFromBytes(bytes));

            }while(value != -1);

            fos.flush();
            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}