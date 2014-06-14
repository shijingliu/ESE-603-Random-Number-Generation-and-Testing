package homework4;  
import java.util.Arrays;   

public class Problem4 {
      public static void main(String []args){
    	  
    	    System.out.print("the D value in KS test is: ");
    	    System.out.println(KStest());
    	    System.out.print("the x^2 value in Chi Square test is: ");
    	    System.out.println(ChiSquareTest());    
      }
      
      public static double [] generateNumbers(){
    	  double [] genNum = new double [100];    
    	  for (int i = 0; i < 100; i++){
    		  genNum[i] = Math.sin(i+1)*Math.sin(i+1);
    	  }
    	  return genNum; 
      }
      
      public static double KStest(){
    	  //obtain all the numbers Ri
    	  double [] num = generateNumbers();
    	  //obtain all the sorted numbers R(i)   
    	  Arrays.sort(num);
    	  
    	  //obtain i/n, (i-1)/n    
    	  double [] iN = new double [num.length];
    	  double [] iminus1N = new double [num.length];
    	  for(int i = 1; i <=num.length; i++)
    	  {
    		  iN[i-1] = i/(num.length*1.0); 
    		  iminus1N[i-1] = (i-1)/(num.length*1.0);   
    	  }
    	  
    	  //obtain i/N -R(i), R(i)-(i-1)/N      
    	  double []DplusArray = new double [num.length];
    	  double []DminusArray = new double [num.length];
    	  
    	  for (int i = 0; i < num.length; i++){
    		  DplusArray[i] = iN[i] - num[i]; 
    		  DminusArray[i] = num[i] - iminus1N[i];
    	  }
    	  
    	  //now we sorted DplusArray and DminusArray, so that the last number of the array is the maximum number
    	  Arrays.sort(DplusArray);
    	  Arrays.sort(DminusArray);
    	  
    	  return Math.max(DplusArray[num.length-1], DminusArray[num.length-1]);
      }
      
      public static double ChiSquareTest(){
    	//obtain all the numbers    
    	  double [] num = generateNumbers();
    	
    	  final int intervals = 10; 
    	  
    	  //declare a list of O              
    	  int [] O = new int [intervals];
    	  int [] E = new int [intervals];
    	  int [] OE = new int [intervals];
    	  int [] OESquare = new int [intervals];
    	  double [] OESquareE = new double [intervals];
    	  
    	  //initialize Oi, Ei, (Oi-Ei), (Oi-Ei)^2      
    	  for (int i = 0; i < intervals; i++){
    		  O[i] = 0; 
    		  OE[i] = 0; 
    		  OESquare[i] = 0; 
    		  OESquareE[i] = 0.0; 
    		  E[i] = num.length/intervals; 
    	  }
    	  
    	  for(int i = 0; i < num.length; i++){
	    	  for (int j = 0; j < intervals; j++){
	    		  if((num[i] >= j/(intervals*1.0)) && (num[i] < (j+1)/(intervals*1.0))){
	    			     O[j]++; 
	    		  }
	    	  }
    	  }
    	  
    	  //calculate Oi-Ei            
    	  for (int i = 0; i < intervals; i++){ 
    		 
    		  OE[i] = O[i]-E[i];
    		  OESquare[i] = OE[i]*OE[i];
    		  OESquareE[i] = OESquare[i]/(E[i]*1.0);
    	  }
    	  //sum all the numbers in the array    
    	  return SumArray(OESquareE); 
      }
      
      //a helper function that sums up all the numbers in the array 
      public static double SumArray (double[] array){
    	  double sum = 0.0; 
    	  for (int i = 0; i < array.length; i++){
    		  sum += array[i];
    	  }
    	  return sum;
      }
}
