/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Flexible;

/**
 *
 * @author breed
 */

// Key value pair flexible type class
public class Pair<T> {
    private String Key;
    private T Value;
    private String Type;
    private boolean isCollectionMode;
    
    Array arr = new Array();
    
    public void checker(){
        if(Value != null){
            System.out.println("not null");
        } else {
            System.out.println("null");
        }
    }
    
    public Pair(boolean setArray){
        if(setArray == false){
            return;
        }
        isCollectionMode = true;
        setType("Pair[]");
        arr.setArray();
        Value = (T) arr.getArray();
    }

    // getters
    public String getKey(){
        return this.Key;
    }
    public T getValue(){
        return this.Value;
    }
    public String getType(){
        return this.Type;
    }

    //getters for a non collection object mode
    public String getValue(String none){
        String value = (String) this.Value;
        return value;
    }
    public int getValue(int none){
        int value = (int) this.Value;
        return value;
    }
    public double getValue(double none){
        double value = (double) this.Value;
        return value;
    }
    public String[] getValue(String[] none){
        String[] value = (String[]) this.Value;
        return value;
    }
    public int[] getValue(int[] none){
        int[] value = (int[]) this.Value;
        return value;
    }
    public double[] getValue(double[] none){
        double[] value = (double[]) this.Value;
        return value;
    }

    //setters
    public void setKey(String key){
        this.Key = key;
    }
    public void setValue(T value){
        this.Value = value;
    }
    public void setType(String type){
        this.Type = type;
    }

    //for collection mode
    public void setPairKey(String key){
        this.Key = key;
    }
    

    public void add(String key, String value){
        if(isCollectionMode == false){
            System.out.println("Error: Adding an item to a non-collection mode Pair object.");
            System.exit(1);
        }
        String type = ((Object)value).getClass().getSimpleName();

        Pair<String> temp = new Pair<String>(false);

        temp.setKey(key);
        temp.setValue(value);
        temp.setType(type);

        arr.add(temp);
        Value = (T) arr.getArray();
    }
    public void add(String key, int value){
        if(isCollectionMode == false){
            System.out.println("Error: Adding an item to a non-collection mode Pair object.");
            System.exit(1);
        }
        String type = ((Object)value).getClass().getSimpleName();

        Pair<Integer> temp = new Pair<Integer>(false);

        temp.setKey(key);
        temp.setValue(value);
        temp.setType(type);

        arr.add(temp);
        Value = (T) arr.getArray();
    }
    public void add(String key, double value){
        if(isCollectionMode == false){
            System.out.println("Error: Adding an item to a non-collection mode Pair object.");
            System.exit(1);
        }
        String type = ((Object)value).getClass().getSimpleName();

        Pair<Double> temp = new Pair<Double>(false);

        temp.setKey(key);
        temp.setValue(value);
        temp.setType(type);

        arr.add(temp);
        Value = (T) arr.getArray();
    }
    public void add(String key, Pair<?> value){
        if(isCollectionMode == false){
            System.out.println("Error: Adding an item to a non-collection mode Pair object.");
            System.exit(1);
        }

        arr.add(value);
        Value = (T) arr.getArray();
    }
    
    //Pair object functions
    //temporary
    public void show(){
        // System.out.println(Type);
        arr.printAll();
    }

    public void print(){
        String type = ((Object)Value).getClass().getSimpleName();

        System.out.print(Key + ":");
        if(type.equals("Pair")){
            Pair<?> pair = (Pair<?>) Value;
            pair.print();
            return;
        }
        if(type.equals("Pair[]")){
            System.out.print("{");
            Pair<?>[] temp = (Pair<?>[]) Value;

            for(int x = 0; x < temp.length; x++){
                Pair<?> item = temp[x];
                item.print();
                if(x == temp.length - 1){
                    break;
                }
                System.out.print(",");
            }
            System.out.println("}@" + Type);
            return;
        }
        if(type.equals("String[]")){
            String[] temp = (String[]) Value;
            
            String array = "[";
            for(int x = 0; x < temp.length; x++){
                array += temp[x];
                if(x == temp.length - 1){
                    break;
                }
                array += "-";
            }
            array += "]";
            System.out.print(array + "@" + Type);
            return;
        }
        System.out.print(Value + "@" + Type);
    }
    
    //to get the data in the data collection of a pair
    //only works if the pair object is set to array mode
    public String getData(String key, String none){
        Pair<?> temp = arr.getPair(key);
        String value = (String) temp.getValue();
        return value;
    }
    public int getData(String key, int none){
        Pair<?> temp = arr.getPair(key);
        int value = (Integer) temp.getValue();
        return value;
    }
    public double getData(String key, double none){
        Pair<?> temp = arr.getPair(key);
        double value = (double) temp.getValue();
        return value;
    }
    public String[] getData(String key, String[] none){
        Pair<?> temp = arr.getPair(key);
        String[] value = (String[]) temp.getValue();
        return value;
    }
    public int[] getData(String key, int[] none){
        Pair<?> temp = arr.getPair(key);
        int[] value = (int[]) temp.getValue();
        return value;
    }
    public double[] getData(String key, double[] none){
        Pair<?> temp = arr.getPair(key);
        double[] value = (double[]) temp.getValue();
        return value;
    }
}