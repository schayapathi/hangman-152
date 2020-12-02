import java.util.*;
import java.awt.*;
import javax.swing.*;
/**
* This class serves as the model, or data structure, that updates all the user's views. 
**/
public class Model extends Observable
{
	private String word;
	private String inProgress;
	public int guesses;

	public Model (String word)
	{
		this.word = word.toUpperCase();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < word.length(); i++)
		{
			sb.append("_");
		}
		this.inProgress = sb.toString();
		guesses = 6;
	}

	public int getLength()
	{
		return word.length();
	}

	public String getInProgress()
	{
		return inProgress;
	}

	public String getWord()
	{
		return word;
	}
	public void incorrectGuesses()
	{
		guesses--;
		setChanged();
		notifyObservers();
	}

	//Check whether letters can be revealed based on the player's guess
	public void checkGuess(String s)
	{
		int count = 0;
		word = word.toUpperCase();
		for(int i = 0; i < word.length(); i++)
		{
			if((word.charAt(i) + "").equals(s))
			{
				count++;
				inProgress = inProgress.substring(0, i) + word.charAt(i) + inProgress.substring(i+1, word.length());
				System.out.println("in progress: " + inProgress);
			}
		}
		System.out.println("matches: " + count);
		if(count == 0) {
			incorrectGuesses();
		}
		setChanged();
		notifyObservers();
	}
	public boolean wordIsComplete(String guess) {
		System.out.println("checking: " + guess + " and " + word);
		guess = guess.toUpperCase();
		if(guess.equals(word))
			return true;
		else 
			return false;
	}
}