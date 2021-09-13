import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
//Your life is the sum of a remainder of an unbalanced equation inherent to the programming
//of the matrix

import model.Player;

public class TheArchitect extends JFrame {
    private Player player;
    int foundPlayer=0;
    String[][] updatedMatrix;
    int WallXCord;
    int WallYCord;
    int collected=0;
    boolean level;
    int globalTotalDimonds=0;

    public void setExit(int x, int y)//records the location of the exit so we can show it when its time
    {
        WallXCord=x;
        WallYCord=y;
    } 

    public void movePlayerToTile(String[][] currentMatrix, int xScale, int yScale){
        currentMatrix[this.player.getXPosition()][this.player.getYPosition()] = "N";
        currentMatrix[this.player.getXPosition()+xScale][this.player.getYPosition()+yScale] = "P";
    }

    public void showWall()//used when its time to show the exit.  
    {
        updatedMatrix[WallXCord][WallYCord]="E";  
    }

    public void playerMove(int xScale, int yScale, String[][] currentMatrix,int totalDimonds)throws StupidAssMove
    {
        nextLevel(false); //dont go to the next level yet.
        for (int i = 0; i < currentMatrix.length; i++) {
            for (int j = 0; j < currentMatrix[i].length; j++){
                if(currentMatrix[i][j].equals("P")){
                        this.player = new Player(i, j);
                        foundPlayer = 1;
                        break;
                }
            }
        }//end both for loops
            
        String nextPlayerPosition = currentMatrix[this.player.getXPosition()+xScale][this.player.getYPosition()+yScale];
        switch (nextPlayerPosition) {
            case "H": // Next move is a hidden diamond
                movePlayerToTile(currentMatrix, xScale, yScale);
                collected+=1;
                break;
            case "D": // Next move is a Diamond
                movePlayerToTile(currentMatrix, xScale, yScale);
                collected+=1;
                break;
            case "M": // Next move is a Move Block
                if (currentMatrix[player.getXPosition()+(xScale*2)][player.getYPosition()+(yScale*2)].equals("N")){
                    movePlayerToTile(currentMatrix, xScale, yScale);
                    currentMatrix[player.getXPosition()+xScale*2][player.getYPosition()+yScale*2] = "M";
                }
                break;
            case "N": // Next move is Nothing
                movePlayerToTile(currentMatrix, xScale, yScale);
                break;
            case "E": // Next move is Exit
                movePlayerToTile(currentMatrix, xScale, yScale);
                nextLevel(true);
                break;            
            default: // Next move is blocked or is invalid value
                throw new StupidAssMove("Ass Hole hit wall!"); 
        }
                
            if(collected==totalDimonds)//if we have all the dimonds give the player the exit
                showWall();
               
            updatedMatrix=currentMatrix;  //we will return updatedMatrix for the gui                     
        }//end method

    public void nextLevel(boolean tOrF)//true we go to next level, false we update current level's gui 
    {
        level=tOrF;
    }
    
    public boolean getLevel()//returs level true or false
    {
        return level;
    }
        
    public int getDimondsLeft()
    {
        return globalTotalDimonds-collected;//for GUI JLabel, show how many dimonds are left to be collected
    }
    
    public String[][] getUpdatedMatrix()//returns the updated matrix for the gui to display
    {
        return updatedMatrix;    
    }
    
    private class StupidAssMove extends RuntimeException
    {
         public StupidAssMove(String event)
         {
             JFrame frame = new JFrame("Warning");
             JOptionPane.showMessageDialog(frame, "You Stupid Ass, Ran into something did you?");
         }
    }
}