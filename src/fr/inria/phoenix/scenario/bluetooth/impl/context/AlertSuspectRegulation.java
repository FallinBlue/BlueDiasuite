package fr.inria.phoenix.scenario.bluetooth.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AbstractAlertSuspectRegulation;
import fr.inria.phoenix.diasuite.framework.device.deviceinput.InputFromDeviceInput;

/* (non-Javadoc)
 * The implementation of the AlertSuspectRegulation context
 * @see fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AbstractAlertSuspectRegulation
 */
public class AlertSuspectRegulation extends AbstractAlertSuspectRegulation {
    
    public AlertSuspectRegulation(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }
    
    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AbstractAlertSuspectRegulation#onInputFromDeviceInput(InputFromDeviceInput, DiscoverForInputFromDeviceInput)
     */
    @Override
    protected java.lang.String onInputFromDeviceInput(InputFromDeviceInput inputFromDeviceInput, DiscoverForInputFromDeviceInput discover) {
        // TODO Auto-generated method stub
        return null;
    }
}
