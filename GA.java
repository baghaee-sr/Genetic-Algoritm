package GA.cards.new1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.lang.Double;
/**
 * @author baghaee.saeed
 */
public class GA {
    
	 int card = 10;
	 int numsum = 36;
	 int numzarb = 360;
	 int sizestack = 100;
	
    int pop[][] = new int[sizestack][card];
    
    public void exec(){
        popstart();
        
         int filiation = 100;
	 double MUTATEPROB = 0.3;
	 double COPYPROB = 0.1; 
         
	for(int Ga = 0; Ga <= filiation; Ga++) {
            int bcard[] = GABest();
            System.out.println(".::number GA  : " + (Ga+1));
            System.out.println(".::highest Result: " +  bcard[0] + " " + bcard[1] + "\n");
            System.out.println("**-----------*-----------------*-----------------*----------------*----------------**");
            
            if(Ga==filiation || bcard[0]==numsum && bcard[1]==numzarb) {
                    System.out.println(".:::.....Result.....:::. ");
                    System.out.print("number for sum \n ");
                    for(int i = 0; i < card; i++) {
                            if(bcard[2] == i){
                                System.out.print("\n number for multi \n ");
                            }
                            System.out.print(" " + pop[bcard[3]][i]+"");
                    }
                    System.out.print("\n ");
                    break;
            }
            for(int i = 0; i < sizestack; i++) {
                    if(Math.random() < COPYPROB)
                    {
                        pop[i] = pop[bcard[3]];
                    }
                    if(Math.random() < MUTATEPROB) 
                    {
                        Mutated(pop[i]);
                    }
            }
        }
    }

	private void popstart() {
		for(int i = 0; i < sizestack; i++)
                {
			for(int j = 0; j < card; j++) 
                        {
                            pop[i][j] = j+1;
                        }
                }
        }
	
	private int[] GABest() {
		int list1[] = new int[4];
		for(int i = 0; i < 3; i++)
                    list1[i] = eval(pop[0])[i];
		for(int i = 1; i < sizestack; i++) {
			int temp[] = eval(pop[i]);
                        int t0abs = Math.abs(numsum - temp[0]);
                        int t1abs = Math.abs(numzarb - temp[1]);
                        int r0abs = Math.abs(numsum - list1[0]);
                        int r1abs = Math.abs(numzarb - list1[1]);
                                              
			if(t0abs +  t1abs < r0abs + r1abs) {
				for(int j = 0; j < 3; j++)
                                    list1[j] = temp[j];
				list1[3] = i;
			}
		}
		return list1;
	}
        
         private int rand(int min, int max) {
            int randi= (min + (int)(Math.random() * ((max - min) + 1)));
		return randi;
         }
	private int[] eval(int list[]) {
		int dem[] = new int[3];
		dem[2] = rand(1,card-2);
                System.out.println("- num break= " + dem[2]);
		dem[1] = 1;
		for(int i = 0; i < dem[2]; i++){
			dem[0] +=list[i];
                }
                System.out.println("- sum num break= " + dem[0]);
		for(int i = dem[2]; i < card; i++){
			dem[1] *= list[i];		
                }
                System.out.println("- multi num break= " + dem[1]);
                System.out.println("..........................");
		return dem;
	}
     
	private void Mutated(int list[]) {
		int i1 = rand(0,card-1);
		int i2 = rand(0,card-1);
		int temp = list[i1];
		list[i1] = list[i2];
		list[i2] = temp;
                
                
	}
		
    
}