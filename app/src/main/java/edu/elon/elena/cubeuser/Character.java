package edu.elon.elena.cubeuser;

/**
 * Elena Sparacio (c) 2016
 *
 * Character is a class that allows the user to move the character
 * through the 3Darray that represents the background on the cube.
 *
 */
public class Character {

    private A_L3D l3d;
    public int xValue;
    public int yValue;
    public int zValue;
    public int[][][] threeDarray;
    public int[][][] array2;
    public int prevSpace;
    public final int COL = 16711680;


    public Character(A_L3D aL3d){
        this.l3d = aL3d;

    }
    public Character(int xValue, int yValue, int zValue, int[][][] threeDArray) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.zValue = zValue;
        this.threeDarray = threeDArray;
    }

    //sets an array at the beginning of the level. Sets prevSpace (the previous space)
    //to equal the color of the cube (currently red, will change).
    public void setArray(int [][][] threeDarray){
        this.threeDarray = threeDarray;
        //value of a platform (assuming array is created at the start of the game)
        prevSpace = 16776960;
    }

    //sets the x value of the character in the array
    public void setxValue(int xValue){
        this.xValue = xValue;
    }

    //sets the y value of the character in the array
    public void setyValue(int yValue){
        this.yValue = yValue;
    }

    //sets the z value of the character in the array
    public void setzValue(int zValue){
        this.zValue = zValue;
    }

    //gets the x value of the character in the array
    public int getxValue(){
        return xValue;
    }

    //gets the y value of the character in the array
    public int getyValue(){
        return yValue;
    }

    //gets the z value of the character in the array
    public int getzValue(){
        return zValue;
    }

    //gets the value in the previous space, or the space the
    //character was in before moving - returns a color
    public int getPrevSpace(){ return prevSpace; }

    //sets the value in the previous space
    public void setPrevSpace(int prevSpace){
        this.prevSpace = prevSpace;
    }


    //allows the user to move up in the game. Updates all the values of the
    //character's location, and previous space. Accounts for teleporting
    //as well.
    public void moveUp(){

        //gets current values
        int x = this.xValue;
        int y = this.yValue;
        int z = this.zValue;

        //Allows movement as long as it is within the constraints of the
        //cube
        if(y<=6) {

            //store the next character location
            int aboveSpace = threeDarray[x][y + 1][z];

            //only moves if the aboveSpace isn't air
            if(!(aboveSpace==0||aboveSpace==255)) {
                //move character in array
                threeDarray[x][y + 1][z] = COL;
                //fill character's previous location with the old value
                threeDarray[x][y][z] = prevSpace;
                //change character color and former space
                l3d.setVoxel(x, (y + 1), z, COL);
                l3d.setVoxel(x, y, z, prevSpace);

                //store the current location of the character
                setPrevSpace(aboveSpace);
                //reset character location
                setyValue(y + 1);
                l3d.update();
                if(aboveSpace==5688359||aboveSpace==5688356){
                    teleport(aboveSpace,getxValue(),getyValue());
                }

            }
            else{
                System.out.println("You died...");
            }

        }
        else{
            System.out.println("Reached the top");
        }

    }

    //allows the user to move down in the game. Updates all the values of the
    //character's location, and previous space. Accounts for teleporting
    //as well.
    public void moveDown(){

        //gets the location
        int x = this.xValue;
        int y = this.yValue;
        int z = this.zValue;

        //Allows movement as long as within constraints of the cube
        if(y>=1) {
            //store the next character location
            int belowSpace = threeDarray[x][y - 1][z];
            if(!(belowSpace==0||belowSpace==255)) {
                //move character in array
                threeDarray[x][y - 1][z] = COL;
                //fill character's previous location with the old value
                threeDarray[x][y][z] = prevSpace;
                //change character color and former space
                l3d.setVoxel(x, (y - 1), z, COL);
                l3d.setVoxel(x, y, z, prevSpace);
                //store the current location of the character
                setPrevSpace(belowSpace);
                //reset character location
                setyValue(y - 1);
                l3d.update();

            }
            else{
                System.out.println("You died...");
            }
        }
        else{
            System.out.println("Reached the bottom");
        }

    }

    //allows the user to move right in the game. Updates all the values of the
    //character's location, and previous space. Accounts for teleporting
    //as well.
    public void moveRight(){

        //gets location
        int x = this.xValue;
        int y = this.yValue;
        int z = this.zValue;

        //allows user to move only within constraints of the cube
        if(x<=6) {
            //store the next character location
            int rightSpace = threeDarray[x+1][y][z];
            if(!(rightSpace==0||rightSpace==255)) {
                //move character in array
                threeDarray[x + 1][y][z] = COL;
                //fill character's previous location with the old value
                threeDarray[x][y][z] = prevSpace;
                //change character color and former space
                l3d.setVoxel((x + 1), y, z, COL);
                l3d.setVoxel(x, y, z, prevSpace);
                //store the current location of the character
                setPrevSpace(rightSpace);
                //reset character location
                setxValue(x + 1);
                l3d.update();


            }
            else{
                System.out.println("You died...");
            }
        }
        else{
            System.out.println("Reached the rightmost");
        }

    }

    //allows the user to move left in the game. Updates all the values of the
    //character's location, and previous space. Accounts for teleporting
    //as well.
    public void moveLeft(){
        //gets current location
        int x = this.xValue;
        int y = this.yValue;
        int z = this.zValue;

        //allows users to move only in range of cube
        if(x>=1) {
            //store the next character location
            int leftSpace = threeDarray[x-1][y][z];

            if(!(leftSpace==0||leftSpace==255)) {
                //move character in array
                threeDarray[x - 1][y][z] = COL;
                //fill character's previous location with the old value
                threeDarray[x][y][z] = prevSpace;
                //change character color and former space
                l3d.setVoxel((x - 1), y, z, COL);
                l3d.setVoxel(x, y, z, prevSpace);
                //store the current location of the character
                setPrevSpace(leftSpace);
                //reset character location
                setxValue(x - 1);
                l3d.update();
            }
            else{
                System.out.println("You died...");
            }
        }
        else{
            System.out.println("Reached the leftmost");
        }

    }


    //Teleporters the user if they land on a teleporter spcae

    public void teleport(int space, int prevX, int prevY){

        //this method is long and complicated, having variables for teleporter
        //locations may be better - but this works for now. It searches through
        //the layer (only in 2 dimensions, as teleporters don't work through layers)
        //and finds the corresponding teleport landing location. It then moves
        //the user there.

        for(int e=0;e<8;e++) {
            for (int j = 0; j < 8; j++) {
                int output = threeDarray[e][j][getzValue()];
                if(space==5688359&&output==5688358) {
                    setxValue(e);
                    setyValue(j);
                    setPrevSpace(space);
                    threeDarray[prevX][prevY][getzValue()] = prevSpace;
                    l3d.setVoxel(e, j, getzValue(), COL);
                    l3d.setVoxel(prevX, prevY, getzValue(), space);
                    l3d.update();
                }
                else if(space==5688356&&output==5688357){
                    setxValue(e);
                    setyValue(j);
                    setPrevSpace(space);
                    threeDarray[prevX][prevY][getzValue()] = prevSpace;
                    l3d.setVoxel(e, j, getzValue(), COL);
                    l3d.setVoxel(prevX, prevY, getzValue(), space);
                    l3d.update();
                }
                }
            }

    }



    //allows the user to move through a layer in the game. Updates all the values of the
    //character's location, and previous space.

    //Currently unfinished
    public void scrollUp(){

        array2 = new int[8][8][8];
        for(int e=0;e<8;e++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {

                    int output = threeDarray[e][j][k];
                    if(!(k==7)) {
                        array2[e][j][k + 1] = output;
                        }
                    if(k==7){
                        //instead of 5, it would be 8-num of layers)
                        array2[e][j][5] = output;
                    }
                    }
                }
            }

    for(int e=0;e<8;e++) {
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 8; k++) {
                int output = array2[e][j][k];
                l3d.setVoxel(e, j, k, output);
            }
        }
    }
        //make character stay where he is, fill the new space with a platform
        l3d.setVoxel(getxValue(),getyValue(),getzValue(),COL);
        //fill in space...
        l3d.setVoxel(getxValue(),getyValue(),(getzValue()-2),getPrevSpace());
        l3d.update();
        threeDarray = array2;



        System.out.println("Where is char: " + getxValue() + getyValue() + getzValue());

}



}



