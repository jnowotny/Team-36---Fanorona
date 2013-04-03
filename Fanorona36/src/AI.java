import java.util.ArrayList;

public class AI {
	
	private int playerNum;
	//private int difficultyLevel;
	private Fanorona fan;
	
	public AI(final Fanorona f, int pNum) {
		fan = f;
		//difficultyLevel = 1;
		if (pNum == -1) {
			if (fan.getPlayerNum() == 1)
				playerNum = 2;
			else playerNum = 1;
		}
		else playerNum = pNum;
	}
	
	public int getAINum() {
		return playerNum;
	}
	
	public void makeMove() {
		if (fan.board.getBoardState().hasCaptureMoves()){
			ArrayList<Pair> captureMovables = fan.board.getCaptureMovable();
			int maxPair = captureMovables.size() - 1;
			int randomPiece = (int) Math.floor((Math.random()*maxPair)+0);
			Pair[] listMoves = fan.board.getBoardState().checkCaptureDestinations(captureMovables.get(randomPiece));
			maxPair = listMoves.length;
			boolean foundDestination = false;
			int randomDestination = -1;
			while(!foundDestination) {
				randomDestination = (int) Math.floor((Math.random()*maxPair)+0);
				if (listMoves[randomDestination] != null)
					foundDestination = true;
			}
			if (randomDestination != -1) {
				fan.board.move(captureMovables.get(randomPiece), listMoves[randomDestination], "capture");
				fan.board.activateRemovables(captureMovables.get(randomPiece), listMoves[randomDestination]);
				int advanceOrWithdrawl = (int) Math.floor(Math.random() + .5);
				if (!(fan.board.getBoardState().getRemovables().isEmpty())) {
					if (!(fan.board.getBoardState().getRemovables().get(advanceOrWithdrawl).isEmpty())) {
						fan.board.removeRemovables(advanceOrWithdrawl);
					}
					else {
						if (advanceOrWithdrawl == 1)
							advanceOrWithdrawl = 0;
						else advanceOrWithdrawl = 1;
						fan.board.removeRemovables(advanceOrWithdrawl);
					}
				}
			}
		}
		//Else show pieces that can make paika moves, if any
		else {
			ArrayList<Pair> paikaMovables = fan.board.getPaikaMovable();
			int maxPair = paikaMovables.size() - 1;
			int randomPiece = (int) Math.floor((Math.random()*maxPair)+0);
			Pair[] listMoves = fan.board.getBoardState().checkDestinations(paikaMovables.get(randomPiece));
			maxPair = listMoves.length;
			boolean foundDestination = false;
			int randomDestination = -1;
			while(!foundDestination) {
				randomDestination = (int) Math.floor((Math.random()*maxPair)+0);
				if (listMoves[randomDestination] != null)
					foundDestination = true;
			}
			if (randomDestination != -1)
				fan.board.move(paikaMovables.get(randomPiece), listMoves[randomDestination], "paika");
		}
		fan.board.nextTurn();
	}
}