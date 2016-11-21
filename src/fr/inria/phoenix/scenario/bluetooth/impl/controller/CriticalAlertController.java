package fr.inria.phoenix.scenario.bluetooth.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.criticalalertcontroller.AbstractCriticalAlertController;
import fr.inria.phoenix.diasuite.framework.context.criticalalert.CriticalAlertValue;

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
    	if (criticalAlert.value()== 1) {
    	// reset notif	
    	}
    	
    	// disable timer
    	String IdTimer = "AlertTimerFall007";
    	discover.timers().anyOne().cancel(IdTimer); // particular ID...    	
    }
}