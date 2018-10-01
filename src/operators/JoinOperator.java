package operators;

import java.util.*;

import data.Tuple;

public class JoinOperator extends Operator{

	private Operator joinLeft;
	private Operator joinRight;
    private Tuple currLeftTup;
    private Tuple currRightTup;
    
	// Constructor
	public JoinOperator(Operator op1, Operator op2) {
	    joinLeft = op1;
	    joinRight = op2;
	    currLeftTup = null;
	    currRightTup = null;
	}
	

	@Override
	public Tuple getNextTuple() {		
		// Corner Case: when there are less than two operators under join operator.
		if (joinLeft == null && joinRight == null) {
			return null;
		}
		if (joinLeft == null || joinRight == null) {
			return joinLeft == null ? joinRight.getNextTuple() : joinLeft.getNextTuple();
		}

		// If currLeftTup and currRightTup are both null, it is the start of join
		// If currLeftTup is null but currRightTup is not null, it is the end of join 
	    if (currLeftTup == null) {
	    	if (currRightTup == null) {
		    	currLeftTup = joinLeft.getNextTuple();
		    	currRightTup = joinRight.getNextTuple();
	    	} else {
	    		return null;
	    	}
	    } else {
	    	if (currRightTup == null) {
		    	joinRight.reset();
		    	currLeftTup = joinLeft.getNextTuple();
		    	currRightTup = joinRight.getNextTuple();
		    } else {
		    	currRightTup = joinRight.getNextTuple();	
		    }
	    	
	    }

	    Tuple currTuple = concatenate(currLeftTup, currRightTup);
		return currTuple;
	}
	
    private Tuple concatenate(Tuple t1, Tuple t2) { 
    	// deal with corner case
    	if (t1 == null && t2 == null) {
    		return null;
    	}
    	if (t1 == null || t2 == null) {
    		return t1 == null ? 
    				     new Tuple(t2.getData(), t2.getSchema()) 
    				     : new Tuple(t1.getData(), t1.getSchema());
    	}
    	
    	// compose the new data
    	long[] data = Arrays.copyOf(t1.getData(), t2.getSize());
    	System.arraycopy(t2.getData(), 0, data, t1.getSize(), t2.getSize());
    	
    	// compose the new schema
    	Map<String, Integer> schema = new HashMap<>();
    	for (Map.Entry<String, Integer> e : t1.getSchema().entrySet()) {
    		schema.put(e.getKey(), e.getValue());
    	}
    	for (Map.Entry<String, Integer> e : t2.getSchema().entrySet()) {
    		schema.put(e.getKey(), e.getValue() + t1.getSize());
    	}
    	
    	// construct the result tuple
    	Tuple result = new Tuple(data, schema);
    	return result;
	}

	@Override
	public void reset() {
		if (joinLeft != null) {
			joinLeft.reset();
		}
		if (joinRight != null) {
			joinRight.reset();
		}
		
	}

}
