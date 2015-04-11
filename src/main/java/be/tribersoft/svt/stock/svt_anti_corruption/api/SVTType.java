package be.tribersoft.svt.stock.svt_anti_corruption.api;

public enum SVTType {

	FILE("file"), HTTP("http");

	private String type;

	SVTType(String type) {
		this.type = type;
	}

	public static SVTType convert(String type) {
		if ("file".equalsIgnoreCase(type)) {
			return SVTType.FILE;
		}
		return SVTType.HTTP;
	}
}
