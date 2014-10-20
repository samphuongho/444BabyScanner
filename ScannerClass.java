import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextArea;


public class ScannerClass 
{
    private int currentRead;
    private int state;
    private String tokenUnderConstruction;
    
    ScannerClass()
    {
    	tokenUnderConstruction = "";
        state = 0;
        currentRead = 0; 
    }

    public void readCharacters(String fileName) throws FileNotFoundException, IOException
    {
    	JTextArea outputArea = new JTextArea();
        char currentChar = ' ';
        boolean buffered = false;
        Scanner scan = null;
        File inFile = new File(fileName);
        scan = new Scanner(inFile);
        
        while(scan.hasNext())
        {
            char [] charArr = scan.nextLine().toCharArray();
            for(int i = 0; i < charArr.length; i++)
            {
                if ((!buffered) || currentChar == ' ' || (currentChar == '\n'))
                	currentChar = charArr[i];
            
                System.out.print("Current char = "+currentChar);             
                System.out.print(" Status of EOF = "+scan.hasNext()+"\n");

                if(Character.isLetter(currentChar))
                	currentRead = 0;
                else if(Character.isDigit(currentChar))
                	currentRead = 1;
                else
                {
                    switch(currentChar)
                    {
                            case '_':
                            	currentRead = 2;
                                break;
                            case '.':
                            	currentRead = 3;
                                break;
                            case '=':
                            	currentRead = 4;
                                break;
                            case '+':
                            	currentRead = 5;
                                break;
                            case ';':
                            	currentRead = 6;
                                break;
                            case '*':
                            	currentRead = 7;
                                break;
                            case '(':
                            	currentRead = 8;
                                break;
                            case ')':
                            	currentRead = 9;
                                break;
                            case '\n':
                            	currentRead = 10;
                                break;
                            case ' ':
                            	currentRead = 10;
                                break;
                            default:
                            	currentRead = 10;
                                break;
                    }
                }
                System.out.print("Current state = " + state + " currentChar = " + currentChar + " Token status = " + tokenUnderConstruction +
                        "\n");

                if(nextState(state, currentRead) != -1 && (actionTake(state, currentRead) == 1))
                {
                    buffered = false;
                    tokenUnderConstruction = tokenUnderConstruction + currentChar;
                    state = nextState(state, currentRead);
                }
                else if(nextState(state, currentRead) == -1 && (actionTake(state, currentRead) == 2))
                {
                    System.out.print("Inside switch with state = " + state + " and char " + currentRead + " \nWe have a buffered character = "
                    		+ currentChar + "\n");
                    buffered = true;
                    switch(lookUp(state, currentRead))
                    {
                    case 1:
                    	System.out.print("TOKEN DISCOVERED is IDENTIFIER -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 2:
                    	System.out.print("TOKEN DISCOVERED is INTEGER -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 3:
                    	System.out.print("TOKEN DISCOVERED is ASSIGNMENT -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 4:
                    	System.out.print("TOKEN DISCOVERED is ADDITION -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 5:
                    	System.out.print("TOKEN DISCOVERED is SEMICOLON -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 6:
                    	System.out.print("TOKEN DISCOVERED is MULTIPLICATION -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 7:
                    	System.out.print("TOKEN DISCOVERED is OPEN PARENTHESES -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 8:
                    	System.out.print("TOKEN DISCOVERED is CLOSE PARENTHESES -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 9:
                    	System.out.print("TOKEN DISCOVERED is DOUBLE -> " + 
                                tokenUnderConstruction + "\n");
                        break;  
                    }
                    state = 0;
                    tokenUnderConstruction = "";
                    --i;
                }
            }
	}
        switch(lookUp(state, currentRead))
                    {
                    case 1:
                    	System.out.print("TOKEN DISCOVERED is IDENTIFIER -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 2:
                    	System.out.print("TOKEN DISCOVERED is INTEGER -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 3:
                    	System.out.print("TOKEN DISCOVERED is ASSIGNMENT -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 4:
                    	System.out.print("TOKEN DISCOVERED is ADDITION -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 5:
                    	System.out.print("TOKEN DISCOVERED is SEMICOLON -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 6:
                    	System.out.print("TOKEN DISCOVERED is MULTIPLICATION -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 7:
                    	System.out.print("TOKEN DISCOVERED is OPEN PARENTHESES -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 8:
                    	System.out.print("TOKEN DISCOVERED is CLOSE PARENTHESES -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                    case 9:
                    	System.out.print("TOKEN DISCOVERED is DOUBLE -> " + 
                                tokenUnderConstruction + "\n");
                        break;
                            default:
                                System.out.println("error");
                                break;  
                    }
        
        System.out.print("Done scanning!\n");
        scan.close();
    }
    public static int nextState(int newState, int newChar)
    {
        int stateTable[][]= {  {1, 2, -1, 3, 4, 5, 6, 7, 8, 9, -1},
                                {1, 1, 10, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, 2, -1, 11, -1, -1, -1, -1, -1, -1, -1},
                                {-1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1 -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                                {-1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1} };
        return stateTable[newState][newChar];
    }
    public static int actionTake(int newState, int newChar)
    {
        int actionTable[][]= { {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                                {1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2},
                                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
                                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2} };
        return actionTable[newState][newChar];
    }
    public static int lookUp(int newState, int newChar)
    {
        int lookUpTable[][]= { {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                                 {2, 0, 2, 0, 2, 2, 2, 2, 2, 2, 2},
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                                 {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
                                 {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
                                 {6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
                                 {7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7},
                                 {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                                 {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                 {9, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9}};
        return lookUpTable[newState][newChar];
    }

}
