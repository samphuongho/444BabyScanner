import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends ScannerClass
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		ScannerClass l = new ScannerClass();
		l.readCharacters("source.txt");
	}
}
