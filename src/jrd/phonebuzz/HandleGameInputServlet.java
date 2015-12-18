package jrd.phonebuzz;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jrd.fizzbuzz.FizzBuzzGame;

import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Redirect;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

/**
 * Servlet implementation class HandleGameInputServlet
 */
public class HandleGameInputServlet extends HttpServlet {

  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    // Get input, create twiml response and game
    String digits = request.getParameter("Digits");
    TwiMLResponse twiml = new TwiMLResponse();
    FizzBuzzGame game = new FizzBuzzGame();
    
    // Parse number and check validity
    boolean isValidNumber = true;
    int n = 0;
    try {
      n = Integer.parseInt(digits);
    } catch (NumberFormatException e) {
      isValidNumber = false;
    }
    
    // If valid, construct game response. Otherwise redirect
    if (isValidNumber) {
      
      Say say1 = new Say("You have entered, " + digits + ". Commencing game.");
     
      String gameOutputText = game.generateRound(n);
      Say say2 = new Say(gameOutputText);
      
      Say goodbye = new Say("Thank you for playing Phone Buzz. Goodbye.");
      try {
        twiml.append(say1);
        twiml.append(say2);
        twiml.append(goodbye);
      } catch (TwiMLException e) {
        e.printStackTrace();
      }
    } else {
      Say saybad = new Say("I'm sorry. That is not a valid number.");
      Redirect redirect = new Redirect("gamestart");
      try {
        twiml.append(saybad);
        twiml.append(redirect);
      } catch (TwiMLException e) {
        e.printStackTrace();
      }
    }
    
    response.setContentType("application/xml");
    response.getWriter().print(twiml.toXML());
  }
}
