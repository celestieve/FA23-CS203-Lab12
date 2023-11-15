import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	String mssg = readFile(inputFilePath);
    	encrypted = encrypt(mssg);
    	writeFile(encrypted, encryptedFilePath);
    }
    
    private String encrypt(String mssg) {
    	StringBuilder encryptedMssg = new StringBuilder();
    	mssg = mssg.toLowerCase();
    	
    	for(int i = 0; i < mssg.length(); i ++) {
    		char c = mssg.charAt(i);
    		if (Character.isLetter(c)) {
    			c = (char) ((c-'a' + shift + 26 )% 26 + 'a');
    			
    		}
    		encryptedMssg.append(c);
    	}
		return encryptedMssg.toString();
	}
    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	String encryptedMssg = readFile(messageFilePath);
    	String decryptedMssg = decrypt(encryptedMssg);
    	writeFile(decryptedMssg, decryptedFilePath);
    }
    private String decrypt(String encryptedMssg) {
		// TODO Auto-generated method stub
    	StringBuilder decryptedMssg = new StringBuilder();
    	encryptedMssg = encryptedMssg.toLowerCase();
    	
    	for(int i = 0; i < encryptedMssg.length(); i ++) {
    		char c = encryptedMssg.charAt(i);
    		if (Character.isLetter(c)) {
    			c = (char) ((c -'a' - shift + 26 )% 26 + 'a');
    			
    		}
    		decryptedMssg.append(c);
    	}
		return decryptedMssg.toString();
	}
    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
    	StringBuilder message = new StringBuilder();
        //TODO: Read file from filePath
    	try (Scanner input = new Scanner(Paths.get(filePath))) {
        	while (input.hasNextLine()) {
        		String line = input.nextLine();
        		message.append(line).append("\n");
        	}
        	input.close();
        }catch(Exception e) {
        	System.out.println("error: " + e.toString());
        	throw e;
        }
		return message.toString();
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
    		writer.write(data);
    		System.out.println("encrypted data written to: " + filePath);
    	}catch (IOException e) {
    		System.out.println("error" + filePath);
    		e.printStackTrace();
    		
    	}
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
