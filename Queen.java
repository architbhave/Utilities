public class Queen {
	private final int BOARD_SIZE = 8;
	private final int QUEEN = 1;
	private final int DANGER = -1;
	private final int SAFE = 0;
	
	private int[] queenPositions = null;
	private int[][] chessBoard = null;
	
	public Queen(){
		queenPositions = new int[BOARD_SIZE];
		for(int i = 0 ; i < queenPositions.length; i++)
			queenPositions[i] = -1;
		chessBoard = new int[BOARD_SIZE][BOARD_SIZE];
	}
	
	public void placeQueen(int row, int column){
		queenPositions[column] = row;
		updateChessBoard();
	}
	
	public void removeQueen(int row, int column){
		queenPositions[column] = -1;
		updateChessBoard();
	}
	
	public void updateChessBoard(){
		clearChessBoard();
		for(int i = 0; i < BOARD_SIZE; i++){
			if(queenPositions[i] != -1){
				chessBoard[queenPositions[i]][i] = QUEEN;
				updateDangerPositions(queenPositions[i],i);
			}
		}
	}
	
	public void updateDangerPositions(int row, int column){
		//Update all the row positions
		for(int i = column+1; i < BOARD_SIZE; i++)
			chessBoard[row][i] = DANGER;
		//Update all the column positions
		for(int i = row+1; i < BOARD_SIZE; i++)
			chessBoard[i][column] = DANGER;
		
		int startRow = 0;
		int startColumn = 0;
		
		//Update the diagonally upward positions
		startRow = row-1;
		startColumn = column+1;
		while(startRow >= 0 && startColumn < BOARD_SIZE){
			chessBoard[startRow][startColumn] = DANGER;
			startRow--;
			startColumn++;
		}
		//Update the diagonally downward positions
		startRow = row+1;
		startColumn = column+1;
		while(startRow < BOARD_SIZE && startColumn < BOARD_SIZE){
			chessBoard[startRow][startColumn] = DANGER;
			startRow++;
			startColumn++;
		}
	}
	
	public void clearChessBoard(){
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0 ; j < BOARD_SIZE; j++){
				chessBoard[i][j] = SAFE;
			}
		}
	}
	
	public boolean areAllQueensPlaced(){
		for(int i : queenPositions){
			if(i == -1)
				return false;
		}
		return true;
	}
	
	public boolean fillChessBoard(int row, int column){
		if(areAllQueensPlaced())
			return true;
		if(column >= BOARD_SIZE)
			return true;
		if(row >= BOARD_SIZE && queenPositions[column] == -1){
			return fillChessBoard(queenPositions[column-1],column-1);
		}
		if(chessBoard[row][column] == SAFE){
			placeQueen(row, column);
			return fillChessBoard(0, column+1);
		}
		if(chessBoard[row][column] == DANGER){
			return fillChessBoard(row+1, column);
		}
		if(chessBoard[row][column] == QUEEN){
			removeQueen(row, column);
			return fillChessBoard(row+1, column);
		}
		return false;
	}
	
	public void printChessBoard(){
		for(int i = 0; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				System.out.print(chessBoard[i][j]);
			}
			System.out.println();
		}
	}
	
	public void showQueens(){
		for(int i = 0 ; i < BOARD_SIZE; i++){
			for(int j = 0; j < BOARD_SIZE; j++){
				if(chessBoard[i][j] == 1){
					System.out.print("Q");
				}
				else{
					System.out.print("_");
				}
				System.out.print("\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args){
		Queen queenGame = new Queen();
		//queenGame.printChessBoard();
		queenGame.fillChessBoard(0,0);
		queenGame.showQueens();
	}
	
}
