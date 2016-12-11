package fr.inria.phoenix.scenario.bluetooth.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.criticalalertcontroller.AbstractCriticalAlertController;
import fr.inria.phoenix.diasuite.framework.context.criticalalert.CriticalAlertValue;
import fr.inria.phoenix.diasuite.framework.datatype.contact.Contact;

/* (non-Javadoc)
 * The implementation of the CriticalAlertController context
 * @see fr.inria.phoenix.diasuite.framework.controller.criticalalertcontroller.AbstractCriticalAlertController
 */
public class CriticalAlertController extends AbstractCriticalAlertController {
	
	/**
     * A constructor that instantiate the class CriticalAlertController
     */
    public CriticalAlertController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /**
     * This method is called when the <code>CriticalAlert</code> context publishes a value.
     * 
     * <pre>
     * when provided CriticalAlert do SendTrustedMessage on CommunicationService, ScheduleTimer on Timer;
     * </pre>
     * 
     * @param criticalAlert the value of the <code>CriticalAlert</code> context.
     * @param discover a discover object to get context values and action methods
     */
    @Override
    protected void onCriticalAlert(CriticalAlertValue criticalAlert, DiscoverForCriticalAlert discover) {
    	
    	String IdTimer = "AlertTimerFall007";
    	discover.timers().anyOne().cancel(IdTimer);     	// disable timer with particular ID... 

		for (Contact to : criticalAlert.value()) {			// iterate over the list of contact
			if (to.getGroups().contains("emergencyCall")) { // test if the contact belongs to the emergencyCall group
				discover.communicationServices().anyOne().sendTrustedMessage(to, "[EMERGENCY DIASUITE]", "My Name has probably fallen. Please call here or help her."); // send SMS to these contacts
			}
		}
    }
}