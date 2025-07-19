package co.unaicorn.shipfyze.jobmatching.domain.enums;

public enum LocationType {
  ON_SITE("On-site"),
  REMOTE("Remote"),
  HYBRID("Hybrid");

  private final String displayName;

  LocationType(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
