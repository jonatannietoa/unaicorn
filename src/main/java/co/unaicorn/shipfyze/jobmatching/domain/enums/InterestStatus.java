package co.unaicorn.shipfyze.jobmatching.domain.enums;

public enum InterestStatus {
  PENDING("Pending"),
  LIKED("Liked"),
  DISLIKED("Disliked");

  private final String displayName;

  InterestStatus(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
