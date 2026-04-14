package com.driveshare.pattern.chain;

/**
 * Abstract handler for the Chain of Responsibility.
 * Each concrete handler checks one security question.
 */
public abstract class RecoveryHandler {

    // Reference to the next handler in the chain
    protected RecoveryHandler nextHandler;

    /**
     * Sets the next handler in the chain.
     *
     * @param nextHandler the next handler
     */
    public void setNextHandler(RecoveryHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * Handles one step of the password recovery process.
     *
     * @param context holds the recovery data
     * @return true if the current step succeeds and chain continues, false otherwise
     */
    public abstract boolean handle(RecoveryContext context);
}