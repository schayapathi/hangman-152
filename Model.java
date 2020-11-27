import java.util.*;
import java.awt.*;
import javax.swing.*;
/**
* This class serves as the model, or data structure, that updates all the user's views. 
**/
public class Model extends Observable
{
	private String word;

	public Model (String word)
	{
		this.word = word;
	}

	public int getLength()
	{
		return word.length();
	}

}