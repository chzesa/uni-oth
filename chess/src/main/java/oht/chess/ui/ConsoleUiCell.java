package oht.chess.ui;

class ConsoleUiCell {
	char c = ' ';
	ForegroundColor fg = ForegroundColor.Default;
	BackgroundColor bg = BackgroundColor.Default;

	void set(char c) {
		this.c = c;
	}
	void set(ForegroundColor color) {
		this.fg = color;
	}
	void set(BackgroundColor color) {
		this.bg = color;
	}

	@Override
	public String toString() {
		return this.bg.toString() + this.fg.toString() + this.c
			+ ForegroundColor.Default.toString()
			+ BackgroundColor.Default.toString();
	}
}