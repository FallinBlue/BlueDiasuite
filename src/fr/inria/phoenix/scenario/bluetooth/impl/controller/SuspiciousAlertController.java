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
    
	/**
     * A constructor that instantiate the class SuspiciousAlertController
     */
    public SuspiciousAlertController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /**
     * This method is called when the <code>AlertSuspectRegulation</code> context publishes a value.
     * 
     * <pre>
     * when provided AlertSuspectRegulation do SendNonCriticalNotification on Notifier, ScheduleTimer on Timer;
     * </pre>
     * 
     * @param alertSuspectRegulation the value of the <code>AlertSuspectRegulation</code> context.
     * @param discover a discover object to get context values and action methods
     */
    @Override
    protected void onAlertSuspectRegulation(AlertSuspectRegulationValue alertSuspectRegulation, DiscoverForAlertSuspectRegulation discover) {
    	String IdTimer = "AlertTimerFall007";
    	Integer delayMs = 4*60*1000; // 4 min
		discover.timers().anyOne().schedule(IdTimer, delayMs );// schedule timer with particular ID 

		String IdNotification = "AlertNotificationFall007";
    	List<String> answers = new ArrayList<String>(); // Initialize list of answers of notification
    	answers.add("Oui appeler les secours"); // First choice of the notification
    	answers.add("Non tout va bien"); // First choice of the notification
    	NonCriticalNotification notification = new NonCriticalNotification(IdNotification,"Fall Alert","Avez-vous chuté ?",answers,false); // create the notification en mode non silencieuse

    	discover.notifiers().anyOne().sendNonCriticalNotification(notification); // envoi de la notification

		
    }
}
