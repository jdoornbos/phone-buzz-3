package jrd.phonebuzz;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.verbs.Gather;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

/**
 * Servlet implementation class GameStartServlet
 */
public class GameStartServlet extends HttpServlet {
  
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    // Create a TwiML response
    TwiMLResponse twiml = new TwiMLResponse();
    
    // Create a gather verb to collect user input
    Gather gather = new Gather();
    gather.setAction("handleinput");
    gather.setMethod("POST");
    Say sayInGather = new Say("Please enter a number, followed by the pound sign.");
    
    // Construct TwiML
    try {
        gather.append(sayInGather);
        twiml.append(gather);
    } catch (TwiMLException e) {
        e.printStackTrace();
    }

    // Set response
    response.setContentType("application/xml");
    response.getWriter().print(twiml.toXML());
  }

}
