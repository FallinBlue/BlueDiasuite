package fr.inria.phoenix.scenario.bluetooth.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.razcontroller.AbstractRAZController;
import fr.inria.phoenix.diasuite.framework.context.wrongalert.WrongAlertValue;

/* (non-Javadoc)
 * The implementation of the RAZController context
 * @see fr.inria.phoenix.diasuite.framework.controller.razcontroller.AbstractRAZController
 */
public class RAZController extends AbstractRAZController {
	/**
     * A constructor that instantiate the class RAZController
     */
    public RAZController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /**
     * This method is called when the <code>WrongAlert</code> context publishes a value.
     * 
     * <pre>
     * when provided WrongAlert 
     * 	 do SendNonCriticalNotification on Notifier, ScheduleTimer on Timer;
     * </pre>
     * 
     * @param wrongAlert the value of the <code>WrongAlert</code> context.
     * @param discover a discover object to get context values and action methods
     */
    @Override
    protected void onWrongAlert(WrongAlertValue wrongAlert, DiscoverForWrongAlert discover) {    	
    	String IdTimer = "AlertTimerFall007";
    	discover.timers().anyOne().cancel(IdTimer);     	// cancel timer with particular ID...

    	String IdNotification = "AlertNotificationFall007";
    	discover.notifiers().anyOne().cancelNonCriticalNotification(IdNotification); // cancel notification with particular ID...
    }
}
