package fr.inria.phoenix.scenario.bluetooth.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AbstractAlertSuspectRegulation;
import fr.inria.phoenix.diasuite.framework.device.input.MessageFromInput;

/* (non-Javadoc)
 * The implementation of the AlertSuspectRegulation context
 * @see fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AbstractAlertSuspectRegulation
 */
public class AlertSuspectRegulation extends AbstractAlertSuspectRegulation {
    
	/**
     * A constructor that instantiate the class AlertSuspectRegulation
     */
    public AlertSuspectRegulation(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }
    
    /**
     * A class that represents a value that might be published for the <code>AlertSuspectRegulation</code> context. It is used by
     * event methods that might or might not publish values for this context.
     */
	@Override
	protected AlertSuspectRegulationValuePublishable onMessageFromInput(MessageFromInput messageFromInput,
			DiscoverForMessageFromInput discover) {
		String messageAboutBodyPosition = messageFromInput.value(); // get the value of the string returned by the websocket

		if (messageAboutBodyPosition.equals("Une chute a eu lieu")) { // if a fall has been detected by the smarthphone. (info communicated through bluetooth and websocket DiaSuite)

			if (discover.presenceDetectors().anyOne().getInBedroom()) { // test if the person is in the bedroom (probably sleeping and not fallen)
				return new AlertSuspectRegulationValuePublishable("", false); // No Suspect alert has to be communicated
			}
			else {
				return new AlertSuspectRegulationValuePublishable("", true); // A Suspect alert has to be communicated
			}
		}
		else { 
			return new AlertSuspectRegulationValuePublishable(messageAboutBodyPosition, false); // if the message returned by the web socket is not the one intended (no fall but an other event)
		}
	}
}
