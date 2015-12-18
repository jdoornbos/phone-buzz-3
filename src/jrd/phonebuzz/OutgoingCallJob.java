package jrd.phonebuzz;

import java.util.HashMap;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;

public class OutgoingCallJob implements Job {

  private TwilioRestClient client;
  private Map<String, String> callParams;
  
  private String account_sid;
  private String auth_token;
  private String param_to;
  private String param_from;
  private String param_url;
  
  public void execute(JobExecutionContext context)
          throws JobExecutionException {

    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    account_sid = dataMap.getString("account_sid");
    auth_token = dataMap.getString("auth_token");
    param_to = dataMap.getString("param_to");
    param_from = dataMap.getString("param_from");
    param_url = dataMap.getString("param_url");

    TwilioRestClient client = new TwilioRestClient(account_sid, auth_token);
    
    Map<String, String> callParams = new HashMap<String, String>();
    callParams.put("To", param_to);
    callParams.put("From", param_from);     
    callParams.put("Url", param_url);
    
    Account mainAccount = client.getAccount();
    CallFactory callFactory = mainAccount.getCallFactory();
    
    // Make the call
    System.out.println("Calling...");
    Call call;
    try {
      call = callFactory.create(callParams);
    } catch (TwilioRestException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
