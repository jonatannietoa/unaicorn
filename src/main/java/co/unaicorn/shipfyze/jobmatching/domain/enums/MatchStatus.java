package co.unaicorn.shipfyze.jobmatching.domain.enums;

public enum MatchStatus {
  PENDING("Pending"),
  MATCHED("Matched"),
  NO_MATCH("No Match");

  private final String displayName;

  MatchStatus(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
