package jrd.fizzbuzz;

public class FizzBuzzGame {

  
  public String generateRound(int endNum) {
    
    StringBuilder sb = new StringBuilder();
    
    for (int i = 1; i <= endNum; i++) {
      
      String turnOutput = String.valueOf(i);
      if (i % 3 == 0 && i % 5 == 0)
        turnOutput = "Fizz Buzz";
      else {
        if (i % 3 == 0)
          turnOutput = "Fizz";
        if (i % 5 == 0)
          turnOutput = "Buzz";
      }
      
      sb.append(turnOutput);
      if (i < endNum)
        sb.append(", ");
    }
    
    return sb.toString();
  }
}
