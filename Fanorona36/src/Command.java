import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Command {
	

    //State Variables

    /** The type of Command */
    protected String type;

    /** The content of the Command */
    protected String content;

    /** The delimiter for parts of a command.*/
    static final String fieldTerminator = "\001";

    /** The maximum number of bytes read at a time. */
    static final int MAXBYTES = 8;
    
    //Constructors

    /**  Creates an instance of Command from two String args.
	 @param t The type of the Command.
	 @param c the content of the Command.*/
    
	public Command(String t, String c) {
		type = t.trim();
		content = c.trim();
	}

	public Command(InputStream inStream) {
		byte [] buff = new byte[4096];
		try {
		    inStream.read(buff);
		    String command = new String(buff);
		    StringTokenizer sToken = new StringTokenizer(command, fieldTerminator);
		    type = sToken.nextToken();
		    content = sToken.nextToken();
		    //System.err.println("Constructor 2 Message: " + type + content);
		}
		catch (NoSuchElementException e) {;}
		catch (IOException e) {System.err.println("Message error in inStream: " + e.getMessage());}
	}


    /** Returns a String of the type of the Message.
	@return The String of the type of the Message.*/
    public String getType() {
	return type;
    }
    
    /** Returns a String with the content of the Message.
	@return The String of the content of the Message.*/
    public String getContent() {
	return content;
    }

    /** Write the message to an OutputStream
	@param outStream The OutputStream we are writing to.*/
	public void send(OutputStream outStream) {
		try {
		    outStream.write(getBytes());
		    }
		catch (IOException e) {System.err.println("Message error in write to outStream: " + e.getMessage());}
		    
	}
	
	/** Return the message as a String.
	@return The String of the message.*/
	public String toString() {
			String s = "[type = ";
			s += type;
			s += ", content = ";
			s += content;
			s += "]\n";
			return s;
	}
	
    /** Returns a byte array of the Command
	@return The byte array of the Command */
    public byte[] getBytes(){
        String s = type + fieldTerminator + content + fieldTerminator;
	return s.getBytes();
    }

}
