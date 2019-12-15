package com.farmogo.rest.reporting;

public class ReportGeneratingError extends RuntimeException {
    public ReportGeneratingError(Throwable cause) {
        super(cause);
    }
}
