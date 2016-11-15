package fr.inria.phoenix.scenario.bluetooth.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.criticalalert.AbstractCriticalAlert;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerValueFromTimer;

/* (non-Javadoc)
 * The implementation of the CriticalAlert context
 * @see fr.inria.phoenix.diasuite.framework.context.criticalalert.AbstractCriticalAlert
 */
public class CriticalAlert extends AbstractCriticalAlert {
    
    public CriticalAlert(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }
    
    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.context.criticalalert.AbstractCriticalAlert#onTimerValueFromTimer(TimerValueFromTimer, DiscoverForTimerValueFromTimer)
     */
    @Override
    protected java.lang.Float onTimerValueFromTimer(TimerValueFromTimer timerValueFromTimer, DiscoverForTimerValueFromTimer discover) {
        // TODO Auto-generated method stub
        return null;
    }
}
