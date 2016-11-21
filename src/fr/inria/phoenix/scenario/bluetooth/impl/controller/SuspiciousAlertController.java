package fr.inria.phoenix.scenario.bluetooth.impl.controller;

import java.util.ArrayList;
import java.util.List;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.suspiciousalertcontroller.AbstractSuspiciousAlertController;
import fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AlertSuspectRegulationValue;
import fr.inria.phoenix.diasuite.framework.datatype.noncriticalnotification.NonCriticalNotification;

/* (non-Javadoc)
 * The implementation of the SuspiciousAlertController context
 * @see fr.inria.phoenix.diasuite.framework.controller.suspiciousalertcontroller.AbstractSuspiciousAlertController
 */
public class SuspiciousAlertController extends AbstractSuspiciousAlertController {
    
    public SuspiciousAlertController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.controller.suspiciousalertcontroller.AbstractSuspiciousAlertController#onAlertSuspectRegulation(AlertSuspectRegulationValue, DiscoverForAlertSuspectRegulation)
     */
    @Override
    protected void onAlertSuspectRegulation(AlertSuspectRegulationValue alertSuspectRegulation, DiscoverForAlertSuspectRegulation discover) {
        // TODO Auto-generated method stub
    	
    	// timer
    	String IdTimer = "AlertTimerFall007";
    	Integer delayMs = 4*60*1000; // 4 min
		discover.timers().anyOne().schedule(IdTimer, delayMs );// particular ID...

		// notif
    	String IdNotification = "AlertNotificationFall007";
    	List<String> answers = new ArrayList<String>();
    	answers.add("Oui appeler les secours ");
    	answers.add("Non tout va bien");
    	NonCriticalNotification notification = new NonCriticalNotification(IdNotification,"Fall Alert","Avez-vous chuté ?",answers,false);
    	discover.notifiers().anyOne().sendNonCriticalNotification(notification);

		
    }
}
