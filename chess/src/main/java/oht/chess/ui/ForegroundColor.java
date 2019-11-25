package oht.chess.ui;

enum ForegroundColor {
	Default("\u001B[0m"),
	Black("\u001B[30m"),
	Red("\u001B[31m"),
	Green("\u001B[32m"),
	Yellow("\u001B[33m"),
	Blue("\u001B[34m"),
	Purple("\u001B[35m"),
	Cyan("\u001B[36m"),
	White("\u001B[37m");

	private final String color;
	ForegroundColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return this.color;
	}
}