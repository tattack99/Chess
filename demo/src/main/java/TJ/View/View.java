package TJ.View;

import TJ.Model.Board;
import TJ.Model.Pieces.*;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class View{
    private final int TILE_SIZE = 80;
    private final int WIDTH = 8;
    private final int HEIGHT = 8;

    private final Board model;
    private GridPane board;
    private GridPane pieces;
    private final Stage primaryStage;


    public View(Board model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
        this.board = new GridPane();
    }

    public void init(){
        board = initBoard();
        pieces = initPieces();
        Scene scene = new Scene(new StackPane(board,pieces), WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private ImageView getPiecePic(Type type, Team team){
        double color = 0;
        int piecePos = 0;
        if(team==Team.BLACK){color = 106.5;}
        if(type == Type.KING){piecePos = 0;}
        else if(type == Type.QUEEN){piecePos = 1;}
        else if(type == Type.BISHOP){piecePos = 2;}
        else if(type == Type.KNIGHT){piecePos = 3;}
        else if(type == Type.ROOK){piecePos = 4;}
        else if(type == Type.PAWN){piecePos = 5;}

        Image image = new Image("file:/Users/timjohansson/Documents/GitHub/Chess/demo/src/main/java/TJ/View/640pxChessPiecesSprite.png");
        ImageView imageView = new ImageView(image);
        imageView.setViewport(new Rectangle2D( (piecePos%6)* (float) 640/6 - 1.25, color, (float) 640/6, (float) 640/6));
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        imageView.setPickOnBounds(false);

        return imageView;
    }

    private ImageView getPieceImage(Type type, Team team){
        ImageView imageView = getPiecePic(type, team);
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        imageView.setSmooth(true);
        imageView.setCache(true);

        int[] original = new int[2];
        imageView.setOnMousePressed(event -> {
            original[0] = (int) (event.getSceneX() / TILE_SIZE);
            original[1] = (int) (event.getSceneY() / TILE_SIZE);

            // add transparent on the piece pressed
            pieces.add(emptyPane(),original[0],original[1]);

            imageView.toFront();
        });

        imageView.setOnMouseReleased(event -> {
            int offsetX = (int) event.getSceneX() / TILE_SIZE;
            int offsetY = (int) event.getSceneY() / TILE_SIZE;

            model.move(original[1], original[0], offsetY, offsetX);
            ImageView piece = getPieceImage(type,team);

            pieces.getChildren().remove(pieces.getChildren().size() - 1); // remove the transparent pane
            pieces.add(piece,offsetX,offsetY); // add the moved piece to the new position
        });

        return imageView;
    }


    private GridPane initBoard(){
        GridPane gridPane = new GridPane();
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Rectangle rectangle = new Rectangle(TILE_SIZE, TILE_SIZE);
                if ((x + y) % 2 == 0) {
                    rectangle.setFill(Color.WHITE);
                } else {
                    rectangle.setFill(Color.LIGHTGRAY);
                }
                gridPane.add(rectangle, x, y);
            }
        }
        return gridPane;
    }

    private GridPane initPieces(){
        GridPane gridPane = new GridPane();

        for(int i=0;i<HEIGHT;i++){
            for(int j=0;j<WIDTH;j++){
                if(model.getCell(i,j).getPiece()==null){
                    gridPane.add(emptyPane(),j,i);
                }
                else{
                    Type type = model.getCell(i,j).getPiece().getType();
                    Team team = model.getCell(i,j).getPiece().getTeam();
                    ImageView piece = getPieceImage(type,team);
                    gridPane.add(piece,j,i);
                }
            }
        }
        return gridPane;

    }

    private Pane emptyPane(){
        Pane empty = new Pane();
        Rectangle rectangle = new Rectangle(TILE_SIZE,TILE_SIZE);
        rectangle.setFill(Color.TRANSPARENT);
        empty.getChildren().add(rectangle);
        return empty;
    }


}
