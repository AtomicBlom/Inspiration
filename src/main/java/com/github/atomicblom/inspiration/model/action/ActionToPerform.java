package com.github.atomicblom.inspiration.model.action;

import com.github.atomicblom.inspiration.model.IAcquiredInspiration;

public class ActionToPerform implements Comparable<ActionToPerform>
{
    private final IAcquiredInspiration acquiredInspiration;
    private final Action action;
    private final ActionContext context;

    public ActionToPerform(IAcquiredInspiration acquiredInspiration, Action action, ActionContext context) {this.acquiredInspiration = acquiredInspiration;
        this.action = action;
        this.context = context;
    }

    public IAcquiredInspiration getAcquiredInspiration()
    {
        return acquiredInspiration;
    }

    public Action getAction()
    {
        return action;
    }

    public ActionContext getContext()
    {
        return context;
    }

    @Override
    public int compareTo(ActionToPerform actionToPerform)
    {
        return Double.compare(acquiredInspiration.getAmount(), actionToPerform.acquiredInspiration.getAmount());
    }

}
