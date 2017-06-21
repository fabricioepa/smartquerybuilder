package com.fabway.smartquerybuilder;

/**
 * This class exists for backward compatibility with previous versions of java 8
 * with no java.util.Predicate support.
 */
public final class Conditions {
    
    public Conditions(){
    }

    /**
     * @see Assertions#isTrue(Object)
     */
    public static final Condition True = new Condition() {
        @Override
        public boolean evaluate(Object val) {
            return Assertions.isTrue(val);
        }
    };

    /**
     * @see Assertions#isFalse(Object)
     */
    public static final Condition False = new Condition() {
        @Override
        public boolean evaluate(Object val) {
            return Assertions.isFalse(val);
        }
    };

    /**
     * @see Assertions#isNull(Object)
     */
    public static final Condition Null = new Condition() {
        @Override
        public boolean evaluate(Object val) {
            return Assertions.isNull(val);
        }
    };

    /**
     * @see Assertions#isEmpty(Object)
     */
    public static final Condition Empty = new Condition() {
        @Override
        public boolean evaluate(Object val) {
            return Assertions.isEmpty(val);
        }
    };

    /**
     * @see Assertions#notNull(Object)
     */
    public static final Condition NotNull = new Condition() {
        @Override
        public boolean evaluate(Object val) {
            return Assertions.notNull(val);
        }
    };

    /**
     * @see Assertions#isZero(Object)
     */
    public static final Condition IsZero = new Condition() {
        @Override
        public boolean evaluate(Object val) {
            return Assertions.isZero(val);
        }
    };

    /**
     * Encapsulate all conditions using the AND operation.
     * 
     * @param conditions
     * @return the new condition
     */
    public static Condition and(final Condition... conditions) {
        if (conditions == null || conditions.length == 0) {
            return null;
        }

        return new Condition() {
            @Override
            public boolean evaluate(Object value) {
                for (Condition cond : conditions) {
                    if (!cond.evaluate(value)) {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    /**
     * Encapsulate all conditions using the OR operation.
     * 
     * @param conditions
     * @return the new condition
     */
    public static Condition or(final Condition... conditions) {
        if (conditions == null || conditions.length == 0) {
            return null;
        }

        return new Condition() {
            @Override
            public boolean evaluate(Object value) {
                for (Condition cond : conditions) {
                    if (cond.evaluate(value)) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    /**
     * Negates the condition using the NOT operator.
     * 
     * @param condition
     * @return the new condition
     */
    public static Condition not(final Condition condition) {
        if (condition == null) {
            return null;
        }

        return new Condition() {
            @Override
            public boolean evaluate(Object value) {
                return !condition.evaluate(value);
            }
        };
    }

}