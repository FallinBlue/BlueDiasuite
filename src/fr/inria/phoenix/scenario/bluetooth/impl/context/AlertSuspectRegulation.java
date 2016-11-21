package fr.inria.phoenix.scenario.bluetooth.impl.context;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.core.exception.InvocationException;
import fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AbstractAlertSuspectRegulation;
import fr.inria.phoenix.diasuite.framework.device.input.MessageFromInput;

/* (non-Javadoc)
 * The implementation of the AlertSuspectRegulation context
 * @see fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AbstractAlertSuspectRegulation
 */
public class AlertSuspectRegulation extends AbstractAlertSuspectRegulation {
    
    public AlertSuspectRegulation(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }
    

	@Override
	protected AlertSuspectRegulationValuePublishable onMessageFromInput(MessageFromInput messageFromInput,
			DiscoverForMessageFromInput discover) {
		String messageAboutBodyPosition = messageFromInput.value();
		String userLocation = "" ;
		Timestamp timestampMin = new Timestamp(1); // a long time ago
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	    Date parsedDate = null ;
	    Timestamp newTimestamp ;

		if (messageAboutBodyPosition.equals("lieDown")) {// allongé

			  Iterator<MotionDetectorProxyForMessageFromInput> allMotionDetectorsIterator = discover.motionDetectors().all().iterator();
		      
			  while(allMotionDetectorsIterator.hasNext()) {


		    	  MotionDetectorProxyForMessageFromInput oneMotionDetector = allMotionDetectorsIterator.next();
		    	 

		    	  // convert timestamp
		    		    try {
							parsedDate = dateFormat.parse(oneMotionDetector.getMotion().getTimestamp());

						} catch (InvocationException e) {

							e.printStackTrace();
						} catch (ParseException e) {

							e.printStackTrace();
						}
		    		    newTimestamp = new java.sql.Timestamp(parsedDate.getTime());

		    	  if (newTimestamp.after(timestampMin)) {
			    	  //String motionDetectorState = oneMotionDetector.getMotion().getState();
			    	  // see how is managed the state.... see where he is
		    		  
		    		  userLocation = oneMotionDetector.location();
		    		  
		    		  // if location == lieDown
//			    	  if (motionDetectorState.equals("movementDetected")) { // TO CHANGE §!! // tester si pièce couchée ou non ?
//			    	  }
		    	  }
		      }
		      return new AlertSuspectRegulationValuePublishable(userLocation, true);

		}
		else { 
			return new AlertSuspectRegulationValuePublishable(messageAboutBodyPosition, false);
		}
	}
}
