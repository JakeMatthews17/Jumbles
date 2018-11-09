import java.util.*;
import java.io.*;

public class Jumbles
{
	public static void main (String[] args) throws Exception
	{
		ArrayList<String> pairs = new ArrayList<String>();
		BufferedReader dFile = new BufferedReader(new FileReader(args[0]));

		while (dFile.ready())
		{
			String dWord = dFile.readLine();
			pairs.add(toCanonical(dWord)+" "+dWord);
		}
		Collections.sort(pairs);

		ArrayList<String> dCanons = new ArrayList<String>();
		ArrayList<String> dWords = new ArrayList<String>();
		for (String line : pairs) 
		{
			String[] pair = line.split("\\s+");
			dCanons.add(pair[0]);
			dWords.add(pair[1]);
		}
		
		BufferedReader jFile = new BufferedReader(new FileReader(args[1]));
		ArrayList<String> jWords = new ArrayList<String>();

		while (jFile.ready())
		{
			jWords.add(jFile.readLine());
		}
		Collections.sort(jWords);
		
		for(String line : jWords)
		{

			String jWord = line;

			System.out.print(jWord+" ");
			String jCanon = toCanonical(jWord);
			int index = Collections.binarySearch(dCanons, jCanon);
			if (index < 0) {
			}
			else{
				while (index>=0 && jCanon.equals(dCanons.get(index)))
				{
					index--;
				}
				index++;
				while(index<dCanons.size() && jCanon.equals(dCanons.get(index)))
				{
					System.out.print(dWords.get(index)+" ");
					index++;
				}
			}	
			System.out.print("\n");
		}	
	}
	static String toCanonical(String word)
	{
		char[] letters = word.toCharArray();
		Arrays.sort(letters);
		return new String (letters);
	}
}