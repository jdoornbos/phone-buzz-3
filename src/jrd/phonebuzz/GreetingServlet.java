package jrd.phonebuzz;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.TwilioUtils;
import com.twilio.sdk.verbs.Gather;
import com.twilio.sdk.verbs.Redirect;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;

/**
 * Servlet implementation class GreetingServlet
 */
public class GreetingServlet extends HttpServlet {
  
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    // Create a TwiML response and add a greeting
    TwiMLResponse twiml = new TwiMLResponse();
    Say say = new Say("Hello and welcome to Phone buzz.");
    Redirect redirect = new Redirect("gamestart");
    
    // Construct TwiML
    try {
        twiml.append(say);
        twiml.append(redirect);
    } catch (TwiMLException e) {
        e.printStackTrace();
    }

    // Set response
    response.setContentType("application/xml");
    response.getWriter().print(twiml.toXML());
  }

}
