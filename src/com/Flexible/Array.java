/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Flexible;

/**
 *
 * @author breed
 */

import java.util.Scanner;
import java.io.FileWriter; 
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Array {
    private Pair[] Item;
    
    // to start new array session
    public void setArray(){
        this.Item = new Pair[0];
    }
    
    public void setAsRootArray(){
        this.Item = new Pair[0];
        initialize();
    }
    
    public void add(Pair item){
        String key = item.getKey();
        
        boolean doExist = exist(key);
        if(doExist == true){
            setDataOf(item);
            return;
        }
        
        addCell();
        int size = Item.length;
        Item[size - 1] = item;
    }

    // array utility functions
    private void addCell(){
        int size = Item.length;
        Pair[] temp = new Pair[size + 1];
        
        System.arraycopy(Item, 0, temp, 0, Item.length);
        
        Item = new Pair[size + 1];
        Item = temp.clone();
    }

    public int size(){
        return Item.length;
    }
    
    public void clear(){
        setArray();
    }
    
    private Pair suGetPair(int pos){
        for(int x = 0; x < Item.length; x++){
            Pair pair = Item[x];
            if(x == pos){
                return pair;
            }
        }
        return null;
    }

    //set data of specific key in the collection
    private void setDataOf(Pair pair){
        int size = Item.length;
        String key = pair.getKey();
        
        int z = 0;
        Pair[] temp = Item;
        
        for(int x = 0; x < size; x++){
            Pair item = temp[x];
            String tempKey = item.getKey();
            
            if(tempKey.equals(key)){
                z = x;
                continue;
            }
            Item[x] = item;
        }
        Item[z] = pair;
    }

    // check if item is new to the collection
    private boolean exist(String key){
        int size = size();

        if(size == 0){
            return false;
        }
        
        for (Pair temp : Item) {
            String ref = temp.getKey();
            
            if(ref.equals(key)){
                return true;
            }
        }

        return false;
    }
    
    public void printAll(){
        if(size() == 0){
            System.out.println("No Items found!");
        }
        
        for(Pair temp: Item){
            temp.print();
        }
    }
    
    //array functions
    public void migrate(Pair pair){
        //check if the item is new to the collection
        String key = pair.getKey();
        String type = pair.getType();

        boolean doExist = exist(key);
        if(doExist == true){ // if item already exist
            int s = size();
            String pairKey = pair.getKey();

            for(int x = 0; x < s; x++){
                Pair temp = Item[x];
                String currKey = temp.getKey();
                
                if(currKey.equals(pairKey)){
                    Item[x] = pair;
                }
            }
            return;
        }
 
        // if item is new to the collection
        addCell();
        int size = size();
        Item[size - 1] = pair;
    }
    
    //outside class array functions
    public Pair[] getArray(){
        return Item;
    }
    
    // return specific pair data
    public Pair getPair(String key){
        for(int x = 0; x < Item.length; x++){
            Pair temp = Item[x];
            String tempKey = temp.getKey();
            
            if(key.equals(tempKey)){
                return temp;
            }
        }

        return null;
    }
    
    //multidimentional array functions
    public void extend(String title, Array arr){

        Pair pair = new Pair(true);
        pair.setPairKey(title);
        
        //anonymous array access
        int size = arr.size();
        int i = 0;
        while(i < size){
            String count = Integer.toString(i);
            Pair temp = arr.suGetPair(i);
            Pair[] temp1 = (Pair[]) temp.getValue();
            
            pair.add(count, temp1);
            i++;
        }
        
        pair.add("data", i);
        add(pair);
    }
    
    //exporting data to file
    public void export(Pair[] pairArray){
        String container = "";
        int x = 0;
        
        for(Pair item: pairArray){
            String itemKey = item.getKey();
            String itemType = item.getType();

            if(itemType.equals("String")){
                String value = item.getValue("");
                container = container + itemKey + ":" + value + " " + itemType + " " + x + "\n";
            }
            if(itemType.equals("Integer")){
                int tempValue = item.getValue(1);
                String value = Integer.toString(tempValue);
                container = container + itemKey + ":" + value + " " + itemType + " " + x + "\n";
            }
            if(itemType.equals("Double")){
                double tempValue = item.getValue(1.00);
                String value = Double.toString(tempValue);
                container = container + itemKey + ":" + value + " " + itemType + " " + x + "\n";
            }
            if(itemType.equals("Pair[]")){
                String key = Integer.toString(x);
                Pair[] value = (Pair[]) item.getValue();
                export(key, value);
                String contain = key + "-" + itemKey + ":" + key + "@" + itemType + "\n"; //0-lower:1.0@double
                container = container + contain + "\n";
            }
            
            x++;
        }
        
        recordData(container);

    }
    private void export(String key, Pair[] items){
        String container = "";
        int x = 0;
        
        for(Pair item: items){
            String itemKey = item.getKey();
            String itemType = item.getType();
            
            if(itemType.equals("String")){
                String value = item.getValue("");
                String contain = key + "-" + itemKey + ":" + value + "@" + itemType + "\n"; //0-lower:hi@String
                toFile(contain);
            }
            if(itemType.equals("Integer")){
                int tempValue = item.getValue(1);
                String value = Integer.toString(tempValue);
                String contain = key + "-" + itemKey + ":" + value + "@" + itemType + "\n"; //0-lower:1@Integer
                toFile(contain); 
            }
            if(itemType.equals("Double")){
                double tempValue = item.getValue(1.00);
                String value = Double.toString(tempValue);
                String contain = key + "-" + itemKey + ":" + value + "@" + itemType + "\n"; //0-lower:1.0@double
                toFile(contain); 
            }
            if(itemType.equals("Pair[]")){
                Pair[] value = (Pair[]) item.getValue();
                String constKey = Integer.toString(x);
                export(constKey, value);
                String contain = key + "-" + itemKey + ":" + constKey + "@" + itemType + "\n"; //0-lower:1.0@double
                //save the id to file
                toFile(contain);
            }
            
            x++;
        }
        
    }
    
    //function to save each individual data to its destination
    private void toFile(String data){
        String container = "";
        String path = "";
        
        if(data.contains("String")){
            path = "./database/String.fragment";
        }
        if(data.contains("Integer")){
            path = "./database/Integer.fragment";
        }
        if(data.contains("Double")){
            path = "./database/Double.fragment";
        }
        if(data.contains("Pair[]")){
            path = "./database/PairArray.fragment";
        }
        
        //read the path
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            String line = "";
            
            try {
                while ((line = br.readLine()) != null) {
                    container = container + line + "\n";
                }
            }catch(IOException e){   }
            
            br.close();
        }catch(IOException e){    System.out.println("File Not Found!");    }
        
        //append
        container += data;
        
        //save
        
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(container);
            writer.close();

        }catch(IOException e){  }
    }
    
    //check if the array is deep
    private boolean dig(Pair[] items){
        
        for(Pair item: items){
            
        }
        
        return false;
    }
    
    //importing data to file
    
    //record to assest
    private void recordData(String toRecord){
        try{
            FileWriter writer = new FileWriter("asset.root");
            writer.write(toRecord);
            writer.close();
        }catch(IOException e){  }
    }
    
    //initiation and file checking
    private void initialize(){
        
        checkFileSystem();
        
        //read asset file
        
        //start migrating
        //migrateFromFiles();
        
        //delete the files
        
        //recreate files
        //checkFileSystem();
    }
    
    private void checkFileSystem(){
        File directory = new File("./database/");
        File asset = new File("./asset.root");
        
        if (!directory.exists()){
            directory.mkdirs();
        }
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
        
        asset = new File("./database/String.fragment");
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
        asset = new File("./database/Integer.fragment");
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
        asset = new File("./database/Double.fragment");
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
        asset = new File("./database/Pair.fragment");
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
        asset = new File("./database/StringArray.fragment");
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
        asset = new File("./database/IntegerArray.fragment");
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
        asset = new File("./database/Double.fragment");
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
        asset = new File("./database/PairArray.fragment");
        if(!asset.exists()){
            try {
                asset.createNewFile();
            }catch(IOException e){ System.out.println("Failed!");  }
        }
    }
   
    private void migrateFromFiles(){
        String assetData = "";
        
        //read the root file
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("asset.root")));
            String line = "";
            
            try {
                while ((line = br.readLine()) != null) {
                    String[] data = line.split("[-:@]");
                    if(data[2].equals("Pair[]")){
                        //prepare an array
                        Array first = new Array();
                        first.setArray();
                        
                        //read the pair array file
                        
                    }
                }
            }catch(IOException e){   }
            
            br.close();
        }catch(IOException e){    System.out.println("File Not Found!");    }
        
    }
    
    private void loader(){
        
    }
    
    
    
}
    



