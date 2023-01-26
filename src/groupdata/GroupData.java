/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package groupdata;

/**
 *
 * @author breed
 */

import java.util.*;
import com.logicbig.Table;
import com.Flexible.Array;
import com.Flexible.Pair;

public class GroupData {
    
    static Array collection = new Array();
    static Array root = new Array(); // main array for saving data and importing data from file 
    
    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args){
        collection.setArray();
        root.setAsRootArray();
        
        root.printAll();
        
        while(true){
            try{
                System.out.println("[1] New GroupData");
                System.out.println("[2] View Recent Data");
                System.out.println("[3] Exit");
                
                int x = input.nextInt();
                input.nextLine();
                
                if(x < 1 || x > 3){
                    System.out.println("Out of bounds!");
                    continue;
                }
                
                if(x == 1){
                    createNewGroupData();
                    compute();
                    presentDataTable();
                    
                    System.out.println("Do you want to save the result as data? y/n");
                    String save = input.nextLine();
                    if(save.equals("y") || save.equals("Y")){
                        System.out.print("Enter the graph title: ");
                        String title = input.nextLine();
                        collection.printAll();
                        // root.extend(title, collection);
                        // Pair[] temp = root.getArray();
                        // root.export(temp);
                        collection.clear();
                    } else if(save.equals("n") || save.equals("N")){
                        
                    } else {
                        
                    }
                }
                if(x == 2){
                    
                }
                if(x == 3){
                    System.exit(1);
                }
                
            }catch(InputMismatchException e){
                System.out.println();
                System.out.println("Invalid input!");
            }
        }
    }
    
    public static void presentDataTable(){
        //set new table
        Table table = new Table();
        table.ShowVerticalLines(true);
        table.setHeaders("RANGE", "F", "X", "FX", "CF");
        
        Pair<?> data = collection.getPair("data");
        int x = data.getData("count", 1);
        
        int i = 1;
        while(i < x){
            String key = Integer.toString(i);
            Pair<?> pair = collection.getPair(key);
            
            double lowerLim = pair.getData("lower", 1.00);
            double upperLim = pair.getData("upper", 1.00);
            double f = pair.getData("f", 1.00);
            double m = pair.getData("midpoint", 1.00);
            double fx = pair.getData("fx", 1.00);
            double cf = pair.getData("cf", 1.00);
            
            String lowL = Double.toString(lowerLim);
            String upL = Double.toString(upperLim);
            
            String range = lowL + "-" + upL;
            String freq = Double.toString(f);
            String mid = Double.toString(m);
            String FX = Double.toString(fx);
            String CF = Double.toString(cf);
            
            table.addRow(range, freq, mid, FX, CF);
            i++;
        }
        
        double gap = data.getData("i", 1.0);
        double n = data.getData("n", 1.0);
        double tcf = data.getData("F", 1.0);
        
        String G = "i = " + Double.toString(gap);
        String N = "n = " + Double.toString(n);
        String TCF = "F = " + Double.toString(tcf);

        table.addRow(G, N, "null", TCF, "null");
        table.print();
        table.clear();
        
        double mean = data.getData("mean", 1.00);
        
        System.out.println();
        System.out.println("Mean: F / n");
        System.out.println("    X = " + tcf + " / " + n);
        System.out.println("    X = " + tcf / n);
        System.out.println();
        System.out.println("    Mean: " + tcf / n);
        
        Pair<?> median = collection.getPair("medianCom");
        double ll = median.getData("ll", 1.00);
        double lcf = median.getData("lcf", 1.00);
        double f = median.getData("f", 1.00);
        
        double proc1 = median.getData("proc1", 1.00);
        double proc2 = median.getData("proc2", 1.00);
        double proc3 = median.getData("proc3", 1.00);
        double proc4 = median.getData("proc4", 1.00);
        
        System.out.println();
        System.out.println("Median: LL + i * [m - lower CF / f]");
        System.out.println("Given:\n LL: " + ll + "\n i: " + gap + "\n m: " + mean + "\n lowerCF: " + lcf + "\n f: " + f);
        System.out.println();
        System.out.println("\t X = " + ll + " + " + gap + " * [" + mean + " - " + lcf + " / " + f + "]");
        System.out.println("\t   = " + ll + " + " + gap + " * [" + proc1 + " / " + f + "]");
        System.out.println("\t   = " + ll + " + " + gap + " (" + proc2 + ")");
        System.out.println("\t   = " + ll + " + " + proc3);
        System.out.println("\t X = " + proc4);
        System.out.println();
        System.out.println("\t Median: " + proc4);
        
        Pair<?> mode = collection.getPair("modeCom");
        double lbmo = mode.getData("lbmo", 1.00);
        double d1 = mode.getData("d1", 1.00);
        double d2 = mode.getData("d2", 1.00);
        
        proc1 = mode.getData("proc1", 1.00);
        proc2 = mode.getData("proc2", 1.00);
        proc3 = mode.getData("proc3", 1.00);
        proc4 = mode.getData("proc4", 1.00);
        
        System.out.println();
        System.out.println("Mode: lbmo + i * [d1 / d1 + d2]");
        System.out.println("Given:\n lbmo: " + lbmo + "\n i: " + gap + "\n d1: " + d1 + "\n d2: " + d2 );
        System.out.println();
        System.out.println("\t X = " + lbmo + " + " + gap + " * [" + d1 + " / " + d1 + " + " + d2 + "]");
        System.out.println("\t   = " + lbmo + " + " + gap + " * [" + d1 + " / " + proc1 + "]");
        System.out.println("\t   = " + lbmo + " + " + gap + " (" + proc2 + ")");
        System.out.println("\t   = " + lbmo + " + " + proc3);
        System.out.println("\t X = " + proc4);
        System.out.println();
        System.out.println("\t Mode: " + proc4);
        System.out.println();
        
    }

    public static void createNewGroupData(){
        
        //collect data as rows
        int i = 1;
        int x = 1;
        double cf = 0;
        
        double gap = 0;
        double n = 0;
        double F = 0;
        
        System.out.println("New Grouped Data: ");
        
        while(true){
            if(i > 4){
                System.out.println("Enter [x] to end.");
            }
            
            System.out.print("Enter a range(Ex. 1-10): ");
            String range = input.nextLine();

            if(range.equals("x") || range.equals("x")){
                break;
            }
            double freq = 0;
            while(true){
                try{
                    System.out.print("Enter frequency for " + range + ": ");
                    freq = input.nextDouble();
                    input.nextLine();
                    
                    break;
                } catch(InputMismatchException e) {
                    System.out.println();
                    System.out.println("Invalid frequency input!");
                    System.out.println();
                }
            }
            
            System.out.println();
            
            //prepare the range
            String[] ranges = range.split("-");
            String lowL = ranges[0];
            String upL = ranges[1];
            
            //get lower limit
            double lowerLim = Double.parseDouble(lowL);
            
            //get upper limit
            double upperLim = Double.parseDouble(upL);
            
            //prepare the range gap
            gap = upperLim - lowerLim;
            gap = gap + 1;
            
            //compute the midpoint
            double sum = lowerLim + upperLim;
            double midpoint = sum / 2;
            
            //compute the f of x
            double fx = freq * midpoint;
            
            //compute the cumulative frequency
            cf = cf + freq;
            
            //compute the n total
            n = n + freq;
            
            //compute the F
            F = F + fx;
            
            //current position
            String count = Integer.toString(i);
            
            //prepare the pair data
            Pair<Pair<?>[]> pair = new Pair<>(true);
            pair.setPairKey(count);
            
            pair.add("lower", lowerLim);
            pair.add("upper", upperLim);
            pair.add("f", freq);
            pair.add("midpoint", midpoint);
            pair.add("fx", fx);
            pair.add("cf", cf);

            // pair.show();
            collection.migrate(pair);   
            i++;
            x++;
        }
        
        //prepare the mean
        double m = n / 2;
        
        Pair<?> data = new Pair<>(true);
        data.setPairKey("data");
        
        data.add("i", gap);
        data.add("n", n);
        data.add("mean", m);
        data.add("F", F);
        data.add("count", x);
        
        collection.migrate(data);
    }
    
    public static void compute(){
        calculateMedian();
        calculateMode(); 
    }
    
    public static void calculateMedian(){
        Pair<?> temp = collection.getPair("data");
        double mean = temp.getData("mean", 1.00);
        int count = temp.getData("count", 1);
        double i = temp.getData("i", 1.00);
        
        Pair<?> compute = new Pair<>(true);
        compute.setPairKey("medianCom");
        
        Pair<?> selectedRow = getRow(mean, count);
        
        //compute the actual median
        double lowerLim = selectedRow.getData("lower", 1.00);
        lowerLim = lowerLim - .5;
        double lcf = selectedRow.getData("lcf", 1.00);
        double f = selectedRow.getData("f", 1.00);
        
        compute.add("lcf", lcf);
        compute.add("f", f);
        compute.add("ll", lowerLim);
        compute.add("i", i);
        
        //in brackets
        double in = mean - lcf; compute.add("proc1", in);
        in = in / f; compute.add("proc2", in);
        
        //compute all
        double res = in * i; compute.add("proc3", res);
        res = res + lowerLim; compute.add("proc4", res);
        
        temp.add("median", res);
        
        collection.add(temp);
        collection.add(compute);
    }
    
    public static void calculateMode(){
        Pair<?> data = collection.getPair("data");
        int count = data.getData("count", 1);
        double i = data.getData("i", 1.00);
        
        Pair<?> compute = new Pair<>(true);
        compute.setPairKey("modeCom");
        
        Pair<?> selectedRow = collectRowDataOfHighestFrequency(count);
        
        double lowerLim = selectedRow.getData("lower", 1.00);
        lowerLim = lowerLim - .5;
        double f = selectedRow.getData("f", 1.00);
        double d1 = selectedRow.getData("d1", 1.00);
        double d2 = selectedRow.getData("d2", 1.00);
        
        d1 = f - d1;
        d2 = f - d2;
        
        compute.add("lbmo", lowerLim);
        compute.add("d1", d1);
        compute.add("d2", d2);
        
        //in brackets
        double in = d1 + d2; compute.add("proc1", in);
        in = d1 / in; compute.add("proc2", in);
        
        //out brackets
        double res = i * in; compute.add("proc3", res);
        res = lowerLim + res; compute.add("proc4", res);
        
        data.add("Mode", res);
        
        collection.add(data);
        collection.add(compute);
    }
    
    public static Pair<?> collectRowDataOfHighestFrequency(int x){
        
        int i = 1;
        int pos = 0;
        double recent = 0;
        
        //figure out what is the position of highest frequency
        while(i < x){
            String key = Integer.toString(i);
            Pair<?> pair = collection.getPair(key);
            
            double f = pair.getData("f", 1.00);
            
            //check if it is the highest
            if(f > recent){
                recent = f;
                pos = i;
            }            
            i++;
        }
        
        //prepare the storage for the data
        Pair<Pair<?>[]> temp = new Pair<>(true);
        temp.setPairKey("HF");
        
        //get the actual data
        i = 1;
        while(i <= pos + 1){
            String key = Integer.toString(i);
            Pair<?> pair = collection.getPair(key);
            
            double lowerLim = pair.getData("lower", 1.00);
            double upperLim = pair.getData("upper", 1.00);
            double f = pair.getData("f", 1.00);
            double m = pair.getData("midpoint", 1.00);
            double fx = pair.getData("fx", 1.00);
            double cf = pair.getData("cf", 1.00);

            //check if it is the highest
            if(i == pos - 1){
                temp.add("d1", f);
            }
            if(i == pos){
                temp.add("lower", lowerLim);
                temp.add("upper", upperLim);
                temp.add("f", f);
                temp.add("midpoint", m);
                temp.add("fx", fx);
                temp.add("cf", cf);
            }
            if(i == pos + 1){
                temp.add("d2", f);
                return temp;
            }
            i++;
        }
        
        return null;
    }
    
    public static Pair<?> getRow(double mean, int x){
        int i = 1;
        double lowerCf = 0;
        while(i <= x){
            String key = Integer.toString(i);
            Pair<?> pair = collection.getPair(key);
            
            double lowerLim = pair.getData("lower", 1.00);
            double upperLim = pair.getData("upper", 1.00);
            double f = pair.getData("f", 1.00);
            double m = pair.getData("midpoint", 1.00);
            double fx = pair.getData("fx", 1.00);
            double cf = pair.getData("cf", 1.00);
            
            if(mean <= cf){
                Pair temp = new Pair(true);
                temp.setPairKey("selected row");
                
                temp.add("lower", lowerLim);
                temp.add("upper", upperLim);
                temp.add("f", f);
                temp.add("midpoint", m);
                temp.add("fx", fx);
                temp.add("cf", cf);
                temp.add("lcf", lowerCf);
                
                return temp;
            } else {
                lowerCf = cf;
            }
            i++;
        }
        
        return null;
    }
    
}

