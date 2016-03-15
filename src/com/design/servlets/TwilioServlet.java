package com.design.servlets;
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.sdk.verbs.Record;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
@WebServlet("/echo")
public class TwilioServlet extends HttpServlet
{
	// Recording time in seconds
	static int REC_DURATION = 30;
	
	public void service(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException
	{
		TwiMLResponse response = new TwiMLResponse();
		
//		String callerNumber = httpRequest.getParameter("From");
		
		Record rec = new Record();
		rec.setMaxLength(REC_DURATION);
		
		rec.setAction("/voice-servlet");
		// Send a response
		try
		{
			// Prompt
			response.append(new Say("Please say your query in a clear tone"));
			response.append(rec);
		}
		catch(TwiMLException e)
		{
			e.printStackTrace();
		}
		httpResponse.setContentType("application/xml");
		httpResponse.getWriter().print(response.toXML());
		
	} // service class
	
} // TwilioServlet class