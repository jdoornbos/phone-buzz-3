package jrd.phonebuzz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.factory.CallFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.twilio.sdk.resource.instance.Account;

/**
 * Servlet implementation class CallOutServlet
 */
public class CallOutServlet extends HttpServlet {
  
  Scheduler scheduler = null;
  
  public static final String ACCOUNT_SID = "AC2da10a450be1f811b14e55d4f1ff77b3";
  public static final String AUTH_TOKEN = "af0ef05a2a7a48c4b01118aca57b3671";
  
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    String delay = request.getParameter("delay");
    int mins = Integer.parseInt(delay.split(":")[0]);
    int secs = Integer.parseInt(delay.split(":")[1]);

    long delayInMillis = mins * 60000 + secs * 1000;
    long startTime = System.currentTimeMillis() + delayInMillis;
    
    try {
      // Setup the Job class and the Job group
      JobDetail job = newJob(OutgoingCallJob.class).withIdentity(
                      "OutgoingCallJob", "Group").build();

      // Create a Trigger that fires every 5 minutes.
      Trigger trigger = newTrigger()
        .withIdentity("TriggerName", "Group")
        .startAt(new Date(startTime))
        .build();

      // Setup the Job and Trigger with Scheduler & schedule jobs
      scheduler = new StdSchedulerFactory().getScheduler();
      scheduler.start();
      job.getJobDataMap().put("account_sid", ACCOUNT_SID);
      job.getJobDataMap().put("auth_token", AUTH_TOKEN);
      job.getJobDataMap().put("param_to", request.getParameter("number"));
      job.getJobDataMap().put("param_from", "6162025118");
      String url = request.getRequestURL().toString();
      url = url.substring(0, url.indexOf(request.getServletPath()));
      job.getJobDataMap().put("param_url", url + "/greeting");
      scheduler.scheduleJob(job, trigger);
    } catch (SchedulerException e) {
          e.printStackTrace();
    }
    
    // Set response content type
    response.setContentType("text/html");
    
    request.setAttribute("mins", mins);
    request.setAttribute("secs", secs);
    try {
      request.getRequestDispatcher("/countdown.jsp").forward(request, response);
    } catch (ServletException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }


  }
}
