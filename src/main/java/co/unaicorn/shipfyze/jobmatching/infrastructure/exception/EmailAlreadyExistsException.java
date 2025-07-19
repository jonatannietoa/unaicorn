package co.unaicorn.shipfyze.jobmatching.infrastructure.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public static EmailAlreadyExistsException forCandidate(String email) {
        return new EmailAlreadyExistsException(
            "Candidate with email " + email + " already exists"
        );
    }

    public static EmailAlreadyExistsException forRecruiter(String email) {
        return new EmailAlreadyExistsException(
            "Recruiter with email " + email + " already exists"
        );
    }
}
