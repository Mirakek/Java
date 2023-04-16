import org.junit.*;
import static org.junit.Assert.*;

import java.beans.Transient;
import java.time.DateTimeException;

import javax.xml.crypto.Data;

import org.junit.After;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.io.BufferedReader;
import java.io.FileReader;

public class DataTester{
    @Test
    public void testItemID(){
        assertTrue(DataEntry.itemID("RRL00123"));   
        assertFalse(DataEntry.itemID("ABCA0012"));
        assertFalse(DataEntry.itemID("1AB80005"));
        assertTrue(DataEntry.itemID("AAA12345"));
        assertFalse(DataEntry.itemID("1A"));

    }

    @Test
    public void testName(){
        assertTrue(DataEntry.name("Battery"));   
        assertTrue(DataEntry.name("1"));   
        assertTrue(DataEntry.name("okay28"));   
        assertTrue(DataEntry.name("AHHHHHHHHHHHHHHHHHHHHHHH BRUHHHHHHHHHHHHHHHHHH"));
        assertFalse(DataEntry.name(""));
        assertFalse(DataEntry.name("BRUHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH"));
    }

    @Test
    public void testBrand(){
        assertTrue(DataEntry.brand("Power Face"));
        assertFalse(DataEntry.brand(""));
        assertTrue(DataEntry.brand("AHHHHHHHHHHHHHHHHHHHHHHHHH BRUHHHHHHHHHHHHHHHHHHH"));
        assertFalse(DataEntry.brand("How are you?"));
    }

    @Test
    public void testSKUNumber(){
        assertTrue(DataEntry.skuNumber("KJ987URU"));
        assertFalse(DataEntry.skuNumber("123456789"));
        assertFalse(DataEntry.skuNumber("1234567"));
        assertTrue(DataEntry.skuNumber("ABCDEFGH"));
        assertTrue(DataEntry.skuNumber("12345678"));
    }

    @Test
    public void testUPCNumber(){
        assertTrue(DataEntry.upcNumber("0 563489 562800"));
        assertFalse(DataEntry.upcNumber("1 A1121212 12121212 "));
        assertFalse(DataEntry.upcNumber("1"));
        assertTrue(DataEntry.upcNumber("1 163411 562100"));
    }
    
    @Test
    public void testCost(){
        assertTrue(DataEntry.cost("$156.20"));
        assertFalse(DataEntry.cost("15912123.55"));
        assertFalse(DataEntry.cost("156.20"));
    }

    @Test
    public void testSellPrice(){
        assertTrue(DataEntry.sellPrice("$8908.90"));
        assertTrue(DataEntry.sellPrice("$1.90"));
        assertFalse(DataEntry.sellPrice("$1"));
        assertTrue(DataEntry.sellPrice("$1000000000000000000000000000000.90"));
        assertFalse(DataEntry.sellPrice("$2.000"));
    }
    
    @Test
    public void testVerifyData(){
        assertTrue(DataEntry.verifyData("RRL00123,Battery,Power Face,KJ987URU,0 563489 562800,$156.20,$8980.90"));
        assertTrue(DataEntry.verifyData("KKD00421,Ultra Epic Dinosaur 12345,Uber Mega 159,KJ987URU,1 444489 562800,$15613123.20,$8980121312.90"));
        assertFalse(DataEntry.verifyData("KD00421,Ultra Epic Dinosaur 12345,Uber Mega 159,KJ987URU,1 444489 562800,$15613123.20,$8980121312.90"));
        assertFalse(DataEntry.verifyData(",,,,"));
    }

    @Test
    public void testWriteFile(){
        DataEntry.writeToFile("test.txt", "AHHHHH");
        assertTrue(readToFile("test.txt").equals("AHHHHH"));
    }

    public static String readToFile(String fileName){
        BufferedReader reader = null;
        try{
            String path = Paths.get(fileName).toString();
            String line;

            reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine()) != null){
                return line;
                
            }
            
           
            reader.close();
        }   catch(IOException | NoSuchElementException | IllegalStateException e){
            e.printStackTrace();
        }
        
        return "";
    }
}