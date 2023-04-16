import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.io.BufferedReader;
import java.io.FileReader;


public class DataEntry{
    public static void main(String[] args){
    
        boolean repeat = true;

        Scanner input = new Scanner(System.in);

        System.out.println("1. Enter Data");
        System.out.println("2. Read-in file");
        System.out.println("3. Exit");

        int option = input.nextInt();

        if(option == 1){

            System.out.println("Filename: ");
            String file = input.next();
            input.nextLine();
            
            System.out.println("Enter a valid values delimited by commas for the following: ");
            System.out.println("Item ID, Name, Brand, SKU, UPC, Cost, and Sell Price");
            System.out.println("Example: RRL00123,Battery,Power Face,KJ987URU,0 563489 562800,$156.20,$8980.90");
            
            while(repeat){

                String string = input.nextLine();
                

                

                if(verifyData(string) == false){
                
                    
                    
                    //AKA0123,Battery@,Power Face,KJ987URU,0 563489 562800,$156.20,$8980.90
                }
                

                else if (verifyData(string) == true){
                    System.out.println("Would you like to add another entry? [Y/N]");
                    String selection = input.next().toUpperCase();
                    input.nextLine();
                    
                    writeToFile(file, string+"\n");
                    if(selection.equals("Y")){
                        repeat = true;
                    }
                    else if(selection.equals("N")){
                        repeat = false; 
                    }
                    
                    else{
                        System.out.println("Invalid input.");
                        repeat = false;
                    }

                }


            }
    
        }



        if(option == 2){
            System.out.println("Enter the file to read in: ");
            String file = input.next();
            readToFile(file);


        }


        if(option == 3){
            System.out.println("Later");
        }

    }


    public static boolean verifyData(String data){
        int count = data.length() - data.replace(",", "").length();
        if(count != 6){
            System.out.println("There should be seven entries");
            return false;
        }
        boolean verify = false;
        String[] split = data.split(",");
        StringBuilder result = new StringBuilder();

        String ID = split[0];
        String name = split[1];
        String brand = split[2];
        String skuNumber = split[3];
        String upcNumber = split[4];
        String cost = split[5];
        String price = split[6];
        
        boolean check1 = itemID(ID);
        if(check1 == false){
            System.out.println("Item ID must be 3 capital letters followed by 5 numbers");
        }
        
        boolean check2 = name(name);
        if(check2 == false){
            System.out.println("Name must be string of letters and decimals 1-50 characters");
        }

        boolean check3 = brand(brand);
        if(check3 == false){
            System.out.println("Brand must be string of letters and decimals 1-50 characters");
        }

        boolean check4 = skuNumber(skuNumber);
        if(check4 ==  false){
            System.out.println("SKU Number must be 8 capital letters and or numbers");
        }

        boolean check5 = upcNumber(upcNumber);
        if(check5 == false){
            System.out.println("UPC number must be 1 digit, space, 6 digits, space, 6 digits");
        }

        boolean check6 = cost(cost);
        if(check6 == false){
            System.out.println("Cost must be money format with 2 decimals");
        }

        boolean check7 = sellPrice(price);
        if(check7 == false){
            System.out.println("Sell price must be money format with 2 decimals");
            
        }
        
        if(check1 == true && check2 == true & check3 == true && check4 == true && check5 == true && check6 == true && check7 == true){
            verify = true;
        }

        if(verify == false){
            return false;
        }


        for(int i = 0; i < 7; i++){
            result.append(split[i]);
            if(i != 6){
                result.append(",");
            }
        }
        

        return result.toString().equals(String.format("%s,%s,%s,%s,%s,%s,%s", ID, name, brand, skuNumber, upcNumber, cost, price));
    }



    public static boolean itemID(String ID){
        return ID.matches("[a-zA-z]{3}\\d{5}");

    }
    
    public static boolean name(String name){
        return name.matches("[a-zA-Z0-9 ]{1,50}");
    }
    
    public static boolean brand(String brand){
        return brand.matches("[a-zA-Z0-9 ]{1,50}");
    }

    public static boolean skuNumber(String sku){
        return sku.matches("[a-zA-Z0-9]{8}");
    }

    public static boolean upcNumber(String upc){
        return upc.matches("\\d\\s\\d{6}\\s\\d{6}");
    }

    public static boolean cost(String cost){
        return cost.matches("[$]\\d+[.]\\d{2}");
    }

    public static boolean sellPrice(String price){
        return price.matches("[$]\\d+[.]\\d{2}");
    }

    public static void writeToFile(String fileName, String data){

        try{

            String path = Paths.get(fileName).toString();
            File file = new File(path);

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getName(), true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(data);
            bw.close();
        }   catch(IOException | NoSuchElementException | IllegalStateException e){
            e.printStackTrace();
        }
        
    }


    public static void readToFile(String fileName){
        BufferedReader reader = null;
        try{
            String path = Paths.get(fileName).toString();
            String line;

            reader = new BufferedReader(new FileReader(path));
            while((line = reader.readLine()) != null){
                if(verifyData(line) == false){
                    System.out.println("\nIncorrect line is below: ");
                    System.out.println(line);
                    
                }
                else{
                    System.out.println(line);
                }
            }
            
           
            reader.close();
        }   catch(IOException | NoSuchElementException | IllegalStateException e){
            e.printStackTrace();
        }
        
    }
  

}