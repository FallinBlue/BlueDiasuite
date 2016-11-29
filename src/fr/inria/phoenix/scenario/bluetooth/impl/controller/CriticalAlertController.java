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
    
    public CriticalAlertController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.controller.criticalalertcontroller.AbstractCriticalAlertController#onCriticalAlert(CriticalAlertValue, DiscoverForCriticalAlert)
     */
    @Override
    protected void onCriticalAlert(CriticalAlertValue criticalAlert, DiscoverForCriticalAlert discover) {
    	
    	// disable timer
    	String IdTimer = "AlertTimerFall007";
    	discover.timers().anyOne().cancel(IdTimer); // particular ID... 
    	
    	// sms urgence 
    	
    	
		for (Contact to : criticalAlert.value()) {
			discover.communicationServices().anyOne().sendTrustedMessage(to, "[EMERGENCY DIASUITE]", "My Name has probably fallen. Please call here or help her."); // checker autre classe ????
		}
    }
}