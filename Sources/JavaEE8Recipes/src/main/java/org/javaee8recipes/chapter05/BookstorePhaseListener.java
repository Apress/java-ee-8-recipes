package org.javaee8recipes.chapter05;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;

/**
 * Recipe 05-15
 * @author juneau
 */
public class BookstorePhaseListener implements javax.faces.event.PhaseListener {
    
    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext.getCurrentInstance().getExternalContext().log("Before the Phase - "
                + event.getPhaseId());

    }

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext.getCurrentInstance().getExternalContext().log("After the Phase - "
                + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
