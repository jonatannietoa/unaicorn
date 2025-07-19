package co.unaicorn.shipfyze.jobmatching.infrastructure.exception;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(String message) {
        super(message);
    }

    public CandidateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CandidateNotFoundException(Long candidateId) {
        super("Candidate not found with ID: " + candidateId);
    }

    public CandidateNotFoundException(String field, String value) {
        super("Candidate not found with " + field + ": " + value);
    }
}
