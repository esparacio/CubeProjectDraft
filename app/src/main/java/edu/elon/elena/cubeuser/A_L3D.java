package edu.elon.elena.cubeuser;


import android.os.AsyncTask;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Joel Hollingsworth (c) 2016
 *
 * A_L3D is a class that connects the cube with the phone, and
 * has methods to update the cube.
 *
 */
public class A_L3D {

    protected final int SIDE = 8;

    private String ipAddress;
    private InetAddress cubeAddress = null;
    private DatagramSocket socket;
    protected int[][][] cube;

    public A_L3D() {

        // create the cube model
        cube = new int[SIDE][SIDE][SIDE];

        ipAddress = "192.168.1.138";
        try {
            cubeAddress = InetAddress.getByName(ipAddress);
            socket = new DatagramSocket();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        new SendDatagram().execute();
    }

    public void setVoxel(int x, int y, int z, int col) {
        if ((x >= 0) && (x < SIDE))
            if ((y >= 0) && (y < SIDE))
                if ((z >= 0) && (z < SIDE))
                    cube[x][y][z] = col;
    }

    public void setVoxel(double x, double y, double z, int col) {
        setVoxel((int) x, (int) y, (int) z, col);
    }

    public void setCube(int [][][] cube) {
        this.cube = cube;
    }

    public byte[] serializeCube(int[][][] cube)
    {
        int index=0;
        byte[] data=new byte[cube[0][0].length*cube[0].length*cube.length];
        for (int z=0; z<cube[0][0].length; z++)
            for (int y=0; y<cube[0].length; y++)
                for (int x=0; x<cube.length; x++)
                {
                    index=z*cube.length*cube[0].length+y*cube.length+x;
                    data[index]=colorByte(cube[x][y][z]);
                }
        return data;
    }

    public byte colorByte(int col)
    {
        return (byte)(red(col)&224 | (green(col)&224)>>3 | (blue(col)&192)>>6);
    }

    public int red(int col)
    {
        return((col>>16)&255);
    }

    public int green(int col)
    {
        return((col>>8)&255);
    }

    public int blue(int col)
    {
        return(col&255);
    }

    private class SendDatagram extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            // prepare the data for sending
            byte [] data = serializeCube(cube);

            // send the data to the cube
            try {
                DatagramPacket p = new DatagramPacket(data, data.length, cubeAddress, 2222);
                socket.send(p);
                System.out.println("Send datagram to " + ipAddress + ":" + 2222);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
