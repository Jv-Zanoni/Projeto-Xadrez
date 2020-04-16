package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			if (position.getRow() - 1 < 0) {
				boolean[][] Mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
				
				Position p1 = new Position(0, 0);
				
				//above
				p1.setValues(position.getRow() - 1, position.getColumn());
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
					p1.setRow(p1.getRow() - 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				}
				
				//left
				p1.setValues(position.getRow(), position.getColumn() - 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
					p1.setColumn(p1.getColumn() - 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				}
				
				//right
				p1.setValues(position.getRow(), position.getColumn() + 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
					p1.setColumn(p1.getColumn() + 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				}
				
				//bolow
				p1.setValues(position.getRow() + 1, position.getColumn());
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
					p1.setRow(p1.getRow() + 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				}
				
				//nw
				p1.setValues(position.getRow() - 1, position.getColumn() - 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() - 1, p1.getColumn() - 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				}
				
				//ne
				p1.setValues(position.getRow() - 1, position.getColumn() + 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				p1.setValues(p1.getRow() - 1, p1.getColumn() + 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				}
				
				//se
				p1.setValues(position.getRow() + 1, position.getColumn() + 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() + 1, p1.getColumn() + 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				}
				
				//sw
				p1.setValues(position.getRow() + 1, position.getColumn() - 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() + 1, p1.getColumn() - 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					Mat[p1.getRow()][p1.getColumn()] = true;
				}
				
				return Mat;
			}
			else {
		
				p.setValues(position.getRow() - 1, position.getColumn());
				if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				p.setValues(position.getRow() - 2, position.getColumn());
				Position p2 = new Position(position.getRow() - 1, position.getColumn());
				if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
					mat[p.getRow()][p.getColumn()] = true;
				}
				p.setValues(position.getRow() - 1, position.getColumn() - 1);
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}			
				p.setValues(position.getRow() - 1, position.getColumn() + 1);
				if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
					mat[p.getRow()][p.getColumn()] = true;
				}
			}
			
			//special move en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}
				Position rigth = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(rigth) && isThereOpponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVulnerable()) {
					mat[rigth.getRow() - 1][rigth.getColumn()] = true;
				}
			}	
		}
		else {
			if (position.getRow() + 1 > 7) {
				boolean[][] mat1 = new boolean[getBoard().getRows()][getBoard().getColumns()];
				
				Position p1 = new Position(0, 0);
				
				//above
				p1.setValues(position.getRow() - 1, position.getColumn());
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
					p1.setRow(p1.getRow() - 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				}
				
				//left
				p1.setValues(position.getRow(), position.getColumn() - 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
					p1.setColumn(p1.getColumn() - 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				}
				
				//right
				p1.setValues(position.getRow(), position.getColumn() + 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
					p1.setColumn(p1.getColumn() + 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				}
				
				//bolow
				p1.setValues(position.getRow() + 1, position.getColumn());
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
					p1.setRow(p1.getRow() + 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				}
				
				//nw
				p1.setValues(position.getRow() - 1, position.getColumn() - 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() - 1, p1.getColumn() - 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				}
				
				//ne
				p1.setValues(position.getRow() - 1, position.getColumn() + 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				p1.setValues(p1.getRow() - 1, p1.getColumn() + 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				}
				
				//se
				p1.setValues(position.getRow() + 1, position.getColumn() + 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() + 1, p1.getColumn() + 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				}
				
				//sw
				p1.setValues(position.getRow() + 1, position.getColumn() - 1);
				while (getBoard().positionExists(p1) && !getBoard().thereIsAPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
					p1.setValues(p1.getRow() + 1, p1.getColumn() - 1);
				}
				if(getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
					mat1[p1.getRow()][p1.getColumn()] = true;
				}
				
				return mat1;
			}else {
				
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}			
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			}
			//special move en passant black
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}
				Position rigth = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(rigth) && isThereOpponentPiece(rigth) && getBoard().piece(rigth) == chessMatch.getEnPassantVulnerable()) {
					mat[rigth.getRow() + 1][rigth.getColumn()] = true;
				}
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}

}