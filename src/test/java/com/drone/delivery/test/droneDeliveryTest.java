package com.drone.delivery.test;

import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import com.drone.delivery.run.droneDelivery;
import com.drone.delivery.utils.*;
import java.io.FileNotFoundException;
import java.util.List;

public class droneDeliveryTest {


    @Test
    public void testGoodNPS() throws Exception{
        String inputfile = "/Users/p0t00c0/Desktop/workshop/input.txt";
        String[] list = droneDelivery.processCustOrders(inputfile);
        assertEquals("NPS 87",list[list.length-1] );
    }

    @Test
    public void testBadNPS() throws Exception{
        String inputfile = "/Users/p0t00c0/Desktop/workshop/lowNPS.txt";
        String[] list = droneDelivery.processCustOrders(inputfile);
        assertEquals("NPS -22",list[list.length-1] );
    }

@Ignore
    @Test
    public void testZeroNPS() throws Exception{
        String inputfile = "/Users/p0t00c0/Desktop/workshop/zeroLines.txt";
        String[] list = droneDelivery.processCustOrders(inputfile);
        assertEquals("NPS 0",list[list.length-1] );
    }
    @Test
    public void testAllOrdersProcessedsuccessfully() throws Exception{
        String inputfile = "/Users/p0t00c0/Desktop/workshop/input.txt";
        String[] list = droneDelivery.processCustOrders(inputfile);
        assertEquals(5,list.length );
    }

    @Test
    public void validateAllCustOrders() throws Exception{


            String inputfile = "/Users/p0t00c0/Desktop/workshop/input.txt";
            String outputfile = "/Users/p0t00c0/Desktop/workshop/output.txt";
           String[] list = droneDelivery.processCustOrders(inputfile);
            List<String> outlist = fileReader.readFile(outputfile);
            String[] out = outlist.stream().toArray(String[]::new);
            assertEquals(list.length,outlist.size() );

    }

    @Test
    public void testFileNotFoundException() throws Exception{

        try {
            String inputfile = "/Users/p0t00c0/Desktop/workshop/input.txtt";
            List<String> outlist = fileReader.readFile(inputfile);
            fail();
        } catch (FileNotFoundException e) {
            assertThat(e.getMessage(), is("/Users/p0t00c0/Desktop/workshop/input.txtt (No such file or directory)"));
        }
    }

    @Test
    public void testNumberFormatException() throws Exception{

        try {
            String findStartTime = "AA:BB:CC";
            String l = timestampManager.findStartTime(findStartTime,2.0);
            fail();
        } catch (NumberFormatException e) {
            assertThat(e.getMessage(), is("For input string: \"AA\""));
        }
    }

}
