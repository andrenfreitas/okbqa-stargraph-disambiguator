package de.unipassau.disambiguation;

public class ResolvedEntity {

	private String uri;
	private double score;
	
	public ResolvedEntity() {

	}

	public ResolvedEntity(String uri, double score) {
		this.uri = uri;
		this.score = score;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "ResolvedEntity [uri=" + uri + ", score=" + score + "]";
	}

	
}
