package com.github.atomicblom.inspiration.model.action;

import com.github.atomicblom.inspiration.model.behaviour.BehaviourModifier;
import com.github.atomicblom.inspiration.model.location.LocationModifier;
import com.github.atomicblom.inspiration.model.shape.ShapeModifier;
import com.github.atomicblom.inspiration.model.size.SizeModifier;

public class ActionContext
{
    private final LocationModifier locationModifier;
    private final SizeModifier sizeModifier;
    private final ShapeModifier shapeModifier;
    private final BehaviourModifier behaviourModifier;

    public ActionContext(LocationModifier locationModifier, SizeModifier sizeModifier, ShapeModifier shapeModifier, BehaviourModifier behaviourModifier)
    {

        this.locationModifier = locationModifier;
        this.sizeModifier = sizeModifier;
        this.shapeModifier = shapeModifier;
        this.behaviourModifier = behaviourModifier;
    }

    public LocationModifier getLocationModifier()
    {
        return locationModifier;
    }

    public SizeModifier getSizeModifier()
    {
        return sizeModifier;
    }

    public ShapeModifier getShapeModifier()
    {
        return shapeModifier;
    }

    public BehaviourModifier getBehaviourModifier()
    {
        return behaviourModifier;
    }
}
