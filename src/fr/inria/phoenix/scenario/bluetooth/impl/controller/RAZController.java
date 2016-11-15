package fr.inria.phoenix.scenario.bluetooth.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.controller.razcontroller.AbstractRAZController;
import fr.inria.phoenix.diasuite.framework.context.wrongalert.WrongAlertValue;

/* (non-Javadoc)
 * The implementation of the RAZController context
 * @see fr.inria.phoenix.diasuite.framework.controller.razcontroller.AbstractRAZController
 */
public class RAZController extends AbstractRAZController {
    
    public RAZController(ServiceConfiguration serviceConfiguration) {
        super(serviceConfiguration);
    }

    /* (non-Javadoc)
     * @see fr.inria.phoenix.diasuite.framework.controller.razcontroller.AbstractRAZController#onWrongAlert(WrongAlertValue, DiscoverForWrongAlert)
     */
    @Override
    protected void onWrongAlert(WrongAlertValue wrongAlert, DiscoverForWrongAlert discover) {
        // TODO Auto-generated method stub
    }
}
