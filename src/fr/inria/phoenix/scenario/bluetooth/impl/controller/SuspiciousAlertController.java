package fr.inria.phoenix.scenario.bluetooth.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.suspiciousalertcontroller.AbstractSuspiciousAlertController;
import fr.inria.phoenix.diasuite.framework.context.alertsuspectregulation.AlertSuspectRegulationValue;

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
    }
}
