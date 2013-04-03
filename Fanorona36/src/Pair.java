
/**Container class for 2 ints, corresponding to an xPosition and yPosition*/
public class Pair {
	private int first;
	private int second;
	
	public Pair(int x, int y){
		first = x;
		second = y;
	}
	
	public int getFirst(){
		return first;
	}
	public int getSecond(){
		return second;
	}

	public boolean isEqualTo(Pair p){
		if( (this.getFirst() == p.getFirst()) && (this.getSecond() == p.getSecond()) ){
			return true;
		}
		else{
			return false;
		}
	}
}
