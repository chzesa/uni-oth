package oht.chess.ui;

class ConsoleUiCell
{
	char _c = ' ';
	ForegroundColor _fg = ForegroundColor.Default;
	BackgroundColor _bg = BackgroundColor.Default;

	void set(char c) { _c = c; }
	void set(ForegroundColor color) { _fg = color; }
	void set(BackgroundColor color) { _bg = color; }

	@Override
	public String toString()
	{
		return _bg.toString() + _fg.toString() + _c
			+ ForegroundColor.Default.toString()
			+ BackgroundColor.Default.toString();
	}
}