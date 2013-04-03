import java.util.ArrayList;


public class MovesList {
	
	public class Triplet {
		private String moveType;
		private Pair pair1;
		private Pair pair2;
		
		Triplet(Pair p1, Pair p2, String type){
			pair1 = p1;
			pair2 = p2;
			moveType = type;
		}
		protected String getMoveType(){
			return moveType;
		}
		protected Pair getPair1(){
			return pair1;
		}
		protected Pair getPair2(){
			return pair2;
		}
		
	}
	private ArrayList<Triplet> moves;
	
	public MovesList() {
		moves = new ArrayList<Triplet>(20);
	}
	
	public void add(Pair p1, Pair p2, String type ) {
		moves.add(new Triplet(p1, p2, type));
	}
	
	public Triplet getMove() {
		return moves.remove(0);
	}
	
	public int getSize() {
		return moves.size();
	}
	
	public ArrayList<Triplet> getMoveArray() {
		return moves;
	}

}
