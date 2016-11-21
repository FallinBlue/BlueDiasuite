package fr.inria.phoenix.scenario.bluetooth.impl.context;

import java.util.Iterator;

import fr.inria.diagen.core.ServiceConfiguration;
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
		if (messageAboutBodyPosition.equals("lieDown")) {// allongé
			  Iterator<MotionDetectorProxyForMessageFromInput> allMotionDetectorsIterator = discover.motionDetectors().all().iterator();
			  
		      while(allMotionDetectorsIterator.hasNext()) {
		    	  MotionDetectorProxyForMessageFromInput oneMotionDetector = allMotionDetectorsIterator.next();
		    	  String motionDetectorState = oneMotionDetector.getMotion().getState();
		    	  // see how is managed the state.... see where he is
		    	  if (motionDetectorState.equals("isHere")) { // TO CHANGE §!!
		    		  userLocation = oneMotionDetector.location();
		    	  }
		      }
		      return new AlertSuspectRegulationValuePublishable(userLocation, true);

		}
		else { 
			return new AlertSuspectRegulationValuePublishable(messageAboutBodyPosition, false);
		}
	}
}
