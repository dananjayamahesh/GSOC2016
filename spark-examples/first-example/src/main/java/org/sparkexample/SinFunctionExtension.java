package org.sparkexample;


        import org.wso2.siddhi.core.config.ExecutionPlanContext;
        import org.wso2.siddhi.core.exception.ExecutionPlanRuntimeException;
        import org.wso2.siddhi.core.executor.ExpressionExecutor;
        import org.wso2.siddhi.core.executor.function.FunctionExecutor;
        import org.wso2.siddhi.query.api.definition.Attribute;
        import org.wso2.siddhi.query.api.exception.ExecutionPlanValidationException;

/*
* sin(a);
* Returns the sine of a (a is in radians).
* Accept Type(s) :DOUBLE/INT/FLOAT/LONG
* Return Type(s): DOUBLE
*/
public class SinFunctionExtension extends FunctionExecutor {

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ExecutionPlanContext executionPlanContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new ExecutionPlanValidationException("Invalid no of arguments passed to math:sin() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }
        Attribute.Type attributeType = attributeExpressionExecutors[0].getReturnType();
        if (!((attributeType == Attribute.Type.DOUBLE)
                || (attributeType == Attribute.Type.INT)
                || (attributeType == Attribute.Type.FLOAT)
                || (attributeType == Attribute.Type.LONG))) {
            throw new ExecutionPlanValidationException("Invalid parameter type found for the argument of math:sin() function, " +
                    "required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                    " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                    ", but found " + attributeType.toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;  //Since the sin function takes in only 1 parameter, this method does not get called. Hence, not implemented.
    }

    @Override
    protected Object execute(Object data) {
        if (data != null) {
            //type-conversion
            if (data instanceof Integer) {
                int inputInt = (Integer) data;
                return Math.sin((double) inputInt);
            } else if (data instanceof Long) {
                long inputLong = (Long) data;
                return Math.sin((double) inputLong);
            } else if (data instanceof Float) {
                float inputFloat = (Float) data;
                return Math.sin((double) inputFloat);
            } else if (data instanceof Double) {
                return Math.sin((Double) data);
            }
        } else {
            throw new ExecutionPlanRuntimeException("Input to the math:sin() function cannot be null");
        }
        return null;
    }

    @Override
    public void start() {
        //Nothing to start.
    }

    @Override
    public void stop() {
        //Nothing to stop.
    }

    @Override
    public Attribute.Type getReturnType() {
        return Attribute.Type.DOUBLE;
    }

    @Override
    public Object[] currentState() {
        return null;    //No need to maintain state.
    }

    @Override
    public void restoreState(Object[] state) {
        //Since there's no need to maintain a state, nothing needs to be done here.
    }
}
