package fr.inria.phoenix.scenario.bluetooth.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.criticalalert.AbstractCriticalAlert;
import fr.inria.phoenix.diasuite.framework.device.notifier.ReplyFromNotifier;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;

/* (non-Javadoc)
 * The implementation of the CriticalAlert context
 * @see fr.inria.phoenix.diasuite.framework.context.criticalalert.AbstractCriticalAlert
 */
public class CriticalAlert extends AbstractCriticalAlert {
    
    public CriticalAlert(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }


	@Override
	protected CriticalAlertValuePublishable onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer) {
		// TODO Auto-generated method stub
		if (timerTriggeredFromTimer.value().equals("Finished")) // to change with the time !!???
			// tester l'id du timer
			return new CriticalAlertValuePublishable(0,true);
		else 
			return new CriticalAlertValuePublishable(12,false);	
	}

	@Override
	protected Integer onReplyFromNotifier(ReplyFromNotifier replyFromNotifier) {
		// TODO Auto-generated method stub
		return 1;
	}
  
}
