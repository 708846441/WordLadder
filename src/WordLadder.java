import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
/*
extra features:Allow word ladders between words of different length
function:ladder
lines:line152-174

extra features:Allow word ladder end-points to be outside the dictionary
function:ladder
lines:line136-147

how it works:

Create an empty queue of stacks. 
	Create/add a stack containing {w1} to the queue.  
	While the queue is not empty: 
		Dequeue the partial-ladder stack from the front of the queue. 
		For each valid English word that is a "neighbor" (differs by 1 letter) of the word on top
of the stack: 
			If that neighbor word has not already been used in a ladder before: 
				If the neighbor word is w2: 
					Hooray! we have found a solution 
					(and it is the stack you are working on in the queue). 
				Else:
					Create a copy of the current partial ladder stack. 
					Put the neighbor word on top of the copy stack. 
					Add the copy stack to the end of the queue.
*/


public class WordLadder 
{
	private static Stack<String> ladder;
	
	public static void main(String[] args) throws IOException
	   {
			Set<String> dictionary = new HashSet<String>();
			String dictionaryFileName;
			String word;
			while(true)
			{
				System.out.print("Dictionary file name? ");
			    BufferedReader br = new BufferedReader(new
	                    InputStreamReader(System.in));
			    dictionaryFileName=br.readLine();
				File myFile = new File(dictionaryFileName);
				try
				{
					Scanner fin=new Scanner(myFile);
					while ( fin.hasNext())
					{
						word = fin.next();
						dictionary.add(word);
					}
					fin.close();
					break;
				}
				catch(IOException e)
				{
					System.out.println("Unable to open that file.  Try again.");
				}
			}
			while (true)
			{
				char c;
				String w1="";
				String w2="";
				while(true)
				{
					System.out.print("\nWord #1 (or Enter to quit):");
				    BufferedReader br = new BufferedReader(new
		                    InputStreamReader(System.in));
				    c=(char)br.read();
				    w1=String.valueOf(c);
				    c=(char)br.read();
				    w1=w1+c;
				    if(c=='\n')
				    {
				    	System.out.println("Have a nice day.");
				    	break;
				    }
				    w1=w1+br.readLine();
				    w1=w1.toLowerCase();
				    System.out.print("Word #2 (or Enter to quit):");
				    c=(char)br.read();
				    w2=String.valueOf(c);
				    c=(char)br.read();
				    w2=w2+c;
				    if(c=='\n')
				    {
				    	System.out.println("Have a nice day.");
				    	break;
				    }
				    w2=w2+br.readLine();
				    w2=w2.toLowerCase();
				    if(w1.equals(w2))
				    {
				    	System.out.println("The two words must be different.");
				    	continue;
				    }
				    else
				    	break;
				}
				if(c=='\n')
					break;
				ladder(w1, w2, dictionary);
			}
	   }

	public static void ladder(String w1,String w2, Set<String> dictionary)
	//to create a word ladder from w2 back to w1 with the dictionary
	{
		Set<String> used =new HashSet<String>();
		used.add(w1);
		Queue<Stack<String>> q = new LinkedList<Stack<String>>();
		Stack<String> s = new Stack<String>();
		s.push(w1);
		q.offer(s);
		while(!q.isEmpty())
		{
			Stack<String> partial = q.poll();
			//dequeue the partial-ladder stack from the front of the queue
			String presentWord = partial.peek();
			int size = presentWord.length();
			Vector<String> neighbors=new Vector<String>();
			String neighborWord = "";
			for (int i =0;i<size;++i)
			{
				for (char c='a';c<='z';++c)
				{
					neighborWord = presentWord;
					StringBuilder sb = new StringBuilder(neighborWord);
					sb.replace(i, i+1, String.valueOf(c));
					neighborWord = sb.toString();
					//Allow word ladder end-points to be outside the dictionary
					if (neighborWord.equals(w2))
					{
						System.out.print("A ladder from "+w2+" back to "+w1+":\n"+w2);
						ladder=new Stack<String>();
						ladder.addAll(partial);
						while(!partial.isEmpty())
						{
							String word = partial.pop();
							System.out.print(" "+word);
						}
						System.out.print('\n');
						return;
					}
					else if(dictionary.contains(neighborWord)&&(!neighborWord.equals(presentWord)))
						neighbors.add(neighborWord);
				}
			}
			//add
			for(int i =0;i<=size;++i)
			{
				for (char c='a';c<='z';++c)
				{
					String str = String.valueOf(c);
					neighborWord = presentWord;
					StringBuilder sb = new StringBuilder(neighborWord);
					sb.insert(i, str);
					neighborWord=sb.toString();
					if(dictionary.contains(neighborWord)&&(!neighborWord.equals(presentWord)))
						neighbors.add(neighborWord);
					
				}
			}
			//remove
			for(int i =0;i<size;++i)
			{
				neighborWord = presentWord;
				neighborWord = neighborWord.substring(0, i)+neighborWord.substring(i+1);
				if(dictionary.contains(neighborWord)&&(!neighborWord.equals(presentWord)))
					neighbors.add(neighborWord);
			}

			for (String neighbor:neighbors)
			{
				if(!used.contains(neighbor))
				{
					if(neighbor.equals(w2))
					{
						System.out.print("A ladder from "+w2+" back to "+w1+":\n"+w2);
						ladder=new Stack<String>();
						ladder.addAll(partial);
						while(!partial.isEmpty())
						{
							String word = partial.pop();
							System.out.print(" "+word);
						}
						System.out.print("\n");
						return;
					}
					else
					{
						Stack<String> copy = new Stack<String>();
						copy.addAll(partial);
						copy.push(neighbor);
						used.add(neighbor);
						q.offer(copy);
					}
				}
			}
		}
		System.out.println("No word ladder found from " + w2 + " back to " + w1 + "." );
	}
	
	public static Stack<String> getLadder()
	{
		return ladder;
	}
}
