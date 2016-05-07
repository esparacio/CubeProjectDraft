package edu.elon.elena.cubeuser;

/**
 * Created by ElenaSparacio on 4/24/16.
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

    public void setArray(int [][][] threeDarray){
        this.threeDarray = threeDarray;
        //value of a platform (assuming array is created at the start of the game)
        prevSpace = 16776960;
    }

    public void setxValue(int xValue){
        this.xValue = xValue;
    }

    public void setyValue(int yValue){
        this.yValue = yValue;
    }

    public void setzValue(int zValue){
        this.zValue = zValue;
    }

    public int getxValue(){
        return xValue;
    }

    public int getyValue(){
        return yValue;
    }

    public int getzValue(){
        return zValue;
    }

    public int getPrevSpace(){
        return prevSpace;
    }

    public void setPrevSpace(int prevSpace){
        this.prevSpace = prevSpace;
    }

    public void moveUp(){
        int x = this.xValue;
        int y = this.yValue;
        int z = this.zValue;

        //location appears to be working.

        System.out.println("Current location: " + x + y + z);
        if(y<=6) {
            //store the next character location
            int aboveSpace = threeDarray[x][y + 1][z];
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
                System.out.println("New location: " + getxValue() + getyValue() + getzValue());
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

    public void moveDown(){
        int x = this.xValue;
        int y = this.yValue;
        int z = this.zValue;
        System.out.println("Current location: " + x + y + z);

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
                System.out.println("New location: " + getxValue() + getyValue() + getzValue());

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

    public void moveRight(){
        int x = this.xValue;
        int y = this.yValue;
        int z = this.zValue;
        System.out.println("Current location: " + x + y + z);

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
                System.out.println("New location: " + getxValue() + getyValue() + getzValue());


            }
            else{
                System.out.println("You died...");
            }
        }
        else{
            System.out.println("Reached the rightmost");
        }

    }

    public void moveLeft(){
        int x = this.xValue;
        int y = this.yValue;
        int z = this.zValue;
        System.out.println("Current location: " + x + y + z);

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
                System.out.println("New location: " + getxValue() + getyValue() + getzValue());

            }
            else{
                System.out.println("You died...");
            }
        }
        else{
            System.out.println("Reached the leftmost");
        }

    }

    //Teleporter stuff here

    public void teleport(int space, int prevX, int prevY){
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



